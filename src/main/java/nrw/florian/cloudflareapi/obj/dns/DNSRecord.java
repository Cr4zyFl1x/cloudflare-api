package nrw.florian.cloudflareapi.obj.dns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import nrw.florian.cloudflareapi.constant.RecordType;
import nrw.florian.cloudflareapi.obj.Identifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Florian J. Kleine-Vorholt
 */
@Getter
public final class DNSRecord implements Identifiable {

    @Expose
    @SerializedName("id")
    private String id;

    @Expose
    @SerializedName("type")
    @Setter
    private RecordType type;

    @Expose
    @SerializedName("name")
    @Setter
    private String name;

    @Expose
    @SerializedName("content")
    @Setter
    private String content;

    @Expose
    @SerializedName("proxiable")
    private Boolean proxiable;

    @Expose
    @SerializedName("proxied")
    @Setter
    private Boolean proxied;

    @Expose
    @SerializedName("ttl")
    @Setter
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


    public DNSRecord(final String name, final RecordType type, final String content, final Integer ttl, final Boolean proxied)
    {
        this.name = name;
        this.type = type;
        this.content = content;
        this.proxied = proxied;
    }

    public DNSRecord(final String name, final RecordType type, final String content, final Integer ttl)
    {
        this(name, type, content, ttl, null);
    }

    public DNSRecord(final String name, final RecordType type, final String content)
    {
        this(name, type, content, null, null);
    }

    public DNSRecord()
    {}




    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ID", id)
                .append("Type", type)
                .append("Name", name)
                .append("Content", content)
                .toString();
    }
}