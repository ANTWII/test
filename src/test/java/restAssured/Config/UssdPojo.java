package restAssured.Config;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "ussdString",
        "ussdServiceOp",
        "sessionID",
        "msisdn",
        "network"
})
@Generated("jsonschema2pojo")
public class UssdPojo {

    @JsonProperty("ussdString")
    private String ussdString;
    @JsonProperty("ussdServiceOp")
    private String ussdServiceOp;
    @JsonProperty("sessionID")
    private  String sessionID=null;
    @JsonProperty("msisdn")
    private String msisdn;
    @JsonProperty("network")
    private String network;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("ussdString")
    public  String getUssdString() {
        return ussdString;
    }

    @JsonProperty("ussdString")
    public void setUssdString(String ussdString) {
        this.ussdString = ussdString;
    }

    @JsonProperty("ussdServiceOp")
    public String getUssdServiceOp() {
        return ussdServiceOp;
    }

    @JsonProperty("ussdServiceOp")
    public void setUssdServiceOp(String ussdServiceOp) {
        this.ussdServiceOp = ussdServiceOp;
    }

    @JsonProperty("sessionID")
    public    String getSessionID() {
        return sessionID;
    }

    @JsonProperty("sessionID")
    public  void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    @JsonProperty("msisdn")
    public String getMsisdn() {
        return msisdn;
    }

    @JsonProperty("msisdn")
    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    @JsonProperty("network")
    public String getNetwork() {
        return network;
    }

    @JsonProperty("network")
    public void setNetwork(String network) {
        this.network = network;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}