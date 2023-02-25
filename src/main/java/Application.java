import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import jdbc.ConnectionManager;
import model.City;
import model.Employee;

import java.sql.*;
import java.util.Optional;

public class Application {

    public static void main(String[] args) {

        // Задание 1
        System.out.println("Задание 1");
        System.out.println();
        EmployeeDao employeeDao = new EmployeeDaoImpl();
        City spb = new City(10, "Санкт-Петербург");
        City msc = new City(11, "Москва");
        City tmn = new City(12, "Тюмень");

        employeeDao.add(new Employee("Евгений", "Давыдов", "муж", 42, spb))
                .ifPresent(employee -> System.out.println("Добавленный сотрудник: " + employee));
        Optional<Employee> employeeOptional = employeeDao.add(new Employee("Сергей", "Иванов", "муж", 65, msc));
        employeeOptional.ifPresent(
                employee -> System.out.println("Добавленный сотрудник: " + employee));
        employeeDao.add(new Employee("Алевтина", "Петрововна", "жен", 70, tmn))
                .ifPresent(employee -> System.out.println("Добавленный пенсионер: " + employee));
        employeeDao.add(new Employee("Антон", "Филипов", "муж", 33))
                .ifPresent(employee -> System.out.println("Добавленный сотрудник: " + employee));

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);

        if (employeeOptional.isPresent()) {
            employeeDao.findById(employeeOptional.get().getId())
                    .ifPresent(employee -> System.out.println("Найденный сотрудник: " + employee));
        }
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setAge(55);
            employee.setFirst_name("Яков");
            employeeDao.update(employee)
                    .ifPresent(emp -> System.out.println("Обновленный сотрудник: " + emp));
        }
        if (employeeOptional.isPresent()) {
            employeeDao.deleteById(employeeOptional.get().getId())
                    .ifPresent(emp -> System.out.println("Удаленный сотрудник: " + emp));
        }

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);
    }
}
