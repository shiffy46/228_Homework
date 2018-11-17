package edu.iastate.cs228.hw05;

/**
 * A class of priority queues represented by a chain of linked nodes.
 * 
 * @author Sam Shifflett
 * 
 * NOTE: 
 * 0. Put your Firstname and Lastname after above author tag.
 * 			Make sure that in both cases the first letter is uppercase
 *    and all others are lowercase.
 * 1. You are allowed to create and use your own private helper methods. 
 * 2. No additional data fields can be introduced.
 * 3. No custom classes of your own can be introduced or used.
 * 4. Import statements are not allowed.
 * 5. Fully qualified class names usage is not allowed.
 * 6. You are allowed to reuse any part of the source codes of provided 
 *    source codes or shown under lecture notes.
 * 7. You are not allowed to create arrays of objects and manipulate
 *    queue objects using arrays.
 *  
 *
 * DESCRIPTION:
 * A class of priority queues represented by a linked chain of nodes.
 * 
 * For details of priority queue implementation using linked chain of nodes, 
 * check slide number 14 of "queueDequePriorityQueueImplementations_part3.pdf" 
 * file under lecture notes of Friday of Week 5 on Canvas.
 */


public final class LinkedPriorityQueue<T extends Comparable<? super T>>
                                         implements PriorityQueueInterface<T>
{
   private Node firstNode; // Reference to first node of chain and the front
                           // of the priority queue, which has the highest priority
   private int  length;    // Number of entries in chain

   public LinkedPriorityQueue()
   {
      firstNode = null;
      length = 0;
   }

   public void add(T newEntry)
   {
	   Node tempFirst = firstNode;
	   Node temp = new Node(newEntry,null);
	   
	   if(firstNode == null)
	   {
		   firstNode = temp;
		   length++;
	   }
	   else if(firstNode.next == null)
	   {
		   if(firstNode.data.compareTo(newEntry) > 0)
		   {
			   firstNode.next = temp;
		   }
		   else
		   {
			   temp.next = firstNode;
			   firstNode = temp;
		   }
		   length++;
	   }
	   else
	   {
		   while(tempFirst.next != null)
		   {
			   if(tempFirst.data.compareTo(newEntry) == -1)
			   {
				   temp.next = tempFirst;
				   firstNode = temp;
				   break;
			   }
			   else if(tempFirst.data.compareTo(newEntry) == 1 && tempFirst.next.data.compareTo(newEntry) <= 0)
			   {
				   temp.next = tempFirst.next;
				   tempFirst.next = temp;
				   break;
			   }
			   else
			   {
				   tempFirst = tempFirst.next;
			   }
		   }
		   length++;
	   }
   	return;
   } 

   public T remove()
   {
	   if(firstNode == null)
	   {
		   return null;
	   }
	   else if(firstNode.next == null)
	   {
		   T temp = firstNode.data;
		   firstNode = null;
		   return temp;  
	   }
	   else
	   {
		   T temp = firstNode.data;
		   firstNode = firstNode.next;
		   return temp;
	   }
   }

   public T peek()
   {
	   if(firstNode == null)
	   {
		   return null;
	   }
	   else
	   {
		   return firstNode.data;
	   }
   }

   /**
    * If queue is empty returns [].
    * Else, returns as [1, 2, 3]
    * Important: note a comma and single space before every
    * item except the last, and after last there is no space.
    * In both cases before and after square brackets there
    * is no space.
    *    
    */
   @Override
   public String toString()
   {
	   String temp = new String("");
	   String backwardsTemp = new String("[");
	   Node tempFirst = firstNode;
	   
	   if(isEmpty())
	   {
		   backwardsTemp = "[]";
	   }
	   else
	   {
		   if(firstNode.next == null)
		   {
			   backwardsTemp = ("[" + firstNode.data + "]");
		   }
		   else
		   {
			   while(tempFirst != null)
			   {
				   
				   temp = temp + tempFirst.data;
				   if(tempFirst.next == null)
				   {
					   break;
				   }
				   tempFirst = tempFirst.next;
			   }
			   for(int i = temp.length()-1; i >= 0; i--)
			   {
				   if(i == temp.length()-1)
				   {
					   backwardsTemp = backwardsTemp + temp.charAt(i);
				   }
				   else if(i == 0)
				   {
					   backwardsTemp = backwardsTemp + ", " + temp.charAt(i) + ']';
				   }
				   else
				   {
					   backwardsTemp = backwardsTemp + ", " + temp.charAt(i);
				   }
			   }
		   }
	   }
	   
   	return backwardsTemp;
   }
   
   
   public boolean isEmpty()
   {
      boolean result;
            
      if (length == 0)
      {
         assert firstNode == null;
         result = true;
      }
      else
      {
         assert firstNode != null;
         result = false;
      }
         
      return result;
   }

   public int getSize()
   {
      return length;
   }

   public void clear()
   {
      firstNode = null;
      length = 0;
   }
   
   
   private class Node
   {
      private T    data; // Entry in priority queue
      private Node next; // Link to next node

      private Node(T dataPortion) 
      {
         data = dataPortion;
         next = null;	
      }
      
      private Node(T dataPortion, Node nextNode) 
      {
         data = dataPortion;
         next = nextNode;	
      }
      
      private T getData()
      {
         return data;
      }
      
      private void setData(T newData)
      {
         data = newData;
      }
      
      private Node getNextNode()
      {
         return next;
      }
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      }
   }
} // end LinkedPriorityQueue