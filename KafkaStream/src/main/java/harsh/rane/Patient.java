package harsh.rane;

public class Patient {

	private int patientId;
	private int empId;
	private String illness;
	
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getIllness() {
		return illness;
	}

	public void setIllness(String illness) {
		this.illness = illness;
	}

	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", empId=" + empId + ", illness=" + illness + "]";
	}
	
}
