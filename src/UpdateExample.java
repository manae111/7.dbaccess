public class UpdateExample {
    public static void main(String[] args) {
        EmployeeDao dao = new EmployeeDao();
        Employee employee = dao.load(1000);

        System.out.println("-----更新前-----");
        System.out.println("id = " + employee.getId());
        System.out.println("name = " + employee.getName());
        System.out.println("age = " + employee.getAge());
        System.out.println("gender = " + employee.getGender());
        System.out.println("departmet_id = " + employee.getDepartmentId());

        employee.setName("伊賀");
        employee.setAge(18);
        employee.setDepartmentId(4);

        dao.update(employee);
        employee = dao.load(1000);

        System.out.println("-----更新後-----");
        System.out.println("id = " + employee.getId());
        System.out.println("name = " + employee.getName());
        System.out.println("age = " + employee.getAge());
        System.out.println("gender = " + employee.getGender());
        System.out.println("departmet_id = " + employee.getDepartmentId());
        
    }
}
