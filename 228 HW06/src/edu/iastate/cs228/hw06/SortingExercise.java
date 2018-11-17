package edu.iastate.cs228.hw06;


/**
 * 
 * @author Sam Shifflett
 * 
 * NOTE:
 * 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 * 2. No data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 * 6. You are allowed to reuse any part of the source codes provided
 *    or shown under lecture notes, which do not violate any of above.
 *    
 *    
 * 
 */


public class SortingExercise
{
	/**
	 * Modified implementation of in class provided quick sort code.
	 * 
	 * 
	 * The implementation of our original quick sort needs to be
	 * revised as follows in this implementation. If the array has 
	 * 23 entries, choose the middle entry as the pivot. For arrays
	 * between 24 - 50 use the last element as the pivot value. For 
	 * arrays larger than 50 entries, use the median-of-three 
	 * pivot-selection scheme described below. For arrays fewer than 
	 * 23 entries, use insertion sort instead of quick sort.
	 * 
	 * Median-of-three pivot selection chooses as pivot the median of
	 * three entries in the array, i.e., the first entry, the middle 
	 * entry, and the last entry. We will use specific version of it
	 * as follows. 
	 * 
	 * For example, let's say original array is as follows
	 * 
	 *  5, 8, 6, 4, 9, 3, 7, 1, 2
	 * 
	 * first entry = 5
	 * middle entry = 9 // index is (0+8)/2=4
	 * last entry = 2
	 * 
	 * Median of 5, 9, 2, would be 5.
	 * Check: https://en.wikipedia.org/wiki/Median
	 * 
	 * Now our array would look as follows after positioning the pivot:
	 * 
	 *  2, 8, 6, 4, 5, 3, 7, 1, 9
	 * 
	 * Now our array would look as follows just before partitioning:
	 * 
	 *  2, 5, 6, 4, 8, 3, 7, 1, 9
	 *  
	 * Our pivot is at position 1 of array, i.e., value 5.
	 * Both low and high start as shown in source code of quick sort under
	 * lecture notes, i.e., 
	 * 
	 * int low = first + 1;
	 * int high = last;
	 * 
	 * 
	 * @param arr Array of ints to be sorted in nondecreasing order.
	 */
 public static void modifiedQuickSort(int[] arr)
 	{
	  if(arr == null) throw new NullPointerException();
	  if(arr.length == 0) throw new IllegalArgumentException();
	  if(arr.length == 1) return;
	  
//	  if(arr.length < 23)
//	  {
//		  selectionSort(arr);
//	  }
	  else if(arr.length <= 23)
	  {
		  //middle pivot partition quicksort
		  quickSort1(arr,0,arr.length-1);
	  }
	  else if(arr.length > 23 && arr.length <= 50)
	  {
		  //quicksort with last index pivot
		  quickSort1(arr,0,arr.length-1);
	  }
	  else
	  {
		  //median of three partition for quicksort
		  quickSort1(arr,0,arr.length-1);
	  }
  
  //TODO
 	}
 
 /*
  * @param arr
  * array that is to be sorted
  */
 public static void selectionSort(int[] arr)
 {
	 for(int i = 0; i < arr.length-1; i++)
		{
			int index = i;
			for(int j = i+1; j < arr.length; j++)
			{
				if(arr[j] < arr[index])
				{
					index = j;
				}
			}
			if(index != i)
			{
				int temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
			}
		}
 }
 /*
  * @param arr
  * array that is to be sorted
  * 
  * @param first
  * index of the first element in the array to be sorted
  * 
  * @param last
  * index of the last element in the array to be sorted
  */
 public static void quickSort1(int[] arr,int first,int last)
 {
	 int lastI = last;
	 int firstI = first;
	 int midI = (last+first)/2;
	 if(lastI <= firstI)
		{
			return;
		}
		int pivot = arr[midI];
		int i = first;
		int j = last;
		
		while(i < j)
		{
			while(arr[i] < pivot)
			{
				i++;
			}
			while(arr[j] > pivot)
			{
				j--;
			}
			if(arr[i] == arr[j])
			{
				i++;
			}
			if(i < j)
			{
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int g = 0;
		while(arr[g] != pivot)
			g++;

		quickSort1(arr,first,g-1);
		quickSort1(arr,g+1,last);
 }
 /*
  * @param arr
  * array that is to be sorted
  * 
  * @param first
  * index of the first element in the array to be sorted
  * 
  * @param last
  * index of the last element in the array to be sorted
  */
 public static void quickSort2(int[] arr,int first,int last)
 {
	 int lastI = last;
	 int firstI = first;
	 if(lastI <= firstI)
		{
			return;
		}
		int pivot = arr[lastI];
		int i = first-1;
		int j = first;
		
		while(j < lastI)
		{
			if(arr[j] <= pivot)
			{
				i++;
				int temp = arr[j];
				arr[j] = arr[i];
				arr[i] = temp;
			}
			j++;
		}
		int temp = arr[i+1];
		arr[i+1] = pivot;
		arr[last] = temp;
		int mid = i;

		quickSort2(arr,0,mid);
		quickSort2(arr,mid+1,last);
 }
 /*
  * @param arr
  * array that is to be sorted
  * 
  * @param first
  * index of the first element in the array to be sorted
  * 
  * @param last
  * index of the last element in the array to be sorted
  */
 public static void quickSort3(int[] arr,int first,int last)
 {
	 int lastI = last;
	 int firstI = first;
	 int midI = arr.length/2;
	 int pivot = 0;
	 if(lastI <= firstI)
		{
			return;
		}
	 
	 	if(arr[firstI] <= arr[lastI] && arr[firstI] >= arr[midI] || arr[firstI] >= arr[lastI] && arr[firstI] <= arr[midI])
	 	{
	 		pivot = arr[firstI];
	 	}
	 	if(arr[midI] <= arr[lastI] && arr[midI] >= arr[firstI] || arr[midI] <= arr[firstI] && arr[midI] >= arr[lastI])
	 	{
	 		pivot = arr[midI];
	 	}
	 	if(arr[lastI] <= arr[firstI] && arr[lastI] >= arr[midI] || arr[lastI] <= arr[midI] && arr[lastI] >= arr[firstI])
	 	{
	 		pivot = arr[firstI];
	 	}

		int i = first;
		int j = last;
		
		while(i < j)
		{
			while(arr[i] < pivot)
			{
				i++;
			}
			while(arr[j] > pivot)
			{
				j--;
			}
			if(arr[i] == arr[j])
			{
				i++;
			}
			if(i < j)
			{
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int g = 0;
		while(arr[g] != pivot)
			g++;

		quickSort3(arr,0,g-1);
		quickSort3(arr,g+1,last);
 }
}
