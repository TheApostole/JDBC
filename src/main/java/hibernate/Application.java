package hibernate;

import hibernate.model.City;
import hibernate.model.Employee;
import hibernate.service.CityDaoImpl;
import hibernate.service.CityDao;
import hibernate.service.EmployeeDAOImpl;
import hibernate.service.EmployeeDAO;

import javax.transaction.Transactional;
import java.sql.*;
import java.util.List;
@Transactional
public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "Fyfnjkbq1899";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (final Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT * FROM employee " +
                             "INNER JOIN city ON employee.city_id = city.city_id WHERE id = (2)")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String city_name = resultSet.getString("city_name");
                System.out.println("Имя: " + first_name);
                System.out.println("Фамилия: " + last_name);
                System.out.println("Пол: " + gender);
                System.out.println("Возраст: " + age);
                System.out.println("Город проживания: " + city_name);
            }
        }catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        System.out.println("=========+++++++++=========");
        CityDao cityDao = new CityDaoImpl();
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
//        employeeDAO.createEmployee(new Employee(1, "Александр", "Полочкин", "м", 46, City.builder().city_name("Владимир").employees(List.of()).build()));
//        employeeDAO.updateEmployee(new Employee(4, "Пенёк", "Олеханов", "м", 26, new City(1, "Владивосток", List.of()) ), 4);
//        employeeDAO.deleteEmployee(new Employee(18, "Александр", "Полочкин", "м", 46, new City(1, "Владивосток", List.of())));
//        employeeDAO.getAllEmployees();
       System.out.println("=========+++++++++=========");
//       employeeDAO.getEmployeeById(4);
        City city = new City(6, "Владивосток", List.of());
        cityDao.create(city);
        Employee employee1 = Employee.builder().first_name("Генрих").last_name("Форд").gender("м").city(city).age(45).build();
        Employee employee2 = Employee.builder().first_name("Томас").last_name("Шелби").gender("м").city(city).age(50).build();
        city.setEmployees(List.of(employee1, employee2));
        cityDao.updateCity(new City(12, "Мексика", List.of()));
        cityDao.deletedCity(new City(13, "Мексика", List.of()));
        cityDao.getAllCities();
        System.out.println("=========+++++++++=========");
        cityDao.getCityById(5);
        System.out.println(cityDao.getAllCities());


    }
}