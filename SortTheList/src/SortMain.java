import java.util.Arrays;

public class SortMain {
	
	public static final int BestCase = 0;
	public static final int WorstCase = 1;
	public static final int AverageCase = 2;
	public static int howMany = 10000;
	public static int[] numList = new int[howMany];
	public static int[] original = new int[howMany];
	public static int[] original2 = new int[howMany];
	public static int[] original3 = new int[howMany];
	public static int[] original4 = new int[howMany];
	public static int[] original5 = new int[howMany];
	public static int[] original6 = new int[howMany];
	
	
	public static void main(String[] args) {
				casePicker(howMany);
		
	}
		
		//Use constructor to make FindBiggestNum object and call other class
		public static void printer(int[] numList, int[] numListV2, int [] numListV3,int [] numListV4,int [] numListV5,int [] numListV6,int [] numListV7) {
			
			SelectionSort SSort = new SelectionSort(numList);
			long startT = System.nanoTime();		//Start Timer
			SSort.selectionSortIt();
			long endT = System.nanoTime();			//End Timer
			timer(startT, endT);
			
			
			InsertionSort ISort = new InsertionSort();
			long startT2 = System.nanoTime();
			ISort.InsSort(numListV2);
			long endT2 = System.nanoTime();
			timer(startT2,endT2);
			
			BubbleSort BSort = new BubbleSort();
			long startT3 = System.nanoTime();
			BSort.BSorter(numListV3);
			long endT3 = System.nanoTime();
			timer(startT3,endT3);
			
			MergeSort MSort = new MergeSort(numListV4);
			
			long startT4 = System.nanoTime();		//Start Timer
			MSort.rm(0, numListV4.length-1);
			long endT4 = System.nanoTime();			//End Timer
			timer(startT4, endT4);
			long startT5 = System.nanoTime();		//Start Time
			MSort.nrms(numListV5);
			long endT5 = System.nanoTime();
			timer(startT5, endT5);
			
			
			long startT6 = System.nanoTime();		//Start Timer
			qsort Q = new qsort(numListV6);
			Q.quicks(0, numListV6.length-1);
			long endT6 = System.nanoTime();			//End Timer
			timer(startT6,endT6);
			
			
			long startT7 = System.nanoTime();		//Start Timer
			shells S = new shells(numListV7);
			S.sort();
			long endT7 = System.nanoTime();			//End Timer
			timer(startT7,endT7);
		
		}
		
		public static void timer (long start, long end){
			long totalNS = end-start;
			System.out.println(totalNS+" Nanoseconds\n");
		}
		
		public static void casePicker(int howMany){
			System.out.println("Selection Sort\nInsertion Sort\nBubble\nMerge (R/NR)\nQuick\nShell\n\nBest\nWorst\nAverage\n");		
			for(int i =0;i<3;i++){	//Make the code run using all three cases
				int whatCase = i;
				switch(whatCase){
				case 0:	//Best Case (already sorted)
					for(int b=0;b<howMany;b++){
						numList[b]= b+1;
						original[b] = b+1;
						original2[b] = b+1;
						original3[b] = b+1;
						original4[b] = b+1;
						original5[b] = b+1;
						original6[b] = b+1;
					}
					System.out.println("Best Case:");
					printer(numList,original,original2,original3,original4,original5,original6);
					break;
					
				case 1: //Worst Case (inverse)
					int filler = howMany;
					for(int w=howMany;w>0;w--){
						numList[howMany-w]= filler;
						original[howMany-w] = filler;
						original2[howMany-w] = filler;
						original3[howMany-w] = filler;
						original4[howMany-w] = filler;
						original5[howMany-w] = filler;
						original6[howMany-w] = filler;
						filler--;
					}
					System.out.println("Worst Case: ");
					printer(numList,original,original2,original3,original4,original5,original6);
					break;
					
				case 2: //Average Case
					for(int a=0;a<howMany;a++){
						numList[a] = original[a] = original2[a]= original3[a]=original4[a]=original5[a]=original6[a]=(int) (Math.random() * 5100);
					}
					System.out.println("Average Case:");
					printer(numList,original,original2,original3,original4,original5,original6);
					break;
						
				}
		
		}
}
}

