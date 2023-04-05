package hibernate;

import hibernate.model.Employee;
import hibernate.service.EmployeeDAOImpl;

import java.sql.*;

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
       EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
       employeeDAO.createEmployee(new Employee(1, "Александр", "Полочкин", "м", 46, 1));
       employeeDAO.updateEmployee(new Employee(4, "Пенёк", "Олеханов", "м", 26, 1), 4);
       employeeDAO.deleteEmployee(new Employee(16, "Александр", "Полочкин", "м", 46, 2));
       employeeDAO.getAllEmployees().forEach(System.out::println);
       System.out.println("=========+++++++++=========");
       employeeDAO.getEmployeeById(4);


    }
}