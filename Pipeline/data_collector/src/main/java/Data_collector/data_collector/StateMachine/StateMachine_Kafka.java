package Data_collector.data_collector.StateMachine;

import java.util.Vector;

import org.apache.log4j.BasicConfigurator;
import org.json.JSONObject;
import org.json.simple.JSONArray;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.oxo42.stateless4j.delegates.Action;

import Data_collector.data_collector.DataCollector.KafkaMessage;
import Data_collector.data_collector.DataCollector.SpectralAnalysis;
import Data_collector.data_collector.LiveData.LiveServer;
//zu Thread machen
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;


public class StateMachine_Kafka {
	private StateMachine<State, Trigger> producePart;
	private String[] activemq;
	private  Vector<KafkaMessage> messages = new Vector<>();
	private LiveServer server; 
	private SpectralAnalysis spectral;// = new SpectralAnalysis();
	private String[] spectralData;
	private 	JSONArray json = new JSONArray();
	private Producer<String, String> producer;
	
	public StateMachine_Kafka(LiveServer server, SpectralAnalysis spectral){
		try{
		this.producePart = producePartMachine();
		this.server = server;
		this.spectral = spectral;
		System.out.println("added Server");
		}catch(Exception e){
			
		}
	}
	
	public StateMachine_Kafka(LiveServer server, SpectralAnalysis spectral, Producer<String,String>producer){
		try{
		this.producePart = producePartMachine();
		this.server = server;
		this.spectral = spectral;
		this.producer = producer;
		System.out.println("added Server");
		}catch(Exception e){
			
		}
	}
	
    public StateMachine<State, Trigger> producePartMachine() throws Exception {
        StateMachineConfig<State, Trigger> producePartConfig = new StateMachineConfig<>();
        
        producePartConfig.configure(State.Start)
        .permit(Trigger.L1false, State.L1);
        
        producePartConfig.configure(State.L1)
                .permit(Trigger.L1true, State.L1toL2);

        producePartConfig.configure(State.L1toL2)
                .permit(Trigger.L2false, State.L2);
        
        producePartConfig.configure(State.L2)
        .permit(Trigger.L2true, State.L2toL3);
        
        producePartConfig.configure(State.L2toL3)
        .permit(Trigger.L3false, State.Milling);
        
        producePartConfig.configure(State.Milling)
        .onEntry(this::startMilling)
        .onExit(this::stopMilling)
        .permit(Trigger.L3true, State.L3toL4);
        
        producePartConfig.configure(State.L3toL4)
        .permit(Trigger.L4false, State.Drilling);
        
        producePartConfig.configure(State.Drilling)
        .onEntry(this::startDrilling)
        .onExit(this::stopDrilling)
        .permit(Trigger.L4true, State.L4toL5);
        
        producePartConfig.configure(State.L4toL5)
        .permit(Trigger.L5false, State.L5);
        
        producePartConfig.configure(State.L5)
        .permit(Trigger.L5true, State.Done);
        
        producePartConfig.configure(State.Done)
        .onEntry(this::startDone);
        // ...
        StateMachine<State, Trigger> producePart = new StateMachine<>(State.Start, producePartConfig);
        return producePart;

        //producePart.fire(Trigger.L1true);
    }
    private void startDone() {
    	try{
    		System.out.println("Starting spectral");
    	JSONArray json2 = new JSONArray();
    	JSONObject jo = new JSONObject();
    	
    	spectralData = spectral.getSpectralAnalysis();
    	KafkaMessage message;
    	for(int i=0; i< messages.size(); i++){
    		JSONObject jo2 = new JSONObject();
    		message = messages.get(i);
    		jo2.put("value", message.getValue());
    		jo2.put("itemName", message.getItemName());
    		jo2.put("status", message.getStatus());
    		jo2.put("timestamp", message.getTimestamp());
    		json2.add(jo2);
    	}
    	jo.put("customerNumber", activemq[0]);
    	jo.put("materialNumber", activemq[1]);
    	jo.put("orderNumber", activemq[2]);
    	jo.put("timestampERP", activemq[3]);
    	jo.put("em1", spectralData[0]);
    	jo.put("em2", spectralData[1]);
    	jo.put("a1", spectralData[2]);
    	jo.put("a2", spectralData[3]);
    	jo.put("b2", spectralData[4]);
    	jo.put("b1", spectralData[5]);
    	jo.put("overalStatus", spectralData[6]);
    	jo.put("ts_start", spectralData[7]);
    	jo.put("ts_stop", spectralData[8]);
    	jo.put("Messages",json2 );

    	json.add(jo);
		KeyedMessage<String, String> data = new KeyedMessage<String, String>("allData", json.toString());	 
		producer.send(data);
    	System.out.println("Done with all"+json);
    	}catch(Exception e){
    		System.out.println(e);
    	}
    }
    
