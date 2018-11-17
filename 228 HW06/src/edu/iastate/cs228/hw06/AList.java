package edu.iastate.cs228.hw06;

/**

   A class that implements the ADT list by using a resizable array.
   Entries in a list have positions that begin with 1.
   Duplicate entries are allowed.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 4.0
*/

import java.util.Arrays;
import java.util.NoSuchElementException;

public class AList<T> extends CustomClass<T>
{
	private T[] list; // Array of list entries; ignore list[0]
	private int numberOfEntries;
	private boolean initialized = false;
	private static final int DEFAULT_CAPACITY = 25;
	private static final int MAX_CAPACITY = 10000;

	public AList()
	{
		this(DEFAULT_CAPACITY);
	} // end default constructor

	public AList(int initialCapacity)
	{
		// Is initialCapacity too small?
		if (initialCapacity < DEFAULT_CAPACITY)
			initialCapacity = DEFAULT_CAPACITY;
		else // Is initialCapacity too big?
			checkCapacity(initialCapacity);

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] tempList = (T[]) new Object[initialCapacity + 1];
		list = tempList;
		numberOfEntries = 0;
		initialized = true;
	} // end constructor

	public int getLength()
	{
		return list.length;
	}

	public void add(T newEntry)
	{
		checkInitialization();
		list[numberOfEntries + 1] = newEntry;
		numberOfEntries++;
		ensureCapacity();
		// add(numberOfEntries + 1, newEntry); // ALTERNATE CODE
	} // end add

	
	public void clear()
	{ /* < Implementation deferred > */
		for (int index = 1; index <= numberOfEntries; index++)
		{
			list[index + 1] = null;
		} // end for
	} // end clear

	
	public T[] toArray()
	{
		checkInitialization();

		// The cast is safe because the new array contains null entries
		@SuppressWarnings("unchecked")
		T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
		for (int index = 0; index < numberOfEntries; index++)
		{
			result[index] = list[index + 1];
		} // end for
		return result;
	} // end toArray

	

	public int getSize()
	{
		return numberOfEntries;
	} // end getLength

	public boolean isEmpty()
	{
		return numberOfEntries == 0; // Or getLength() == 0
	} // end isEmpty

	// Doubles the capacity of the array list if it is full.
	// Precondition: checkInitialization has been called.
	private void ensureCapacity()
	{
		int capacity = list.length - 1;
		if (numberOfEntries >= capacity)
		{
			int newCapacity = 2 * capacity;
			checkCapacity(newCapacity); // Is capacity too big?
			list = Arrays.copyOf(list, newCapacity + 1);
		} // end if
	} // end ensureCapacity

	/*
	 * < This class will define checkCapacity, checkInitialization, and two more
	 * private methods that will be discussed later. >
	 */
	private void checkInitialization()
	{
		if (!initialized)
			throw new SecurityException("AList object is not initialized properly!");
	}

	private void checkCapacity(int capacity)
	{
		if (capacity > MAX_CAPACITY)
			throw new IllegalStateException(
					"Attempt to create a list whose capacity " + "exceeds allowed maximum of " + MAX_CAPACITY);
	}

	// Precondition: The array list has room for another entry.
	public void add(int newPosition, T newEntry)
	{
		checkInitialization();
		if ((newPosition >= 1) && (newPosition <= numberOfEntries + 1))
		{
			if (newPosition <= numberOfEntries)
				makeRoom(newPosition);
			list[newPosition] = newEntry;
			numberOfEntries++;
			ensureCapacity(); // Ensure enough room for next add
		} else
			throw new IndexOutOfBoundsException("Given position of add's new entry is out of bounds.");
	} // end add

	// Makes room for a new entry at newPosition.
	// Precondition: 1 <= newPosition <= numberOfEntries + 1;
	// numberOfEntries is list's length before addition;
	// checkInitialization has been called.
	private void makeRoom(int newPosition)
	{
		assert (newPosition >= 1) && (newPosition <= numberOfEntries + 1);
		int newIndex = newPosition;
		int lastIndex = numberOfEntries;
		// Move each entry to next higher index, starting at end of
		// list and continuing until the entry at newIndex is moved
		for (int index = lastIndex; index >= newIndex; index--)
			list[index + 1] = list[index];
	} // end makeRoom

	public T remove(int givenPosition)
	{
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
			T result = list[givenPosition]; // Get entry to be removed
			// Move subsequent entries towards entry to be removed,
			// unless it is last in list
			if (givenPosition < numberOfEntries)
				removeGap(givenPosition);
			numberOfEntries--;
			return result;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
	} // end remove

	// Shifts entries that are beyong the entry to be removed to the
	// next lower position.
	// Precondition: 1 <= givenPosition < numberOfEntries;
	// numberOfEntries is list's length before removal;
	// checkInitialization has been called.
	private void removeGap(int givenPosition)
	{
		assert (givenPosition >= 1) && (givenPosition < numberOfEntries);

		int removedIndex = givenPosition;
		int lastIndex = numberOfEntries;
		for (int index = removedIndex; index < lastIndex; index++)
			list[index] = list[index + 1];
	}

	public T replace(int givenPosition, T newEntry)
	{
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
			T originalEntry = list[givenPosition];
			list[givenPosition] = newEntry;
			return originalEntry;
		} else
			throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
	} // end replace

	public T getEntry(int givenPosition)
	{
		checkInitialization();
		if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
		{
			assert !isEmpty();
			return list[givenPosition];
		} else
			throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
	} // end getEntry

	public boolean contains(T anEntry)
	{
		checkInitialization();
		boolean found = false;
		int index = 1;
		while (!found && (index <= numberOfEntries))
		{
			if (anEntry.equals(list[index]))
				found = true;
			index++;
		} // end while
		return found;
	} // end contains
	

	public void addFirst(T entry)
	{
		super.addFirst(entry);
	}
	
	@Override
	public T removeFirst()
	{
		if(list[1] != null)
		{
			return this.remove(1);
		}
		else
			throw new NoSuchElementException();
	}
	
	@Override
	public void addLast(T newEntry)
	{
		this.add(numberOfEntries+1, newEntry);
	}
	
	@Override
	public T removeLast()
	{
		if(list[numberOfEntries] != null)
		{
			return this.remove(numberOfEntries);
		}
		else
			throw new NoSuchElementException();
	}
	
	@Override
	public T getFirst()
	{
		if(list[1] != null)
		{
			return list[1];
		}
		else
			throw new NoSuchElementException();
	}
	
	@Override
	public T getLast()
	{
		if(list[numberOfEntries] != null)
		{
			return list[numberOfEntries];
		}
		else
			throw new NoSuchElementException();
	}
	
	@Override
	public void moveToEnd()
	{
		T temp = list[1];
		this.removeFirst();
		list[numberOfEntries+1] = temp;
		numberOfEntries++;
		
	}
	
	@Override
	public boolean remove(T anEntry)
	{
		int position = this.getPosition(anEntry);
		if(position != -1)
		{
			this.remove(position);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public int getPosition(T anEntry)
	{
		T temp = anEntry;
		int i = 1;
		while(i <= numberOfEntries)
		{
			if(temp.equals(list[i]))
			{
				return i;
			}
			i++;
		}
		return -1;
	}
} // end AList