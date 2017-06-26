package apidez.com.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by nongdenchet on 10/21/16.
 */

public class ImageLoader {

    public void loadUrl(ImageView ivImage, String imagePath) {
        new Loader(ivImage).execute(imagePath);
    }

    private class Loader extends AsyncTask<String, Integer, Bitmap> {
        ImageView ivImage;

        public Loader(ImageView ivImage) {
            this.ivImage = ivImage;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null) {
                ivImage.setImageBitmap(bitmap);
            }
        }
    }
}
