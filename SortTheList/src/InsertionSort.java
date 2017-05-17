

public class InsertionSort {
	
	public int[] InsSort(int []list){
		int len = list.length;
		for(int i =1; i<len;i++){
			int elem = list[i];
			int j = i;
			while(j>0 && elem<list[j-1]){
				list[j]=list[j-1];
				list[j-1]=elem;
				j--;
			}
			
		}
		return list;
	}

}
