package nrw.florian.cloudflareapi.obj.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import nrw.florian.cloudflareapi.obj.Identifiable;

/**
 * @author Florian J. Kleine-Vorholt
 */
@Getter
@Setter
public final class PlanPending implements Identifiable {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("price")
    private Integer price;

    @Expose
    @SerializedName("currency")
    private String currency;

    @Expose
    @SerializedName("Frequency")
    private String frequency;

    @Expose
    @SerializedName("legacy_id")
    private String legacyId;

    @Expose
    @SerializedName("is_subscribed")
    private Boolean isSubscribed;

    @Expose
    @SerializedName("can_subscribe")
    private Boolean canSubscribe;
}
