package nrw.florian.cloudflareapi.obj.zone;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;
import nrw.florian.cloudflareapi.obj.Identifiable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author Florian J. Kleine-Vorholt
 */
@Getter
@Setter
public final class Owner implements Identifiable {

    @Expose
    @SerializedName(value = "id")
    private String id;

    @Expose
    @SerializedName(value = "name")
    private String name;

    @Expose
    @SerializedName(value = "type")
    private String type;


    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("ID", id)
                .append("Name", name)
                .append("Type", type)
                .toString();
    }
}
