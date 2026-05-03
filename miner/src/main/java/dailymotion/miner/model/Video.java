
package dailymotion.miner.model;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.annotation.processing.Generated;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "title",
    "description",
    "created_time"
})
@Generated("jsonschema2pojo")
public class Video {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("created_time")
    @JsonDeserialize(using = com.fasterxml.jackson.databind.deser.std.StringDeserializer.class)
    private String releaseTime;
    @JsonProperty(value = "owner", access = JsonProperty.Access.WRITE_ONLY)
    private String userId;
    @JsonProperty(value = "tags", access = JsonProperty.Access.WRITE_ONLY)
    private List<String> comments;

    private List<Caption> captions;

    private User user;

    private List<Comment> commentList;




    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("title")
    public String getName() {
        return name;
    }

    @JsonProperty("title")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("created_time")
    public String getReleaseTime() {
        return releaseTime;
    }

    @JsonProperty("created_time")
    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }



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
