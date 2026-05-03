package dailymotion.miner.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "screenname",
        "url",
        "avatar_120_url"
})
@Generated("jsonschema2pojo")
public class User {

    @JsonProperty("id")
    private String id;

    @JsonProperty("screenname")
    private String name;

    @JsonProperty("url")
    private String user_link;

    @JsonProperty("avatar_120_url")
    private String picture_link;

    @JsonProperty("id")
    public String getId() { return id; }

    @JsonProperty("id")
    public void setId(String id) { this.id = id; }

    @JsonProperty("screenname")
    public String getName() { return name; }

    @JsonProperty("screenname")
    public void setName(String name) { this.name = name; }

    @JsonProperty("url")
    public String getUser_link() { return user_link; }

    @JsonProperty("url")
    public void setUser_link(String user_link) { this.user_link = user_link; }

    @JsonProperty("avatar_120_url")
    public String getPicture_link() { return picture_link; }

    @JsonProperty("avatar_120_url")
    public void setPicture_link(String picture_link) { this.picture_link = picture_link; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id=").append(id == null ? "<null>" : id).append(',');
        sb.append("name=").append(name == null ? "<null>" : name).append(',');
        sb.append("user_link=").append(user_link == null ? "<null>" : user_link).append(',');
        sb.append("picture_link=").append(picture_link == null ? "<null>" : picture_link).append(',');
        if (sb.charAt(sb.length() - 1) == ',')
            sb.setCharAt(sb.length() - 1, ']');
        else
            sb.append(']');
        return sb.toString();
    }
}

