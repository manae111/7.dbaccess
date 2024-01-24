package ex_popular_group_story2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Ex01Popular2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String name ="postgres";
        String password = "postgres"

        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(url, sql, password);
            sql = """
                drop table if exists colors;
                create table colors(
                    id integer primary key,
                    name text
                );
                """;
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
        }

        
    }
}
