package ex_popular_group_story;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Ex03Popular {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = """
                select
                *
                from
                members
                """;
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            // Print column names
            for (int i = 1; i <= columnsNumber; i++) {
                if (i > 1) System.out.print(",  ");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println("");

            while (rs.next()) {
                String name = rs.getString("name");
                Date birth_day = rs.getDate("birth_day");
                String gender = rs.getString("gender");
                int color_id = rs.getInt("color_id");
                System.out.println(name);
                System.out.println(birth_day);
                System.out.println(gender);
                System.out.println(color_id);

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

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}