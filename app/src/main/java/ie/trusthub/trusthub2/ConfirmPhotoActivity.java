package ie.trusthub.trusthub2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;


public class ConfirmPhotoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_photo);

        // Get the message from the intent
        Intent intent = getIntent();
//        Bitmap bitmap = (Bitmap) intent.getParcelableExtra("Bitmap");

        // resize iamge
//        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 100, 100, true);
//        Bitmap conv_bm = getRoundedRectBitmap(resized, 100);

        Bitmap bitmap = decodeSampledBitmapFromFile(Environment.getExternalStorageDirectory()+File.separator + "icon-image.jpg", 400, 400);

        RoundedBitmapDrawable roundedBitmapDrawable=
                RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        roundedBitmapDrawable.setCornerRadius(Math.max(bitmap.getWidth(), bitmap.getHeight()));
//        Math.max(bitmap.getWidth(), bitmap.getHeight()) / 1.25f
        roundedBitmapDrawable.setAntiAlias(true);
        // find image
        ImageView mImageView = (ImageView) findViewById(R.id.imageView3);
        mImageView.setImageDrawable(roundedBitmapDrawable);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirm_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void enterPhoneNumber(View view) {
        Intent intent = new Intent(this, PhoneNumberActivity.class);
        startActivity(intent);
    }

    public void retakePhoto(View view) {
        Intent intent = new Intent(this, PhotoActivity.class);
        startActivity(intent);
    }


    public static Bitmap getRoundedRectBitmap(Bitmap bitmap, int pixels) {
        Bitmap result = null;
        try {
            result = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);

            int color = 0xff424242;
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, 200, 200);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawCircle(50, 50, 50, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

        } catch (NullPointerException e) {
        } catch (OutOfMemoryError o) {
        }
        return result;
    }

    // attempt to create a larger rounded icon for image
    public static Bitmap getRoundedRectBitmap2(Bitmap bitmap, int pixels) {
        Bitmap result = null;
        try {
            result = Bitmap.createBitmap(800, 800, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);

            int color = 0xff424242;
            Paint paint = new Paint();
            Rect rect = new Rect(0, 0, 800, 800);

            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);
            canvas.drawCircle(200, 200, 200, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);

        } catch (NullPointerException e) {
        } catch (OutOfMemoryError o) {
        }
        return result;
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight)
    { // BEST QUALITY MATCH

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth)
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }
}
