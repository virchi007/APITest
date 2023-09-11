package API;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Переводим дату из JSON в POJO. И после этого создаём конструктор и геттеры.
 * Название переменных в классе должны быть такими же как и в JSONе.
 * Обязательно делаем их private по правилам OOP.
 * С помощью библиотеки REST Assured можно все переменные (ответ с сервера) извлечь в класс.
 * У нас будет список пользователей. В дженериках создакем конструктор чтобы заполнился класс с сервера. Создаем get чтобы получать интересующие нас данные (id & email).
 * И с их помощью будем сравнивать результаты.
 */
public class UserData {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;

    @JsonCreator

    public UserData(@JsonProperty("id")Integer id, @JsonProperty("email")String email,
                    @JsonProperty("first_name")String first_name,
                    @JsonProperty("last_name")String last_name, @JsonProperty("avatar")String avatar) {
        this.id = id;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }
}
