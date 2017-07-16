package fcu.shang.adoptionanimal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.util.SortedList;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String GSON="GSON";
    private ArrayList<Animal> animalList;                 //動物資訊的列表
    private String[] shelterName;                  //收容所的所有名稱
    private RecyclerView infoRecylerView;                 //主頁面的
    private RecyclerView.Adapter infoAdapter;
    private RecyclerView.LayoutManager infoLayoutManager;
    private Spinner adoptionSp,dogcatSp;




    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    String json=msg.getData().getString(GSON);
                    Gson gson=new Gson();                        //變數可以缺少,不一定都要有
                    Type arrayAnimal=new TypeToken<List<Animal>>(){}.getType();
                    animalList=gson.fromJson(json,arrayAnimal);
                    shelterName();
                    initLayout();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputData();




    }

    private void initLayout(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        infoRecylerView=(RecyclerView)findViewById(R.id.infoRecyclerView);
        infoRecylerView.setHasFixedSize(true);

        infoLayoutManager=new GridLayoutManager(this,2);
        infoRecylerView.setLayoutManager(infoLayoutManager);

        infoAdapter=new MyInfoAdapter(animalList,this);
        infoRecylerView.setAdapter(infoAdapter);

        adoptionSp=(Spinner)findViewById(R.id.adoptionSp);
        dogcatSp=(Spinner)findViewById(R.id.dogcatSp);

        adoptionSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,shelterName));
        dogcatSp.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,new String[]{"全部","狗","貓"}));
    }

    private void inputData(){                         //取得所有動物資訊列表
        AnimalInfo info=new AnimalInfo(handler,MainActivity.this);
        info.getInfo();
    }

    private void shelterName(){                             //取出所有收容所名稱,且不重複
        HashSet shelterSet=new HashSet();
        for(int i=0;i<animalList.size();i++){
            shelterSet.add(animalList.get(i).getShelter_name());
        }
        int count=1;
        shelterName=new String[shelterSet.size()+1];
        shelterName[0]="全部";
        Iterator iterator=shelterSet.iterator();     //印出
        while(iterator.hasNext()){
            shelterName[count++]=(String)iterator.next();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
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

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
