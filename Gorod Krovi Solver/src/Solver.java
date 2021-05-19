public class Solver {
	public static Valve armory, department, dragon, supply, infirmary, tank, first, last;
	
	public static void main(String[] args) {
		setupValves();
		solve(department, tank);
	}
	
	public static void setupValves() {
		armory = new Valve("Armory");
		department = new Valve("Department Store");
		dragon = new Valve("Dragon Command");
		supply = new Valve("Supply Depot");
		infirmary = new Valve("Infirmary");
		tank = new Valve("Tank Factory");
		
		armory.setPaths(supply, tank, department);
		department.setPaths(armory, infirmary, dragon);
		dragon.setPaths(supply, department, infirmary);
		supply.setPaths(dragon, armory, tank);
		infirmary.setPaths(department, tank, dragon);
		tank.setPaths(infirmary, supply, armory);
	}
	
	public static void solve(Valve start, Valve end) {
		first = start;
		last = end;
		first.setPassed();
		last.setLast();
		first.selectNext();
	}
	
	public static void resetAll() {
		armory.resetPassed();
		department.resetPassed();
		dragon.resetPassed();
		supply.resetPassed();
		infirmary.resetPassed();
		tank.resetPassed();
		Valve.count = 0;
		Valve.solutionString = "";
		first.selectNext();
	}
	
}
