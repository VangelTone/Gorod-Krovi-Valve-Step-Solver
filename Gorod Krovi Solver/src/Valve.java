public class Valve {
	
	private final String valveName;
	private Valve selectedValve, valveOne, valveTwo, valveThree;
	private boolean passed, last = false;
	public static int count;
	public static String solutionString = "";

	public Valve(final String name) {
		valveName = name;
		passed = false;
	}

	public void setPaths(Valve valve1, Valve valve2, Valve valve3) {
		valveOne = valve1; valveTwo = valve2; valveThree = valve3;
	}
	
	public void selectNext() {
		this.setPassed();
		
		if(!this.isLast()) {
			int random = (int)((Math.random()*100)-1)%3;
			
			//FIRST VALVE PROCESS
			if(random==0) {
				if(!valveOne.isPassed()) {	
					selectValve(valveOne, 1);
				}
				else { 	
					random = (int)((Math.random()*100)-1)%2;
					if(random==0) {
						if(!valveTwo.isPassed()) {
							selectValve(valveTwo, 2);
						}
						else if(!valveThree.isPassed()) {
							selectValve(valveThree, 3);
						}
						else {
							Solver.resetAll();
						}
					}
					else if(random==1) {
						if(!valveThree.isPassed()) {
							selectValve(valveThree, 3);
						}
						else if(!valveTwo.isPassed()) {
							selectValve(valveTwo, 2);
						}
						else {
							Solver.resetAll();
						}
					}
				}
			}
			
			//SECOND VALVE PROCESS
			else if(random==1) {
				if(!valveTwo.isPassed()) {
					selectValve(valveTwo, 2);
				}
				else {
					random = (int)((Math.random()*100)-1)%2;
					if(random==0) {
						if(!valveThree.isPassed()) {
							selectValve(valveThree, 3);
						}
						else if(!valveOne.isPassed()) {
							selectValve(valveOne, 1);
						}
						else {
							Solver.resetAll();
						}
					}
					else if(random==1) {
						if(!valveOne.isPassed()) {
							selectValve(valveOne, 1);
						}
						else if(!valveThree.isPassed()) {
							selectValve(valveThree, 3);
						}
						else {
							Solver.resetAll();
						}
					}
				}
			}
			
			//THIRD VALVE PROCESS
			else if(random==2) {
				if(!valveThree.isPassed()) {
					selectValve(valveThree, 3);
				}
				else {
					random = (int)((Math.random()*100)-1)%2;
					if(random==0) {
						if(!valveTwo.isPassed()) {
							selectValve(valveTwo, 2);
						}
						else if(!valveOne.isPassed()) {
							selectValve(valveOne, 1);
						}
						else {
							Solver.resetAll();
						}
					}
					if(random==1) {
						if(!valveOne.isPassed()) {
							selectValve(valveOne, 1);
						}
						else if(!valveTwo.isPassed()) {
							selectValve(valveTwo, 2);
						}
						else {
							Solver.resetAll();
						}
					}
				}
			}
			
			else {
				new RuntimeException("RANDOM NUMBER WAS NOT WITHIN LIMITS");
			}
		}
		else {
			if(count==5) {
				System.out.print(solutionString);
				System.out.println(valveName+": last");
			}
			else {
				Solver.resetAll();
			}
			
		}
		
	}
	
	public void selectValve(Valve valve, int value) {
		selectedValve = valve;
		selectedValve.setPassed();
		count++;
		solutionString+=valveName+": "+value+"\n";
		selectedValve.selectNext();
	}
	
	public void setPassed() {
		passed = true;
	}
	public void resetPassed() {
		passed = false;
	}
	public boolean isPassed() {
		return passed;
	}
	public void setLast() {
		last = true;
	}
	public boolean isLast() {
		return last;
	}

}
