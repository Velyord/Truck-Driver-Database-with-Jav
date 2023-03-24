
public class Vehicle {
	
	String brand;
	String model;
	int year;
	float cons;
	
	public Vehicle (String b, String m, int y, float c) {
		
		this.brand = b;
		this.model = m;
		this.year = y;
		this.cons = c;
		
	}
	
	public String toString() {
		
		return brand + ", " + model + ", " + year + ", " + cons;
		
	}
	
}
