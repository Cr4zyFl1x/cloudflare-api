package nrw.florian.cloudflareapi.obj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * @author Florian J. Kleine-Vorholt
 */
@Getter
public final class ResultInfo {

    @Expose
    @SerializedName("page")
    public Integer page;

    @Expose
    @SerializedName("per_page")
    public Integer perPage;

    @Expose
    @SerializedName("count")
    public Integer count;

    @Expose
    @SerializedName("total_count")
    public Integer totalCount;
}