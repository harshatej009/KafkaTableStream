package harsh.rane;

public class Employee {

	private int empId;
	private String name;
	private String surname;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + name + ", surname=" + surname + "]";
	}
	
}
