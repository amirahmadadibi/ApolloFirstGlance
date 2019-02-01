package projects.com.amirahmadadibi.apollofirstglance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import projects.com.amirahmadadibi.apollofirstglance.model.Post;

public class MainActivity extends AppCompatActivity {
    ApolloClient apolloClient;
    List<Post> postList = new ArrayList<>();
    public static final String BASE_URL = "https://api.graph.cool/simple/v1/cjrhtrvlvari401295bq0ul6f";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        apolloClient = ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttpClient)
                .build();
        apolloClient.query(
                AllPostsQuery.builder()
                        .build()
        ).enqueue(new ApolloCall.Callback<AllPostsQuery.Data>() {
            @Override
            public void onResponse(@NotNull Response<AllPostsQuery.Data> response) {
                int i = response.data().allPosts.size() - 1;
                Log.d("test", "onResponse: size" + i);
                for (; i >= 0; i--) {
                    Post post = new Post();
                    post.setTitle(response.data().allPosts.get(i).postTitle);
                    post.setContent(response.data().allPosts.get(i).postContent);
                    post.setImageUrl(response.data().allPosts.get(i).imageUrl);
                    post.setTopic(response.data().allPosts.get(i).topic);
                    postList.add(post);
                }
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
