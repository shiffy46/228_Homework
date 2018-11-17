package edu.iastate.cs228.hw04;

/**
 * 
 * @author Sam Shifflett
 * 
 * NOTE: 
 * 0. Put your Firstname and Lastname after above author tag.
 * 	  Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase (and separated by a space).
 * 1. In all of these methods implementations you are allowed
 *    to use the StringBuilder class. 
 * 2. You are allowed to create and use your own private helper methods.
 * 3. No data fields can be introduced.
 * 4. No custom classes of your own can be introduced or used.
 * 5. Import statements are not allowed.
 * 6. Fully qualified class names usage is not allowed.
 * 
 * 
 */
public final class RecursionExercises
{
 /**
  This method displays a given character the specified
  number of times on one line. For example, the call
   displayRowOfChacaters('+', 5);
  returns "+++++"
   
  Implement this method using recursion.
   
  @param toShow The character to display.
  @param n The number of times to display it; n>0.  
  @return String representations of n toShow chars.
  */
	 public static String displayRowOfCharacters(char toShow, int n)
	 {
		 String temp = new String("");
		 
		 if(n == 1)
		 {
			 return temp + toShow;
		 }
		 else
		 {
			 return(temp = toShow + displayRowOfCharacters(toShow,n-1));
		 }
	 }
	 
	 /**
	  * Recursively display an array backward starting at position n.
	  * E.g., displayBackwards(new Integer[]{1,2,3,4}, 2)
	  * would return "21".
	  * 
	  * Implement this method using recursion.
   * 
	  *  @param arr The array to display will have at least two elements.
	  *  @param n The number of entries to be returned; 1<=n<=arr.length.
	  *  @return String value of first n values of arr backwards.
	  */
	 public static String displayBackwards(int[] arr, int n)
	 {
		 String temp = new String("");
		 
	 	if(n-1 == 0)
	 	{
	 		return temp + arr[n-1];
	 		
	 	}
	 	else
	 	{
	 		return temp + arr[n-1] + displayBackwards(arr,n-1);
	 	}
	 }
	 
	 /**
	  * A palindrome is a string that reads the same forward and backward.
	  * For example deed and level are palindromes. Implement this method
	  * using recursion.
	  * 
	  * @param aString A string under consideration; nonempty and 
	  * consists only of upper/lowercase letters and digits.
	  * @return true if the given string is a palindrome, and false otherwise.
	  */
	 public static boolean isPalindrome(String aString)
	 {
		 int length = aString.length();
		 
		 if(length <= 1)
		 {
			 return true;
		 }
		 
		 if(aString.charAt(0) == aString.charAt(length-1))
		 {
			 return isPalindrome(aString.substring(1, length-1));
		 }

		 return false;
	 }
	 
	 /**
	  * Recursive method that finds the second smallest object in an array of
	  * Comparable objects. E.g., getSecondSmallest(new Integer[]{-1,10,3,2},4)
	  * would return 2.
	  *  
	  * @param arr An array of Comparable objects; arr.length>=2.
	  * @param len Length/size of arr array.
	  * @return Second smallest (Comparable) object in this array.
	  */
	 public static <T extends Comparable<? super T>> T getSecondSmallest(T[] arr, int len)
	 {
	 	if(arr.length < 2)
	 	{
	 		return null;
	 	}
	 	return getSecondSmallest(arr,len,0,null,null);
	 }
	 
	 public static <T extends Comparable<? super T>> T getSecondSmallest(T[] arr, int len, int currIndex, T smallest, T secondSmallest)
	 {
		 if(currIndex == len)
		 {
			 return secondSmallest;
		 }
		 
		 if(smallest == null || arr[currIndex].compareTo(smallest) < 0)
		 {
			 if(smallest != null)
			 {
				 secondSmallest = smallest;
			 }
			 smallest = arr[currIndex];
		 }
		 else if(secondSmallest == null || arr[currIndex].compareTo(secondSmallest) < 0)
		 {
			 secondSmallest = arr[currIndex];
		 }
		 return getSecondSmallest(arr,len,currIndex+1,smallest,secondSmallest);
	 }
	 
	 
}
