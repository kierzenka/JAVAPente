import java.util.Arrays;

public class BubbleSort {
		public int[] BSorter (int[] numList){
		     boolean flag = true;
		     int temp;   

		     while ( flag ){
		            flag= false;   
		            for(int i=0; i<numList.length -1;  i++ ){
		                   if ( numList[i]>numList[i+1] ){
		                           temp = numList[ i ];               
		                           numList[ i ] = numList[ i+1 ];
		                           numList[ i+1 ] = temp;
		                          flag = true;              
		            } 

		} 
		}
		return numList;
}
}
