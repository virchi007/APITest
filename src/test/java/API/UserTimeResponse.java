package API;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UserTimeResponse extends UserTime{
    private String updatedAt;

    @JsonCreator

    public UserTimeResponse(@JsonProperty("name")String name,
                            @JsonProperty("job")String job, @JsonProperty("updatedAt")String updatedAt) {
        super(name, job);
        this.updatedAt = updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
