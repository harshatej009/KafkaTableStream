package harsh.rane;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class EmployeeDeserializer implements Deserializer<Employee> {

	@Override
	public void configure(Map<String, ?> configs, boolean isKey) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee deserialize(String topic, byte[] data) {
		 ObjectMapper mapper = new ObjectMapper();
		    Employee user = null;
		    try {
		      user = mapper.readValue(data, Employee.class);
	    } catch (Exception e) {
	
		      e.printStackTrace();
		    }
		    return user;
		  }
	

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

//	  @Override 
//	  public void close() {
//
//	  }
//
//
//	  @Override
//	  public Employee deserialize(String arg0, byte[] arg1) {
//	    ObjectMapper mapper = new ObjectMapper();
//	    Employee user = null;
//	    try {
//	      user = mapper.readValue(arg1, Employee.class);
//	    } catch (Exception e) {
//
//	      e.printStackTrace();
//	    }
//	    return user;
//	  }
//
//	@Override
//	public void configure(Map configs, boolean isKey) {
//		// TODO Auto-generated method stub
//		
//	}
}
