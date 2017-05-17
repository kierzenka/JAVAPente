import java.util.Arrays;

public class shells {
	int []list;
	shells(int[] numList) {
		list=numList;
		//System.out.println(Arrays.toString(list));
		//for(int i = 0;i<numList.length;i++){
			//int value =numList[i];
			//list[i] = numList[i];
		//}
		sort();
	}
	public void sort(){
		
		int l =list.length;
		int k;
		for(k=l/2;k>0;k=k/2){
			for(int i =0;i+k<l;i++){
				if(list[i]>list[i+k]){
					list = swap(i,i+k,list);
					int t = i;
					if(t-k>=0){
						while(list[t]<list[t-k]){
							list= swap(t,t-k,list);
							t+=k;
				}
				}
			}
		}
		}
	}
	
	public int[] swap(int dLoc, int startLoc,int[] list){
		//3 step swap
		int tempNum = list[dLoc];
		list[dLoc] = list[startLoc];
		list[startLoc]=tempNum;
		return list;
	}
}
