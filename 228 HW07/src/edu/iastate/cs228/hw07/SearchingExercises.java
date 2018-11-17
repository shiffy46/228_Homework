package edu.iastate.cs228.hw07;


/**
 * 
 * @author
 * 
 * NOTE:
 * 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods.
 *    If you are introducing your own helper methods those need to be
 *    private and properly documented as per Javadoc style.
 * 2. No data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 * 	  Except the ones that are already provided to you.
 * 6. You are allowed to reuse any part of the provided source codes
 *    or shown under lecture notes, which do not violate any of above.
 * 7. If you have any additional questions please ask on Piazza Q/A
 *    platform, but first PLEASE search and make sure that it was not
 *    already asked and answered. PLEASE setup your notifications for 
 *    both Canvas and Piazza so that you are updated whenever there
 *    are any changes immediately.
 *    
 * 
 * 8. You need to answer the questions below for each method.     
 * 
 */


public class SearchingExercises
{
	/**
	 * Assume you are given an array of Integer values sorted in strictly
	 * increasing order, and a List of unsorted Integer values. Your task in 
	 * this method is to find the minimal interval, i.e., to compute the 
	 * smallest range, of array indices that contains all of the target values 
	 * in a given List givenValues. If the value in givenValues is smaller than 
	 * sortedArr[0], then it means that range will start at -1. If the value in 
	 * givenValue is larger than sortedArr[sortedArray.length-1], then it means 
	 * that range will end at sortedArray.length.
	 * 
	 * For example, if the givenValue is composed of [8, 2, 9, 17], and the
	 * sortedArr is composed of {5, 8, 10, 13, 15, 20, 22, 26}, the range
	 * returned would be {-1, 5}.
	 *
	 * 
	 * QUESTION: Assuming sortedArr has m elements and givenValues has n values, 
	 * what is the Big Oh performance of your algorithm?
	 * 
	 * ANSWER: The Big Oh of this algorithm is O(n) because to find the min and max of the
	 * givenValues array we need to traverse the entire thing in order to compare the largest
	 * and smallest values amongst all the values of the array therefore the worst case would
	 * be to traverse the entire contents of the array which would be n.
	 * 
	 * 
	 * 
	 * NOTE: Brief description is required as part of your answer.
	 * 
	 * @param sortedArr an array of Integers sorted in strictly increasing order. At least one element will be present. No null.
	 * @param givenValues a List of unsorted Integer values. At least one element will be present. No null.
	 * @return a two element array of Integers indicating mininmal lower and upper
	 * bounds (indices) in sortedArr which would include all List values. 
	 */
	public static Integer[] findMinInterval(
			Integer[] sortedArr, java.util.List<Integer> givenValues)
	{
		int minIndex = -1;
		int maxIndex = sortedArr.length;
		
		int min = givenValues.get(0);
		int max = givenValues.get(0);
		
		for(int i = 0; i < givenValues.size(); i++)
		{
			if(givenValues.get(i) < min)
			{
				min = givenValues.get(i);
			}
			else if(givenValues.get(i) > max)
			{
				max = givenValues.get(i);
			}
		}
		
		if(sortedArr[0] < min)
		{
			minIndex = recurBSLT(sortedArr,0,sortedArr.length-1, min);
		}
		if(sortedArr[maxIndex-1] > max)
		{
			maxIndex = recurBSGT(sortedArr,0,sortedArr.length-1, max);
		}
		
		Integer[] returnedList = new Integer[] {minIndex,maxIndex};
		
		return returnedList;
	}
	
	
	//recursiveBinarySearchGreaterThan
	/*
	 * @param arr
	 *     array of integers to be compared with
	 *     
	 * @param first
	 *     first index to be compared with to find the index of the comp value
	 * 
	 * @param last
	 *     last index to be compared with to find the index of the comp value
	 * 
	 * @param comp
	 *     value that we are trying to find the index of in the array
	 */
	private static int recurBSGT(Integer[] arr, int first, int last, int comp)
	{
		int midI = (last+first)/2;
		
		if(midI == first)
		{
			if(comp <= arr[first])
			{
				return first;
			}
			else if(comp <= arr[last])
			{
				return last;
			}
			else
				return -1;
		}
		
		if(comp < arr[midI])
		{
			return recurBSGT(arr,first,midI,comp);
		}
		else if(comp > arr[midI])
		{
			return recurBSGT(arr,midI,last,comp);
		}
		else
		{
			return midI;
		}
		
	}
	
	
	//recursiveBinarySearchLessThan
	/*
	 * @param arr
	 *     array of integers to be compared with
	 *     
	 * @param first
	 *     first index to be compared with to find the index of the comp value
	 * 
	 * @param last
	 *     last index to be compated with to find the index of the comp value
	 * 
	 * @param comp
	 *     value that we are trying to find the index of in the array
	 */
	private static int recurBSLT(Integer[] arr, int first, int last, int comp)
	{
		int midI = (last+first)/2;
		
		if(midI == first)
		{
			if(comp >= arr[first])
			{
				return last;
			}
			else if(comp >= arr[last])
			{
				return last+1;
			}
			else
				return -1;
		}
		
		if(comp < arr[midI])
		{
			return recurBSLT(arr,first,midI,comp);
		}
		else if(comp > arr[midI])
		{
			return recurBSLT(arr,midI,last,comp);
		}
		else
		{
			return midI;
		}
		
	}
	
	
	/**
	 * Assume that you are given a two-dimensional array with Comparable
	 * items stored in it. For example, consider the following 3 by 4
	 * two-dimensional array
	 * 
	 *  1,   4,  55,  88
	 *  7,  15,  61,  91
	 * 14,  89,  90,  99
	 * 
	 * Values in each row and column of this two-dimensional array are
	 * always come sorted in strictly increasing order.
	 * 
	 * Our method find2D needs to inform the user whether the the 
	 * user searched key exists in this two-dimensional array or not.
	 * You can assume that two-dimensional array will have at least one
	 * element in it.
	 *  
	 * 
	 * QUESTION: Assuming arr has m rows and n columns, what is the 
	 * Big Oh performance of your algorithm?
	 * 
	 * ANSWER: The big Oh of this algorithm is O(m*n) because the worst case is 
	 * when the indexs have to traverse all the columns (n) to zero and then get the 
	 * last index of that column starting from zero to m. therefore the worst time
	 * case would be the number of cols by the number of rows.
	 * 
	 * 
	 * NOTE: Brief description is required as part of your answer.
	 * 
	 * @param arr two-dimensional array of Comparable objects.
	 *        At least will have a single element. No null values.	
	 * @param key searched Comparable object. Not null.
	 * @return indication of whether key exists in arr or not.
	 *  
	 */
 public static <T extends Comparable<? super T>>
 						 boolean find2D(T[][] arr, T key)
 {
	 
	 int i = 0;
	 int j = arr[0].length-1;
	 
	 while(j >= 0)
	 {
		 T temp = arr[i][j];
		 if(temp.compareTo(key) <= 0)
		 {
			 if(i == arr.length-1 && (temp.compareTo(key) != 0))
			 {
				 return false;
			 }
			 else if(temp.compareTo(key) == 0)
			 {
				 return true;
			 }
			 i++;
		 }
		else
		{
			i = 0;
			j--;
		}
	 }
	 
	 return false;
 }
 
}
