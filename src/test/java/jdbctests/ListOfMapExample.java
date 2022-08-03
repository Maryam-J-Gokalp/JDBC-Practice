package jdbctests;

import org.junit.jupiter.api.Test;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListOfMapExample {

    String dbURL = "jdbc:oracle:thin:@54.237.206.21:1521:XE";
    String dbUserName= "hr";
    String dbPassword= "hr";

    @Test
    public void test1() throws SQLException {

        //creating list for keep all the rows maps in one table

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
        //print to get Steven last name
        System.out.println(queryData.get(0).get("last_name"));


    }
    // copy everything from the first part to the second part change the () and use result set

    @Test
    public void test2() throws SQLException {
        Connection conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet=statement.executeQuery("select first_name,last_name,salary,job_id from employees where rownum<6");

        //in order to get column names we need result set method data
        //First create one empty list of maps-->ResultSetMetaData rsmd = resultSet.getMetaData();
        //instead of use colum name I use index number

        ResultSetMetaData rsmd = resultSet.getMetaData();

        //move to first row information I use result set get string & colum number-->resultSet.getString(1));
        // for the second & other column same implementation but just change column number ex: from (1) to column (2)
// basically replace 1st column as Key-->row1.put(rsmd.getColumnName(1),&Second columnAsValue-->resultSet.getString(1));
        resultSet.next();

        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();

        row1.put(rsmd.getColumnName(1),resultSet.getString(1));
        row1.put(rsmd.getColumnName(2),resultSet.getString(2));
        row1.put(rsmd.getColumnName(3),resultSet.getString(3));
        row1.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println("row1 = " + row1);

        //for the second row some STRUTURA of column number is not change 4 the row just do same thing
        // in the next row
        resultSet.next();

        Map<String,Object> row2 = new HashMap<>();
        row2.put(rsmd.getColumnName(1),resultSet.getString(1));
        row2.put(rsmd.getColumnName(2),resultSet.getString(2));
        row2.put(rsmd.getColumnName(3),resultSet.getString(3));
        row2.put(rsmd.getColumnName(4),resultSet.getString(4));

        System.out.println(row2);

        //The Add rows one by one into my list.
        queryData.add(row1);
        queryData.add(row2);

        System.out.println(queryData);

        conn.close();
        statement.close();
        resultSet.close();

    }

}

// Next class create one configure to put one list of rows WHICH CREATE DIRECTLY  a list of maps
//Without changing anything will dynamically know the column & rows number is going to iterate & keep
// all the info inside the lists of maps