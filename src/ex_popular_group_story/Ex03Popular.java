package ex_popular_group_story;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            while (rs.next()) {
                String name = rs.getString("name");
                Date birth_day = rs.getDate("birth_day");
                String gender = rs.getString("gender");
                int color_id = rs.getInt("color_id");
                System.out.print("name=" + name);
                System.out.print(" birth_day=" + birth_day);
                System.out.print(" gender=" + gender);
                System.out.print(" color_id=" + color_id);
                System.out.println();
            }
        } catch (SQLException ex) {
            System.err.println("SQLの例外が発生しました。");
            System.err.println("発行したSQLは「" + sql + "」");
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