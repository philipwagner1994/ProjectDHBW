package Data_collector.data_collector.DataCollector;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.gson.Gson;

import Data_collector.data_collector.StateMachine.StateMachine_Kafka;
import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.log.Log;
import kafka.message.MessageAndMetadata;

import com.github.oxo42.stateless4j.StateMachine;
import com.github.oxo42.stateless4j.StateMachineConfig;
import com.github.oxo42.stateless4j.delegates.Action;



public class KafkaConsumer {
	private static Logger log = LoggerFactory.getLogger(KafkaConsumer.class);
	private String topicName;
	private Gson gson = new Gson();

	private ConsumerConfig config;
	
	//private StateMachine_Kafka StateMachine_creator = new StateMachine_Kafka();
	private Vector<StateMachine_Kafka> stateMachines = new Vector<>();
	
	public static void main(String[] args){
		BasicConfigurator.configure();
		try{
		KafkaConsumer consumer = new KafkaConsumer("192.168.99.100:1000","prod");
		consumer.run();
		}catch(Exception e){
			log.info("Error "+e );
		}
		
		
	}
	public KafkaConsumer(String server, String topicName) throws Exception {
		Properties props = new Properties();
		server ="192.168.99.100:1001";
		//server ="127.0.0.1:9092";
		topicName = "prodData";	
		props.put("bootstrap.servers", server);
		props.put("zookeeper.connect", server);
		props.put("group.id", "bla1");
		props.put("client.id", this.getClass().getSimpleName());
		props.put("key.deserializer", StringDeserializer.class.getName());
		props.put("value.deserializer", StringDeserializer.class.getName());
		props.put("partition.assignment.strategy", "range");

		this.config = new ConsumerConfig(props);
		this.topicName = topicName;
	}

	public void run() throws Exception{
		log.info("Starting");

		ConsumerConnector connector = Consumer.createJavaConsumerConnector(config);
		Map<String, List<KafkaStream<byte[], byte[]>>> messageStreams = connector.createMessageStreams(ImmutableMap.of(topicName, 1));
		List<KafkaStream<byte[], byte[]>> streams = messageStreams.get(topicName);
		ExecutorService executor = Executors.newFixedThreadPool(streams.size());

		for ( final KafkaStream<byte[], byte[]> stream : streams) {
			executor.submit(new Runnable() {
				public void run(){
					ConsumerIterator<byte[], byte[]> iterator = stream.iterator();
					while (iterator.hasNext()) {
						MessageAndMetadata<byte[], byte[]> messageAndMetadata = iterator.next();
						System.out.println("TEST");
						log.info("Received: {}", new String(messageAndMetadata.message()));
						String m = new String(messageAndMetadata.message());
						KafkaMessage message = gson.fromJson(m,KafkaMessage.class);
						for(int i=0; i<stateMachines.size();i++){
							try{
							String answer = stateMachines.get(i).handleMessage(message);
							
							if(answer.equals("done")){
								stateMachines.remove(i);
								i--;
								
							}
							}catch(Exception e){}
						}
						//log.info(StateMachine_creator.handleMessage(message));
					}
				}
			});
		}

		log.info("Exiting");
	}
	public Vector<StateMachine_Kafka> getStateMachines() {
		return stateMachines;
	}
	public void setStateMachines(StateMachine_Kafka stateMachine) {
		System.out.println("ADD Statemachine");
		this.stateMachines.add(stateMachine);
	}
}

