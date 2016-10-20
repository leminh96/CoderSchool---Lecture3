package apidez.com.imageloader;

/**
 * Created by nongdenchet on 10/21/16.
 */

public class Post {
    private String title;
    private String tags;
    private long timestamp;
    private String image;

    public Post(String title, String tags, long timestamp, String image) {
        this.title = title;
        this.tags = tags;
        this.timestamp = timestamp;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getTags() {
        return tags;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getImage() {
        return image;
    }
}
