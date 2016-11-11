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

public class LiveServer extends Thread{
	private 	JSONArray json = new JSONArray();
	private JSONObject jo = new JSONObject();
	private String sdata, output;
	
	private static final Vector<String>server  = new Vector<String>();
	
	public LiveServer(){

	}
	
    public static void main(String[] argv) throws Exception {
    	/*JSONArray json = new JSONArray();
		JSONObject jo = new JSONObject();
		jo.put("sensor", "L3");
		jo.put("value1", "30");
		jo.put("value2", "50");
		jo.put("OrderNum", "XC35");
		json.add(jo);
		String text = json.toString();
        ServerSocket s = new ServerSocket(1234);
        System.out.println("Server started");
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
            //out.println(json);
            String data = "ich bin super \n";

            StringBuilder builder = new StringBuilder();

            builder.append("HTTP/1.1 200 OK\r\n");
            builder.append("Access-Control-Allow-Origin: *\n");
            builder.append("Content-Type: text/html; charset=utf-8\r\n");
            builder.append("Content-Length:" + text.length() + "\r\n\r\n");
            builder.append(text);
            out.write(builder.toString());
            out.flush();
            in.close();
            out.close();
            t.close();
            System.out.println("Runde:" + test);
        }*/

    }
    
    public void run(){
    	System.out.println("Server");
    	try{
            ServerSocket s = new ServerSocket(12345);
            System.out.println("Server started");
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
                
                output = getData();
                
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
    		
    	}
    }
    
    public void sendData()throws Exception{

        ServerSocket s = new ServerSocket(1234);
        System.out.println("Server started");
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
            
            output = getData();
            
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
        
        public void setData(String[] data) throws Exception{        
        	System.out.println("ADD DATA");
        	jo.put("OrderNum", data[0]);
        	jo.put("CustomerNum", data[1]);
        	jo.put("Value1", data[2]);
        	jo.put("Item", data[3]);
        	jo.put("Value2", data[4]);
        	jo.put("Value3", data[5]);
        	json.add(jo);
        	sdata = json.toString();
        	server.add(sdata);
        	
        	json.clear();
        }
        
        public String getData(){
        	
        	while(server.size() == 0){

        	}
        	String nextdata = server.get(0);
        	server.remove(0);
        	return nextdata;
        	
        }
        }

