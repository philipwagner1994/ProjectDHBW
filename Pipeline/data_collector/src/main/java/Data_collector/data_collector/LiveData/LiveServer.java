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
	private String output;
	private boolean setData = false;
	private boolean getData = false;
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
                t.setSoTimeout(6000);
                System.out.println("server connected");
                String serverResponse = null;
                BufferedReader in = new BufferedReader(new InputStreamReader(t.getInputStream()));
                try{
                    
                	serverResponse = in.readLine();
                	System.out.println("Server-Antwort: " + serverResponse);    
                }catch(Exception e){
                	System.out.println("Timeout? "+e);
                }
                PrintWriter out = new PrintWriter(t.getOutputStream(), true);
                if(serverResponse != null){
                try{	
            		String hilf = null;
                		while(hilf == null){
                			hilf = getData();
                		}
                		System.out.println(hilf);
                		
                	output = hilf;
                
                	}catch(Exception e){
                		System.out.println(e + "while getting Data");
                		output = "null";
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
        	if(getData == true){    
        		System.out.println("Add Data Skipped");
        		return;
        	}
        	setData = true;
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
        	jo.put("timestamp", data[6]);
        	json.clear();
        	json.add(jo);
        	setData = false;
        	System.out.println(json.toString());
        }
        
        public String getData(){
        	if(setData == true ){   
        		return null;
        	}
        	
        	String blubb = json.toString();
        	if(json.isEmpty()){
        		getData=false;
        		return null;
        	}
        	getData = true;
        	String nextdata = json.toString();
        	json.clear();
        	getData = false;
        	return nextdata;
        	
        }

  }

