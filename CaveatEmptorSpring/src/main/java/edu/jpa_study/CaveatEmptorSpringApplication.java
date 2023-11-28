package edu.jpa_study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


// Данная аннотация, добавленная Spring Boot к классу, который содержит
// main метод, позволяет запустить механизм автоконфигурации Spring Boot
// и сканирование пакетов приложения, это также позволит регистрацию
// дополнительных бинов в контекст.
@SpringBootApplication
public class CaveatEmptorSpringApplication {

	public static void main(String[] args) {
		// Данный метод запустит Spring приложение (создаст ApplicationContext
		// и загрузит бины)
		SpringApplication.run(CaveatEmptorSpringApplication.class, args);
	}


	// Spring Boot запускает метод, помеченный аннотацией @Bean,
	// возвращающий ApplicationRunner непосредственно перед окончанием выполнения метода run()
//	@Bean
//	public ApplicationRunner configure(UserRepository userRepository) {
//		return env -> {
//			// Создадим 2 покупателей
//			User user1 = new User("Vasiliy1990", "Vasiliy", "Sidorov", "VasilSidorov@mail.ru");
//			User user2 = new User("Petr1987", "Petr", "Ivanov", "pivanov@gmail.com");
//			// Сохраним их в базу данных
//			userRepository.save(user1);
//			userRepository.save(user2);
//
//			List<User> allOrderByUsernameAsc = userRepository.findAllByOrderByUsernameAsc();
//			for (User u :allOrderByUsernameAsc) {
//				System.out.println(u);
//			}
//
//			// Получим всех покупателей и выведем в консоль
//			userRepository.findAll().forEach(System.out::println);
//		};
//	}
}
