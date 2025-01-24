package nrw.florian.cloudflareapi.obj.dns;

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
public final class DNSRecord implements Identifiable {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("content")
    private String content;

    @Expose
    @SerializedName("proxiable")
    private Boolean proxiable;

    @Expose
    @SerializedName("proxied")
    private Boolean proxied;

    @Expose
    @SerializedName("ttl")
    private Integer ttl;

    @Expose
    @SerializedName("locked")
    private Boolean locked;

    @Expose
    @SerializedName("zone_id")
    private String zoneId;

    @Expose
    @SerializedName("zone_name")
    private String zoneName;

    @Expose
    @SerializedName("modified_on")
    private String modifiedOn;

    @Expose
    @SerializedName("created_on")
    private String createdOn;

    @Expose
    @SerializedName("meta")
    private RecordMeta meta;
}