package nrw.florian.cloudflareapi.obj.dns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;

/**
 * @author Florian J. Kleine-Vorholt
 */
@Getter
public final class RecordMeta {

    @Expose
    @SerializedName("auto_added")
    private Boolean autoAdded;

    @Expose
    @SerializedName("managed_by_apps")
    private Boolean managedByApps;
}