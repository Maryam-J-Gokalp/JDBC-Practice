package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicList {

    String dbURL = "jdbc:oracle:thin:@18.206.123.3:1521:XE";
    String dbUserName= "hr";
    String dbPassword= "hr";

    @Test
    public void test1() throws SQLException {

        Connection conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees where rownum<6");
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //list of maps keep all the data

        List<Map<String,Object>> queryData = new ArrayList<>();

        int columnCount = rsmd.getColumnCount();

        while (resultSet.next()){

            Map<String,Object> row = new HashMap<>();

            //some code to fill the dynamically

            for (int i = 1; i <= columnCount; i++) {

                row.put(rsmd.getColumnName(i),resultSet.getString(i));

            }


            //add ready map row to the list

            queryData.add(row);



        }


        for (Map<String, Object> queryDatum : queryData) {
            System.out.println(queryDatum);
        }


        conn.close();
        statement.close();
        resultSet.close();











    }








}
