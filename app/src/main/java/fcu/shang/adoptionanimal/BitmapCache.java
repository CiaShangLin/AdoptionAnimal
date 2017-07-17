package fcu.shang.adoptionanimal;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by SERS on 2017/7/17.
 */

public class BitmapCache implements ImageLoader.ImageCache{

    private LruCache<String,Bitmap> mCache;


    public BitmapCache() {

        int maxSize=(int) (Runtime.getRuntime().maxMemory()/8);
        mCache=new LruCache<String,Bitmap>(maxSize){
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //return bitmap.getRowBytes()*bitmap.getHeight();
                return bitmap.getByteCount()/1024;
            }
        };
    }

    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url,bitmap);

    }

}
