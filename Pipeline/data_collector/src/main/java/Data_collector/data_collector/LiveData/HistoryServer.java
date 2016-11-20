package Data_collector.data_collector.LiveData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.io.PrintWriter;
import java.io.PrintWriter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import Data_collector.data_collector.Database.PostgreSQLJDBC;

public class HistoryServer extends Thread{
	private 	JSONArray json = new JSONArray();
	private JSONObject jo = new JSONObject();
	private String sdata, output;
	private PostgreSQLJDBC sql;
	private boolean newDataDrilling = false;
	private boolean newDataMilling = false;
	
	private static final Vector<String>server  = new Vector<String>();
	private static final Vector<JSONObject>serverMS  = new Vector<JSONObject >();
	private static final Vector<JSONObject >serverMH  = new Vector<JSONObject >();
	private static final Vector<JSONObject >serverDS  = new Vector<JSONObject >();
	private static final Vector<JSONObject >serverDH  = new Vector<JSONObject >();
	
	public HistoryServer(){
		//this.sql = sql;
	}
	
    public static void main(String[] argv) throws Exception {


    }
    
    public void run(){
    	System.out.println("HistoryServer");
    	try{
            ServerSocket s = new ServerSocket(9887);
            System.out.println("HistoryServer started");
            int test=0;
            while (true) {
            	test++;
                Socket t = s.accept();// wait for client to connect
                System.out.println("server connected");
                BufferedReader in = new BufferedReader(new InputStreamReader(t.getInputStream()));
                String serverResponse = in.readLine();
                System.out.println("Server-Antwort: " + serverResponse);
                
                PrintWriter out = new PrintWriter(t.getOutputStream(), true);
                //out.println("grr grr roger hier Server");
                
                output = getData(serverResponse);
                
                StringBuilder builder = new StringBuilder();

                builder.append("HTTP/1.1 200 OK\r\n");
                builder.append("Access-Control-Allow-Origin: *\n");
                builder.append("Content-Type: text/html; charset=utf-8\r\n");
                builder.append("Content-Length:" + output.length() + "\r\n\r\n");
                builder.append(output);
                out.write(builder.toString());
                out.flush();
                in.close();
                out.close();
                t.close();
                System.out.println("Runde:" + test);
            }
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
    }
            
        
        public String getData(String request){
        	

        	String[] hilf = request.split(" ");
        	String[] hilf2 = hilf[1].split("\\?");
        	String[] hilf3 = hilf2[1].split("\\&");
        	String[] hilf4 = hilf3[0].split("=");
        	String[][] result;
        	//JSONObject AnswerJSON = new JSONObject();
        	switch(hilf4[1]){
        	case "history_drilling":
        		String entries = hilf3[1].split("=")[1];
        		int materialNo = -1;
        		if(hilf3[2].split("=")[1].equals("null") == false){
        		materialNo  = Integer.parseInt(hilf3[2].split("=")[1]);
        		}
        		int customerNo = -1;
        		if(hilf3[2].split("=")[1].equals("null")== false){
        		customerNo =  Integer.parseInt(hilf3[4].split("=")[1]);
        		}
        		result = PostgreSQLJDBC.select(Integer.parseInt(entries), "drillinghistory",materialNo ,customerNo );
    			try{
    			for(int i = 0; i<Integer.parseInt(entries);i++){
    				JSONObject AnswerJSON = new JSONObject();
        		 AnswerJSON.put("orderno",result[i][0]);
        		 AnswerJSON.put("customerno", result[i][1]); 
        		 AnswerJSON.put("materialno", result[i][2]);
        		 AnswerJSON.put("timestamp", result[i][3]);
        		 if(result[i][4]== null){
        			 AnswerJSON.put("speed", "0"); 
        		 } else if(result[i][4].length()>7){
        		 AnswerJSON.put("speed", result[i][4].substring(0,7));
        		 }else{
        			 AnswerJSON.put("speed", result[i][4]); 
        		 }
        		 if(result[i][5]== null){
        			 AnswerJSON.put("temp",  "0");
        		 }else if( result[i][5].length()>7){
        			 
        		 AnswerJSON.put("temp",  result[i][5].substring(0,7));
        		 }else{
        			 AnswerJSON.put("temp",  result[i][5]);
        		 }
       			json.add(AnswerJSON);
        		}
        		String answer = json.toString();
        		json.clear();
        		
        		return answer;
    			}catch(Exception e){
    				System.out.println(e);
    				return "null";
    			}
        	case "history_milling":
        		//System.out.println("READ HISTORY MILLING");
        		String entries1 = hilf3[1].split("=")[1];
        		int materialNo1 = -1;
        		if(hilf3[2].split("=")[1].equals("null") == false){
        		materialNo1  = Integer.parseInt(hilf3[2].split("=")[1]);
        		}
        		int customerNo1 = -1;
        		if(hilf3[2].split("=")[1].equals("null")== false){
        		customerNo1 =  Integer.parseInt(hilf3[4].split("=")[1]);
        		}
        		System.out.println(Integer.parseInt(entries1)+ "millinghistory"+materialNo1 +customerNo1 );
        		result = PostgreSQLJDBC.select(Integer.parseInt(entries1), "millinghistory",materialNo1 ,customerNo1 );
    			
    			try{
    			for(int i = 0; i<Integer.parseInt(entries1) ;i++){
    			JSONObject AnswerJSON = new JSONObject();
    			System.out.println(result[i][0]);
        		 AnswerJSON.put("orderno",result[i][0]);
        		 AnswerJSON.put("customerno", result[i][1]); 
        		 AnswerJSON.put("materialno", result[i][2]);
        		 AnswerJSON.put("timestamp", result[i][3]);
        		 System.out.println(result[i][4]);
     
        		 if(result[i][4]== null){
        			 AnswerJSON.put("speed", "0"); 
        		 } else if(result[i][4].length()>7){
        		 AnswerJSON.put("speed", result[i][4].substring(0,7));
        		 }else{
        			 AnswerJSON.put("speed", result[i][4]); 
        		 }
        		 if(result[i][5]== null){
        			 AnswerJSON.put("temp",  "0");
        		 }else if( result[i][5].length()>7){
        			 
        		 AnswerJSON.put("temp",  result[i][5].substring(0,7));
        		 }else{
        			 AnswerJSON.put("temp",  result[i][5]);
        		 }
          		System.out.println(AnswerJSON.toString());
       			json.add(AnswerJSON);
        		}
        		String answer = json.toString();
        		json.clear();
        		
        		return answer;
    			}catch(Exception e){
    				System.out.println(e);
    				return "null";
    			}
        	case "history_details":
        		String entries2 = hilf3[1].split("=")[1];
        		int materialNo2 = -1;
        		if(hilf3[2].split("=")[1].equals("null") == false){
        		materialNo2  = Integer.parseInt(hilf3[2].split("=")[1]);
        		}
        		int customerNo2 = -1;
        		if(hilf3[2].split("=")[1].equals("null")== false){
        		customerNo1 =  Integer.parseInt(hilf3[4].split("=")[1]);
        		}
        		System.out.println(Integer.parseInt(entries2)+ "detailhistory"+materialNo2 +customerNo2 );
        		result = PostgreSQLJDBC.select(Integer.parseInt(entries2), "detailhistory",materialNo2 ,customerNo2 );
    			
    			try{
    			for(int i = 0; i<Integer.parseInt(entries2) ;i++){
    			JSONObject AnswerJSON = new JSONObject();
    			System.out.println(result[i][0]);
        		 AnswerJSON.put("orderno",result[i][0]);
        		 AnswerJSON.put("customerno", result[i][1]); 
        		 AnswerJSON.put("materialno", result[i][2]);
        		 AnswerJSON.put("timestamp", result[i][3]);
        		 if(result[i][4].equals("t")){
        			 AnswerJSON.put("overallstatus", "OK");
        		 }else{
        			 AnswerJSON.put("overallstatus", "NOT OK");
        		 }
        		 AnswerJSON.put("em1", result[i][5]);
        		 AnswerJSON.put("em2", result[i][6]);
        		 AnswerJSON.put("a1", result[i][7]);
        		 AnswerJSON.put("a2", result[i][8]);
        		 AnswerJSON.put("b1", result[i][9]);
        		 AnswerJSON.put("b2", result[i][10]);
        		 AnswerJSON.put("drillingspeed", result[i][11]);//.substring(0,8));
        		 AnswerJSON.put("drillingtemp", result[i][12]);//.substring(0,6));
        		 AnswerJSON.put("millingspeed", result[i][13]);//.substring(0,7));
        		 AnswerJSON.put("millingtemp", result[i][14]);//.substring(0,6));
          		System.out.println(AnswerJSON.toString());
       			json.add(AnswerJSON);
        		}
        		String answer = json.toString();
        		json.clear();
        		
        		return answer;
    			}catch(Exception e){
    				System.out.println(e);
    				return "null";
    			}
    			
        	case "history_runtime":
        		String entries3 = hilf3[1].split("=")[1];
        		int materialNo3 = -1;
        		if(hilf3[2].split("=")[1].equals("null") == false){
        		materialNo1  = Integer.parseInt(hilf3[2].split("=")[1]);
        		}
        		int customerNo3 = -1;
        		if(hilf3[2].split("=")[1].equals("null")== false){
        		customerNo3 =  Integer.parseInt(hilf3[4].split("=")[1]);
        		}
        		System.out.println(Integer.parseInt(entries3)+ "runtimeistory"+materialNo3 +customerNo3 );
        		result = PostgreSQLJDBC.select(Integer.parseInt(entries3), "runtimehistory",materialNo3 ,customerNo3 );
    			
    			try{
    			for(int i = 0; i<Integer.parseInt(entries3) ;i++){
    			JSONObject AnswerJSON = new JSONObject();
    			System.out.println(result[i][0]);
        		 AnswerJSON.put("orderno",result[i][0]);
        		 AnswerJSON.put("customerno", result[i][1]); 
        		 AnswerJSON.put("materialno", result[i][2]);
        		 AnswerJSON.put("timestamp", result[i][3]);
        		 AnswerJSON.put("runtime", result[i][4]);
        		 //AnswerJSON.put("starttime", result[i][4]); 
        		 //AnswerJSON.put("endtime",  result[i][5]);
          		System.out.println(AnswerJSON.toString());
       			json.add(AnswerJSON);
        		}
        		String answer = json.toString();
        		json.clear();
        		
        		return answer;
    			}catch(Exception e){
    				System.out.println(e);
    				return "null";
    			}
    			
        	case "history_errors":
        		String entries4 = hilf3[1].split("=")[1];
        		int materialNo4 = -1;
        		if(hilf3[2].split("=")[1].equals("null") == false){
        		materialNo4  = Integer.parseInt(hilf3[2].split("=")[1]);
        		}
        		int customerNo4 = -1;
        		if(hilf3[2].split("=")[1].equals("null")== false){
        		customerNo4 =  Integer.parseInt(hilf3[4].split("=")[1]);
        		}
        		System.out.println(Integer.parseInt(entries4)+ "errorhistory"+materialNo4 +customerNo4 );
        		result = PostgreSQLJDBC.select(Integer.parseInt(entries4), "errorhistory",materialNo4 ,customerNo4 );
    			
    			try{
    			for(int i = 0; i<Integer.parseInt(entries4) ;i++){
    				if(result[i][0]!= null){
    					JSONObject AnswerJSON = new JSONObject();
    					System.out.println(result[i][0]);
    					AnswerJSON.put("Errors",result[i][0]);
    					AnswerJSON.put("timestamp", result[i][1]); 
    					System.out.println(AnswerJSON.toString());
    					json.add(AnswerJSON);
    				}
        		}
        		String answer = json.toString();
        		json.clear();
        		
        		return answer;
    			}catch(Exception e){
    				System.out.println(e);
    				return "null";
    			}
    			
        		default:
        			return "Nothing found";
        	}
        	
        }
        public String getPseudoData(String request)throws Exception{
        	String[] hilf = request.split(" ");
        	String[] hilf2 = hilf[1].split("\\?");
        	String[] hilf3 = hilf2[1].split("=");
        	
        	switch(hilf3[1]){
        	case "history_drilling":
        		boolean helpSpeed1= true;
        		int helpInt1 = 0;
        		if(serverDH.size()>10){
        			helpInt1 = serverDH.size()-10;
        		}
        		for(int i=helpInt1; i<serverDH.size(); i++){
        			JSONObject AnswerJSON = new JSONObject();
        			JSONObject hilfJson = serverDH.get(i);
        			AnswerJSON.put("orderno",hilfJson.get("OrderNum"));
        			AnswerJSON.put("customerno",hilfJson.get("CustomerNum"));
        			AnswerJSON.put("materialno",hilfJson.get("Value1"));
        			AnswerJSON.put("temp",hilfJson.get("Value3"));
        			if(helpInt1<serverDS.size()){
        				if(helpSpeed1){
        					AnswerJSON.put("speed", serverDS.get(helpInt1).get("Value3"));
        					helpSpeed1 =false;
        					
        				}else{
        					AnswerJSON.put("speed", serverDS.get(helpInt1).get("Value3"));
        					helpSpeed1 =true;
        					helpInt1++;
        				}
        				
        			}else{
        				AnswerJSON.put("speed","null");
        			}
        			json.add(AnswerJSON);
        		}
        		String answer = json.toString();
        		json.clear();
        		return answer;
        		//return sql.readMessages(hilf);
        	case "history_milling":
        		System.out.println("READ HISTORY MILLING");
        		boolean helpSpeed= true;
        		int helpInt =0;
        		if(serverMH.size()>10){
        			helpInt = serverMH.size()-10;
        		}
        		for(int i=helpInt; i<serverMH.size(); i++){
        			JSONObject AnswerJSON = new JSONObject();
        			JSONObject hilfJson = serverMH.get(i);
        			AnswerJSON.put("orderno",hilfJson.get("OrderNum"));
        			AnswerJSON.put("customerno",hilfJson.get("CustomerNum"));
        			AnswerJSON.put("materialno",hilfJson.get("Value1"));
        			AnswerJSON.put("temp",hilfJson.get("Value3"));
        			
        			if(helpInt<serverMS.size()){
        				if(helpSpeed){
        					AnswerJSON.put("speed", serverMS.get(helpInt).get("Value3"));
        					helpSpeed =false;
        					
        				}else{
        					AnswerJSON.put("speed", serverMS.get(helpInt).get("Value3"));
        					helpSpeed =true;
        					helpInt++;
        				}
        				
        			}else{
        				AnswerJSON.put("speed","null");
        			}
        			json.add(AnswerJSON);
        		}
        		String answerMilling = json.toString();
        		json.clear();
        		System.out.println(answerMilling);
        		return answerMilling;
        		//return sql.readMessages(hilf);
        	case "live_drilling":
        		if(newDataDrilling == false){
        			return "null";
        		}else{
        			JSONObject AnswerJSON = new JSONObject();
        			JSONObject hilfJson = serverDH.get(serverDH.size()-1);
        			AnswerJSON.put("orderno",hilfJson.get("OrderNum"));
        			AnswerJSON.put("customerno",hilfJson.get("CustomerNum"));
        			AnswerJSON.put("materialno",hilfJson.get("Value1"));
        			AnswerJSON.put("temp",hilfJson.get("Value3"));
    				AnswerJSON.put("speed", serverDS.get(serverDS.size()-1).get("Value3"));
        			json.add(AnswerJSON);
        		}
        		newDataDrilling = false;
        		String answerLiveD = json.toString();
        		json.clear();
        		return answerLiveD;
        		
        		
        		
        	case "live_milling":
        		if(newDataMilling == false){
        			return "null";
        		}else{
        			JSONObject AnswerJSON = new JSONObject();
        			JSONObject hilfJson = serverMH.get(serverMH.size()-1);
        			AnswerJSON.put("orderno",hilfJson.get("OrderNum"));
        			AnswerJSON.put("customerno",hilfJson.get("CustomerNum"));
        			AnswerJSON.put("materialno",hilfJson.get("Value1"));
        			AnswerJSON.put("temp",hilfJson.get("Value3"));
    				AnswerJSON.put("speed", serverMS.get(serverMS.size()-1).get("Value3"));
        			json.add(AnswerJSON);
        		}
        		String answerLiveM = json.toString();
        		json.clear();
        		newDataMilling = false;
        		return answerLiveM;
        		default:
        			return "null";
        	}
        	
        }
      
        public void setPseudoData(String[] data) throws Exception{  
        	JSONObject jo = new JSONObject();
        	switch(data[3]){

        	case "MILLING_HEAT":
            	jo.put("OrderNum", data[0]);
            	jo.put("CustomerNum", data[1]);
            	jo.put("Value1", data[2]);
            	jo.put("Item", data[3]);
            	jo.put("Value2", data[4]);
            	jo.put("Value3", data[5]);
            	json.add(jo);
            	//sdata = json.toString();
            	serverMH.add(jo);
            	json.clear();
            	newDataMilling = true;
        		break;
        		
        	case "MILLING_SPEED":
            	jo.put("OrderNum", data[0]);
            	jo.put("CustomerNum", data[1]);
            	jo.put("Value1", data[2]);
            	jo.put("Item", data[3]);
            	jo.put("Value2", data[4]);
            	jo.put("Value3", data[5]);
            	json.add(jo);
            	//sdata = json.toString();
            	serverMS.add(jo);
            	json.clear();
            	newDataMilling = true;
        		break;
        	case "DRILLING_HEAT":
            	jo.put("OrderNum", data[0]);
            	jo.put("CustomerNum", data[1]);
            	jo.put("Value1", data[2]);
            	jo.put("Item", data[3]);
            	jo.put("Value2", data[4]);
            	jo.put("Value3", data[5]);
            	json.add(jo);
            	//sdata = json.toString();
            	serverDH.add(jo);
            	newDataDrilling = true;
            	json.clear();
        		break;
        	case "DRILLING_SPEED":
            	jo.put("OrderNum", data[0]);
            	jo.put("CustomerNum", data[1]);
            	jo.put("Value1", data[2]);
            	jo.put("Item", data[3]);
            	jo.put("Value2", data[4]);
            	jo.put("Value3", data[5]);
            	json.add(jo);
            	//sdata = json.toString();
            	serverDS.add(jo);
            	newDataDrilling = true;
            	json.clear();
        		break;
        	}

        }
        
       }

