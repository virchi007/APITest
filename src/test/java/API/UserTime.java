package API;

import org.openxmlformats.schemas.spreadsheetml.x2006.main.STIconSetType;

public class UserTime {

    private String name;
    private String job;

    public UserTime(String name, String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }
}
