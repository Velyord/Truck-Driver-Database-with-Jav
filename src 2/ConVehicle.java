
public class ConVehicle extends Vehicle{

	String engine;
	
	public ConVehicle(String b, String m, int y, float c, String e) {
		
		super(b, m, y, c);
		this.engine = e;
		
	}

	public String toString() {
		
		return super.toString() + ", " + engine;
		
	}
	
}
