//amount of time each takes in array
//n how many registers
//each customer
//return total time
import java.util.*;
public class ShoppingQueue {
	public static void main(String[] args){
		int [] test = {5,20,3,84,77,12,9,8};
		int r = 5;
		int answer = solveSuperMarket(test, r);
		System.out.println(answer + " minutes");
	}
	
	public static int solveSuperMarket(int [] customers, int n){ 
		
		int [] registerT = new int[n];					//Array keeping track of time of each register
		
		int customerQ = customers.length-n;				//Num customers left after initial filling
		
		for(int i = 0;i<n;i++){							//The first customers filling in the register time array
			registerT[i]=customers[i];
		}
		Arrays.sort(registerT);
		System.out.println(Arrays.toString(registerT));
	
		
		for(int num = 0;num<customers.length-n;num++){	//For every remaining customer, place them in the smallest time
			Arrays.sort(registerT);						//Sorting the register times, so that we can add the next time to the current shortest 
			registerT[0]=registerT[0]+customers[customers.length-customerQ];//Adding the times
			System.out.println(Arrays.toString(registerT));
			customerQ--;								//there's one less customer left
		}
		
		Arrays.sort(registerT);							//Sort the final list of times
		return registerT[registerT.length-1];			//Return the greatest time
	}
}
