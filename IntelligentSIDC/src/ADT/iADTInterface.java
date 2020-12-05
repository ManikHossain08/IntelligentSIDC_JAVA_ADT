package ADT;

import ADT.LinkListADT.Node;

public interface iADTInterface {
	public void add(long key, long value);
	public boolean remove(long key);
	public long getValues(long key);
	public void allKeys();
	public long nextKey(long key);
	public long prevKey(long key);
	public void rangeKeys(long key1, long key2);
	public LinkedHashEntry[] copyArrayADT();
	public Node copyLinkedListDT();
	public LinkedHashEntry[] copyHashTable();
	public TreeNode[] copyAVLTreeToHashMap();
	public int getSize();
	public void setSize(int size);
	public Node getSortedHead();
}
