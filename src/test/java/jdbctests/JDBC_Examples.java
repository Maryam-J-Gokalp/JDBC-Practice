import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBC_Examples {

    String dbURL = "jdbc:oracle:thin:@54.237.206.21:1521:XE";
    String dbUserName= "hr";
    String dbPassword= "hr";
    String QUERY = "select * from departments";

    @Test
    public void test1()  {

        try{


            Connection conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY);


           /* resultSet.next();

            System.out.println(resultSet.getString(2));
            */
            //display departments table in 10 - Administration - 200 - 1700 format

            while (resultSet.next()){

                System.out.println(resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " +
                        resultSet.getInt(3) + " - " + resultSet.getInt(4));

            }

            //************************************----------------------***************------*************-----------


            resultSet.close();
            statement.close();
            conn.close();



        }catch (SQLException e){
            e.printStackTrace();
        }




    }


    @Test
    public void test2() throws SQLException {

        Connection conn = DriverManager.getConnection(dbURL,dbUserName,dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //hot to find how many rows we have for the query
        //Result set is to get the inf only
/*        int count =0;

        while (resultSet.next()){
            count++;

        }
        System.out.println(count);*/

        //2. method go to end get row number and print it.

        resultSet.last();

        int rowNumber = resultSet.getRow();

        System.out.println(rowNumber);

//----------------------****-------------------------****----------------------***-----------------**---------------*------------

        resultSet.beforeFirst();//after last method feito para voltar ao início

        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }

        resultSet.close();
        statement.close();
        conn.close();

    }



    @Test
    public void test3() throws SQLException {

        Connection conn = DriverManager.getConnection(dbURL, dbUserName, dbPassword);
        Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");


        // get the database related data inside the dbMetaData object

        DatabaseMetaData dbMetaData = conn.getMetaData();

        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseProductVersion() = " + dbMetaData.getDatabaseProductVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());

        /*
        *   dbMetaData.getUserName() = HR
            dbMetaData.getDatabaseProductName() = Oracle
            dbMetaData.getDatabaseProductVersion() = Oracle Database 11g Express Edition Release 11.2.0.2.0 - 64bit Production
            dbMetaData.getDriverName() = Oracle JDBC driver
            dbMetaData.getDriverVersion() = 21.1.0.0.0
            *
            * */


        //get the resultset metadata

        ResultSetMetaData rsMetaData = resultSet.getMetaData();

        //how many columns we have?
        System.out.println("rsMetaData.getColumnCount() = " + rsMetaData.getColumnCount());

        //remetaData.getColumnName(i)  --> gets column name
        //remetaData.getColumnCount(i)  --> total number of columns
        //get column name dynamically

        for (int i = 1; i <= rsMetaData.getColumnCount(); i++) { //column names starts with one ( 1 )

            System.out.print(rsMetaData.getColumnLabel(i)+"  ");
        }


        resultSet.close();
        statement.close();
        conn.close();


    }

}
