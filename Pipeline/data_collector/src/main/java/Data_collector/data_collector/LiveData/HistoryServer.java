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
	
	private static final Vector<String>server  = new Vector<String>();
	
	public HistoryServer(postgresql sql){
		this.sql = sql;
	}
	
    public static void main(String[] argv) throws Exception {


    }
    
    public void run(){
    	System.out.println("HistoryServer");
    	try{
            ServerSocket s = new ServerSocket(8765);
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
        
        
        public String getData(String request){
        	String[] hilf = request.split(" ");
        	switch(hilf[3]){
        	case "history-drilling":
        		return sql.readMessages(hilf);
        	case "history-milling":
        		return sql.readMessages(hilf);
        	case "live-drilling":
        		return sql.readMessages(hilf);
        	case "live-milling":
        		return sql.readMessages(hilf);
        		default:
        			return "Nothing found";
        	}
        	
        }
       }

