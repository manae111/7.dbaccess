import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Ex01 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = """
            select
            id,
            name
            from
            departments;
            """;
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
        
            while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            System.out.print("id=" + id);
            System.out.println("  name=" + name);
            }

        } catch (SQLException ex) {
            System.out.println(sql + "のエラーです");
            ex.printStackTrace();

        } finally {

            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            
        }
    }
}
