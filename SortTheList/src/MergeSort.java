import java.util.Arrays;
public class MergeSort {
	int []numlist;
	/*public static int[] MSorter(int[] nl1, int []nl2){
		int length = nl1.length + nl2.length;
		int[] nl3 = new int[length];
		int list1Index = 0; 
		int list2Index = 0;
		for(int i=0; i < length; ++i){
			if (list1Index==nl1.length && list2Index!=nl2.length){
				nl3[i]=nl2[list2Index];
				++list2Index;
			}else if (list2Index==nl2.length && list1Index!=nl1.length){
				nl3[i]=nl1[list1Index];
				++list1Index;
			}else if(nl1[list1Index]>nl2[list2Index]){
				nl3[i]=nl2[list2Index];
				++list2Index;
			}else if (nl1[list1Index]<nl2[list2Index]){
				nl3[i]=nl1[list1Index];
				++list1Index;
			}
		}
		return nl3;
	}
	*/
public MergeSort(int []list2sort){
		numlist = new int[list2sort.length];
		for(int i =0;i<list2sort.length;i++){
			int value = list2sort[i];
			numlist[i]=value;
		}
}

	public int[]getlist(){
		return numlist;
	}
public void rm(int start, int end){
		if(start<end){
			int mid = (start+end)/2;
			rm(start,mid);
			rm(mid+1,end);
			
			RMerge(start, mid, mid+1,end);
		}else{
		}
		
		
}

private void RMerge(int start, int end, int start2, int end2) {
	int l1in = start;
	int l2in =start2;
	int[] temp= new int[end2-start +1];
	int tempi = 0;
	
	for( int i =0;i<temp.length;i++){
		if(l1in<=end && l2in<=end2){
			if(numlist[l1in]<numlist[l2in]){
				temp[tempi]=numlist[l1in];
				tempi++;
				l1in++;
			}else{
				temp[tempi]=numlist[l2in];
				tempi++;
				l2in++;
			}
		}else{
			if(l1in<=end){
				temp[tempi]=numlist[l1in];
				tempi++;
				l1in++;
			}else{
				temp[tempi]=numlist[l2in];
				tempi++;
				l2in++;
			}
		}
	}
	for(int i =0;i<temp.length;i++){
		numlist[start+i]=temp[i];
	}
}	
public void nrms(int[]numlist){
	int ll = numlist.length;
	
	int start,end,start1,end1,mid;
	int sss=1;
	for(sss=1;sss<=ll;sss=sss*2){
		for(int sstart = 0;sstart<ll;sstart+=(sss*2)){
			start = sstart;
			end1=sstart+sss*2-1;
			mid = (start+end1)/2;
			end=mid;
			start1=mid+1;
			if(end>=ll)end=ll-1;
			if(end1>=ll)end1=ll-1;
			if(start1>=ll)start1=ll-1;
			RMerge(start,end,start1,end1);
		}
	}
}


}
