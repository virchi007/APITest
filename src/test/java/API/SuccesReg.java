package API;

public class SuccesReg {
    private Integer id;
    private String token;

    public Integer getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public SuccesReg(Integer id, String token) {
        this.id = id;
        this.token = token;
    }
}
