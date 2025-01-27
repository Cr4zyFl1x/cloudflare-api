package nrw.florian.cloudflareapi.constant;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Florian J. Kleine-Vorholt
 */
public enum ZoneType {

    @Expose
    @SerializedName("full")
    FULL,

    @Expose
    @SerializedName("partial")
    PARTIAL,

    @Expose
    @SerializedName("secondary")
    SECONDARY;


    /**
     * Gets the enum value in lowercase
     *
     * @return enum value in lowercase
     */
    public String asLower()
    {
        return name().toLowerCase();
    }
}