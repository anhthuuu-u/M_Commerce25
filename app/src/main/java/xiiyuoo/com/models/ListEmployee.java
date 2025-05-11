package xiiyuoo.com.models;

import java.util.ArrayList;

public class ListEmployee {
    private ArrayList<Employee> employees;

    public ListEmployee() {
        employees= new ArrayList<>();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void gen_dataset()
    {
        Employee e1=new Employee();
        e1.setId(1);
        e1.setName("John");
        e1.setEmail("John@gmail.com");
        e1.setPhone("0992938434");
        e1.setUsername("john");
        e1.setPassword("123");
        employees.add(e1);

        Employee e2 = new Employee();
        e2.setId(2);
        e2.setName("Tom");
        e2.setEmail("Tom@gmail.com");
        e2.setPhone("0992943523");
        e2.setUsername("tom");
        e2.setPassword("456");
        employees.add(e2);

        Employee e3 = new Employee();
        e3.setId(3);
        e3.setName("Emmy");
        e3.setEmail("Emmy@gmail.com");
        e3.setPhone("0353943523");
        e3.setUsername("Emmy");
        e3.setPassword("478");
        employees.add(e3);
    }
}
