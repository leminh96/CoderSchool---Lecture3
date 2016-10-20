package apidez.com.imageloader;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by nongdenchet on 10/21/16.
 */

public class PostDAO {

    public void bulkInsert(List<Post> posts) {
        ActiveAndroid.beginTransaction();
        for (Post post : posts) {
            new PostEntity(post).save();
        }
        ActiveAndroid.setTransactionSuccessful();
        ActiveAndroid.endTransaction();
    }

    public List<PostEntity> getAll() {
        return new Select().from(PostEntity.class)
                .execute();
    }

    public void deleteAll() {
        new Delete().from(PostEntity.class)
                .execute();
    }
}
