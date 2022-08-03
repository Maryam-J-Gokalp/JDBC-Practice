package jdbctests;

import java.sql.*;

public class TestConnection {

    public static void main(String[] args) throws SQLException {


        //Connection String

        String dbURL = "jdbc:oracle:thin:@54.237.206.21:1521:XE";
        String dbUserName= "hr";
        String dbPassword= "hr";

        Connection connection = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM REGIONS");

/*

        //next() -> move pointer to first row
        resultSet.next();

        //getting information with column name
        System.out.println(resultSet.getString("region_name")); //Europe
        System.out.println(resultSet.getString(2));
        //resultSet.next(); //new row a used to pass
       // System.out.println(resultSet.getString("region_name")); //Americas


        //2 I used next 2 times because it prints
       // System.out.println(resultSet.getString(1));

        // 1- Europa
        // 2- Americas
        // 3- Europa
        // 4- Americas

        System.out.println(resultSet.getInt(1) + "- " + resultSet.getString("region_name"));
        resultSet.next();
        System.out.println(resultSet.getInt(1) + "- " + resultSet.getString("region_name"));
        resultSet.next();
        System.out.println(resultSet.getInt(1) + "- " + resultSet.getString("region_name"));
        resultSet.next();
        System.out.println(resultSet.getInt(1) + "- " + resultSet.getString("region_name"));

        */

        while (resultSet.next()){

            System.out.println(resultSet.getInt(1) + "- " + resultSet.getString("region_name"));
        }

        //Close connection

        resultSet.close();
        statement.close();
        connection.close();


    }
}
