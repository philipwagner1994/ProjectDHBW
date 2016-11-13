package Data_collector.data_collector.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.json.JSONObject;
import org.json.simple.JSONArray;

public class postgresql {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    public void postgressql(){
    	try{
    	// This will load the MySQL driver, each DB has its own driver
        Class.forName("org.postgresql.Driver");
        // Setup the connection with the DB
        this.connect = DriverManager.getConnection("jdbc:postgresql://localhost:3307/feedback", "user", "password");
    	}catch(Exception e){System.out.println(e);}
    	
    }
    
    public String readMessages(String[] filters) {
    	try{

    	ResultSet resultSet = null;
    	PreparedStatement preparedStatement = null;
    	statement = connect.createStatement();
    	 preparedStatement = connect
                 .prepareStatement("SELECT * from dhbw.messages");
 resultSet = preparedStatement.executeQuery();
      	JSONArray json = new JSONArray();
      	JSONObject jo = new JSONObject();
              // ResultSet is initially before the first data set
              while (resultSet.next()) {
                      // It is possible to get the columns via name
                      // also possible to get the columns via the column number
                      // which starts at 1
                      // e.g. resultSet.getSTring(2);
              		jo.put("customerNumber", resultSet.getString(1));
              		jo.put("materialNumber", resultSet.getString(1));

              		json.add(jo);
              }
     	return json.toString();
    	}catch(Exception e){
    		
    		return null;
    	}
    	
    }    
    
    public String readItems(String[] filters){
    	try{
    	ResultSet resultSet = null;
    	PreparedStatement preparedStatement = null;
    	 preparedStatement = connect
                 .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
 resultSet = preparedStatement.executeQuery();
      	JSONArray json = new JSONArray();
      	JSONObject jo = new JSONObject();
              // ResultSet is initially before the first data set
              while (resultSet.next()) {
                      // It is possible to get the columns via name
                      // also possible to get the columns via the column number
                      // which starts at 1
                      // e.g. resultSet.getSTring(2);
              		jo.put("customerNumber", resultSet.getString(1));
              		jo.put("materialNumber", resultSet.getString(1));

              		json.add(jo);
              }
     	return json.toString();
    	}catch(Exception e){
    		
    		return null;
    	}
    }   
    
    public String readBla(String[] filters){
    	try{
    	ResultSet resultSet = null;
    	PreparedStatement preparedStatement = null;
    	 preparedStatement = connect
                 .prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
    	 resultSet = preparedStatement.executeQuery();
     	JSONArray json = new JSONArray();
     	JSONObject jo = new JSONObject();
             // ResultSet is initially before the first data set
             while (resultSet.next()) {
                     // It is possible to get the columns via name
                     // also possible to get the columns via the column number
                     // which starts at 1
                     // e.g. resultSet.getSTring(2);
             		jo.put("customerNumber", resultSet.getString(1));
             		jo.put("materialNumber", resultSet.getString(1));

             		json.add(jo);
             }
    	return json.toString();
    	}catch(Exception e){
    		
    		return null;
    	}
    }   
    
    public void insertMessages(String s){
    	try{
    		System.out.println("Teskjadhkjashads");
    	ResultSet resultSet = null;
    	PreparedStatement preparedStatement = null;
        // PreparedStatements can use variables and are more efficient
        preparedStatement = connect
                        .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
        // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
        // Parameters start with 1
        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "TestEmail");
        preparedStatement.setString(3, "TestWebpage");
        preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
        preparedStatement.setString(5, "TestSummary");
        preparedStatement.setString(6, "TestComment");
        preparedStatement.executeUpdate();
    	} catch(Exception e){
    		
    	}
    	
    }
    
    public void insertItem(String s){
    	try{
    	ResultSet resultSet = null;
    	PreparedStatement preparedStatement = null;
        // PreparedStatements can use variables and are more efficient
        preparedStatement = connect
                        .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
        // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
        // Parameters start with 1
        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "TestEmail");
        preparedStatement.setString(3, "TestWebpage");
        preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
        preparedStatement.setString(5, "TestSummary");
        preparedStatement.setString(6, "TestComment");
        preparedStatement.executeUpdate();
    	} catch(Exception e){
    		
    	}
    }
    
    public void insertBla(String s){
    	try{
    	ResultSet resultSet = null;
    	PreparedStatement preparedStatement = null;
        // PreparedStatements can use variables and are more efficient
        preparedStatement = connect
                        .prepareStatement("insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
        // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
        // Parameters start with 1
        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "TestEmail");
        preparedStatement.setString(3, "TestWebpage");
        preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
        preparedStatement.setString(5, "TestSummary");
        preparedStatement.setString(6, "TestComment");
        preparedStatement.executeUpdate();
    	} catch(Exception e){
    		
    	}
    }
    
    public void deleteMessage(String s){
    	try{
        	ResultSet resultSet = null;
        	PreparedStatement preparedStatement = null;
            // Remove again the insert comment
            preparedStatement = connect
            .prepareStatement("delete from feedback.comments where myuser= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();
    	}catch(Exception e){
    		
    	}
    }
    
    public void deleteItem(String s){
    	try{
        	ResultSet resultSet = null;
        	PreparedStatement preparedStatement = null;
            // Remove again the insert comment
            preparedStatement = connect
            .prepareStatement("delete from feedback.comments where myuser= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();
    	}catch(Exception e){
    		
    	}
    }
    
    public void deleteBla(String s){
    	try{
        	ResultSet resultSet = null;
        	PreparedStatement preparedStatement = null;
            // Remove again the insert comment
            preparedStatement = connect
            .prepareStatement("delete from feedback.comments where myuser= ? ; ");
            preparedStatement.setString(1, "Test");
            preparedStatement.executeUpdate();
    	}catch(Exception e){
    		
    	}
    }
    
    

    private void writeMetaData(ResultSet resultSet) throws SQLException {
            //         Now get some metadata from the database
            // Result set get the result of the SQL query

            System.out.println("The columns in the table are: ");

            System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
            for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
                    System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
            }
    }


    // You need to close the resultSet
    private void close() {
            try {
                    if (resultSet != null) {
                            resultSet.close();
                    }

                    if (statement != null) {
                            statement.close();
                    }

                    if (connect != null) {
                            connect.close();
                    }
            } catch (Exception e) {

            }
    }

}
