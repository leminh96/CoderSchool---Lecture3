package apidez.com.imageloader;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by nongdenchet on 10/21/16.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
