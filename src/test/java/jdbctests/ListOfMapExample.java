package jdbctests;

import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {

    String dbURL = "jdbc:oracle:thin:@18.206.123.3:1521:XE";
    String dbUserName= "hr";
    String dbPassword= "hr";


    @Test
    public void test1() throws SQLException {

        //creating list for keep all the rows maps

        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();

        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id", "AD_PRES");


        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("last_name","Kochhar");
        row2.put("salary",17000);
        row2.put("job_id", "AD_VP");

        System.out.println(row2);


        queryData.add(row1);
        queryData.add(row2);

        System.out.println(queryData);

        //get the steven lastname directly from the list

        System.out.println(queryData.get(0).get("last_name"));

    }




    @Test
    public void test2() throws SQLException {
        Connection conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees where rownum<6");

        //in order to get column names we need resultsetmetadata

        ResultSetMetaData rsmd = resultSet.getMetaData();

        //move to first row
        resultSet.next();

        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();

        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4));


        System.out.println("row1 = " + row1);

        //for the second row
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();
        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println(row2);


        queryData.add(row1);
        queryData.add(row2);

        System.out.println(queryData);
















        conn.close();
        statement.close();
        resultSet.close();





    }

}
