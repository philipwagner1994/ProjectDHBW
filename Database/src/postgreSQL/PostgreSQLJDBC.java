package postgreSQL;
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
        /* String sql = "CREATE TABLE tablename " +
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
         /*String sql = "INSERT INTO tablename (ID,NAME,AGE,ADDRESS,SALARY) "
                 + "VALUES (5, 'Paul', 32, 'California', 2000.0012 );";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
         System.out.println("Records created successfully");*/
         
         String zahl="2";
         //Select Operation
         String sql2 = "SELECT "+
        		 		"*"+
        		 	"FROM "+
        		 		"tablename WHERE id='"+zahl+"';";
         ResultSet rs = stmt.executeQuery(sql2);
         while (rs.next()){
        	 //System.out.println(rs.getString("time"));
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
    	 if ( e.getMessage().contains( "ERROR: relation" ) && e.getMessage().contains( "already exists" )){
    		System.out.println("test"); 
    	 }
    	 System.out.println(e.getMessage());
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         System.exit(0);
       }
      // 
     }
   
   public static boolean create() {
	   Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://192.168.99.100:5432/pgdb", "pguser", "pguser");
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         
         //Create Operation
         String sqldata = "CREATE TABLE Data " +
                      "(ordernumber		TEXT PRIMARY KEY    NOT NULL," +
                      " timestamp      	DOUBLE PRECISION	NOT NULL," +
                      " overallstatus  	BOOLEAN				NOT NULL," +
                      " endtime        	DOUBLE PRECISION	NOT NULL," +
                      " starttime       DOUBLE PRECISION	NOT NULL," +
                      " customernumber	INTIGER				NOT NULL," +
                      " materialnumber	INTIGER				NOT NULL," +
                      " em1				DECIMAL				NOT NULL," +
                      " em2				DECIMAL				NOT NULL," +
                      " a1				DECIMAL				NOT NULL," +
                      " a2				DECIMAL				NOT NULL," +
                      " b1				DECIMAL				NOT NULL," +
                      " b2				DECIMAL				NOT NULL)";
         stmt.executeUpdate(sqldata);
         String sqlproperties = "CREATE TABLE Properties " +
                 "(id				SERIAL PRIMARY KEY 	NOT NULL," +
                 " ordernumber		TEXT					NOT NULL," +
                 " timestamp      	DOUBLE PRECISION    	NOT NULL, " +
                 " status		  	BOOLEAN     			NOT NULL, " +
                 " itemName        	TEXT					NOT NULL, " +
                 " value			DECIMAL					NOT NULL)";
         stmt.executeUpdate(sqlproperties);
         stmt.close();
         c.close();
         System.out.println("Table created successfully");  
         return true;
	   } 
       catch ( Exception e ) {
    	 if ( e.getMessage().contains( "ERROR: relation" ) && e.getMessage().contains( "already exists" )){
       		return true;
       	 }
    	 else{
    		 System.err.println( e.getClass().getName()+": "+ e.getMessage() );
    		 return false;
    	 }
	   }
   }
   
   public static boolean insert(String [] insertarray){
	   Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         String sqlinsert="";
         c = DriverManager
            .getConnection("jdbc:postgresql://192.168.99.100:5432/pgdb", "pguser", "pguser");
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         if(insertarray.length == 13){
        	 sqlinsert = "INSERT INTO Data (ordernumber, timestamp, overallstatus, endtime, starttime, customernumber, materialnumber, em1, em2, a1, a2, b1, b2) "
                     + "VALUES ("+insertarray[0]+", "+ insertarray[1]+", "+insertarray[2]+", "+ insertarray[3]+", "+insertarray[4]+", "+ insertarray[5]+", "+insertarray[6]+", "+ insertarray[7]+", "+insertarray[8]+", "+ insertarray[9]+", "+insertarray[10]+", "+ insertarray[11]+", "+insertarray[12]+");";
         }
         else if(insertarray.length == 5){
        	 sqlinsert = "INSERT INTO Properties (ordernumber, timestamp, status, itemName, value) "
                     + "VALUES ("+insertarray[0]+", "+insertarray[1]+", "+insertarray[2]+", "+insertarray[3]+", "+insertarray[4]+");";
         }

         stmt.executeUpdate(sqlinsert);
         stmt.close();
         c.commit();
         c.close();  
         return true;
       } 
       catch ( Exception e ) {
         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
         return false;
       }

   }
   
   public static String [][] select(int entries, String function, int materialnumber, int customernumber){
	   Connection c = null;
       Statement stmt = null;
       String [][] resultarray;
       try {
    	   Class.forName("org.postgresql.Driver");
    	   c = DriverManager.getConnection("jdbc:postgresql://192.168.99.100:5432/pgdb", "pguser", "pguser");
    	   System.out.println("Opened database successfully");
    	   stmt = c.createStatement();         
    	   if (function == "millinghistory"){
    		   resultarray= new String [entries][6];
    		   String sqlselect="";
    		   if (materialnumber == -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		"(itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT')"+
	    				   	" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		" (itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT') AND"+
	    				   		" (customernumber = "+customernumber+")"+
		    				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT') AND"+
			   					" (materialnumber = "+materialnumber+")"+
			   				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
			   					" ordernumber,"+
			   					" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'MILLING_SPEED' OR itemNAme = 'MILLING_HEAT') AND"+
			   					" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
			   				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   int i=1;
    		   int j=0;
    		   while (rs.next() && j < entries ){
    			   resultarray[j][0] = rs.getString("ordernumber");
    			   resultarray[j][1] = rs.getString("customernumber");
    			   resultarray[j][2] = rs.getString("materialnumber");
    			   resultarray[j][3] = rs.getString("timestamp");
    			   if(rs.getString("itemName") == "MILLING_SPEED"){
        			   resultarray[j][4] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName") == "MILLING_HEAT"){
        			   resultarray[j][5] = rs.getString("value");
    			   }
    			   if (i%2 == 0) {
    				   j++;
    			   }
    			   i++;
    		   }
    		   rs.close();
    	       stmt.close();
    	       c.close();
    	       return resultarray;
    	   }
    	   else if (function == "drillinghistory"){
    		   resultarray= new String [entries][6];
    		   String sqlselect="";
    		   if (materialnumber == -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		"(itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT')"+
		    				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		" (itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT') AND"+
	    				   		" (customernumber = "+customernumber+")"+
		    				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT') AND"+
			   					" (materialnumber = "+materialnumber+")"+
		    				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
			   					" ordernumber,"+
			   					" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'DRILLING_SPEED' OR itemNAme = 'DRILLING_HEAT') AND"+
			   					" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
		    				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   int i=1;
    		   int j=0;
    		   while (rs.next() && j < entries ){
    			   resultarray[j][0] = rs.getString("ordernumber");
    			   resultarray[j][1] = rs.getString("customernumber");
    			   resultarray[j][2] = rs.getString("materialnumber");
    			   resultarray[j][3] = rs.getString("timestamp");
    			   if(rs.getString("itemName") == "DRILLING_SPEED"){
        			   resultarray[j][4] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName") == "DRILLING_HEAT"){
        			   resultarray[j][5] = rs.getString("value");
    			   }
    			   if (i%2 == 0) {
    				   j++;
    			   }
    			   i++;
    		   }
    		   rs.close();
    	       stmt.close();
    	       c.close();
    	       return resultarray;
    	   }
    	   else if (function == "errorhistory"){
    		   resultarray= new String [entries][2];
    		   String sqlselect="";
    		   if (materialnumber == -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" COUNT(*) AS NumberOfErrors,"+
	    				   		" to_char(to_timestamp(1195374767),'HH24:MI YYYY-MM-DD' )AS timestamp,"+ 
	    				   	" FROM"+
	    				   		" data"+
	    				   	" WHERE "+
	    				   		"(overallstatus='NOK')"+
	    				   	" GROUP BY"+
	    				   		"to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )"+
			   				" ORDER BY"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
   				   				" COUNT(*) AS NumberOfErrors,"+
   				   				" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )AS timestamp,"+ 
   				   			" FROM"+
   				   				" data"+
	    				   	" WHERE"+
	    				   		" (overallstatus='NOK') AND"+
	    				   		" (customernumber = "+customernumber+")"+
		    				" GROUP BY"+
	    				   		"to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )"+
			   				" ORDER BY"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
    			   sqlselect= "SELECT"+
				   				" COUNT(*) AS NumberOfErrors,"+
				   				" to_char(to_timestamp(1195374767),'HH24:MI YYYY-MM-DD' )AS timestamp,"+ 
				   			" FROM"+
				   				" data"+
				   			" WHERE"+
				   				" (overallstatus='NOK') AND"+
				   				" (materialnumber = "+materialnumber+")"+
				   			" GROUP BY"+
				   				"to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )"+
				   			" ORDER BY"+
		   						" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
    			   sqlselect= "SELECT"+
			   				" COUNT(*) AS NumberOfErrors,"+
			   				" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )AS timestamp,"+ 
			   			" FROM"+
			   				" data"+
			   			" WHERE"+
			   				" (overallstatus='NOK') AND"+
			   				" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
			   			" GROUP BY"+
			   				"to_char(to_timestamp(1195374767),'HH24:MI YYYY-MM-DD' )"+
			   			" ORDER BY"+
	   						" timestamp;";
    		   }
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   int i=0;
    		   while (rs.next() && i < entries){
    			   resultarray[i][0] = rs.getString("NumberOfErrors");
    			   resultarray[i][1] = rs.getString("timestamp");
    			   i++;
    		   }
    		   rs.close();
    	       stmt.close();
    	       c.close();
    	       return resultarray;
    	   }
    	   else if (function == "runtimehistory"){
    		   resultarray= new String [entries][5];
    		   String sqlselect="";
    		   if (materialnumber == -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(starttime),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" starttime,"+
	    				   		" endtime"+
	    				   	" FROM"+
	    				   		" data"+
			   				" ORDER BY"+
			   					" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(starttime),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" starttime,"+
	    				   		" endtime"+
	    				   	" FROM"+
	    				   		" data"+
	    				   	" WHERE"+
	    				   		" (customernumber = "+customernumber+")"+
			   				" ORDER BY"+
			   					" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
		   				   		" ordernumber,"+
		   				   		" customernumber,"+
		   				   		" materialnumber,"+
		   				   		" to_char(to_timestamp(starttime),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
		   				   		" starttime,"+
		   				   		" endtime"+
			   				" FROM"+
			   					" data"+
			   				" WHERE"+
			   					" (materialnumber = "+materialnumber+")"+
			   				" ORDER BY"+
			   					" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
		   				   		" ordernumber,"+
		   				   		" customernumber,"+
		   				   		" materialnumber,"+
		   				   		" to_char(to_timestamp(starttime),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
		   				   		" starttime,"+
		   				   		" endtime"+
			   				" FROM"+
			   					" data"+
			   				" WHERE"+
			   					" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
			   				" ORDER BY"+
			   					" ordernumber,"+
			   					" timestamp;";
    		   }
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   int i=0;
    		   while (rs.next() && i < entries){
    			   resultarray[i][0] = rs.getString("ordernumber");
    			   resultarray[i][1] = rs.getString("customernumber");
    			   resultarray[i][2] = rs.getString("materialnumber");
    			   resultarray[i][3] = rs.getString("timestamp");
    			   int runtime = rs.getInt("endtime") - rs.getInt("starttime");
    			   resultarray[i][4] = Integer.toString(runtime);
    			   i++;
    		   }
    		   rs.close();
    	       stmt.close();
    	       c.close();
    	       return resultarray;
    	   }
    	   else if (function == "detailhistory"){
    		   resultarray= new String [entries][15];
    		   String sqlselect="";
    		   if (materialnumber == -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" overalltatus,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		"(itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT' OR itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT' )"+
		    				" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
		   				   		" ordernumber,"+
		   				   		" customernumber,"+
		   				   		" materialnumber,"+
		   				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
		   				   		" overalltatus,"+
		   				   		" em1,"+
		   				   		" em2,"+
		   				   		" a1,"+
		   				   		" a2,"+
		   				   		" b1,"+
		   				   		" b2,"+
		   				   		" itemName,"+
		   				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		" (itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT' OR itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT' ) AND"+
	    				   		" (customernumber = "+customernumber+")"+
	    				   		" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
		   				   		" ordernumber,"+
		   				   		" customernumber,"+
		   				   		" materialnumber,"+
		   				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
		   				   		" overalltatus,"+
		   				   		" em1,"+
		   				   		" em2,"+
		   				   		" a1,"+
		   				   		" a2,"+
		   				   		" b1,"+
		   				   		" b2,"+
		   				   		" itemName,"+
		   				   		" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT' OR itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT' ) AND"+
			   					" (materialnumber = "+materialnumber+")"+
			   					" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
		   				   		" ordernumber,"+
		   				   		" customernumber,"+
		   				   		" materialnumber,"+
		   				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
		   				   		" overalltatus,"+
		   				   		" em1,"+
		   				   		" em2,"+
		   				   		" a1,"+
		   				   		" a2,"+
		   				   		" b1,"+
		   				   		" b2,"+
		   				   		" itemName,"+
		   				   		" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT' OR itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT' ) AND"+
			   					" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
			   					" GROUP BY"+
	    				   		" ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' )AS timestamp,"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" ordernumber,"+
			   					" timestamp;";
    		   }
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   int i=0;
    		   int j=0;
    		   while (rs.next() && i < entries){
    			   resultarray[j][0] = rs.getString("ordernumber");
    			   resultarray[j][1] = rs.getString("customernumber");
    			   resultarray[j][2] = rs.getString("materialnumber");
    			   resultarray[j][3] = rs.getString("timestamp");
    			   resultarray[j][4] = rs.getString("overallstatus");
    			   resultarray[j][5] = rs.getString("em1");
    			   resultarray[j][6] = rs.getString("em2");
    			   resultarray[j][7] = rs.getString("a1");
    			   resultarray[j][8] = rs.getString("a2");
    			   resultarray[j][9] = rs.getString("b1");
    			   resultarray[j][10] = rs.getString("b2");
    			   if(rs.getString("itemName") == "DRILLING_SPEED"){
        			   resultarray[j][11] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName") == "DRILLING_HEAT"){
        			   resultarray[j][12] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName") == "MILLING_SPEED"){
    				   resultarray[j][13] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName") == "MILLING_HEAT"){
    				   resultarray[j][14] = rs.getString("value");
    			   }
    			   if (i%4 == 0) {
    				   j++;
    			   }
    			   i++;
    		   }
    		   rs.close();
    	       stmt.close();
    	       c.close();
    	       return resultarray;
    	   }
    	   String [][] test= new String [1][1];
    	   test[0][0]="sollte nie verwendet werden";
    	   return test;
       }
       catch ( Exception e ) {
           System.err.println( e.getClass().getName()+": "+ e.getMessage() );
           String [][] error= new String [1][1];
    	   error[0][0]=e.getMessage();
    	   return error;
           //System.exit(0);

       }
	   

   }
   
}