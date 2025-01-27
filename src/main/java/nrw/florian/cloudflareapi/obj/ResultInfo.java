package nrw.florian.cloudflareapi.obj;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * Additional result information used for e.g. paging. Provided by the cloudflare api in the {@code result_info}
 * json object.
 *
 * <pre>
 *  "result_info": {
 *     "count": 1,
 *     "page": 1,
 *     "per_page": 20,
 *     "total_count": 2000
 *  }
 * </pre>
 *
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