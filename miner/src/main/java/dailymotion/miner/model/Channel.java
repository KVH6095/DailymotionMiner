
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
        "createdTime",
        "videos"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Channel {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonAlias("created_time")
    @JsonDeserialize(using = com.fasterxml.jackson.databind.deser.std.StringDeserializer.class)
    private String createdTime;
    @JsonProperty("fans_count")
    private Integer subscriberCount;

    private List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
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

    @JsonProperty("createdTime")
    public String getCreatedTime() { return createdTime; }

    public void setCreatedTime(String createdTime) { this.createdTime = createdTime; }

    @JsonProperty("fans_count")
    public Integer getSubscriberCount() { return subscriberCount; }

    @JsonProperty("fans_count")
    public void setSubscriberCount(Integer subscriberCount) { this.subscriberCount = subscriberCount; }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Channel.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        sb.append("createdTime");
        sb.append('=');
        sb.append(((this.createdTime == null)?"<null>":this.createdTime));
        sb.append(',');
        sb.append("videos");
        sb.append('=');
        sb.append((this.videos == null) ? "<null>" : this.videos);
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
