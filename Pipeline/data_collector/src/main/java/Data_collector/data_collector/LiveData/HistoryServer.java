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

import Data_collector.data_collector.Database.postgresql;

public class HistoryServer extends Thread{
	private 	JSONArray json = new JSONArray();
	private JSONObject jo = new JSONObject();
	private String sdata, output;
	private postgresql sql;
	private boolean newDataDrilling = false;
	private boolean newDataMilling = false;
	
	private static final Vector<String>server  = new Vector<String>();
	private static final Vector<JSONObject>serverMS  = new Vector<JSONObject >();
	private static final Vector<JSONObject >serverMH  = new Vector<JSONObject >();
	private static final Vector<JSONObject >serverDS  = new Vector<JSONObject >();
	private static final Vector<JSONObject >serverDH  = new Vector<JSONObject >();
	
	public HistoryServer(postgresql sql){
		this.sql = sql;
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
                
                output = getPseudoData(serverResponse);
                
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
        	String[] hilf3 = hilf2[1].split("=");
        	switch(hilf3[1]){
        	case "history_drilling":
        		return sql.readMessages(hilf);
        	case "history_milling":
        		System.out.println("READ HISTORY MILLING");
        		return sql.readMessages(hilf);
        	case "live_drilling":
        		return sql.readMessages(hilf);
        	case "live_milling":
        		return sql.readMessages(hilf);
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
        			return "Nothing found";
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

