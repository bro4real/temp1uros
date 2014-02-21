
public class Tax {
	double grossIncome;
	String state;
	int dependents;
	static int customerCount;
	
	//constructor
	Tax (double gi, String st, int depen) {
		grossIncome = gi;
		state = st;
		dependents = depen;
		customerCount++;
		
		System.out.println("Preparing the tax data for "
				+ "customer #" + customerCount + "...");
		
		convertAndAssign(this.calcTax());
		//this way i could use counter to identify customers,
		//otherwise the count goes up every time I use constructor
	}
	
	double calcTax(){
		return (grossIncome*0.33 - dependents*100);
	}
	
	static void convertAndPrint(double tax) {
		System.out.println("tax converted to euros equals: €" + tax/1.25 + "€");
	}
	
	static void convertAndAssign(double tax) {
		System.out.println("For customer #" + customerCount + 
				", tax converted to euros equals: €" + tax/1.25);
	}

}
