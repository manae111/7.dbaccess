package ex_popular_group_story2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Ex01Popular2no2 {
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
                drop table if exists members;
                create table members(
                    id serial primary key,
                    name text not null,
                    birth_day date,
                    gender varchar(1),
                    color_id integer references colors (id)
                );
                """;
            pstmt = con.prepareStatement(sql);
            int numOfUpdate = pstmt.executeUpdate();
            System.out.println(numOfUpdate + "件のデータを操作しました。");

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
