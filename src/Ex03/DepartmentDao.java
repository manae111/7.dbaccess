package Ex03;

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
}
