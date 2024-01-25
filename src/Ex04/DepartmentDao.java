package Ex04;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DepartmentDao {
    public Department load(int id) {
        Connection con = DBManager.createConnection();
        String sql = """
                select 
                id,
                name
                from departments
                where id = ?
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Department department = new Department();
                department.setId(rs.getInt("id"));
                department.setName(rs.getString("name"));
                return department;
            }
            return null;

        } catch (SQLException e) {
            System.err.println("SQL=" + sql);
            throw new RuntimeException("load処理に失敗しました");

        } finally {
            DBManager.closeConnection(con);
        }
    }


    //insert
    public int insert(Department department) {
        Connection con = DBManager.createConnection();
        String sql = """
                insert into departments
                (id,name)
                values
                (?,?)
                """;
        
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, department.getId());
            pstmt.setString(2, department.getName());

            int affected = pstmt.executeUpdate();
            System.out.println(affected + "件を操作しました。");

            return affected;

        } catch (SQLException e) {
            System.err.println("SQL = " + sql);
            throw new RuntimeException("insert処理に失敗しました",e);

        } finally {
            DBManager.closeConnection(con);
        }
    }
}
