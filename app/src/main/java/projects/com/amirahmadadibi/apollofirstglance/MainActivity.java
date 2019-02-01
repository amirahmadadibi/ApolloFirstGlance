package projects.com.amirahmadadibi.apollofirstglance;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import okhttp3.OkHttpClient;
import projects.com.amirahmadadibi.apollofirstglance.model.Post;

public class MainActivity extends AppCompatActivity {
    ApolloClient apolloClient;
    List<Post> postList = new ArrayList<>();
    RecyclerView recyclerView;
    ProgressBar progressBar;
    public static final String BASE_URL = "https://api.graph.cool/simple/v1/cjrhtrvlvari401295bq0ul6f";

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv_main);
        progressBar = findViewById(R.id.pb_main);
        progressBar.setVisibility(View.VISIBLE);
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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        CustomAdapter customAdapter = new CustomAdapter(postList, MainActivity.this);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
                        progressBar.setVisibility(View.INVISIBLE);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(customAdapter);
                    }
                });
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
