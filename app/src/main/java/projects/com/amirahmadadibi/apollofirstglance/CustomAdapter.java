package projects.com.amirahmadadibi.apollofirstglance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import projects.com.amirahmadadibi.apollofirstglance.model.Post;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    List<Post> postList;
    Context context;

    public CustomAdapter(List<Post> postList, Context context) {
        this.postList = postList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.txtTitle.setText(postList.get(i).getTitle());
        viewHolder.txtTopic.setText(postList.get(i).getTopic());
        viewHolder.txtContent.setText(postList.get(i).getContent());
        Glide.with(context).load(postList.get(i).getImageUrl()).into(viewHolder.ivPost);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        TextView txtContent;
        ImageView ivPost;
        TextView txtTopic;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_post_title);
            txtContent = itemView.findViewById(R.id.txt_post_content);
            txtTopic = itemView.findViewById(R.id.txt_post_topic);
            ivPost = itemView.findViewById(R.id.iv_post_image);
        }
    }
}
