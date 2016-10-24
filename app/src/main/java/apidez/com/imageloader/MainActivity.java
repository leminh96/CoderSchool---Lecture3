package apidez.com.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.util.List;

import apidez.com.imageloader.realm.PostDataSource;

public class MainActivity extends AppCompatActivity {
    private PostDAO mPostDAO;
    private PostDataSource mPostDataSource;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Init DAO
        mPostDAO = new PostDAO();
        mPostDataSource = new PostDataSource();

        // Load images
        ivImage = (ImageView) findViewById(R.id.ivImage);
        String url = "https://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg";
        new ImageLoader().loadUrl(ivImage, url);

        // Load api
        new ApiClient().loadPosts(new ApiClient.Listener() {
            @Override
            public void onLoad(List<Post> posts) {
                Log.d("Success", String.valueOf(posts.size()));
                mPostDataSource.store(posts);
                List<Post> newPosts = mPostDataSource.getAll();
                Log.d("Success", String.valueOf(newPosts.size()));
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("Error", throwable.getMessage());
            }
        });
    }
}
