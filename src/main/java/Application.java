import dao.EmployeeDao;
import dao.impl.EmployeeDaoImpl;
import model.City;
import model.Employee;

import java.util.Optional;

public class Application {

    public static void main(String[] args) {

        EmployeeDao employeeDao = new EmployeeDaoImpl();
        City spb = new City(10, "Санкт-Петербург");
        City msc = new City(11, "Москва");
        City tmn = new City(12, "Тюмень");

        Employee evgeny = employeeDao.add(new Employee("Евгений", "Давыдов", "муж", 42, 10L));
        System.out.println("Добавленный сотрудник: " + evgeny);

        Employee sergey = employeeDao.add(new Employee("Сергей", "Иванов", "муж", 65, 11L));
        System.out.println("Добавленный сотрудник: " + sergey);

        Employee alina = employeeDao.add(new Employee("Алевтина", "Петрововна", "жен", 70, 12L));
        System.out.println("Добавленный пенсионер: " + alina);

        Employee anton = employeeDao.add(new Employee("Антон", "Филипов", "муж", 33));
        System.out.println("Добавленный сотрудник: " + anton);

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);

        employeeDao.findById(sergey.getId())
                .ifPresent(employee -> System.out.println("Найденный сотрудник: " + employee));

        sergey.setAge(55);
        sergey.setFirstName("Яков");
        sergey.setCity(11L);
        sergey = employeeDao.update(sergey);
        System.out.println("Обновленный сотрудник: " + sergey);

        employeeDao.delete(sergey);
        System.out.println("Удаленный сотрудник: " + sergey);

        System.out.println("Все сотрудники");
        employeeDao.findAll().forEach(System.out::println);
    }
}
