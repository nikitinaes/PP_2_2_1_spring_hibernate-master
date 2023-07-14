package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      User user1 = new User("Вова", "Вовочкин", "ya@ya.ru");
      Car car1 = new Car("Mercedes", 234);
      CarService carService = context.getBean(CarService.class);
      carService.addCar(car1);
      userService.addUser(user1.setCar(car1).setUser(user1));
      System.out.println("Это владелец " +userService.getUserByCar("Volkswagen", 234));

      userService.addUser(new User("Вова", "Вовин", "user1@mail.ru"));
      userService.addUser(new User("Петр", "Петров", "user2@mail.ru"));
      userService.addUser(new User("Иван", "Иванов", "user3@mail.ru"));
      userService.addUser(new User("Сидр", "Сидоров", "user4@mail.ru"));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      context.close();
   }
}
