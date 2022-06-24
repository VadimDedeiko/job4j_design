package ru.job4j.ood.srp;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

/**Класс-обертка, так как JAXB ждет аннотаций, а у коллекций их нет.
 * Не забываем создавать конструктор по умолчанию
 * */

@XmlRootElement(name = "employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListEmployees {
    @XmlElement(name = "employee")
    private List<Employee> employeeList;

    public ListEmployees() {
    }

    public ListEmployees(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ListEmployees that = (ListEmployees) o;
        return Objects.equals(employeeList, that.employeeList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeList);
    }
}
