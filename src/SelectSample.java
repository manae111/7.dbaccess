import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectSample {
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
                SELECT 
                id,
                name,
                age 
                FROM 
                employees 
                order by age;
                """;//複数行文字列
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                System.out.print("id=" + id);
                System.out.print("  name=" + name);
                System.out.print("  age=" + age);
                System.out.println();
            }    
        } catch (SQLException ex) {
            System.out.println("SQL関連の例外が発生しました");
            System.out.println("発行したSQLは「" + sql + "」");
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) { 
                    rs.close(); 
                }
                if (pstmt != null) {
                    pstmt.close(); 
                }
                if (con != null) {
                    con.close(); 
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
