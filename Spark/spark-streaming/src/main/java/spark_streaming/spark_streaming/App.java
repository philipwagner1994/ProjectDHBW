package spark_streaming.spark_streaming;


import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.JavaRDD;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import scala.Tuple2;

import kafka.serializer.StringDecoder;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.*;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.apache.spark.streaming.*;
import org.apache.spark.streaming.kafka.*;
import org.apache.spark.api.*;
import org.apache.spark.streaming.api.java.*;


/**
/**
 * Hello world!
 *
 */
public class App 
{

    
    public static void App(){
    	SparkConf conf = new SparkConf().setAppName("Test");
    	JavaSparkContext sc = new JavaSparkContext(conf);
    	
    };
	
	  private static final Pattern SPACE = Pattern.compile(" ");

	  public static void main(String[] args) throws Exception {
	  /*  if (args.length < 2) {
	      System.err.println("Usage: JavaDirectKafkaWordCount <brokers> <topics>\n" +
	          "  <brokers> is a list of one or more Kafka brokers\n" +
	          "  <topics> is a list of one or more kafka topics to consume from\n\n");
	      System.exit(1);
	    }*/

	    String brokers = "kafka:9092";
	    String topics = "allData";

	    // Create context with a 2 seconds batch interval
	    SparkConf sparkConf = new SparkConf().setAppName("JavaKafkaConsumer");
	    JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, Durations.seconds(200));

	    Set<String> topicsSet = new HashSet<>(Arrays.asList(topics.split(",")));
	    Map<String, String> kafkaParams = new HashMap<>();
	    kafkaParams.put("metadata.broker.list", brokers);

	    // Create direct kafka stream with brokers and topics
	    JavaPairInputDStream<String, String> messages = KafkaUtils.createDirectStream(
	        jssc,
	        String.class,
	        String.class,
	        StringDecoder.class,
	        StringDecoder.class,
	        kafkaParams,
	        topicsSet
	    );
	    
	    // Get the lines, split them into words, count the words and print
	    JavaDStream<String> lines = messages.map(new Function<Tuple2<String, String>, String>() {
	      @Override
	      public String call(Tuple2<String, String> tuple2) {
	        return tuple2._2();
	      }
	    });
	    lines.print();
	    
	    JavaDStream<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
	      @Override
	      public Iterator<String> call(String x) {
	        return Arrays.asList(x.split(",")).iterator(); //SPACE.split(x)
	      }
	      
	    });
	    words.print();
	    JavaPairDStream<String, Integer> wordCounts = words.mapToPair(
	      new PairFunction<String, String, Integer>() {
	        @Override
	        public Tuple2<String, Integer> call(String s) {
	          return new Tuple2<>(s, 1);
	        }
	      }).reduceByKey(
	        new Function2<Integer, Integer, Integer>() {
	        @Override
	        public Integer call(Integer i1, Integer i2) {
	          return i1 + i2;
	        }
	      });
	  //  wordCounts.print();
	//words.print();
	    // Start the computation
	    jssc.start();
	    jssc.awaitTermination();
		  
/*
		    SparkConf sparkConf = new SparkConf().setAppName("JavaKafkaWordCount");
		    // Create the context with 2 seconds batch size
		    JavaStreamingContext jssc = new JavaStreamingContext(sparkConf, new Duration(2000));

		    int numThreads = 1;
		    Map<String, Integer> topicMap = new HashMap<>();
		    String topic = "prodData";

		      topicMap.put(topic, numThreads);
	

		    JavaPairReceiverInputDStream<String, String> messages =
		            KafkaUtils.createStream(jssc, "192.168.99.100:1001", "blubb", topicMap);

		    JavaDStream<String> lines = messages.map(new Function<Tuple2<String, String>, String>() {
		      @Override
		      public String call(Tuple2<String, String> tuple2) {
		        return tuple2._2();
		      }
		    });

		    JavaDStream<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
		      @Override
		      public Iterator<String> call(String x) {
		        return Arrays.asList(x.split(",")).iterator(); //SPACE.split(x)
		      }
		    });

		    JavaPairDStream<String, Integer> wordCounts = words.mapToPair(
		      new PairFunction<String, String, Integer>() {
		        @Override
		        public Tuple2<String, Integer> call(String s) {
		          return new Tuple2<>(s, 1);
		        }
		      }).reduceByKey(new Function2<Integer, Integer, Integer>() {
		        @Override
		        public Integer call(Integer i1, Integer i2) {
		          return i1 + i2;
		        }
		      });
		    words.print();
		    wordCounts.print();
		    jssc.start();
		jssc.awaitTermination();*/
	}
}
