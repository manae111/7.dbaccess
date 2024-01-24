package ex_popular_group_story2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex04Popular2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user ="postgres";
        String password = "postgres";

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;

        try {
            con = DriverManager.getConnection(url, user, password);
            sql = """
                update members
                set
                name = '酒井 愛男',
                birth_day = '1997-09-09',
                gender = '男',
                color_id = 6
                where id = 1;
                """;
            pstmt = con.prepareStatement(sql);
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを操作しました");

        } catch (SQLException ex) {
            System.err.println("SQL=" + sql);
            ex.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
