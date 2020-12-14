package ADT;

import ADT.LinkListADT.Node;
import SortingAlgo.RadixSort;

public class HashTableADT implements iADTInterface {

	private static int SIZE = 100; // use prime number to reduce collision like 1001, 101
	private static int entrySize = 0;
	LinkedHashEntry[] table;
	private static long[] allKeys;
	private static int rangedCounter = 0;

	public HashTableADT(int size) {
		SIZE = size;
		table = new LinkedHashEntry[SIZE];
		entrySize = 0;
		rangedCounter = 0;
	}

	public HashTableADT allkeys() {
		return null;
	}

	public long getValues(long key) {
		int hash = (int) (key % SIZE);
		if (table[hash] == null)
			return -1;
		else {
			LinkedHashEntry entry = table[hash];
			while (entry != null && entry.getKey() != key)
				entry = entry.getNext();
			if (entry == null)
				return -1;
			else
				return entry.getValue();
		}
	}

	public void add(long key, long value) {
		int hash = (int) (key % SIZE);
		if (table[hash] == null) {
			table[hash] = new LinkedHashEntry(key, value);
			entrySize++;
		} else {
			LinkedHashEntry entry = table[hash];
			while (entry.getNext() != null && entry.getKey() != key)
				entry = entry.getNext();
			if (entry.getKey() == key) {
				// entry.setValue(value);
				System.out.println("THIS KEY = " + key + " ALREADY EXISTS");

			} else {
				entry.setNext(new LinkedHashEntry(key, value));
				entrySize++;
			}
		}

	}

	public boolean remove(long key) {
		int hash = (int) (key % SIZE);
		if (table[hash] != null) {
			LinkedHashEntry prevEntry = null;
			LinkedHashEntry entry = table[hash];
			while (entry.getNext() != null && entry.getKey() != key) {
				prevEntry = entry;
				entry = entry.getNext();
			}
			if (entry.getKey() == key) {
				if (prevEntry == null)
					table[hash] = entry.getNext();
				else
					prevEntry.setNext(entry.getNext());
				entrySize--;
				return true;
			}
		}
		return false;
	}

	public long nextKey(long key) {
		int hash = (int) (key % SIZE);
		LinkedHashEntry entry = table[hash];

		if (entry == null)
			return -1;
		if (entry.getKey() == key && entry.getNext() != null)
			return entry.getNext().getKey();

		while (entry != null && entry.getNext() != null && entry.getNext().getKey() != key) {

			entry = entry.getNext();
		}

		if (entry == null)
			return -1;
		else if (entry.getNext() != null){
			if (entry.getNext().getNext() != null)
				return entry.getNext().getNext().getKey();
			else return -1;
		}else return -1;

	}

	@Override
	public long prevKey(long key) {
		int hash = (int) (key % SIZE);
		LinkedHashEntry entry = table[hash];

		if (entry == null || (entry != null && entry.getKey() == key))
			return -1;

		while (entry != null && entry.getNext() != null && entry.getNext().getKey() != key) {

			entry = entry.getNext();
		}

		return entry.getKey();

	}

	@Override
	public void allKeys() {
		allKeys = new long[entrySize];
		System.out.println();
		int counter = 0;
		for (int i = 0; i < SIZE; i++) {
			LinkedHashEntry entry = table[i];
			while (entry != null) {
				if (entry.getKey() > 0) {
					allKeys[counter] = entry.getKey();
					entry = entry.getNext();
					counter++;
				}
			}
		}
		sortedLinkedList();
	}

	private static void sortedLinkedList() {
		allKeys = RadixSort.radixsort(allKeys, allKeys.length);
		for (int j = 0; j < allKeys.length; j++) {
			if (allKeys[j] > 0)
				System.out.print(allKeys[j] + " ");
		}
		System.out.println();

	}

	@Override
	public void rangeKeys(long k1, long k2) {
		rangedCounter = 0;
		for (int i = 0; i < SIZE; i++) {
			LinkedHashEntry entry = table[i];
			while (entry != null) {
				long storedVal = entry.getValue();
				if (k1 <= storedVal && storedVal <= k2) {
					rangedCounter++;
				}
				entry = entry.getNext();
			}
			
		}
		System.out.println(rangedCounter + " student found in this range (" + k1 + " - " + k2 + ")");

	}

	@Override
	public int getSize() {

		return entrySize;
	}

	@Override
	public LinkedHashEntry[] copyArrayADT() {

		return null;
	}

	@Override
	public Node copyLinkedListDT() {

		return null;
	}

	@Override
	public Node getSortedHead() {
		return null;
	}

	@Override
	public LinkedHashEntry[] copyHashTable() {
		return table;
	}

	@Override
	public TreeNode[] copyAVLTreeToHashMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSize(int size) {
		entrySize = size;

	}

}
