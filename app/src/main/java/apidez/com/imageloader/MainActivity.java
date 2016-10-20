package apidez.com.imageloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivImage = (ImageView) findViewById(R.id.ivImage);
        String url = "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcR7aE8c_B-sUW7sgcXJHsYUFx2Jp1FdJJQ9VroMEBgipouCW_fO";
        new ImageLoader().loadUrl(ivImage, url);
    }
}
