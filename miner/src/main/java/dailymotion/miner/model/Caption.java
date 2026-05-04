package dailymotion.miner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.annotation.processing.Generated;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "id",
        "url",
        "language"
})
@Generated("jsonschema2pojo")
public class Caption {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String link;

    @JsonProperty("language")
    private String language;

    @JsonProperty("id")
    public String getId() { return id; }

    @JsonProperty("id")
    public void setId(String id) { this.id = id; }

    @JsonProperty("url")
    public String getLink() { return link; }

    @JsonProperty("url")
    public void setLink(String link) { this.link = link; }

    @JsonProperty("language")
    public String getLanguage() { return language; }

    @JsonProperty("language")
    public void setLanguage(String language) { this.language = language; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Caption.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("id=").append(id == null ? "<null>" : id).append(',');
        sb.append("link=").append(link == null ? "<null>" : link).append(',');
        sb.append("language=").append(language == null ? "<null>" : language).append(',');
        if (sb.charAt(sb.length() - 1) == ',')
            sb.setCharAt(sb.length() - 1, ']');
        else
            sb.append(']');
        return sb.toString();
    }
}
