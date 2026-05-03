package dailymotion.miner.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import dailymotion.miner.model.Caption;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaptionList {

    @JsonProperty("list")
    private List<Caption> list;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("has_more")
    private Boolean hasMore;

    @JsonProperty("list")
    public List<Caption> getList() { return list; }

    @JsonProperty("list")
    public void setList(List<Caption> list) { this.list = list; }

    @JsonProperty("total")
    public Integer getTotal() { return total; }

    @JsonProperty("total")
    public void setTotal(Integer total) { this.total = total; }

    @JsonProperty("has_more")
    public Boolean getHasMore() { return hasMore; }

    @JsonProperty("has_more")
    public void setHasMore(Boolean hasMore) { this.hasMore = hasMore; }
}
