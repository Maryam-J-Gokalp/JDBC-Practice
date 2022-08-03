package jdbctests;

import org.junit.jupiter.api.Test;
import utilities.DBUtiL;

import java.sql.*;

public class LibraryConnectionTest {

    String dbURL = "mysql://3.89.251.99:3306/library1";
    String dbUserName = "library1_client";
    String dbPassword = "WVF4NdGXCKHeE6VQ";


    @Test
    public void test1() throws SQLException {

        Connection conn = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        Statement statement = conn.createStatement(); //ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
        ResultSet resultSet = statement.executeQuery("SELECT * FROM books");

        // once you set up connection pointer looks for 0
        //next() -->move pointer to first row
        resultSet.next();

        //close connection
        conn.close();
        statement.close();
        resultSet.close();
    }

    @Test
    public void test2(){
        String dbURL = "mysql://3.89.251.99:3306/library1";
        String dbUserName = "library1_client";
        String dbPassword = "WVF4NdGXCKHeE6VQ";

        DBUtiL.createConnection(dbURL,dbUserName,dbPassword);
        DBUtiL.destroy();



    }
}