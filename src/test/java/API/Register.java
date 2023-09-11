package API;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Register {
    //Создаем класс для регистрации
    private String name;
    private String job;
 // Создаем конструктор. Геттер не обязателен т.к. это пост поля
 @JsonCreator

 public Register(@JsonProperty("name")String name,  @JsonProperty("job")String job) {
        this.name = name;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
