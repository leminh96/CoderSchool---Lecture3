package apidez.com.imageloader;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nongdenchet on 10/21/16.
 */

public class ApiClient {
    private final String PATH = "https://hashpet.herokuapp.com/posts.json";

    public interface Listener {
        void onLoad(List<Post> posts);

        void onError(Throwable throwable);
    }

    public void loadPosts(Listener listener) {
        new Loader(listener).execute(PATH);
    }

    private class Loader extends AsyncTask<String, Integer, List<Post>> {
        private Listener mListener;

        public Loader(Listener listener) {
            mListener = listener;
        }

        @Override
        protected List<Post> doInBackground(String... params) {
            String json = "";
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/json");
                InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream(), "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String output;
                while ((output = bufferedReader.readLine()) != null) {
                    json += output;
                }
                return parsePosts(json);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                mListener.onError(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Post> posts) {
            super.onPostExecute(posts);
            mListener.onLoad(posts);
        }

        private List<Post> parsePosts(String json) throws JSONException {
            if (json == null) {
                return null;
            }
            List<Post> posts = new ArrayList<>();
            JSONObject response = new JSONObject(json);
            JSONArray postsJson = response.getJSONArray("posts");
            for (int i = 0; i < postsJson.length(); i++) {
                JSONObject postJson = (JSONObject) postsJson.get(i);
                String title = postJson.getString("title");
                String tags = postJson.getString("tags");
                long timestamp = postJson.getLong("timestamp");
                String image = postJson.getString("image");
                posts.add(new Post(title, tags, timestamp, image));
            }
            return posts;
        }
    }
}
