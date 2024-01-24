import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

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
                name,
                age
                from
                employees
                ouder by age
                """;
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");


            }
        }
    }
}
