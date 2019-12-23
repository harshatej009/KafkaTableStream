package harsh.rane;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertJson {
	public Employee fromJson(String json) throws JsonParseException, JsonMappingException, IOException
	{
		Employee patient = new ObjectMapper().readValue(json, Employee.class);
		return patient;
	}
}