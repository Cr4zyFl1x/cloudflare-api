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
public class Owner implements Identifiable {

    @Expose
    @SerializedName(value = "id")
    private String id;

    @Expose
    @SerializedName(value = "email")
    private String email;

    @Expose
    @SerializedName(value = "owner_type")
    private String ownerType;
}
