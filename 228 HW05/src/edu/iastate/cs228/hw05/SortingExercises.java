package edu.iastate.cs228.hw05;


/**
 * 
 * @author Sam Shifflett
 * 
 * NOTE:
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. In all of these methods implementations you are allowed
 *    to use the StringBuilder class. 
 * 2. You are allowed to create and use your own private helper methods.
 * 3. No data fields can be introduced.
 * 4. No custom classes of your own can be introduced or used.
 * 5. Import statements are not allowed.
 * 6. Fully qualified class names usage is not allowed.
 * 7. You are allowed to reuse any part of the source codes provided
 *    or shown under lecture notes.
 * 
 */


public class SortingExercises
{
	/**
	 * Recursive implementation of selection sort.
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void selectionSort_Rec(int[] arr)
 {
	  if(arr == null) throw new NullPointerException();
	  if(arr.length == 0) throw new IllegalArgumentException();
	  if(arr.length == 1) return;
	  
	  selectionSort_Rec(arr,0);
  
 }
 
 private static void selectionSort_Rec(int[] arr, int indexStart)
 {
	 int indexSmall = indexStart;
	 if(indexStart >= arr.length-1)
	 {
		 return;
	 }
	 for(int i = indexStart + 1; i < arr.length; i++)
	 {
		 if(arr[i] < arr[indexSmall])
		 {
			 indexSmall = i;
		 }
	 }
	 int tempIndex = arr[indexStart];
	 arr[indexStart] = arr[indexSmall];
	 arr[indexSmall] = tempIndex;
	 selectionSort_Rec(arr, indexStart+1);
	 return;
 }
 
 /**
  * Recursive implementation of insertion sort.
  * 
  * @param arr Array of ints to be sorted in nondecreasing order.
  */
 public static void insertionSort_Rec(int[] arr)
 {
	 	if(arr == null) throw new NullPointerException();
	    if(arr.length == 0) throw new IllegalArgumentException();
	    if(arr.length == 1) return;
	    
	    insertion_Sort(arr,0);
 }
 
 private static void insertion_Sort(int[] arr, int i)
 {
	 if(i > arr.length)
	 {
		 return;
	 }
	 
	 if(i < arr.length)
	 {
		 int j = arr[i];
		 int k = i;
		 while(k > 0 && arr[k-1] > j)
		 {
			 arr[k] = arr[k-1];
			 k = k-1;
		 }
		 arr[k] = j;
		 insertion_Sort(arr, i + 1);
		 return;
	 }
 }
 /**
	 * Iterative implementation of selection sort with modifications as follows.
	 * On each pass in this case the method finds both the largest and smallest
	 * values in the unsorted portion of the array, and places them in the correct
	 * locations.
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void selectionSort_Itr(int[] arr)
 {
	  if(arr == null) throw new NullPointerException();
	  if(arr.length == 0) throw new IllegalArgumentException();
	  if(arr.length == 1) return;
  
	  for(int i = 0; i < arr.length-1; i++)
	  {
		  int minIN = i;
		  for(int j = i+1; j < arr.length; j++)
		  {
			  if(arr[j] < arr[minIN])
			  {
				  minIN = j;
			  }
		  }
		  int tempVariable = arr[minIN];
		  arr[minIN] = arr[i];
		  arr[i] = tempVariable;
	  }
	  return;
 }
 
 
 /**
  * A bubble sort can sort an array of n entries into ascending order by 
  * makeing n-1 passes through the array. On each pass, it compares adjacent
  * entries and swaps them if they are out or order. For example, on the 
  * first pass, it compares the first and second entries, then the second and
  * third entries, and so on. At the end of the first pass, the largest entry
  * is in its proper position at the end of the array. We say that it has bubbled
  * to its correct spot. Each subsequent pass ignores the entries at the end of
  * the array, since they are sorted and are larger than any of the remaining
  * entries. Thus, each pass makes one fewer comparison than the previous pass.
  * Check the figure under HW05 assignment on Canvas.
  * 
  * This method implements bubble sort iteratively.
  * 
  * @param arr Array of objects (with specific bounds) to be sorted in nondecreasing order.
  */
 public static <T extends Comparable<? super T>> void bubbleSort_Itr(T[] arr)
 {
	 	if(arr == null) throw new NullPointerException();
	 	if(arr.length == 0) throw new IllegalArgumentException();
	  	if(arr.length == 1) return;
	  	
	  	
	  	for(int i = 0; i < arr.length-1; i++)
	  	{
	  		for(int j = 0; j < arr.length-i-1; j++)	
	  		{
	  			if(arr[j].compareTo(arr[j+1]) > 0)
	  			{
	  				T temp = arr[j];
	  				arr[j] = arr[j+1];
	  				arr[j+1] = temp;
	  			}
	  		}
	  	}

 }
 
 /**
  * A bubble sort can sort an array of n entries into ascending order by 
  * makeing n-1 passes through the array. On each pass, it compares adjacent
  * entries and swaps them if they are out or order. For example, on the 
  * first pass, it compares the first and second entries, then the second and
  * third entries, and so on. At the end of the first pass, the largest entry
  * is in its proper position at the end of the array. We say that it has bubbled
  * to its correct spot. Each subsequent pass ignores the entries at the end of
  * the array, since they are sorted and are larger than any of the remaining
  * entries. Thus, each pass makes one fewer comparison than the previous pass.
  * Check the figure under HW05 assignment on Canvas.
  * 
  * This method implements bubble sort recursively.
  * 
  * @param arr Array of ints to be sorted in nondecreasing order.
  */
 public static void bubbleSort_Rec(int[] arr)
 {
	 	if(arr == null) throw new NullPointerException();
	 	if(arr.length == 0) throw new IllegalArgumentException();
	 	if(arr.length == 1) return;
	 	
	 	bubble_Sort(arr,arr.length);

 } 
 
 private static void bubble_Sort(int[] arr, int work)
 {
	 for(int i = 0; i < work-1;i++)
	 {
		 if(arr[i] > arr[i+1])
		 {
			 int temp;
			 temp = arr[i];
			 arr[i] = arr[i+1];
			 arr[i+1] = temp;
		 }
	 }
	 if(work-1>1)
	 {
		 bubble_Sort(arr,work-1);
	 }
 }
}
