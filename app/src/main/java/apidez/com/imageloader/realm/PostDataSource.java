package apidez.com.imageloader.realm;

import java.util.ArrayList;
import java.util.List;

import apidez.com.imageloader.Post;
import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.rx.RealmObservableFactory;
import rx.functions.Func1;

/**
 * Created by nongdenchet on 10/24/16.
 */

public class PostDataSource {
    private RealmObservableFactory mFactory;

    public PostDataSource() {
        mFactory = new RealmObservableFactory();
    }

    public void store(final List<Post> posts) {
        Realm instance = Realm.getDefaultInstance();
        instance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(PostEntity.class).findAll().deleteAllFromRealm();
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

    public rx.Observable<List<Post>> getAllAsObservable() {
        return mFactory.from(Realm.getDefaultInstance())
                .map(new Func1<Realm, RealmResults<PostEntity>>() {
                    @Override
                    public RealmResults<PostEntity> call(Realm realm) {
                        return realm.where(PostEntity.class)
                                .findAll();
                    }
                })
                .map(new Func1<RealmResults<PostEntity>, List<Post>>() {
                    @Override
                    public List<Post> call(RealmResults<PostEntity> postEntities) {
                        List<Post> posts = new ArrayList<>();
                        for (PostEntity entity : postEntities) {
                            posts.add(entity.toModel());
                        }
                        return posts;
                    }
                });
    }
}
