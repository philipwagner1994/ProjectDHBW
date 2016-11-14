package Data_collector.data_collector.Database;
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class PostgreSQLJDBC {
   public static void main( String args[] )
     {
       Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://192.168.99.100:5432/pgdb", "pguser", "pguser");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         
         //Create Operation
         /*String sql = "CREATE TABLE tablename " +
                      "(ID INT PRIMARY KEY     NOT NULL," +
                      " NAME           TEXT    NOT NULL, " +
                      " AGE            INT     NOT NULL, " +
                      " ADDRESS        CHAR(50), " +
                      " SALARY         REAL)";
         stmt.executeUpdate(sql);
         stmt.close();
         c.close();
         System.out.println("Table created successfully");*/
         
         // Insert Operation
        /* String sql = "INSERT INTO tablename (ID,NAME,AGE,ADDRESS,SALARY) "
                 + "VALUES (1, 'Paul', 32, 'California', 20000.00 );";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
         System.out.println("Records created successfully");*/
         
         
         //Select Operation
         ResultSet rs = stmt.executeQuery( "SELECT * FROM tablename;" );
         while (rs.next()){
        	 int id = rs.getInt("id");
             String  name = rs.getString("name");
             int age  = rs.getInt("age");
             String  address = rs.getString("address");
             float salary = rs.getFloat("salary");
             System.out.println( "ID = " + id );
             System.out.println( "NAME = " + name );
             System.out.println( "AGE = " + age );
             System.out.println( "ADDRESS = " + address );
             System.out.println( "SALARY = " + salary );
             System.out.println();
         }
         rs.close();
         stmt.close();
         c.close();
         
         //Update Operation
         /*String sql = "UPDATE tablename set SALARY = 25000.00 where ID=1;";
         stmt.executeUpdate(sql);
         c.commit();
         stmt.close();
         c.close();*/
         
         //Delete Operation
         /*String sql = "DELETE from tablename where ID=2;";
         stmt.executeUpdate(sql);
         c.commit();
         stmt.close();
         c.close();
         System.out.println("Operation done successfully")*/
         
       } catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
      // 
     }
}