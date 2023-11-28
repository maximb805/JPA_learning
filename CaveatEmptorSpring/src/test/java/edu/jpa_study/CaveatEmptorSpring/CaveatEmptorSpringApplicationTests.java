package edu.jpa_study.CaveatEmptorSpring;

import edu.jpa_study.model.Address;
import edu.jpa_study.model.User;
import edu.jpa_study.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// Сообщает Spring Boot, что необходимо найти класс конфигураций @SpringBootApplication
// и создать ApplicationContext для использования в тестах
@SpringBootTest
// Данной аннотацией мы просим JUnit5 создать единственный экземпляр данного класса
// для всех методов-тестов. Это позволяет сделать методы @BeforeAll, @AfterAll не static
// и использовать @Autowired поле userRepository в них напрямую.
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class CaveatEmptorSpringApplicationTests {

    @Autowired
    UserRepository userRepository;

    // данный метод будет выполнен перед всеми тестами (не перед каждым, а единожды)
    @BeforeAll
    void beforeAll() {
        List<User> users = userRepository.saveAll(generateUsers());
        int year = LocalDate.now().getYear();
        for (User u : users) {
            int regYear = u.getRegistrationDate().getYear();
            userRepository.updateLevelByUsername(u.getUsername(), year - regYear);
            userRepository.updateActiveByUsername(u.getUsername(), (year - regYear < 4));
        }
    }

    private static List<User> generateUsers() {
        List<User> users = new ArrayList<>();
        User user1 = new User("vasiliy1990", "Vasiliy", "Sidorov", "VasilSidorov@mail.ru", LocalDate.of(2020, 3, 7));
        User user2 = new User("petr1987", "Petr", "Ivanov", "pivanov@gmail.com", LocalDate.of(2023, 5, 11));
        User user3 = new User("kirill1337", "Kirill", "Petrov", "kirillpet@mail.ru", LocalDate.of(2021, 1, 27));
        User user4 = new User("turbo1000", "Andrey", "Stadnik", "turbo1000@gmail.com", LocalDate.of(2019, 6, 5));
        User user5 = new User("tolyanRaketa", "Anatoliy", "Kalinov", "tolyanTheRocket@mail.ru", LocalDate.of(2022, 12, 25));
        User user6 = new User("kara_hooker", "Vladislav", "Boytsov", "kara3221337@gmail.com", LocalDate.of(2020, 7, 30));
        User user7 = new User("bogdan2000", "Bogdan", "Malyshev", "lordPirozhkov@mail.ru", LocalDate.of(2018, 4, 23));
        User user8 = new User("sanya2005", "Alexander", "Smirnov", "smirsan@gmail.com", LocalDate.of(2015, 3, 17));
        User user9 = new User("kolya_den1997", "Nikolay", "Denisenko", "nookie1997@mail.ru", LocalDate.of(2022, 9, 2));
        User user10 = new User("maximb805", "Maxim", "Bulychev", "maximb805@gmail.com", LocalDate.of(2021, 8, 12));
        User user11 = new User("lubagal228", "Lubov", "Galkina", "galuba1337@mail.ru", LocalDate.of(2014, 2, 4),
                new Address("Astrahan", "Lenina st.", "123456"),
                new Address("Astrahan", "Stalina st.", "123457"), 2, true);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);
        users.add(user7);
        users.add(user8);
        users.add(user9);
        users.add(user10);
        users.add(user11);

        return users;
    }

    // данный метод будет выполнен после всех тестов (не после каждого, а единожды)
//	@AfterAll
//	void afterAll() {
//		userRepository.deleteAll();
//	}
}
