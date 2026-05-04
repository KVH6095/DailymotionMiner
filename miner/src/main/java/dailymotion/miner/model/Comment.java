package dailymotion.miner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "text",
        "created_on"
})
@Generated("jsonschema2pojo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Comment {

    @JsonProperty("id")
    private String id;

    @JsonProperty("text")
    private String text;

    // No hay fecha en los tags, la dejo como null
    @JsonProperty("created_on")
    private String createdOn;

    @JsonProperty("id")
    public String getId() { return id; }

    @JsonProperty("id")
    public void setId(String id) { this.id = id; }

    @JsonProperty("text")
    public String getText() { return text; }

    @JsonProperty("text")
    public void setText(String text) { this.text = text; }

    @JsonProperty("created_on")
    public String getCreatedOn() { return createdOn; }

    @JsonProperty("created_on")
    public void setCreatedOn(String createdOn) { this.createdOn = createdOn; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Comment.class.getName()).append('@')
                .append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id=").append(id == null ? "<null>" : id).append(',');
        sb.append("text=").append(text == null ? "<null>" : text).append(',');
        sb.append("created_on=").append(createdOn == null ? "<null>" : createdOn).append(',');
        if (sb.charAt(sb.length() - 1) == ',')
            sb.setCharAt(sb.length() - 1, ']');
        else
            sb.append(']');
        return sb.toString();
    }
}
