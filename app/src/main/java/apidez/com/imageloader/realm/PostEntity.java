package apidez.com.imageloader.realm;

import apidez.com.imageloader.Post;
import io.realm.RealmObject;

/**
 * Created by nongdenchet on 10/24/16.
 */

public class PostEntity extends RealmObject {
    private String title;
    private String tags;
    private long timestamp;
    private String image;

    public void map(Post post) {
        this.title = post.getTitle();
        this.tags = post.getTags();
        this.timestamp = post.getTimestamp();
        this.image = post.getImage();
    }

    public Post toModel() {
        return new Post(title, tags, timestamp, image);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
