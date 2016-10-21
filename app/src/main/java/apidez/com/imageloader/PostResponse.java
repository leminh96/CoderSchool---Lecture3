package apidez.com.imageloader;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by nongdenchet on 10/21/16.
 */

public class PostResponse {

    @SerializedName("posts")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }
}
