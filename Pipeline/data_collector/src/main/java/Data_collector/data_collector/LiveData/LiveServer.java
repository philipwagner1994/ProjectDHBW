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

public class LiveServer extends Thread{
	private JSONArray json = new JSONArray();
	private JSONObject jo = new JSONObject();
	private String sdata, output;
	private boolean newMilling = false;
	private boolean newDrilling = false;
	private JSONArray newMillingJSON = new JSONArray();
	private JSONArray newDrillingJSON = new JSONArray();
	//private static final Vector<String>server  = new Vector<String>();
	
	public LiveServer(){
	}
	
    public static void main(String[] argv) throws Exception {


    }
    
    public void run(){
    	System.out.println("Server");
    	try{
            ServerSocket s = new ServerSocket(1234);
            System.out.println("Server started");
            int test=0;
            while (true) {
            	try{
            	test++;
                Socket t = s.accept();// wait for client to connect
                System.out.println("server connected");
                BufferedReader in = new BufferedReader(new InputStreamReader(t.getInputStream()));
                String serverResponse = in.readLine();
                
                //System.out.println(in.readLine());
                System.out.println("Server-Antwort: " + serverResponse);
                
                PrintWriter out = new PrintWriter(t.getOutputStream(), true);
                if(serverResponse != null){
                	String[] requestArray = serverResponse.split(" ");
                	String[] hilf2 = requestArray[1].split("\\?");
                	String[] hilf3 = hilf2[1].split("\\&");
                	String[] hilf4 = hilf3[0].split("=");
                
                	if(hilf4[1].equals("live_milling")){
                		while(newMilling == false){
            			
            		}
                	output = newMillingJSON.toString();
            		newMillingJSON.clear();
            		newMilling = false;

                	}else if(hilf4[1].equals("live_drilling")){
                		while(newDrilling == false){
            			
            		}
            		output = newDrillingJSON.toString();
            		newDrillingJSON.clear();
            		newDrilling = false;
                	}else{
                		String hilf = null;;
                		while(hilf == null){
                			hilf = getData();
                		}
                		System.out.println(hilf);
                		output = hilf;
                	}
                }else{
                	output = "null";
                }
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
            }catch(Exception e){
            	System.out.println("Keep going: "+ e);
            }
            }
    	}
    	catch(Exception e){
    		System.out.println(e);
    	}
    }
    
        
        public void setData(String[] data) throws Exception{        
        //	if(server.size() >0){
        //		server.removeElement(0);
        //	}
        	json.clear();
        	System.out.println("ADD DATA");
        	jo.put("OrderNum", data[0]);
        	jo.put("CustomerNum", data[1]);
        	jo.put("Value1", data[2]);
        	jo.put("Item", data[3]);
        	jo.put("Value2", data[4]);
        	if(data[5].length()>6){
        		jo.put("Value3", data[5].substring(0,5));
        	}else{
        	jo.put("Value3", data[5]);
        	}
        	json.add(jo);
        	if(data[3].equals("MILLING_HEAT")|| data[3].equals("MILLING_SPEED")){
        		newMillingJSON = json;
        		newMilling = true;
        	}else if(data[3].equals("DRILLING_HEAT")|| data[3].equals("DRILLING_SPEED")){
        		newDrillingJSON = json;
        		newDrilling = true;
        	}
        	System.out.println(json.toString());
        	//sdata = json.toString();
        	//server.add(sdata);
        	//json.clear();
        }
        
        public String getData(){
        	//System.out.println(json.toString());
        	//if(server.size() == 0){
        	//	return null;
        	//}
        	if(json.isEmpty()){
        		return null;
        	}
        	String nextdata = json.toString();
        	json.clear();
        	return nextdata;
        	
        }
        }

