package projects.com.amirahmadadibi.apollofirstglance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.exception.ApolloException;

import org.jetbrains.annotations.NotNull;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    ApolloClient apolloClient;
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
                Log.d("tag", "onResponse: " + response.data().allPosts.get(0));
            }

            @Override
            public void onFailure(@NotNull ApolloException e) {

            }
        });
    }
}
