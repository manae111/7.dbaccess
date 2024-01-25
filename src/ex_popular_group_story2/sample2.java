package ex_popular_group_story2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class sample2 {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/student";
        String user = "postgres";
        String password = "postgres";

        
        
        String sql = """
                    select
                    m.name as member_name,
                    m.birth_day,
                    m.gender,
                    c.name as color_name
                    from
                    members m
                    left outer join colors c
                    on m.color_id = c.id;
                    """;
        

        try (Connection con = DriverManager.getConnection(url, user, password);
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            
            while (rs.next()) {
                String member_name = rs.getString("member_name");
                Date birth_day = rs.getDate("birth_day");
                String gender = rs.getString("gender");
                String color_name = rs.getString("color_name");
                System.out.print("name=" + member_name);
                System.out.print(" birth_day=" + birth_day);
                System.out.print(" gender=" + gender);
                System.out.print(" color_name=" + color_name);
                System.out.println();
            }

        } catch (SQLException ex) {
            System.out.println("エラー発生");
            ex.printStackTrace();
        } 
    }
}
