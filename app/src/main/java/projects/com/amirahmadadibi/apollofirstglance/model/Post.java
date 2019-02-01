package projects.com.amirahmadadibi.apollofirstglance.model;

public class Post {
        private String title;
        private String content;
        private String topic;
        private String imageUrl;

    public Post(String title, String content, String topic, String imageUrl) {
        this.title = title;
        this.content = content;
        this.topic = topic;
        this.imageUrl = imageUrl;
    }
    public Post() {
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
