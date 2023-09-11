package API;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

public class UserTime {

    private String name;
    private String job;
    @JsonCreator

    public UserTime(@JsonProperty("name")String name, @JsonProperty("job")String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
