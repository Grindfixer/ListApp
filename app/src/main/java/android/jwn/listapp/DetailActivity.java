package android.jwn.listapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent in = getIntent();
        int index = in.getIntExtra("android.jwn.listapp.ITEM_INDEX", -1);

        if (index > -1){
            int pic = getImage(index);
            ImageView img = (ImageView)findViewById(R.id.imageView);
            scaleImage(img, pic);
        }

    }

    private int getImage(int index){
        switch (index){
            case 0: return R.drawable.peaches;
            case 1: return R.drawable.apples;
            case 2: return R.drawable.carrots;
            default: return -1;
        }
    }

    private void scaleImage(ImageView img, int pic){

        Display screen = getWindowManager().getDefaultDisplay();
        BitmapFactory.Options options = new BitmapFactory.Options();


        //--------------------------------------------------------------------------------------//
        //---- These lines allow the systems to examine the images h & w before drawing them.
        // This should keep large images from crashing the app
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), pic,options);

        int imgWidth = options.outWidth;
        int screenWidth = screen.getWidth();

        if (imgWidth > screenWidth){
            int ratio = Math.round((float)imgWidth / (float)screenWidth);
            options.inSampleSize = ratio;
        }
        options.inJustDecodeBounds = false;//turn it off, we're done with it
        Bitmap scaledImg = BitmapFactory.decodeResource(getResources(), pic, options);
        img.setImageBitmap(scaledImg);
        //--------------------------------------------------------------------------------------//


    }

}
