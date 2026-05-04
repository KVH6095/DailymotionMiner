package dailymotion.miner.model;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "screenname",
        "url",
        "avatar_120_url"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonAlias("screenname")
    private String name;

    @JsonAlias("url")
    private String userLink;

    @JsonAlias("avatar_120_url")
    private String pictureLink;

    @JsonProperty("name")
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @JsonProperty("user_link")
    public String getUserLink() { return userLink; }

    public void setUserLink(String userLink) { this.userLink = userLink; }

    @JsonProperty("picture_link")
    public String getPictureLink() { return pictureLink; }

    public void setPictureLink(String pictureLink) { this.pictureLink = pictureLink; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(User.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id=").append(id == null ? "<null>" : id).append(',');
        sb.append("name=").append(name == null ? "<null>" : name).append(',');
        sb.append("userLink=").append(userLink == null ? "<null>" : userLink).append(',');
        sb.append("pictureLink=").append(pictureLink == null ? "<null>" : pictureLink).append(',');
        if (sb.charAt(sb.length() - 1) == ',')
            sb.setCharAt(sb.length() - 1, ']');
        else
            sb.append(']');
        return sb.toString();
    }
}
