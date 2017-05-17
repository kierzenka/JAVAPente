import java.util.Arrays;

public class qsort {
	int[] numlist;
	qsort(int []n){
		numlist=n;
		//System.out.println(Arrays.toString(numlist));
		quicks(0,numlist.length);
		
	}
	/*
	public static void main(String[]args){
		quicks(0,numlist.length-1);
		System.out.println(Arrays.toString(numlist));
	}
	/*qsort(int[] n){
		numlist=n;
		quicks(0,numlist.length-1);
	}
	*/
	public void quicks(int start, int end){
		
		
		int ploc = partition(start,end);
		if(start<ploc-1) quicks(start,ploc);
		if(ploc+1<end)quicks(ploc+1,end);
		
	}
	
	public int partition(int s,int e){
		int piv = s;
		int lwall=s;
		for(int i =s+1;i<e;i++){
			if(numlist[piv]>numlist[i]){
				swap(i,lwall);
			}
			if(lwall==piv){
				piv=i;
			}
			lwall++;
		}
		swap(piv,lwall);
		return lwall;	
	}
	
	public int probablyw(int start,int end){
		int pivot =numlist[end];
		int lpt = end-1;
		int rpt=start;
		while(lpt<=rpt){
			while(numlist[lpt]<pivot){
				lpt++;
			}
			while(numlist[rpt]>pivot){
				rpt++;
			}
			if(lpt<=rpt){
				swap(lpt,rpt);
				lpt++;
			}
		}
		return lpt;
	}
	public void swap(int dLoc, int startLoc){
		//3 step swap
		int tempNum = numlist[dLoc];
		numlist[dLoc] = numlist[startLoc];
		numlist[startLoc]=tempNum;
	}
}