    private void stopMilling() {
        // ...
    }

    private void startMilling() {
        // ...
    }
    
    private void stopDrilling() {
        // ...
    }

    private void startDrilling() {
        // ...
    }
    
    public String handleMessage(KafkaMessage message) throws Exception{
    	String hilf = message.getItemName()+message.getValue();
    	switch(hilf){
    		case "L1true":
    			if(producePart.canFire(Trigger.L1true)){
    			producePart.fire(Trigger.L1true);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L1true";
    			}
    			return "nothing";
    		case "L1false":
    			if(producePart.canFire(Trigger.L1false)){
    			producePart.fire(Trigger.L1false);
    			messages.add(message);
    			
    			System.out.println("TEST1");
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			System.out.println("TEST2");
    			server.setData(data);
    			return"L1false";
		}
		return "nothing";
    		case "L2true":
    			if(producePart.canFire(Trigger.L2true)){
    			producePart.fire(Trigger.L2true);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L2true";
	}
	return "nothing";
    		case "L2false":
    			if(producePart.canFire(Trigger.L2false)){
    			producePart.fire(Trigger.L2false);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L2false";
}
return "nothing";
    		case "L3true":
    			if(producePart.canFire(Trigger.L3true)){
    			producePart.fire(Trigger.L3true);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L3true";
			}
			return "nothing";
    		case "L3false":
    			if(producePart.canFire(Trigger.L3false)){
    			producePart.fire(Trigger.L3false);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L3false";
			}
			return "nothing";
    		case "L4true":
    			if(producePart.canFire(Trigger.L4true)){
    			producePart.fire(Trigger.L4true);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L4true";
			}
			return "nothing";
    		case "L4false":
    			if(producePart.canFire(Trigger.L4false)){
    			producePart.fire(Trigger.L4false);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L4false";
			}
			return "nothing";
    		case "L5true":
    			if(producePart.canFire(Trigger.L5true)){
        			messages.add(message);
        			
        			String[] data = new String[6];
        			data[0] = activemq[2];
        			data[1] = activemq[0];
        			data[2] = activemq[1];
        			data[3] = message.getItemName();
        			data[4] = message.getStatus();
        			data[5] = message.getValue();
        			server.setData(data);
        			producePart.fire(Trigger.L5true);

        			return"done";
    			}
    			return "nothing";
    		case "L5false":
    			if(producePart.canFire(Trigger.L5false)){
    			producePart.fire(Trigger.L5false);
    			messages.add(message);
    			
    			String[] data = new String[6];
    			data[0] = activemq[2];
    			data[1] = activemq[0];
    			data[2] = activemq[1];
    			data[3] = message.getItemName();
    			data[4] = message.getStatus();
    			data[5] = message.getValue();
    			server.setData(data);
    			return"L5false";
    			}
    			return "nothing";
    			
    		default:	
    			if(producePart.canFire(Trigger.L3true) ){
    				if(message.getItemName().equals("MILLING_SPEED")|| message.getItemName().equals("MILLING")||message.getItemName().equals("MILLING_HEAT")){
    	    			messages.add(message);
    	    			
    	    			String[] data = new String[6];
    	    			data[0] = activemq[2];
    	    			data[1] = activemq[0];
    	    			data[2] = activemq[1];
    	    			data[3] = message.getItemName();
    	    			data[4] = message.getStatus();
    	    			data[5] = message.getValue();
    	    			server.setData(data);
    					return "milling";
    				}
    			}else if(producePart.canFire(Trigger.L4true)){
      				if(message.getItemName().equals("DRILLING_SPEED")|| message.getItemName().equals("DRILLING")||message.getItemName().equals("DRILLING_HEAT")){
      	    			messages.add(message);
      	    			
      	    			String[] data = new String[6];
      	    			data[0] = activemq[2];
      	    			data[1] = activemq[0];
      	    			data[2] = activemq[1];
      	    			data[3] = message.getItemName();
      	    			data[4] = message.getStatus();
      	    			data[5] = message.getValue();
      	    			server.setData(data);
      					return "drilling";
        			}
    			}
    			return "nothing";
    	}
    	
    }
    

    public String[] getActivemq() {
		return activemq;
	}
	public void setActivemq(String[] activemq) {
		System.out.println("ACTIVE");
		this.activemq = activemq;
	}


	private enum State {
        Start, Drilling, Milling, L1, L1toL2, L2, L2toL3,L3toL4, L4toL5, L5, Done

    }
    private enum Trigger {
        L1true, L1false, L2true, L2false, L3true,L3false, L4true,L4false, L5false, L5true

}
	
}