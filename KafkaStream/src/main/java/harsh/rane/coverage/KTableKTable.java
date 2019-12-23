package harsh.rane.coverage;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.Windowed;
import org.springframework.kafka.support.serializer.JsonSerde;

import harsh.rane.model.Address;
import harsh.rane.model.Contact;
import harsh.rane.model.NewTarget;
import harsh.rane.model.Patient;
import harsh.rane.process.PopularPageEmailAlert;

public class KTableKTable {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties streamsConfiguration = new Properties();
		streamsConfiguration.put(StreamsConfig.APPLICATION_ID_CONFIG, "KafkaMain");
		streamsConfiguration.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		streamsConfiguration.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		streamsConfiguration.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG,Serdes.String().getClass() );
		StreamsBuilder builder = new StreamsBuilder();
		
		Serde<Patient> patientSerde=new JsonSerde<Patient>(Patient.class);
		patientSerde.configure(Collections.EMPTY_MAP, false);
		Serde<Address> addressSerde=new JsonSerde<Address>(Address.class);
		patientSerde.configure(Collections.EMPTY_MAP, false);
		Serde<Contact> contactSerde=new JsonSerde<Contact>(Contact.class);
		patientSerde.configure(Collections.EMPTY_MAP, false);
		
		KTable<String,Patient> patienttable=builder.table("topicP",Consumed.with(Serdes.String(), patientSerde)
				,Materialized.as("Patient-store-name"));
		
		KTable<String,Address> addresstable=builder.table("topicA",Consumed.with(Serdes.String(), addressSerde)
				,Materialized.as("Address-store-name"));
		
		KStream<String,Contact> contacttable=builder.stream("topicC",Consumed.with(Serdes.String(), contactSerde));
		
		KTable<String, Patient> NewKeyPatientTable = patienttable.groupBy(
		        // First, going to set the new key to the user's application id
		        (userId, user) -> KeyValue.pair(user.getAid().toString(), user)
		).aggregate(
		        // Initiate the aggregate value
		        () -> null,
		        // adder (doing nothing, just passing the user through as the value)
		        (applicationId, user, aggValue) -> user,
		        // subtractor (doing nothing, just passing the user through as the value)
		        (applicationId, user, aggValue) -> user
		);
		
		KTable<String, NewTarget> PatientAddress=NewKeyPatientTable.outerJoin(addresstable, (objPatient,objAddress)->
		{
			NewTarget newtarget=new NewTarget();
		
			if(objPatient==null)
			{
				newtarget.setPid("--PID--from DB ");
				newtarget.setName("--Name--from DB ");
				newtarget.setSurname("--Surname--from DB ");
				newtarget.setAid(objAddress.getAid());
				newtarget.setCity(objAddress.getCity());
				newtarget.setState(objAddress.getState());
				return newtarget;
			}
			else if (objAddress==null)
			{
				newtarget.setPid(objPatient.getPid());
				newtarget.setName(objPatient.getName());
				newtarget.setSurname(objPatient.getSurname());
				newtarget.setAid("--Aid --from DB");
				newtarget.setCity("--City --from DB");
				newtarget.setState("--State --from DB");
				return newtarget;
			}
			else {
				newtarget.setPid(objPatient.getPid());
				newtarget.setName(objPatient.getName());
				newtarget.setSurname(objPatient.getSurname());
				newtarget.setAid(objAddress.getAid());
				newtarget.setCity(objAddress.getCity());
				newtarget.setState(objAddress.getState());
				return newtarget;
			}
		});
		
		KafkaStreams streams = new KafkaStreams(builder.build(), streamsConfiguration); //Starting kafka stream
        streams.start();
	}

}
