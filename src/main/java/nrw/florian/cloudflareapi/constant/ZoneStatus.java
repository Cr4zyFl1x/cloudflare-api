package nrw.florian.cloudflareapi.constant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Status of the cloudflare zone
 *
 * @author Florian J. Kleine-Vorholt
 */
public enum ZoneStatus {

    @Expose
    @SerializedName("active")
    ACTIVE,

    @Expose
    @SerializedName("pending")
    PENDING,

    @Expose
    @SerializedName("initializing")
    INITIALIZING,

    @Expose
    @SerializedName("moved")
    MOVED;
}
