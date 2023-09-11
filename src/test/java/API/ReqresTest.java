package API;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class ReqresTest {
    private final static String URL = "https://reqres.in/";

    /**
     * Тест 1
     * 1 - Использовуя сервис https://reqres.in/ получить список пользователей со второй страницы
     * 2 - Убедиться что имена файлов аватаров пользователей совпадают
     * 3 - Убедиться что email пользователей имеет окончание reqres.in
     */
    @Test
    public void checkAvatarandIdTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());      //берем метод который устанавливает наши спуцификации и пишим в нем запрос и ответ
        List<UserData> users = given()      //static method in rest. All methods starts with it in Rest Assure
                .when()
//                .contentType(ContentType.JSON)  //which content type      //don't need it any more since we have it in Specifications
                .get("api/users?page=2")          //ссылка на наш запрос URL+ "api/users?page=2" но после создания класса Specifications удаляем URL
                .then().log().all()         //выводим какие результаты мы получаем
                .extract().body().jsonPath().getList("data", UserData.class);       //извлекаем тело. Указываем путь и класс куда извлекаем ответ.
        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));       //c помощью стрима api мы можем перебрать наш список и выбрать метод по очередности. И в forEach мы пропишем assert сравнение. x это переменная, в нашем списке счетчек.

        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));

        //КАк можно создать отдельно переменные
        List<String> avatars = users.stream().map(UserData::getAvatar).collect(Collectors.toList());
        List<String> ids = users.stream().map(x -> x.getId().toString()).collect(Collectors.toList());
        for (int i = 0; i < avatars.size(); i++) {
            Assert.assertTrue(avatars.get(i).contains(ids.get(i)));
        }
    }

    /**
     * Тест 2
     * 1 - Использовуя сервис https://reqres.in/ протестировать регистрацию пользователя в системе
     * 2 - Успешная регистрация
     * 3 - Регистрация с ошибкой
     */
    @Test
    public void successRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());      //берем метод который устанавливает наши спуцификации и пишим в нем запрос и ответ
// Мы будем использовать POST запрос, нам нужно в тело запроса обязательно что-то поместить библиотека REST Assured позволяет заоовывать в тело запроса класс. Т.е. мы через конструктор класса сразу же засунем данные а потом эти данные отправим на сервер
        Integer id = 4;         //Объявляем то что нам нужно получить это id и token
        String token = "QpwL5tke4Pnpja7X4";
        //нужно создать эклемпляр класса
        Register user = new Register("eve.holt@reqres.in", "pistol");       //мы создали тело запроса
        SuccesReg succesReg = given()
                .body(user)
                .when()
                .post("api/register")
                .then().log().all()     //извлекаем все
                .extract().as(SuccesReg.class);
        Assert.assertNotNull(succesReg.getId());        //убеждаемся что получили не пустой ответ
        Assert.assertNotNull(succesReg.getToken());     //убеждаемся что получили не пустой ответ
        Assert.assertEquals(id, succesReg.getId());
        Assert.assertEquals(token, succesReg.getToken());


    }

    @Test
    public void unSuccessRegTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecError400());      //берем метод который устанавливает наши спуцификации и пишим в нем запрос и ответ
// Мы будем использовать POST запрос, нам нужно в тело запроса обязательно что-то поместить библиотека REST Assured позволяет заоовывать в тело запроса класс. Т.е. мы через конструктор класса сразу же засунем данные а потом эти данные отправим на сервер
        Integer id = 4;         //Объявляем то что нам нужно получить это id и token
        String token = "QpwL5tke4Pnpja7X4";
        //нужно создать эклемпляр класса
        Register user = new Register("sydney@fife", "");       //no passowrd for negative test
        UnsuccessReg unSuccessReg = given()
                .body(user)
                .post()
                .then().log().all()     //извлекаем все
                .extract().as(UnsuccessReg.class);
        Assert.assertEquals("Missing password", unSuccessReg.getError());
    }

    /**
     * Используя сервис https://reqres.in/ убедиться, что операция LIST<RESOURCE> возвращает данныеб отсортированные по годам
     */
    @Test
    public void sortedYearsTest() {
        //нужно получить список
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        List<ColorsData> colors = given()
                .when()
                .get("api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data", ColorsData.class);
        List<Integer> years = colors.stream().map(ColorsData::getYear).collect(Collectors.toList()); //мы получаем список
        List<Integer> sortedYears = years.stream().sorted().collect(Collectors.toList());       //а потом его сортируем
        Assert.assertEquals(sortedYears, years);
        //    System.out.println(years);
        //    System.out.println(sortedYears);
    }

    /**
     * Удаляем пользователя
     */
    @Test
    public void deleteUserTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecUniqque(204));
        given()
                .when()
                .delete("api/users/2")
                .then().log().all();
    }

    /**
     * Используя сервис https://reqres.in/  обновить информацию о пользователе и сравнить дату обнавленния с текущей датой на машине
     */
    @Test
    public void timeTest() {
        Specifications.installSpecification(Specifications.requestSpec(URL), Specifications.responseSpecOK200());
        UserTime user = new UserTime("morpheus", "zion resident");
        UserTimeResponse response = given()
                .body(user)
                .when()
                .put("api/users/2")
                .then().log().all()
                .extract().as(UserTimeResponse.class);
        String regex = "(.{5})$";       //
        String currentTime = Clock.systemUTC().instant().toString().replaceAll(regex, "");    //заменяем последние 5 в
        Assert.assertEquals(currentTime, response.getUpdatedAt().replaceAll(regex, ""));
        System.out.println(response.getUpdatedAt().replaceAll(regex, ""));
    }
}
