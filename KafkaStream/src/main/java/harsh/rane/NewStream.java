package harsh.rane;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;

public class NewStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "KafkaMain");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass() );
		StreamsBuilder builder = new StreamsBuilder();
		
		

        Deserializer<PageView> pageViewDeserializer = new JsonPOJODeserializer();
        Serializer<PageView> pageViewSerializer = new JsonPOJOSerializer();

        KStream<String, PageView> topicdata = builder.stream("topic2",Consumed.with(Serdes.String(), Serdes.serdeFrom(pageViewSerializer, pageViewDeserializer)));
		topicdata.selectKey((key,value)->value.getName());
		System.err.print(topicdata);
        
		KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration); //Starting kafka stream
        streams.start();
	
	}

}
