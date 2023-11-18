package scrapping.webscrapping;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 *
 * @author Larbi
 */
public class MySQLConnection {

    public static int executeQuery(String sql) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/crawling_db", "root", "root")) {
            return con.createStatement().executeUpdate(sql);
        }
    }
        public static int executeParametredQuery(int id,String cover, String title,int rating, boolean inStock,double price) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/crawling_db", "root", "root")) {
            PreparedStatement prepareStatement = con.prepareStatement("insert into book values(?,?,?,?,?,?)");
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, title);
            prepareStatement.setInt(3, rating);
            prepareStatement.setDouble(4, price);
            prepareStatement.setBoolean(5, inStock);
            prepareStatement.setString(6, cover);
            return prepareStatement.executeUpdate();
        }
    }
}
