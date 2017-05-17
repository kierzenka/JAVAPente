
public class SelectionSort {

	private int [] myNumberList;
	
	public SelectionSort(int[] list){
		myNumberList = list;
	}
	
	public int[] selectionSortIt(){
		for(int i=0; i<myNumberList.length;++i){
			int whereToSwap = findLowestIndex(i);
				this.swap(i, whereToSwap);
		}
		return myNumberList;
	}
	private int findLowestIndex(int start){
		int currentLowest = myNumberList[start]; //Start of with 0 as the "current biggest" before testing the elements
		int theLowIndex = start;
		for(int i = start;i<myNumberList.length;++i){
			
			if(myNumberList[i]<currentLowest){
				currentLowest=myNumberList[i];
				theLowIndex = i;
			}
		}
		return theLowIndex;
	}
	
	private void swap(int dLoc, int startLoc){
		//3 step swap
		int tempNum = myNumberList[dLoc];
		myNumberList[dLoc] = myNumberList[startLoc];
		myNumberList[startLoc]=tempNum;
	}
}
