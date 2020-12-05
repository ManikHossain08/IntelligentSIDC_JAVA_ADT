package ADT;

import java.util.Random;

import ADT.LinkListADT.Node;

public class IntelligentSIDC {

	public static iADTInterface univeralDBTable = null;

	public iADTInterface SetSIDCThreshold(int size, iADTInterface whichADT) {

		univeralDBTable = whichADT;
		if (size <= 500) { // ARRAY
			if (size == 0 && whichADT.getClass().getName().contentEquals("ADT.ArrayADT")) {
				univeralDBTable = new ArrayADT(501);
			}
			if (size == 500 && whichADT.getClass().getName().contentEquals("ADT.LinkListADT")) {
				univeralDBTable.allKeys();
				Node llHead = univeralDBTable.copyLinkedListDT();
				univeralDBTable = new ArrayADT(501);
				copyLinkListADTToArrayADT(llHead, size);
			}

		} else if (500 < size && size <= 10000) { // LINKED LIST
			if (size == 501 && whichADT.getClass().getName().contentEquals("ADT.ArrayADT")) {
				univeralDBTable.allKeys();
				LinkedHashEntry[] arrayDB = univeralDBTable.copyArrayADT();
				univeralDBTable = new LinkListADT();
				copyArrayADTToLinkedListADT(arrayDB, size);
			}
			if (size == 10000 && whichADT.getClass().getName().contentEquals("ADT.HashTableADT")) {
				univeralDBTable.allKeys();
				LinkedHashEntry[] hashTable = univeralDBTable.copyHashTable();
				univeralDBTable = new LinkListADT();
				copyHashADTToLinkedListADT(hashTable, size);
			}
		} else if (10000 < size && size <= 20000) { // HASH TABLE
			if (size == 10001 && whichADT.getClass().getName().contentEquals("ADT.LinkListADT")) {
				univeralDBTable.allKeys();
				Node llHead = univeralDBTable.copyLinkedListDT();
				univeralDBTable = new HashTableADT(1001);
				copyLinkListADTToHashTableADT(llHead, size);
			} else if (size == 20000 && whichADT.getClass().getName().contentEquals("ADT.AVLTreeADT")) {
				univeralDBTable.allKeys();
				TreeNode[] avlTree = univeralDBTable.copyAVLTreeToHashMap();
				univeralDBTable = new HashTableADT(1001);
				copyAVLTreeToHashTableADT(avlTree, size);
			}
		} else { // AVL TREE
			if (size == 20001 && whichADT.getClass().getName().contentEquals("ADT.HashTableADT")) {
				univeralDBTable.allKeys();
				LinkedHashEntry[] avlTable = univeralDBTable.copyHashTable();
				univeralDBTable = new AVLTreeADT(2001);
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
