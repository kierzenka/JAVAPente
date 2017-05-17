import java.util.Arrays;

import javax.swing.plaf.synth.SynthSpinnerUI;

public class averagesort {
	static int [] list = {123,4235,65,786,433,2,21,11,111};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub'
		//int [] list = {0,214,43,2,32,11,3};
		
		//System.out.println("original "+ Arrays.toString(list));
		 int[ ] finished=averagesorter(0,list.length-1);
		System.out.println("aye " + Arrays.toString(finished));
		
	}
	public static int[] averagesorter(int start, int end){
		int average;
		int length = end-start;
		if(end-start>1){
			int biggest = 0;
			int smallest = 1000000;
			for(int i =start;i<=end;i++){
				if(list[i]>=biggest){
					biggest = list[i];
				}
				if(list[i]<=smallest){
				smallest = list[i];
			}
		}
		System.out.println("biggest = "+biggest+" Smallest = "+smallest);
		average = (biggest+smallest)/2;
		int closestdiff = 1000000000;
		int actualnum=0;
		for(int a =start;a<=end;a++){
			if(Math.abs(list[a]-average)<=closestdiff){
				actualnum=list[a];
				closestdiff = Math.abs(list[a]-average);
			}
		}
		System.out.println("actual num  = "+actualnum);
		int wall = start;
		for(int b=start;b<=end;b++){
			if(list[b]<actualnum){
				swap(wall,b);
				wall++;
			}
		}
		
		int index = findindex(actualnum);
		System.out.println("index = "+index);
		swap(wall,index);
		System.out.println(Arrays.toString(list));
		
		if(start<wall){
			System.out.println("Start = "+start+" Wall (end1) = "+wall);
			averagesorter(start,wall);
		}
		if(wall++<end){
			System.out.println("Start2 = "+(wall++ )+" end = "+end);
			averagesorter(wall++,end);
		
		}
		}
		
		return list;
	
	}
	public static void swap(int dLoc, int startLoc){
		//3 step swap
		int tempNum = list[dLoc];
		list[dLoc] = list[startLoc];
		list[startLoc]=tempNum;
	}
	
	public static int findindex(int number){
		int index= 0;
		for(int i = 0;i<list.length;i++){
			if(list[i]==number){
				index=i;
			}
		}
		return index;
		}
	
	public static void RMerge(int start, int end, int start2, int end2) {
		int l1in = start;
		int l2in =start2;
		int[] temp= new int[end2-start +1];
		int tempi = 0;
		
		for( int i =0;i<temp.length;i++){
			if(l1in<=end && l2in<=end2){
				if(list[l1in]<list[l2in]){
					temp[tempi]=list[l1in];
					tempi++;
					l1in++;
				}else{
					temp[tempi]=list[l2in];
					tempi++;
					l2in++;
				}
			}else{
				if(l1in<=end){
					temp[tempi]=list[l1in];
					tempi++;
					l1in++;
				}else{
					temp[tempi]=list[l2in];
					tempi++;
					l2in++;
				}
			}
		}
		for(int i =0;i<temp.length;i++){
			list[start+i]=temp[i];
		}
	}	
	}

