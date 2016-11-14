package Data_collector.data_collector.main;

import java.util.Properties;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.I0Itec.zkclient.ZkClient;
import org.apache.activemq.ActiveMQConnectionFactory;

import Data_collector.data_collector.DataCollector.*;
import Data_collector.data_collector.Database.postgresql;
import Data_collector.data_collector.LiveData.*;
import Data_collector.data_collector.StateMachine.StateMachine_Kafka;
import kafka.admin.AdminUtils;
import kafka.javaapi.producer.Producer;
import kafka.producer.ProducerConfig;
import kafka.utils.ZKStringSerializer$;

/**
 * Hello world!
 *
 */
public class App 
{	public static void main(String[] args){
    try {
    	
    	//create Kafka Producer to send collected Data
    	ProducerConfig config2;
		Properties props = new Properties();
		props.put("metadata.broker.list", "192.168.99.100:1000");
		props.put("serializer.class", "kafka.serializer.StringEncoder");
		props.put("partitioner.class", "Data_collector.data_collector.main.SimplePartitioner");
		props.put("request.required.acks", "1");
		 
		config2 = new ProducerConfig(props);
		try{
			ZkClient zkClient = new ZkClient("192.168.99.100:1001", 10000, 10000, ZKStringSerializer$.MODULE$);
	
			AdminUtils.createTopic(zkClient, "allData", 10, 1, new Properties());
		}catch(Exception e){
			System.out.println("Error: " + e);
		}
		Producer<String, String> producer = new Producer<String, String>(config2);
		
		//For Database connection
        postgresql sql = new postgresql();
        
    	//start server for UI communication
    	LiveServer server = new LiveServer(sql);
        server.start();
        

        HistoryServer server2 = new HistoryServer(sql);
        server2.start();
    	SpectralAnalysis spectral = new SpectralAnalysis();
    	
    	 //Kafka Consumer
    	KafkaConsumer KafkaConsumer = new KafkaConsumer("","");
    	KafkaConsumer.run();
        // Create a ConnectionFactory
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.99.100:32768");

        // Create a Connection
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // Create a Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // Create the destination (Topic or Queue)
        Destination destination = session.createTopic("m_orders");

        // Create a MessageConsumer from the Session to the Topic or Queue
        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(message -> {

   	 if (message instanceof TextMessage) {
   		try{
        	String [] activemq = new String[4];
            TextMessage textMessage = (TextMessage) message;
            String text = textMessage.getText();
            System.out.println("Received text: " + text);
            //System.out.println("Received text: " + text);
            activemq [0]= text.substring(text.indexOf("<customerNumber>")+16,text.indexOf("</customerNumber>"));
            activemq [1]= text.substring(text.indexOf("<materialNumber>")+16,text.indexOf("</materialNumber>"));
            activemq [2]= text.substring(text.indexOf("<orderNumber>")+13,text.indexOf("</orderNumber>"));
            activemq [3]= text.substring(text.indexOf("<timeStamp>")+11,text.indexOf("</timeStamp>"));;
    		StateMachine_Kafka test = new StateMachine_Kafka(server, spectral, producer, server2);
    		test.setActivemq(activemq);
    		KafkaConsumer.setStateMachines(test);
   		}catch(Exception e){};
   	 	} else {
         System.out.println("Received message: " + message);
     	}
   		
		 });

    	} catch (Exception e) {
        	System.out.println("Caught: " + e);
        	e.printStackTrace();
    	}
	}
}
