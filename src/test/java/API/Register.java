package API;

public class Register {
    //Создаем класс для регистрации
    private String name;
    private String job;
 // Создаем конструктор. Геттер не обязателен т.к. это пост поля
    public Register(String name, String job) {
        this.name = name;
        this.job = job;
    }
}
