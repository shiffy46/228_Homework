
package edu.iastate.cs228.hw08;



import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
/**
 * 
 * 
 * A class that implements the ADT dictionary by using a chain of nodes.
 * The dictionary is unsorted and has distinct search keys, i.e., can have 
 * duplicate values, however, those are differentiated based on their keys.
 * 
 * @author Sam Shifflett
 * 
 * NOTEs and REQUIREMENTs:
 * 
 * Exactly same as the ones listed for SortedVectorDictionary class.
 * 
 * In addition to above ANSWER the following 6 QUESTIONS, inside these 
 * comments right below each question. Figures needed to answer questions
 * 3, 4, and 5 are shown on Canvas under description of HW08.
 * 
 * =========================================================================
 * Q1. (a) What is the height of the shortest binary tree that contains 22
 *     nodes? (b) Is this tree full? (c) Is it balanced?
 *     
 * A1. (a)4
 *     (b)No
 *     (c)No
 * =========================================================================
 * Q2. Consider a binary tree that has four levels.
 *     (a) What is the maximum number of nodes in this tree?
 *     (b) What is the maximum number of leaves in this tree?
 *     
 * A2. (a)31
 *     (b)16  
 * =========================================================================
 * Q3. Consider a traversal of a binary tree, which contains Integer data. 
 *     Suppose that visiting a node means to simply display the data in the 
 *     node. What are the results of each of the following traversals of the 
 *     binary tree shown in Figure 1.
 *     (a) Preorder
 *     (b) Postorder
 *     (c) Inorder
 *     (d) Level order
 *     
 * A3. (a)6,4,2,1,3,5,,8,7,9,10,11
 *     (b)1,3,2,5,4,9,7,11,10,8,6
 *     (c)1,2,3,4,5,6,7,9,8,10,11
 *     (d)6,4,8,2,5,7,10,1,3,9,11
 *     
 * =========================================================================
 * Q4. Repeat Q3 but for the binary tree shwn in Figure 2. 
 * A4. (a)11,8,3,2,1,5,4,6,10,9,7
 *     (b)2,1,3,4,6,5,8,9,7,10,11
 *     (c)2,3,1,8,4,5,6,11,9,10,7
 *     (d)11,8,10,3,5,9,7,2,1,4,6
 *  
 * =========================================================================
 * Q5. The two binary trees shown in Figures 1 and 2 contain Integer data.
 *     (a) Is the tree in Figure 1 a binary search tree? Why or why not?
 *     (b) Is the tree in Figure 2 a maxheap? Why or why not?
 *  
 * A5. (a)yes, because 6 is the median integer and the tree is structure in a
 * 		  binary way where the nodes less than the root are to the left and nodes
 * 		  to the right are greater
 *     (b)No, because the leaf nodes are in the incorrect placement under 10
 *     	  and 3.
 *     
 * =========================================================================
 * Q6. Can a binary search tree ever be a maxheap? Explain.
 * A6.                           
 *     yes, the second example of the trees given was a maxheap technically speaking
 *     but so was the first tree, the analogy can be explained like squares and rectangles
 *     such that a rectangle can be a square but a square cannot be a rectangle. 
 *     
 *     
 *     
 *     
 *     
 */
public class LinkedDictionary<K, V> implements DictionaryInterface<K, V>
{
 private Node firstNode;   // Reference to first node of chain
 private int  numberOfEntries; 
	
 public LinkedDictionary()
 {
  firstNode = null;
  numberOfEntries = 0;
 }
	
 public V add(K key, V value)
 {
  if(Objects.isNull(key) || Objects.isNull(value))
	throw new IllegalArgumentException();
  
  Node tempNode = firstNode;
  
  while(tempNode != null && tempNode.key != key)
  {
		tempNode = tempNode.next;  
  }
  if(tempNode != null)
  {
	  V tempV = tempNode.value;
	  tempNode.value = value;
	  return tempV;
  }
  else
  {
	  tempNode = new Node(key, value, firstNode);
	  firstNode = tempNode;
  }		
  numberOfEntries++;
  return null;
 }

