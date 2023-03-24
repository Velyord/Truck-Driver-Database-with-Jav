
public class ElVehicle extends Vehicle{
	
	int capacity;

	public ElVehicle(String b, String m, int y, float c, int cap) {
		
		super(b, m, y, c);
		this.capacity = cap;
		
	}
	
	public String toString() {
		
		return super.toString() + ", " + capacity;
		
	}	
	
}















//addDogRobot(dogRobotList, new DogRobot("DR000", 2000, true, 4, 6));
//addDogRobot(dogRobotList, new DogRobot("DR001", 500, true, 4, 6));
//addDogRobot(dogRobotList, new DogRobot("DR002", 2000, false, 4, 6));
//addDogRobot(dogRobotList, new DogRobot("DR003", 2000, true, 2, 6));
//addDogRobot(dogRobotList, new DogRobot("DR004", 2000, true, 4, 4));
//addDogRobot(dogRobotList, new DogRobot("", -2000, false, -4, -6));

//addMarsRover(marsRoverList, new MarsRover("MR000", 4000, true, 6, 4));
//addMarsRover(marsRoverList, new MarsRover("MR001", 600, true, 6, 4));
//addMarsRover(marsRoverList, new MarsRover("MR002", 4000, false, 6, 4));
//addMarsRover(marsRoverList, new MarsRover("MR003", 4000, true, 3, 4));
//addMarsRover(marsRoverList, new MarsRover("MR004", 4000, true, 6, 1));
//addMarsRover(marsRoverList, new MarsRover("MR005", 4000, true, 6, -4));

//addBattleBot(battleBotList, new BattleBot("BB000", 500, true, 4, 4));
//addBattleBot(battleBotList, new BattleBot("BB001", 400, true, 4, 4));
//addBattleBot(battleBotList, new BattleBot("BB002", 500, true, 1, 4));
//addBattleBot(battleBotList, new BattleBot("BB003", 500, true, 4, 1));
//addBattleBot(battleBotList, new BattleBot("BB004", 500, false, -4, 4));

//addIndustrialRobot(industrialRobotList, new IndustrialRobot("IR000", 12000, true, true));
//addIndustrialRobot(industrialRobotList, new IndustrialRobot("IR001", 3000, true, true));
//addIndustrialRobot(industrialRobotList, new IndustrialRobot("IR002", 12000, false, true));
//addIndustrialRobot(industrialRobotList, new IndustrialRobot("IR003", 12000, true, false));
