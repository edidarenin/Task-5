package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      // Создаем пользователей с машинами
      userService.add(new User("User1", "Lastname1", "user1@mail.ru", new Car("Toyota", 1)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", new Car("BMW", 2)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", new Car("Mercedes", 3)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", new Car("Audi", 4)));

      // Выводим всех пользователей
      List<User> users = userService.listUsers();
      System.out.println("=== Все пользователи ===");
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + user.getCar());
         System.out.println();
      }

      // Тестируем поиск пользователя по машине
      System.out.println("=== Поиск пользователя по машине ===");
      User foundUser = userService.getUserByCar("BMW", 2);
      System.out.println("Найден пользователь:");
      System.out.println("Id = " + foundUser.getId());
      System.out.println("First Name = " + foundUser.getFirstName());
      System.out.println("Last Name = " + foundUser.getLastName());
      System.out.println("Email = " + foundUser.getEmail());
      System.out.println("Car = " + foundUser.getCar());

      context.close();
   }
}