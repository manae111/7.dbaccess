import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {


    //employeesテーブルの主キーをもとにEmployeeオブジェクトをロードする
    public Employee load(int id) {
        Connection con = DBManager.createConnection();

        String sql = """
                select
                id,
                name,
                age,
                gender,
                department_id
                from
                employees
                where id = ?
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getInt("age"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));
                return employee;
            }
            return null;

        } catch (SQLException ex) {
            System.err.println("SQL=" + sql);
            throw new RuntimeException("load処理に失敗しました",ex);

        } finally {
            DBManager.closeConnection(con);
        }    
    }
    


    //findBy(select)
    //部署に所属している従業員一覧を取得する
    public List<Employee> findByDepartmentId(int departmentId) {
        Connection con = DBManager.createConnection();
        String sql = """
                select
                id,
                name,
                age,
                gender,
                department_id
                from
                employees
                where department_id = ?;
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, departmentId);
            ResultSet rs = pstmt.executeQuery();

            List<Employee> employeeList = new ArrayList<>();

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setAge(rs.getInt("age"));
                employee.setGender(rs.getString("gender"));
                employee.setDepartmentId(rs.getInt("department_id"));
                employeeList.add(employee);
            }
            return employeeList;

        } catch (SQLException e) {
            System.err.println("SQL=" + sql);
            throw new RuntimeException("検索処理に失敗しました",e);

        } finally {
            DBManager.closeConnection(con);
        }
    }


    //insert
    //従業員情報を追加する
    public int insert(Employee employee) {
        Connection con = DBManager.createConnection();
        String sql = """
                insert into employees
                (id,name,age,gender,department_id)
                values
                (?,?,?,?,?);
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setInt(3, employee.getAge());
            pstmt.setString(4, employee.getGender());
            pstmt.setInt(5, employee.getDepartmentId());

            int affected = pstmt.executeUpdate();
            return affected;
  
        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("insert処理に失敗しました",e);

        } finally {
            DBManager.closeConnection(con);
        }
    }



    //update
    public int update(Employee employee) {
        Connection con = DBManager.createConnection();
        String sql = """
                update employees
                set
                name = ?,
                age = ?,
                gender = ?,
                department_id = ?
                where id = ?;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getName());
            pstmt.setInt(2, employee.getAge());
            pstmt.setString(3, employee.getGender());
            pstmt.setInt(4, employee.getDepartmentId());

            pstmt.setInt(5, employee.getId());

            int affected = pstmt.executeUpdate();

            return affected;

        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException();

        } finally {
            DBManager.closeConnection(con);
        }
        
    }



    //Delete
    public int deleteById(int id) {
        Connection con = DBManager.createConnection();
        String sql = """
                delete from employees
                where id = ?;
                """;

        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);

            int affected = pstmt.executeUpdate();
            return affected;

        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("delete処理に失敗しました");

        } finally {
            DBManager.closeConnection(con);
        }
    }
}