 public V remove(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
  
  Node tempNode = firstNode;
  
  if(tempNode.key.equals(key))
  {
	  V tempV1 = firstNode.value;
	  firstNode = firstNode.next;
	  numberOfEntries--;
	  return tempV1;
  }
  else
  {
	  while(tempNode.next != null && !tempNode.next.key.equals(key))
		  tempNode = tempNode.next;
  }
  if(tempNode.next == null)
	  return null;
  else
  {
	  V tempV = tempNode.next.value;
	  tempNode.next = tempNode.next.next;
	  numberOfEntries--;
	  return tempV;
  } 
 }

 public V getValue(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
	   
  Node tempNode = firstNode;
  
  while(tempNode != null)
  {
	  if(tempNode.key.equals(key))
	  {
		  V tempV = tempNode.value;
		  return tempV;
	  }
	  tempNode = tempNode.next;
  }
  return null;
 }

 public boolean contains(K key)
 {
  if(Objects.isNull(key))
	throw new IllegalArgumentException();
		   
  KeyIterator tempKI = new KeyIterator();
  
  while(tempKI.hasNext())
  {
	  K tempKey = tempKI.next();
	  if(tempKey.equals(key))
		  return true;
  }   
  return false; 
 }

 public boolean isEmpty()
 {
  return numberOfEntries == 0;
 }
	
 public int getSize()
 {
  return numberOfEntries;
 }

 public void clear()
 {
	 firstNode = null;
	 numberOfEntries = 0;
 }

 // Needs to output String representation in exact same
 // format as the one done by SortedVectorDictionary.
 public String toString()
 {
	 String temp = new String("[");
	 
	 KeyIterator tempKI = new KeyIterator();
	 ValueIterator tempVI = new ValueIterator();
	 
	 while(tempKI.hasNext())
	 {
		 temp = temp + "(" + tempKI.next() + ":" + tempVI.next() + ")";
		 if(tempVI.hasNext())
			 temp = temp + ", ";		 
	 }
	 
	 temp = temp + "]";
	 
  return temp;
 }

 public Iterator<K> getKeyIterator()
 {
  return new KeyIterator();
 }
	
 public Iterator<V> getValueIterator()
 {
  return new ValueIterator();
 }

 private class KeyIterator implements Iterator<K>
 {
  private Node nextNode;
		
  private KeyIterator()
  {
	  nextNode = firstNode;
  }
		
  public boolean hasNext() 
  {  
   return nextNode != null;
  }
		
  public K next()
  {
	  if(nextNode == null)
		  throw new NoSuchElementException();
	  K tempK = nextNode.key;
	  nextNode = nextNode.next;
   return tempK;
  }
 } 
	
 private class ValueIterator implements Iterator<V>
 {
  private Node nextNode;
  		
  private ValueIterator()
  {
	  nextNode = firstNode;
  }
		
  public boolean hasNext() 
  {	
   return nextNode != null;
  }
		
  public V next()
  {	
	  if(nextNode == null)
		  throw new NoSuchElementException();
	  V tempV = nextNode.value;
	  nextNode = nextNode.next;
   return tempV;
  }
 }

 private class Node
 {
  private K key;
  private V value;
  private Node next;

  private Node(K searchKey, V dataValue)
  {
   key = searchKey;
   value = dataValue;
   next = null;	
  }
		
  private Node(K searchKey, V dataValue, Node nextNode)
  {
   key = searchKey;
   value = dataValue;
   next = nextNode;	
  }
		
  private K getKey()
  {
   return key;
  }
		
  private V getValue()
  {
   return value;
  }

  private void setValue(V newValue)
  {
   value = newValue;
  }

  private Node getNextNode()
  {
   return next;
  }
		
  private void setNextNode(Node nextNode)
  {
   next = nextNode;
  }
  
  public String toString()
  {
   return "("+key+":"+value+")";	
  }
 }
}
		
