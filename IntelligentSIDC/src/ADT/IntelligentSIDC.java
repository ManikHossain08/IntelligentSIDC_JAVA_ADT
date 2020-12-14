package ADT;

import java.util.Random;

import ADT.LinkListADT.Node;

public class IntelligentSIDC {

	public static iADTInterface univeralDBTable = null;
	public static iADTInterface usedADT = null;
	public static final int SAMLL= 500; // range: 1 to 500
	public static final int MEDIUM= 10000; // range: 501 to 10,000
	public static final int LARGE= 100000; // range: 10,001 to 100,000
	public static final int EXTRALARGE= 5000000; // range: 100,001 to infinity

	public void SetSIDCThreshold(int thresHold) {
		if (thresHold <= SAMLL) { // ARRAY ADT
			univeralDBTable = new ArrayADT(SAMLL+1);
		}
		else if (SAMLL < thresHold && thresHold <= MEDIUM) { // LINKED LIST ADT
			univeralDBTable = new LinkListADT();
		} else if (MEDIUM < thresHold && thresHold <= LARGE) { // HASH TABLE WITH LINKED LIST ADT
			univeralDBTable = new HashTableADT(MEDIUM+1);
		} else { // HASH TABLE WITH AVL TREE ADT
			univeralDBTable = new AVLTreeADT(LARGE+1);
		}
	
	}

	public iADTInterface dynamicallyShrinkingADTs(int size) {  

		if (size <= SAMLL) { // ARRAY
			if (size == 0 && univeralDBTable.getClass().getName().contentEquals("ADT.ArrayADT")) {
				univeralDBTable = new ArrayADT(501);
			}
			if (size == SAMLL && univeralDBTable.getClass().getName().contentEquals("ADT.LinkListADT")) {
				//univeralDBTable.allKeys();
				Node llHead = univeralDBTable.copyLinkedListDT();
				univeralDBTable = new ArrayADT(501);
				copyLinkListADTToArrayADT(llHead, size);
			}

		} else if (SAMLL < size && size <= MEDIUM) { // LINKED LIST
			if (size == SAMLL+1 && univeralDBTable.getClass().getName().contentEquals("ADT.ArrayADT")) {
				//univeralDBTable.allKeys();
				LinkedHashEntry[] arrayDB = univeralDBTable.copyArrayADT();
				univeralDBTable = new LinkListADT();
				copyArrayADTToLinkedListADT(arrayDB, size);
			}
			if (size == MEDIUM && univeralDBTable.getClass().getName().contentEquals("ADT.HashTableADT")) {
				//univeralDBTable.allKeys();
				LinkedHashEntry[] hashTable = univeralDBTable.copyHashTable();
				univeralDBTable = new LinkListADT();
				copyHashADTToLinkedListADT(hashTable, size);
			}
		} else if (MEDIUM < size && size <= LARGE) { // HASH TABLE
			if (size == MEDIUM+1 && univeralDBTable.getClass().getName().contentEquals("ADT.LinkListADT")) {
				//univeralDBTable.allKeys();
				Node llHead = univeralDBTable.copyLinkedListDT();
				univeralDBTable = new HashTableADT(MEDIUM+1);
				copyLinkListADTToHashTableADT(llHead, size);
			} else if (size == LARGE && univeralDBTable.getClass().getName().contentEquals("ADT.AVLTreeADT")) {
				//univeralDBTable.allKeys();
				TreeNode[] avlTree = univeralDBTable.copyAVLTreeToHashMap();
				univeralDBTable = new HashTableADT(MEDIUM+1);
				copyAVLTreeToHashTableADT(avlTree, size);
			}
		} else { //EXTRALARGE: AVL TREE
			if (size == LARGE+1 && univeralDBTable.getClass().getName().contentEquals("ADT.HashTableADT")) {
				//univeralDBTable.allKeys();
				LinkedHashEntry[] avlTable = univeralDBTable.copyHashTable();
				univeralDBTable = new AVLTreeADT(LARGE+1);
				copyHashTableADTToAvlTreeADT(avlTable, size);
			}
		}

		return univeralDBTable;
	}

	public long generate() {
		Random rnd = new Random();
		int id = 10000000 + rnd.nextInt(99999999);
		return id;
	}

	public IntelligentSIDC allkeys() {
		univeralDBTable.allKeys();
		return null;
	}

	public void add(long key, long value) {
		univeralDBTable.add(key, value);
	}

	public boolean remove(long key) {
		return univeralDBTable.remove(key);
	}

	public long getValues(long key) {
		return univeralDBTable.getValues(key);
	}

	public long nextKey(long key) {
		return univeralDBTable.nextKey(key);
	}

	public long prevKey(long key) {
		return univeralDBTable.prevKey(key);
	}

	public void rangeKeys(long key1, long key2) {
		univeralDBTable.rangeKeys(key1, key2);
	}

	public int getSize() {
		return univeralDBTable.getSize();
	}

	public TreeNode[] getFullAVLDB() {
		return univeralDBTable.copyAVLTreeToHashMap();
	}

	private void copyLinkListADTToArrayADT(Node llHead, int size) {

		if (llHead == null)
			return;
		Node t = llHead;
		while (t != null) {
			univeralDBTable.add(t.getEntry().getKey(), t.getEntry().getKey());
			t = t.getNext();
		}

	}

	private void copyLinkListADTToHashTableADT(Node llHead, int size) {

		if (llHead == null)
			return;
		Node t = llHead;
		while (t != null) {
			univeralDBTable.add(t.getEntry().getKey(), t.getEntry().getKey());
			t = t.getNext();
		}

	}

	private void copyArrayADTToLinkedListADT(LinkedHashEntry[] arrayDB, int size) {
		for (LinkedHashEntry linkedHashEntry : arrayDB) {
			if (linkedHashEntry != null)
				univeralDBTable.add(linkedHashEntry.getKey(), linkedHashEntry.getValue());
		}

	}

	private void copyHashTableADTToAvlTreeADT(LinkedHashEntry[] avlTable, int size) {

		for (int i = 0; i < avlTable.length; i++) {
			LinkedHashEntry entry = avlTable[i];
			while (entry != null) {
				if (entry.getKey() > 0) {
					univeralDBTable.add(entry.getKey(), entry.getKey());
					entry = entry.getNext();

				}
			}
		}

	}

	private void copyAVLTreeToHashTableADT(TreeNode[] avlTree, int size) {
		for (int i = 0; i < avlTree.length; i++) {
			TreeNode entry = avlTree[i];
			if (entry != null)
				avlTreeTraversalToCopy(entry);
			else
				continue;
		}
	}

	static void avlTreeTraversalToCopy(TreeNode root) {

		if (root == null)
			return;

		avlTreeTraversalToCopy(root.left);
		if (root != null && root.value > 0)
			univeralDBTable.add(root.getValue(), root.getValue());
		avlTreeTraversalToCopy(root.right);

	}

	private void copyHashADTToLinkedListADT(LinkedHashEntry[] hashTable, int size) {
		for (int i = 0; i < hashTable.length; i++) {
			LinkedHashEntry entry = hashTable[i];
			if (entry != null)
				univeralDBTable.add(entry.getValue(), entry.getValue());
		}

	}

}
