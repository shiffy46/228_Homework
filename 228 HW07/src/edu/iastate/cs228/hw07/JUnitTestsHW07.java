package edu.iastate.cs228.hw07;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Iterator;


public class JUnitTestsHW07 {
	
	@Test
	public void LinkedBagHasNext()
	{
		LinkedBag<Object> Bag = new LinkedBag();
		Iterator<Object> bagI = Bag.iterator();
		
		assertFalse(bagI.hasNext());
		Bag.add("A");
		Bag.add("B");
		Iterator<Object> bagI2 = Bag.iterator();
		assertTrue(bagI2.hasNext());
	}
	
	@Test
	public void LinkedBagNext()
	{
		LinkedBag<Object> Bag = new LinkedBag();
		
		Bag.add("A");
		Bag.add("B");
		Bag.add("C");
		Bag.add("D");
		Bag.add("E");
		Bag.add("F");
		
		Iterator<Object> bagI = Bag.iterator();
		assertEquals("F", bagI.next());
		assertEquals("E", bagI.next());
		assertEquals("D", bagI.next());
		assertEquals("C", bagI.next());
		assertEquals("B", bagI.next());
		assertEquals("A", bagI.next());
		
		
	}
	
	@Test
	public void LinkedBagRemove()
	{
		LinkedBag<Object> Bag = new LinkedBag();
		Bag.add("A");
		Bag.add("B");
		Bag.add("C");
		Bag.add("D");
		Bag.add("E");
		Bag.add("F");
		Iterator<Object> bagI = Bag.iterator();
		bagI.remove();
		assertArrayEquals(new String[] {"F","E","D","C","B","A"}, Bag.toArray());
		bagI.next();
		bagI.remove();
		assertArrayEquals(new String[] {"E","D","C","B","A"}, Bag.toArray());
		bagI.next();
		bagI.remove();
		bagI.next();
		bagI.remove();
		bagI.next();
		bagI.remove();
		bagI.next();
		bagI.remove();
		bagI.next();
		bagI.remove();
		bagI.next(); //this is where it will throw exception
		bagI.remove();
		//assertArrayEquals(new String[] {"E","D","B","A"}, Bag.toArray());   BAGs don't keep order so hard to test this.
		
		
		
	}

}
