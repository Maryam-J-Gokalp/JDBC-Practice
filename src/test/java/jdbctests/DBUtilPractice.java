package jdbctests;
import org.junit.jupiter.api.Test;
import utilities.DBUtiL;
import java.util.List;
import java.util.Map;


public class DBUtilPractice {


    @Test
    public void test1(){

        //create connection &  create same query inside the map
        DBUtiL.createConnection();
        String query = "SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <6";

        //print the result use for each loop
        List<Map<String, Object>> queryData = DBUtiL.getQueryResultMap(query);
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());

        }
        // After create connection & print the list u need to close connection with destroy()-->destroy();
        DBUtiL.destroy();

    }
    @Test
    public void test2(){
        // If u use one row inside the mp
        //create connection &  create same query inside the map and return you one map
        DBUtiL.createConnection();
        String query = "SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <2";

        //get one row ()
        Map<String, Object> rowMap = DBUtiL.getRowMap(query);
        System.out.println(rowMap.toString());


        //print the result use for each loop
        List<Map<String, Object>> queryData = DBUtiL.getQueryResultMap(query);
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());

        }
        // After create connection & print the list u need to close connection with destroy()-->destroy();
        DBUtiL.destroy();
    }

}

//JDBC --> I need to create a () get the inf inside the other strutura e depois fecha a minha connecao
// if you have 1 row inside the map
