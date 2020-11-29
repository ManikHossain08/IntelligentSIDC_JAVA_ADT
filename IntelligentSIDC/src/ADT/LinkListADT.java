package ADT;

import SortingAlgo.RadixSort;

public class LinkListADT implements iADTInterface {

	public class Node {

		private LinkedHashEntry entry;
		private Node next;

		public Node(LinkedHashEntry val, Node nxt) {
			entry = val;
			next = nxt;
		}

		public LinkedHashEntry getEntry() {
			return entry;
		}

		public void setEntry(LinkedHashEntry entry) {
			this.entry = entry;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	private static Node head;
	private static Node sortedHead;
	private static Node rangeHead;
	private static int size = 0;

	public LinkListADT() {
		head = null;
		size = 0;
	}

	public int getLLSize() {
		return size;
	}

	@Override
	public void add(long key, long value) {
		LinkedHashEntry val = new LinkedHashEntry(key, value);
		Node n = new Node(val, head);
		head = n;
		n = null;
		size++;

	}

	@Override
	public long getValues(long key) {
		if (head == null)
			return -1;
		Node t = head;
		while (t != null) {
			if (t.entry.getKey() == key)
				return t.entry.getValue();
			t = t.next;
		}
		return -1;
	}

	@Override
	public boolean remove(long key) {
		if (head == null)
			return false;

		Node t = head;
		if (t != null && t.entry.getKey() == key)
			return true;

		while (t != null && t.next != null && t.next.entry.getKey() != key) {
			t = t.next;
		}
		t.next = t.next.next;
		size--;

		return true;
	}

	public void SortAllKeys(Node new_node) {
		Node current;

		if (sortedHead == null || sortedHead.entry.getKey() >= new_node.entry.getKey()) {
			new_node.next = sortedHead;
			head = new_node;
		} else {
			current = sortedHead;

			while (current.next != null && current.next.entry.getKey() < new_node.entry.getKey())
				current = current.next;

			new_node.next = current.next;
			current.next = new_node;
		}
	}

	@Override
	public void allKeys() {
		//Node temp = head;
//		sortedHead = null;
//
//		if (temp == null)
//			return;
//
//		while (temp != null) {
//			SortAllKeys(new Node(temp.entry, sortedHead));
//			temp = temp.next;
//		}
		
		sortedLinkedList(head);
	}
	
	private static void sortedLinkedList(Node head) {
		
		long[] allKeys = new long[size];
		Node sort = head;
		int i = 0;
		while (sort != null) {
			allKeys[i] = sort.getEntry().getKey();
			sort = sort.getNext();
			i++;
		}
		
		allKeys = RadixSort.radixsort(allKeys, allKeys.length);
		for (int j = 0; j < allKeys.length; j++) {
			System.out.print(allKeys[j] +" "); 
		}
		System.out.println(); 
		
	}

	@Override
	public void rangeKeys(long key1, long key2) {
		Node temp = head;
		rangeHead = null;

		if (temp == null)
			return;
		System.out.println();
		while (temp != null) {
			if (key1 <= temp.entry.getKey() && temp.entry.getKey() <= key2) {
				addToRange(temp.entry.getKey(), temp.entry.getValue(), rangeHead);
				System.out.print(temp.entry.getKey() +" "); 
			}
			temp = temp.next;
		}

	}

	public void addToRange(long key, long value, Node rangeHead) {
		LinkedHashEntry val = new LinkedHashEntry(key, value);
		Node n = new Node(val, rangeHead);
		rangeHead = n;
		n = null;
	}

	@Override
	public long prevKey(long key) {
		if (head == null)
			return -1;

		Node t = head;
		if (t.entry.getKey() == key)
			return -1;

		while (t != null && t.next != null && t.next.entry.getKey() != key) {
			t = t.next;
		}

		return t.entry.getKey();
	}

	@Override
	public long nextKey(long key) {
		if (head == null)
			return -1;

		Node t = head;

		while (t != null && t.next != null && t.next.entry.getKey() != key) {
			t = t.next;
		}

		return t.next.next.entry.getKey();
	}

	public Node getHead() {
		return head;
	}

	@Override
	public Node getSortedHead() {
		return sortedHead;
	}

	public Node getRangeHead() {
		return rangeHead;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public LinkedHashEntry[] copyArrayADT() {
		return null;
	}

	@Override
	public Node copyLinkedListDT() {
		return head;
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

}
