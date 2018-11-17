/**
 * @author Micah Mundy
 */
package edu.iastate.cs228.hw05;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class PriorityQueueTest {

	PriorityQueueInterface<Integer> q1;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		/*Uncomment to test the array priority queue*/
		//q1 = new ArrayPriorityQueue<Integer>(); 
		
		
		/*Uncomment to test the linked priority queue*/
		q1 = new LinkedPriorityQueue<Integer>();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		q1 = null;
	}

	/**
	 * Tests all methods in (hopefully) every way possible
	 */
	@Test
	public void test() {
		//Populate the queue
		q1.add(1);
		q1.add(32);
		q1.add(32);
		q1.add(32);
		q1.add(195);
		q1.add(144);
		q1.add(7500);
		
		
		//Run tests on queues with multiple elements
		assertTrue("Were all the elements added correctly?",
				q1.getSize()==7);
		assertFalse("The queue should have been populated, but isEmpty() returns true", 
				q1.isEmpty());
		assertTrue("Both the peeked element and the removed element should be the same", 
				q1.peek().equals(q1.remove()));
		assertTrue("The element with the highest priority has not been removed", 
				q1.remove().equals(195));
		assertFalse("The queue is not empty", 
				q1.isEmpty());
		assertTrue("Check the toString method",
				q1.toString().equals("[1, 32, 32, 32, 144]"));
		assertTrue("The size method didn't return the expected size of the queue",
				q1.getSize()==5);
		//Clear the queue
		int i = 0;
		while(!q1.isEmpty()) {q1.remove(); i++;}
		assertTrue("There are elements in this queue that have been added multiple "
				+ "times or haven't been removed the first time", i==5);
		
		
		//Try operations on empty queues
		assertTrue("The queue is empty, but still has a nonzero size",
				q1.getSize()==0);
		assertNull("Empty queues should return null of users attmpt to remove elements from them \n"
				+ "Please refer to the lecture notes at "
				+ "https://canvas.iastate.edu/courses/53780/pages/lecture-notes", 
				q1.remove());
		assertNull("Empty queues should return null of users attmpt to remove elements from them \n"
				+ "Please refer to the lecture notes at "
				+ "https://canvas.iastate.edu/courses/53780/pages/lecture-notes", 
				q1.peek());
		
		
		//Try operations on a priority queue with a single element
		q1.add(0xC0DE);
		assertTrue("Check the add() method",
				q1.getSize()==1);
		assertFalse("Check the add() method",
				q1.isEmpty());
		assertTrue("Check your implementation of the \"peek()\" method", 
				q1.peek().equals(49374));
		assertTrue("Check your implementation of the \"toString()\" method",
				q1.toString().equals("[49374]"));
		assertTrue("Check your implementation of the \"remove()\" method", 
				q1.remove().equals(49374));
		assertTrue("The size method didn't return the expected size of the queue",
				q1.getSize()==0);
		assertTrue("The queue has been cleared of all contents, but isEmpty() returns false",
				q1.isEmpty());
		assertNull("Empty queues should return null of users attmpt to remove elements from them \n"
				+ "Please refer to the lecture notes at "
				+ "https://canvas.iastate.edu/courses/53780/pages/lecture-notes", 
				q1.remove());
		
		
		//Try overloading the queue if it's an array
		if(q1 instanceof ArrayPriorityQueue) {
			q1.clear(); //robust but redundant: q1 should already be cleared
			//Attempt to add one more element
			try{
				for(i = 10000; i > 0; i++) q1.add(i);
				fail("MAX_CAPACITY has been modified or the queue has exceeded its maximum capacity");
			}catch(IllegalStateException e) {}
			//Attempt to define a new queue with a size that is greater than MAX_CAPACITY
			try{
				q1 = new ArrayPriorityQueue<Integer>(10001);
				fail("MAX_CAPACITY has been modified or the queue has exceeded its maximum capacity");
			}catch(IllegalStateException e) {}
			//Attempt to add a null entry
			try{
				q1 = new ArrayPriorityQueue<Integer>();
				q1.add(0xDEADBEEF); //Good one Kyle ;)
				q1.add(null);
				fail("See the Piazza discussion about adding null entries");
			}catch(NullPointerException e) {}
		}
	}
}