package harsh.rane.process;

import org.apache.kafka.streams.processor.Processor;
import org.apache.kafka.streams.processor.ProcessorContext;

import harsh.rane.model.Patient;

public class PopularPageEmailAlert implements Processor<Integer, Patient>{

	@Override
	public void init(ProcessorContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(Integer key, Patient value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
