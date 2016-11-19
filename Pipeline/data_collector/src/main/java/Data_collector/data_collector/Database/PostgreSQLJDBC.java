package Data_collector.data_collector.Database;
import java.sql.*;


public class PostgreSQLJDBC {
	

   public static void main( String args[] )
     {
       
	   /*String [] array= {"8141c1e8-d161-401f-ae2a-2c27201848ed", "1479291883", "FALSE", "14781905092390", "1478190498239", "4711", "7134", "65.0363364871295", "89.1031266124599", "9.022445317143458", "3.789427033200252", "586.7867895824678", "337.6497967279204"};
	   boolean create = insert(array);
	   System.out.println(create);*/
	   /*String [] array= {"8141c1e8-d161-401f-ae2a-2c27201848ed", "1479291850", "TRUE", "MILLING_HEAT", "160"};
	   boolean create = insert(array);
	   System.out.println(create);*/
	   /*String [] array= {"8141c1e8-d161-401f-ae2a-2c35601848ab", "1479291950", "FALSE", "14781905092390", "1478190498239", "4712", "7135", "65.0363364871295", "89.1031266124599", "9.022445317143458", "3.789427033200252", "586.7867895824678", "337.6497967279204"};
	   boolean create = insert(array);
	   System.out.println(create);*/
	   /*String [] array= {"8141c1e8-d161-401f-ae2a-2c35601848ab", "1479291960", "TRUE", "MILLING_HEAT", "184.713"};
	   boolean create = insert(array);
	   System.out.println(create);*/
	   //String [][] array = select(1, "millinghistory", -1, -1);
	   //System.out.println(array[0][0]);
	   Connection c = null;
       Statement stmt = null;
       try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager
            .getConnection("jdbc:postgresql://192.168.99.100:32782/pgdb", "pguser", "pguser");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         /*String sqlproperties = "CREATE TABLE Properties " +
                 "(id				SERIAL PRIMARY KEY 	NOT NULL," +
                 " ordernumber		TEXT					NOT NULL," +
                 " timestamp      	DOUBLE PRECISION    	NOT NULL, " +
                 " status		  	BOOLEAN     			NOT NULL, " +
                 " itemName        	TEXT					NOT NULL, " +
                 " value			DECIMAL				NOT NULL)";
         stmt.executeUpdate(sqlproperties);
         stmt.close();
         c.commit();
         c.close();*/
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
                 + "VALUES (11, 'Paul', 32, 'California', 2000.0012 );";
         stmt.executeUpdate(sql);
         stmt.close();
         c.commit();
         c.close();
         System.out.println("Records created successfully");*/
         
         
         
         //Select Operation
         /*String sql2 = "SELECT "+
        		 		"*"+
        		 	"FROM "+
        		 		"data;";
         ResultSet rs = stmt.executeQuery(sql2);
         while (rs.next()){
        	 //System.out.println(rs.getString("time"));
			 String ordernumber = rs.getString("ordernumber");
			 String customernumber = rs.getString("customernumber");
			 String materialnumber = rs.getString("materialnumber");
			 String timestamp = rs.getString("timestamp");
			 String overallstatus = rs.getString("overallstatus");
			 String em1 = rs.getString("em1");
			 String em2 = rs.getString("em2");
			 String a1 = rs.getString("a1");
			 String a2 = rs.getString("a2");
			 String b1 = rs.getString("b1");
			 String b2 = rs.getString("b2");
			 String starttime = rs.getString("starttime");
			 String endtime = rs.getString("endtime");
             System.out.println( "ordernumber = " + ordernumber );
             System.out.println( "customernumber = " + customernumber );
             System.out.println( "materialnumber = " + materialnumber );
             System.out.println( "timestamp = " + timestamp );
             System.out.println( "overallstatus = " + overallstatus );
             System.out.println( "em1 = " + em1 );
             System.out.println( "em2 = " + em2 );
             System.out.println( "a1 = " + a1 );
             System.out.println( "a2 = " + a2 );
             System.out.println( "b1 = " + b1 );
             System.out.println( "b2 = " + b2 );
             System.out.println( "starttime = " + starttime );
             System.out.println( "endtime = " + endtime );
             System.out.println();
         }
         rs.close();
         stmt.close();*/
         //c.close();
         
         /*String sql2 = "SELECT "+
 		 		" AVG(value) AS value, ordernumber"+
 		 	" FROM "+
 		 		"Properties WHERE itemName = 'MILLING_SPEED'  AND ordernumber = '2a6344cb-4a40-4189-b29c-cfcfe7d7a67d' group by ordernumber order by ordernumber;";
         ResultSet rs = stmt.executeQuery(sql2);
         while (rs.next()){
        	 //String id = rs.getString("id");
        	 String ordernumber = rs.getString("ordernumber");
        	 //String timestamp = rs.getString("timestamp");
        	 //String status = rs.getString("status");
        	 //String itemName = rs.getString("itemName");
        	 String value = rs.getString("value");
        	 //System.out.println( "id = " + id );
        	 System.out.println( "ordernumber = " + ordernumber );
        	 //System.out.println( "timestamp = " + timestamp );
        	 //System.out.println( "status = " + status );
        	 //System.out.println( "itemName = " + itemName );
        	 System.out.println( "value = " + value );
         }*/
         String sql2 = "SELECT "+
  		 		"*"+
  		 	" FROM "+
  		 		"Properties WHERE itemName = 'MILLING_SPEED'  AND ordernumber = '2a6344cb-4a40-4189-b29c-cfcfe7d7a67d' order by ordernumber;";
          ResultSet rs = stmt.executeQuery(sql2);
          while (rs.next()){
         	 String id = rs.getString("id");
         	 String ordernumber = rs.getString("ordernumber");
         	 String timestamp = rs.getString("timestamp");
         	 String status = rs.getString("status");
         	 String itemName = rs.getString("itemName");
         	 String value = rs.getString("value");
         	 System.out.println( "id = " + id );
         	 System.out.println( "ordernumber = " + ordernumber );
         	 System.out.println( "timestamp = " + timestamp );
         	 System.out.println( "status = " + status );
         	 System.out.println( "itemName = " + itemName );
         	 System.out.println( "value = " + value );
          }
         
         //Update Operation
         /*String sql = "UPDATE tablename set SALARY = 25000.00 where ID=1;";
         stmt.executeUpdate(sql);
         c.commit();
         stmt.close();
         c.close();*/
         
         //Delete Operation
         /*String sql = "DROP TABLE Properties;";
         stmt.executeUpdate(sql);
         c.commit();
         stmt.close();
         c.close();
         System.out.println("Operation done successfully");*/
         
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
            .getConnection("jdbc:postgresql://192.168.99.100:32782/pgdb", "pguser", "pguser");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");

         stmt = c.createStatement();
         
         //Create Operation
         String sqldata = "CREATE TABLE Data " +
                      "(ordernumber		TEXT PRIMARY KEY    NOT NULL," +
                      " timestamp      	DOUBLE PRECISION	NOT NULL," +
                      " overallstatus  	BOOLEAN				NOT NULL," +
                      " endtime        	DOUBLE PRECISION	NOT NULL," +
                      " starttime       DOUBLE PRECISION	NOT NULL," +
                      " customernumber	INT					NOT NULL," +
                      " materialnumber	INT					NOT NULL," +
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
         c.commit();
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
            .getConnection("jdbc:postgresql://192.168.99.100:32782/pgdb", "pguser", "pguser");
         c.setAutoCommit(false);
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         if(insertarray.length == 13){
        	 sqlinsert = "INSERT INTO Data (ordernumber, timestamp, overallstatus, endtime, starttime, customernumber, materialnumber, em1, em2, a1, a2, b1, b2) "
                     + "VALUES ('"+insertarray[0]+"', "+ insertarray[1]+", "+insertarray[2]+", "+ insertarray[3]+", "+insertarray[4]+", "+ insertarray[5]+", "+insertarray[6]+", "+ insertarray[7]+", "+insertarray[8]+", "+ insertarray[9]+", "+insertarray[10]+", "+ insertarray[11]+", "+insertarray[12]+");";
         }
         else if(insertarray.length == 5){
        	 sqlinsert = "INSERT INTO Properties (ordernumber, timestamp, status, itemName, value) "
                     + "VALUES ('"+insertarray[0]+"', "+insertarray[1]+", "+insertarray[2]+", '"+insertarray[3]+"', "+insertarray[4]+");";
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
    	   c = DriverManager.getConnection("jdbc:postgresql://192.168.99.100:32782/pgdb", "pguser", "pguser");
    	   System.out.println("Opened database successfully");
    	   stmt = c.createStatement();         
    	   if (function == "millinghistory"){
    		   resultarray= new String [entries][6];
    		   String sqlselect="";
    		   if (materialnumber == -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" data.ordernumber AS ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		"(itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT')"+
	    				   	" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
	    				   		" data.ordernumber AS ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		" (itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT') AND"+
	    				   		" (customernumber = "+customernumber+")"+
		    				" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" data.ordernumber AS ordernumber,"+
	    				   		" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'MILLING_SPEED' OR itemName = 'MILLING_HEAT') AND"+
			   					" (materialnumber = "+materialnumber+")"+
			   				" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
			   					" data.ordernumber AS ordernumber,"+
			   					" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'MILLING_SPEED' OR itemNAme = 'MILLING_HEAT') AND"+
			   					" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
			   				" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   int i=1;
    		   int j=0;
    		   while (rs.next() && j < entries ){
    			   resultarray[j][0] = rs.getString("ordernumber");
    			   resultarray[j][1] = rs.getString("customernumber");
    			   resultarray[j][2] = rs.getString("materialnumber");
    			   resultarray[j][3] = rs.getString("timestamp");
    			   if(rs.getString("itemName").equals("MILLING_SPEED")){
        			   resultarray[j][4] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName").equals("MILLING_HEAT")){
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
	    				   		" data.ordernumber AS ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		"(itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT')"+
		    				" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
	    				   		" data.ordernumber AS ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
	    				   		" itemName,"+
	    				   		" AVG(value) AS value"+
	    				   	" FROM"+
	    				   		" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
	    				   	" WHERE"+
	    				   		" (itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT') AND"+
	    				   		" (customernumber = "+customernumber+")"+
		    				" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
			   					" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
	    				   		" data.ordernumber AS ordernumber,"+
	    				   		" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'DRILLING_SPEED' OR itemName = 'DRILLING_HEAT') AND"+
			   					" (materialnumber = "+materialnumber+")"+
		    				" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
			   					" data.ordernumber AS ordernumber,"+
			   					" customernumber,"+
			   					" materialnumber,"+
			   					" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD') AS timestamp,"+
			   					" itemName,"+
			   					" AVG(value) AS value"+
			   				" FROM"+
			   					" data INNER JOIN properties ON (data.ordernumber = properties.ordernumber)"+
			   				" WHERE"+
			   					" (itemName = 'DRILLING_SPEED' OR itemNAme = 'DRILLING_HEAT') AND"+
			   					" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
		    				" GROUP BY"+
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD'),"+
	    				   		" itemName"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   int i=1;
    		   int j=0;
    		   while (rs.next() && j < entries ){
    			   resultarray[j][0] = rs.getString("ordernumber");
    			   resultarray[j][1] = rs.getString("customernumber");
    			   resultarray[j][2] = rs.getString("materialnumber");
    			   resultarray[j][3] = rs.getString("timestamp");
    			   if(rs.getString("itemName").equals("DRILLING_SPEED")){
        			   resultarray[j][4] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName").equals("DRILLING_HEAT")){
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
	    				   		" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )AS timestamp"+ 
	    				   	" FROM"+
	    				   		" data"+
	    				   	" WHERE "+
	    				   		"(overallstatus='FALSE')"+
	    				   	" GROUP BY"+
	    				   		" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )"+
			   				" ORDER BY"+
			   					" timestamp DESC;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
   				   				" COUNT(*) AS NumberOfErrors,"+
   				   				" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )AS timestamp"+ 
   				   			" FROM"+
   				   				" data"+
	    				   	" WHERE"+
	    				   		" (overallstatus='FALSE') AND"+
	    				   		" (customernumber = "+customernumber+")"+
		    				" GROUP BY"+
	    				   		" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )"+
			   				" ORDER BY"+
			   					" timestamp DESC;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
    			   sqlselect= "SELECT"+
				   				" COUNT(*) AS NumberOfErrors,"+
				   				" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )AS timestamp"+ 
				   			" FROM"+
				   				" data"+
				   			" WHERE"+
				   				" (overallstatus='FALSE') AND"+
				   				" (materialnumber = "+materialnumber+")"+
				   			" GROUP BY"+
				   				" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )"+
				   			" ORDER BY"+
		   						" timestamp DESC;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
    			   sqlselect= "SELECT"+
			   				" COUNT(*) AS NumberOfErrors,"+
			   				" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )AS timestamp"+ 
			   			" FROM"+
			   				" data"+
			   			" WHERE"+
			   				" (overallstatus='FALSE') AND"+
			   				" ((materialnumber = "+materialnumber+") AND (customernumber = "+customernumber+"))"+
			   			" GROUP BY"+
			   				" to_char(to_timestamp(starttime),'HH24:MI YYYY-MM-DD' )"+
			   			" ORDER BY"+
	   						" timestamp DESC;";
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
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
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
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
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
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
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
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
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
	    				   		" data.ordernumber AS ordernumber,"+
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
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' ),"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber == -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
		   				   		" data.ordernumber AS ordernumber,"+
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
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' ),"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber != -1 && customernumber == -1){
	    		   sqlselect= "SELECT"+
		   				   		" data.ordernumber AS ordernumber,"+
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
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' ),"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
    		   }
    		   else if (materialnumber != -1 && customernumber != -1){
	    		   sqlselect= "SELECT"+
		   				   		" data.ordernumber AS ordernumber,"+
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
	    				   		" data.ordernumber,"+
	    				   		" customernumber,"+
	    				   		" materialnumber,"+
	    				   		" to_char(to_timestamp(data.timestamp),'HH24:MI:SS YYYY-MM-DD' ),"+
	    				   		" itemName,"+
	    				   		" em1,"+
	    				   		" em2,"+
	    				   		" a1,"+
	    				   		" a2,"+
	    				   		" b1,"+
	    				   		" b2"+
			   				" ORDER BY"+
				   				" timestamp DESC,"+
			   					" data.ordernumber;";
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
    			   if(rs.getString("itemName").equals("DRILLING_SPEED")){
        			   resultarray[j][11] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName").equals("DRILLING_HEAT")){
        			   resultarray[j][12] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName").equals("MILLING_SPEED")){
    				   resultarray[j][13] = rs.getString("value");
    			   }
    			   else if(rs.getString("itemName").equals("MILLING_HEAT")){
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
    	   else if (function == "liveupdate"){
    		   resultarray= new String [3][];
    		   resultarray[0]=new String[3];
    		   resultarray[1]=new String[3];
    		   resultarray[2]=new String[2];
    		   String sqlselect= "SELECT"+
  				   		" ordernumber,"+
  				   		" itemName,"+
  				   		" AVG(value) AS value"+
	   				" FROM"+
	   					" properties"+
	   				" WHERE"+
	   					" (itemName = 'MILLING_SPEED')"+
	   				" GROUP BY"+
				   		" data.ordernumber,"+

	   				" ORDER BY"+
		   				" timestamp DESC,"+
	   				" limit1;";
    		   ResultSet rs = stmt.executeQuery( sqlselect );
    		   resultarray[0][0]=rs.getString("ordernumber");
    		   resultarray[0][1]=rs.getString("value");
    		   sqlselect= "SELECT"+
 				   		" ordernumber,"+
 				   		" itemName,"+
 				   		" AVG(value) AS value"+
	   				" FROM"+
	   					" properties"+
	   				" WHERE"+
	   					" (itemName = 'MILLING_HEAT')"+
	   				" GROUP BY"+
				   		" data.ordernumber,"+

	   				" ORDER BY"+
		   				" timestamp DESC,"+
	   				" limit1;";
   		   rs = stmt.executeQuery( sqlselect );
   		   resultarray[0][2]=rs.getString("value");
		   sqlselect= "SELECT"+
				   		" ordernumber,"+
				   		" itemName,"+
				   		" AVG(value) AS value"+
  				" FROM"+
  					" properties"+
  				" WHERE"+
  					" (itemName = 'DRILLING_SPEED')"+
  				" GROUP BY"+
			   		" data.ordernumber,"+

  				" ORDER BY"+
	   				" timestamp DESC,"+
  				" limit1;";
		   rs = stmt.executeQuery( sqlselect );
		   resultarray[1][0]=rs.getString("ordernumber");
		   resultarray[1][1]=rs.getString("value");
		   sqlselect= "SELECT"+
			   		" ordernumber,"+
			   		" itemName,"+
			   		" AVG(value) AS value"+
  				" FROM"+
  					" properties"+
  				" WHERE"+
  					" (itemName = 'DRILLING_HEAT')"+
  				" GROUP BY"+
			   		" data.ordernumber,"+

  				" ORDER BY"+
	   				" timestamp DESC,"+
  				" limit1;";
		   rs = stmt.executeQuery( sqlselect );
		   resultarray[1][2]=rs.getString("value");
		   sqlselect= "SELECT"+
			   		" ordernumber,"+
			   		" starttime,"+
			   		" endtime"+
			   	" FROM"+
			   		" data"+
  				" ORDER BY"+
	   				" timestamp DESC,"+
  					" data.ordernumber;";
		   resultarray[2][0]=rs.getString("ordernumber");
		   resultarray[2][1]=Integer.toString(rs.getInt("endtime")-rs.getInt("starttime"));
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