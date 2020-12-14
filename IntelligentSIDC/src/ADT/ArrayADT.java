package ADT;

import java.util.ArrayList;
import java.util.List;

import ADT.LinkListADT.Node;
import SortingAlgo.RadixSort;

public class ArrayADT implements iADTInterface {

	private static int size = 0;
	private static LinkedHashEntry[] IntelligentArr = null;
	private static LinkedHashEntry[] copyIntelligentArr = null;
	private static List<LinkedHashEntry> rangedIntelligentArr = null;

	public ArrayADT(int arrSize) {
		IntelligentArr = new LinkedHashEntry[arrSize];
		size = 0;
	}

	@Override
	public void add(long key, long value) {
		if (getValues(key) == -1) {
			if (size >= 0)
				try {
					IntelligentArr[size++] = new LinkedHashEntry(key, value);
				} catch (Exception e) {
					// TODO: handle exception
				}

		} else {
			System.out.println("THIS KEY = " + key + " ALREADY EXISTS");
		}
	}

	@Override
	public boolean remove(long key) {
		
		if(getValues(key) == -1) return false;
		
		int index = 0;
		for (int i = 0; i < size; i++) {
			if (key == IntelligentArr[i].getKey()) {
				index = i;
			}
		}
		if (size >= 1) {
			IntelligentArr[index] = IntelligentArr[size - 1];
			IntelligentArr[size - 1] = null;
			size--;
			return true;
		}
		return false;
	}

	@Override
	public long getValues(long key) {
		for (int i = 0; i < size; i++) {
			if (key == IntelligentArr[i].getKey()) {
				return IntelligentArr[i].getValue();
			}
		}
		return -1;
	}

	@Override
	public void allKeys() {
		try {
			long[] allKeys = new long[size];

			for (int i = 0; i < size; i++) {
				allKeys[i] = IntelligentArr[i].getKey();
			}

			allKeys = RadixSort.radixsort(allKeys, allKeys.length);
			for (int i = 0; i < allKeys.length; i++) {
				System.out.print(allKeys[i] + " ");
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println("Data not available for sorting.....");
		}
	}

	@Override
	public long nextKey(long key) {
		for (int i = 0; i < size; i++) {
			if (key == IntelligentArr[i].getKey()) {
				return IntelligentArr[i + 1].getValue();
			}
		}
		return -1;
	}

	@Override
	public long prevKey(long key) {
		for (int i = 0; i < size; i++) {
			if (key == IntelligentArr[i].getKey()) {
				return IntelligentArr[i - 1].getValue();
			}
		}
		return -1;
	}

	@Override
	public void rangeKeys(long key1, long key2) {
		rangedIntelligentArr = new ArrayList<LinkedHashEntry>();

		System.out.println();
		for (int i = 0; i < size; i++) {
			if ((key1 <= IntelligentArr[i].getKey()) && (IntelligentArr[i].getKey() <= key2)) {
				rangedIntelligentArr.add(IntelligentArr[i]);
				// System.out.print(IntelligentArr[i].getKey() + " ");
			}
		}
		System.out.println(rangedIntelligentArr.size() + " student found in this range (" + key1 + " - " + key2 + ")");

	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public LinkedHashEntry[] copyArrayADT() {
		return IntelligentArr;
	}

	public static LinkedHashEntry[] getIntelligentArr() {
		return IntelligentArr;
	}

	public static LinkedHashEntry[] getCompyIntelligentArr() {
		return copyIntelligentArr;
	}

	public static List<LinkedHashEntry> getRangedIntelligentArr() {
		return rangedIntelligentArr;
	}

	@Override
	public Node copyLinkedListDT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getSortedHead() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LinkedHashEntry[] copyHashTable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TreeNode[] copyAVLTreeToHashMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSize(int entry) {
		size = entry;

	}

}
