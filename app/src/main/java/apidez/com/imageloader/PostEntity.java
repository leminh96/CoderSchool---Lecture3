package apidez.com.imageloader;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by nongdenchet on 10/21/16.
 */

@Table(name = "Post")
public class PostEntity extends Model {

    @Column(name = "title")
    private String title;

    @Column(name = "tags")
    private String tags;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "image")
    private String image;

    public PostEntity() {
    }

    public PostEntity(Post post) {
        super();
        this.title = post.getTitle();
        this.tags = post.getTags();
        this.timestamp = post.getTimestamp();
        this.image = post.getImage();
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
