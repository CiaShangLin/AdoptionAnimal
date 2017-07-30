package fcu.shang.adoptionanimal;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import fcu.shang.adoptionanimal.Animal.Animal;
import fcu.shang.adoptionanimal.Animal.AnimalInfo;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    public static final String FAVORITE="FAVORITE";
    public static final String GSON="GSON";
    private Toolbar toolbar;
    private String[] shelterName;                         //收容所的所有名稱
    private RecyclerView infoRecylerView;                 //主頁面的
    private RecyclerView.Adapter infoAdapter;
    private RecyclerView.LayoutManager infoLayoutManager;
    private Spinner adoptionSp,dogcatSp;
    private AnimalInfo animalInfo;
    private String dogcatPick="全部",adoptionPick="全部";
    private ArrayList<Animal> copyList;
    private int beforeAdapter=1,afterAdapter=1;
    private SharedPreferences sp;
    private CustomProgressDialog progressDialog = null;


     /*GIT 應該用SSH clone下來,才能在別台電腦上傳 大概吧
            或者是VCS->import into version control->create git repository*/

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    admod();
                    setShelterName();
                    initLayout();
                    progressDialog.dismiss();
                    break;
                case 2:                                 //FULLINFO模式
                    toolbar.setSubtitle(null);
                    toolbar.setTitle(R.string.full_info);
                    toolbar.getMenu().findItem(R.id.menu_search).setVisible(false);
                    toolbar.getMenu().findItem(R.id.picture_mode).setVisible(false);
                    setFullAdapter(msg.arg1,copyList);
                    break;
                case 3:
                    Animal animal=(Animal)msg.obj;
                    SharePhoto sharePhoto;

                    if(msg.obj==null){
                        Log.d("Handler","NULL");
                        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.failed_image);
                        sharePhoto=new SharePhoto.Builder().setBitmap(bitmap).build();
                    }else{
                        Log.d("Handler","NOT NULL");
                        sharePhoto=new SharePhoto.Builder()
                                .setImageUrl(Uri.parse(animal.getAlbum_file()))
                                .build();
                    }
                    SharePhotoContent sharePhotoContent=new SharePhotoContent.Builder()
                            .addPhoto(sharePhoto)
                            .build();
                    ShareDialog shareDialog=new ShareDialog(MainActivity.this);
                    shareDialog.show(sharePhotoContent, ShareDialog.Mode.AUTOMATIC);

                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(progressDialog==null){
            progressDialog=CustomProgressDialog.createDialog(this);
            progressDialog.setMessage("Loading");
        }
        progressDialog.show();
        inputData();
    }

    private void initLayout(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.toolbartitle);
        toolbar.setSubtitle(R.string.tootbarsubtitle);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);            //使用它可以設置不同顏色的導航導航圖標。

        infoRecylerView=(RecyclerView)findViewById(R.id.infoRecyclerView);
        infoRecylerView.setHasFixedSize(true);
        setPictureAdapter(animalInfo.getAnimalList());

        adoptionSp=(Spinner)findViewById(R.id.adoptionSp);
        dogcatSp=(Spinner)findViewById(R.id.dogcatSp);

        adoptionSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,shelterName));
        adoptionSp.setOnItemSelectedListener(adoptionSpListener);
        dogcatSp.setAdapter(new MydogcatSpAdapter(this,getResources().getStringArray(R.array.animal)));
        dogcatSp.setOnItemSelectedListener(dogcatSpListener);

        sp=getSharedPreferences(FAVORITE,MODE_PRIVATE);     //初始化資料庫
        FacebookSdk.sdkInitialize(getApplicationContext());          //初始化 FB SDK

    }


    private void inputData(){                         //取得所有動物資訊列表
        animalInfo=new AnimalInfo(handler,MainActivity.this);
        animalInfo.getInfo();
    }

    private void setShelterName(){                             //取出所有收容所名稱,且不重複
        HashSet shelterSet=new HashSet();
        for(int i=0;i<animalInfo.getAnimalList().size();i++){
            shelterSet.add(animalInfo.getAnimalList().get(i).getShelter_name());
        }
        int count=1;
        shelterName=new String[shelterSet.size()+1];
        shelterName[0]="全部";
        Iterator iterator=shelterSet.iterator();     //印出
        while(iterator.hasNext()){
            shelterName[count++]=(String)iterator.next();
        }
    }

    private void admod(){
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("BD34A9A0939A0A4AF862F98AB60A85E4").build();
        mAdView.loadAd(adRequest);
    }

    AdapterView.OnItemSelectedListener adoptionSpListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            adoptionPick=shelterName[position];
            copyList=animalInfo.pickAnimal(adoptionPick,dogcatPick);
            if(beforeAdapter==1){
                setPictureAdapter(copyList);
            }else{
                setListAdapter(copyList);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    AdapterView.OnItemSelectedListener dogcatSpListener=new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String[] dogcatArray=getResources().getStringArray(R.array.animal);
            dogcatPick=dogcatArray[position];

            copyList=animalInfo.pickAnimal(adoptionPick,dogcatPick);
            if(beforeAdapter==1){
                setPictureAdapter(copyList);
            }else{
                setListAdapter(copyList);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };



    private void setPictureAdapter(ArrayList<Animal> animalList){                        //圖片模式
        infoLayoutManager=new GridLayoutManager(this,2);
        infoRecylerView.setLayoutManager(infoLayoutManager);

        infoAdapter=new MyPictureAdapter(this,animalInfo,animalList);
        infoRecylerView.setAdapter(infoAdapter);

        beforeAdapter=1;
        afterAdapter=1;
        Log.d("Adapter",beforeAdapter+" "+afterAdapter);
    }

    private void setListAdapter(ArrayList<Animal> animalList){                            //列表模式

        infoLayoutManager=new LinearLayoutManager(this);
        infoRecylerView.setLayoutManager(infoLayoutManager);

        infoAdapter=new MyListAdapter(this,animalInfo,animalList);
        infoRecylerView.setAdapter(infoAdapter);

        beforeAdapter=2;
        afterAdapter=2;
        Log.d("Adapter",beforeAdapter+" "+afterAdapter);
    }

    private void setFullAdapter(int position,ArrayList<Animal> animalList){                        //全部資訊

        adoptionSp.setVisibility(View.GONE);
        dogcatSp.setVisibility(View.GONE);

        infoLayoutManager=new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
        infoRecylerView.setLayoutManager(infoLayoutManager);

        infoAdapter=new MyFullIfoAdapter(animalList,animalInfo,sp);
        infoRecylerView.setAdapter(infoAdapter);
        infoRecylerView.scrollToPosition(position);          //可以移動到position的位置
        beforeAdapter=3;
        Log.d("Adapter",beforeAdapter+" "+afterAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if(beforeAdapter==3 || beforeAdapter==4){                           //從FULLINFO切回來
            toolbar.setSubtitle(R.string.tootbarsubtitle);
            toolbar.setTitle(R.string.toolbartitle);
            toolbar.getMenu().findItem(R.id.menu_search).setVisible(true);
            toolbar.getMenu().findItem(R.id.picture_mode).setVisible(true);

            if(afterAdapter==1){                                                  //從FULL切回圖片模式
                adoptionSp.setVisibility(View.VISIBLE);
                dogcatSp.setVisibility(View.VISIBLE);
                setPictureAdapter(copyList);
            }else if(afterAdapter==2){                                             //從FULL切回列表模式
                adoptionSp.setVisibility(View.VISIBLE);
                dogcatSp.setVisibility(View.VISIBLE);
                setListAdapter(copyList);
            }
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.menu_search:
                Toast.makeText(MainActivity.this,"尚未實作",Toast.LENGTH_SHORT).show();
                break;
            case R.id.picture_mode:
                if(item.getTitle().equals("圖片模式")){
                    item.setTitle("列表模式");
                    item.setIcon(R.drawable.list_mode);
                    setListAdapter(copyList);
                }else{
                    item.setTitle("圖片模式");
                    item.setIcon(R.drawable.picture_mode);
                    setPictureAdapter(copyList);
                }
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.menu_shelter){                                    //收容所
            Intent intent=new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://animal-adoption.coa.gov.tw/index.php/shelter#"));
            startActivity(intent);

        }else if (id == R.id.menu_doctors) {
            Toast.makeText(MainActivity.this,"尚未實作",Toast.LENGTH_SHORT).show();

        }else if (id == R.id.menu_lost) {
            Toast.makeText(MainActivity.this,"尚未實作",Toast.LENGTH_SHORT).show();
        }else if(id==R.id.menu_track){                                 //追蹤寵物
            infoLayoutManager=new LinearLayoutManager(this);
            infoRecylerView.setLayoutManager(infoLayoutManager);

            copyList=animalInfo.getTrackAnimalList(sp);
            infoAdapter=new MyTrackAdapter(animalInfo,copyList,sp);
            infoRecylerView.setAdapter(infoAdapter);
            beforeAdapter=4;
            Log.d("Adapter",beforeAdapter+" "+afterAdapter);

        }else if(id == R.id.menu_email){
            Intent email=new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:west7418@gmail.com"));
            email.putExtra(Intent.EXTRA_SUBJECT, "Youbike");
            email.putExtra(Intent.EXTRA_TEXT, "");
            startActivity(email);
        }else if(id == R.id.menu_maker) {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("製作者")
                    .setMessage("逢甲大學資工系三年級")
                    .setNegativeButton("關閉", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void printKeyHash(){            //印出keyhash
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "fcu.shang.adoptionanimal",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }
    }

}

