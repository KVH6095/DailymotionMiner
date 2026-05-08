
package dailymotion.miner.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.processing.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
        "description",
        "releaseTime",
        "comments",
        "captions",
        "user"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Video {

    @JsonProperty("id")
    private String id;
    @JsonAlias("title")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonAlias("created_time")
    @JsonDeserialize(using = com.fasterxml.jackson.databind.deser.std.StringDeserializer.class)
    private String releaseTime;
    @JsonProperty("views_total")
    private Integer viewCount;
    @JsonProperty("likes_total")
    private Integer likeCount;
    @JsonProperty(value = "owner", access = JsonProperty.Access.WRITE_ONLY)
    private String userId;
    @JsonProperty(value = "tags", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> comments;

    private List<Caption> captions;

    private User user;

    private List<Comment> commentList;





    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("releaseTime")
    public String getReleaseTime() { return releaseTime; }

    public void setReleaseTime(String releaseTime) { this.releaseTime = releaseTime; }

    @JsonProperty("views_total")
    public Integer getViewCount() { return viewCount; }

    @JsonProperty("views_total")
    public void setViewCount(Integer viewCount) { this.viewCount = viewCount; }

    @JsonProperty("likes_total")
    public Integer getLikeCount() { return likeCount; }

    @JsonProperty("likes_total")
    public void setLikeCount(Integer likeCount) { this.likeCount = likeCount; }

    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<String> getComments() {
        return comments;
    }


    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public List<Caption> getCaptions() {
        return captions;
    }

    public void setCaptions(List<Caption> captions) {
        this.captions = captions;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    @JsonProperty("comments")
    public List<Comment> getCommentList() { return commentList; }

    public void setCommentList(List<Comment> commentList) { this.commentList = commentList; }





    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Video.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id");
        sb.append('=');
        sb.append(((this.id == null)?"<null>":this.id));
        sb.append(',');
        sb.append("name");
        sb.append('=');
        sb.append(((this.name == null)?"<null>":this.name));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("releaseTime");
        sb.append('=');
        sb.append(((this.releaseTime == null)?"<null>":this.releaseTime));
        sb.append(',');
        sb.append("userId");
        sb.append('=');
        sb.append((this.userId == null) ? "<null>" : this.userId);
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append((this.comments == null) ? "<null>" : this.comments);
        sb.append(',');
        sb.append("captions");
        sb.append('=');
        sb.append((this.captions == null) ? "<null>" : this.captions);
        sb.append(',');
        sb.append("user");
        sb.append('=');
        sb.append((this.user == null) ? "<null>" : this.user);
        sb.append(',');
        sb.append("commentList");
        sb.append('=');
        sb.append((this.commentList == null) ? "<null>" : this.commentList);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
