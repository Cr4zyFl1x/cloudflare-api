package nrw.florian.cloudflareapi.obj.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import nrw.florian.cloudflareapi.constant.ZoneStatus;
import nrw.florian.cloudflareapi.constant.ZoneType;
import nrw.florian.cloudflareapi.obj.Identifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * Zone information as represented in {@code result} json object on calls for zone component.
 *
 * <pre>
 * "result": {
 *   "id": "023e105f4ecef8ad9ca31a8372d0c353",
 *   "account": {
 *     "id": "023e105f4ecef8ad9ca31a8372d0c353",
 *     "name": "Example Account Name"
 *   },
 *   "activated_on": "2014-01-02T00:01:00.12345Z",
 *   "created_on": "2014-01-01T05:20:00.12345Z",
 *   "development_mode": 7200,
 *   "meta": {
 *     "cdn_only": true,
 *     "custom_certificate_quota": 1,
 *     "dns_only": true,
 *     "foundation_dns": true,
 *     "page_rule_quota": 100,
 *     "phishing_detected": false,
 *     "step": 2
 *   },
 *   "modified_on": "2014-01-01T05:20:00.12345Z",
 *   "name": "example.com",
 *   "name_servers": [
 *     "bob.ns.cloudflare.com",
 *     "lola.ns.cloudflare.com"
 *   ],
 *   "original_dnshost": "NameCheap",
 *   "original_name_servers": [
 *     "ns1.originaldnshost.com",
 *     "ns2.originaldnshost.com"
 *   ],
 *   "original_registrar": "GoDaddy",
 *   "owner": {
 *     "id": "023e105f4ecef8ad9ca31a8372d0c353",
 *     "name": "Example Org",
 *     "type": "organization"
 *   },
 *   "paused": true,
 *   "status": "initializing",
 *   "type": "full",
 *   "vanity_name_servers": [
 *     "ns1.example.com",
 *     "ns2.example.com"
 *   ]
 * }
 * </pre>
 *
 * @author Florian J. Kleine-Vorholt
 */
@Getter
@Setter
public final class Zone implements Identifiable {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("name")
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
    private ZoneStatus status;

    @Expose
    @SerializedName("paused")
    private Boolean paused;

    @Expose
    @SerializedName("type")
    private ZoneType type;


    @Override
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ID", id)
                .append("Name", name)
                .toString();
    }
}