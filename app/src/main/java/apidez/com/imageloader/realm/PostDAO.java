package apidez.com.imageloader.realm;

import java.util.ArrayList;
import java.util.List;

import apidez.com.imageloader.Post;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 Created by nongdenchet on 10/24/16.
 */

public class PostDAO {

    public void store(final List<Post> posts) {
        Realm instance = Realm.getDefaultInstance();
        instance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                for (Post post : posts) {
                    PostEntity entity = realm.createObject(PostEntity.class);
                    entity.map(post);
                }
            }
        });
        instance.close();
    }

    public List<Post> getAll() {
        Realm instance = Realm.getDefaultInstance();
        RealmResults<PostEntity> results = instance.where(PostEntity.class)
                .findAll();
        List<Post> posts = new ArrayList<>();
        for (PostEntity entity : results) {
            posts.add(entity.toModel());
        }
        instance.close();
        return posts;
    }

    public void clear() {
        Realm instance = Realm.getDefaultInstance();
        instance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(PostEntity.class).findAll().deleteAllFromRealm();
            }
        });
        instance.close();
    }
}
