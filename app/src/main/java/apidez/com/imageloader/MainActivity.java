package apidez.com.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import apidez.com.imageloader.realm.PostDAO;

public class MainActivity extends AppCompatActivity {
    private PostDAO mPostDAO;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPostDAO = new PostDAO();

        // Load images
        ivImage = (ImageView) findViewById(R.id.ivImage);
        String url = "https://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg";
        new ImageLoader().loadUrl(ivImage, url);

        List<Post> posts = mPostDAO.getAll();
        Toast.makeText(MainActivity.this, String.valueOf(posts.size()), Toast.LENGTH_SHORT).show();

        // Load api
        new ApiClient().loadPosts(new ApiClient.Listener() {
            @Override
            public void onLoad(List<Post> posts) {
                Log.d("Success", String.valueOf(posts.size()));
                mPostDAO.clear();
                mPostDAO.store(posts);
                List<Post> newPosts = mPostDAO.getAll();
                Log.d("Success", String.valueOf(newPosts.size()));
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("Error", throwable.getMessage());
            }
        });
    }
}
