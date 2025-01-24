package nrw.florian.cloudflareapi.obj.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import nrw.florian.cloudflareapi.obj.Identifiable;

import java.util.List;

/**
 * @author Florian J. Kleine-Vorholt
 */
@Getter
@Setter
public class Zone implements Identifiable {

    @Expose
    @SerializedName(value = "id")
    private int id;

    @Expose
    @SerializedName(value = "name")
    private String name;

    @Expose
    @SerializedName("development_mode")
    private Integer developmentMode;

    @Expose
    @SerializedName("original_name_servers")
    private List<String> originalNameServers = null;

    @Expose
    @SerializedName("original_registrar")
    private String originalRegistrar;

    @Expose
    @SerializedName("original_dnshost")
    private String originalDnsHost;

    @Expose
    @SerializedName("created_on")
    private String createdOn;

    @Expose
    @SerializedName("modified_on")
    private String modifiedOn;

    @Expose
    @SerializedName("name_servers")
    private List<String> nameServers = null;

    @Expose
    @SerializedName("owner")
    private Owner owner;

    @Expose
    @SerializedName("permissions")
    private List<String> permissions = null;

    @Expose
    @SerializedName("plan")
    private Plan plan;

    @Expose
    @SerializedName("plan_pending")
    private PlanPending planPending;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("paused")
    private Boolean paused;

    @Expose
    @SerializedName("type")
    private String type;
}