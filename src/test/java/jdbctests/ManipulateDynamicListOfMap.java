package jdbctests;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManipulateDynamicListOfMap {
    String dbURL = "jdbc:oracle:thin:@54.237.206.21.3:1521:XE";
    String dbUserName= "hr";
    String dbPassword= "hr";

    @Test
    public void test1() throws SQLException {

        Connection conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        Statement statement = conn.createStatement(); //ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY
        ResultSet resultSet = statement.executeQuery("SELECT * from employees");

        // in order to get columns name we need ResultSetMetaData
        ResultSetMetaData rsmd = resultSet.getMetaData();

        //list of maps keep all the data
        List<Map<String,Object>> queryData = new ArrayList<>();

        //number of columns
        int columnCount = rsmd.getColumnCount();


        //loop through each row
        //To implement everything in 1 row & next row & so on-> use while loops then inside while loops create 1 map
        while (resultSet.next()){

            Map<String,Object> row = new HashMap<>();

            //some code to fill the dynamically
            //I getEach column name&value putInsideThe mapIUse 1 loop inside while loop.It startsFromColumn 1 to columnCount(4)
            for (int i = 1; i <= columnCount; i++) {

                row.put(rsmd.getColumnName(i),resultSet.getObject(i));     //<getColumn & VALUE Assign 2 maps

            }

            //add ready map row to the list below. to execute & put inside above queryData list
            queryData.add(row);

        }

        // For each loop to print the list and to put everything into the map
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
//2bring data to ourside
// to use inf later then it contain some UI LATER FROM UI & is easy to save inf & easy to match and after we done
// with connect put the inf from dataBase inside list of maps. To instruct the data We put inside the list of maps
// from 1 row I give each value pair inside one map. 1st above row create a list of maps put Steve & 4 the 2rd
// row put different name so each ro must be separate map
