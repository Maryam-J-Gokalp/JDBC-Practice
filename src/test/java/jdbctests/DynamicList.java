package jdbctests;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicList {

    String dbURL = "jdbc:oracle:thin:@54.237.206.21.3:1521:XE";
    String dbUserName= "hr";
    String dbPassword= "hr";

    @Test
    public void test1() throws SQLException {

        Connection conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        Statement statement = conn.createStatement(); //ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
        ResultSet resultSet = statement.executeQuery("SELECT first_name,last_name,salary,job_id \n" +
                "from employees\n " +
                "where rownum <6");

        // in order to get columns name we need ResultSetMetaData
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //list of maps keep all the data
        List<Map<String,Object>> queryData = new ArrayList<>();



        int columnCount = rsmd.getColumnCount();

        //number of columns
        //To implement everything in 1 row & next row & so on-> use while loops then inside while loops create 1 mao
        while (resultSet.next()){
            Map<String,Object> row = new HashMap<>();

            //some code to fill the dynamically
            //ThenI getEach column name&value 2 putInside the map I use 1 loop inside while loop. It starts from1 columnCount
            for (int i = 1; i <= columnCount; i++) {
                row.put(rsmd.getColumnName(i),resultSet.getString(i));     //<getColumn & VALUE Assign 2 maps

            }
            //add ready map row to the list below. to execute & put inside above queryData list
            queryData.add(row);

        }

        // print each row inside the list
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }

        conn.close();
        statement.close();
        resultSet.close();



    }

}

// Next class create one configure to put one list of rows WHICH CREATE DIRECTLY  a list of maps
//Without changing anything will dynamically know the column & rows number is going to iterate & keep
// all the info inside the lists of maps

//WHY PUT THE INFORMATION INSIDE THE LIST OF MAPS?
// to use inf later is easy to save inf & easy to match and after we done with connect put the inf from
//dataBase inside list of maps. To instruct the data We put inside the list of maps from 1 row I give each value
//pair inside one map. 1st above row create a list of maps put Steve & 4 the 2rd row put different name so
//each ro must be separate map
