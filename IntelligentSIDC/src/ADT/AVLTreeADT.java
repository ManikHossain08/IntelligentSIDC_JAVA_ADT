package ADT;

import ADT.LinkListADT.Node;
import SortingAlgo.RadixSort;

public class AVLTreeADT implements iADTInterface {

	private static int SIZE = 100; // use prime number to reduce collision like 1001, 101
	TreeNode[] table;
	private static long[] allKeys;
	private static int entrySize = 0;
	private static int counter = 0;
	private static int rangedCounter = 0;
	private static long prevKey, nextKey = -1;

	public AVLTreeADT(int size) {
		SIZE = size;
		table = new TreeNode[SIZE];
		entrySize = 0;
	}

	private int height(TreeNode N) {
		if (N == null)
			return 0;
		return N.height;
	}

	@Override
	public void add(long key, long value) {
		int hash = (int) (key % SIZE);
		if (table[hash] == null)
			table[hash] = new TreeNode(key);
		else {
			TreeNode entry = table[hash];
			insert(entry, key);
		}
		entrySize++;
	}

	private TreeNode insert(TreeNode node, long value) {
		/* 1. Perform the normal BST rotation */
		if (node == null) {
			return (new TreeNode(value));
		}

		if (value < node.value)
			node.left = insert(node.left, value);
		else
			node.right = insert(node.right, value);

		/* 2. Update height of this ancestor node */
		node.height = Math.max(height(node.left), height(node.right)) + 1;

		/*
		 * 3. Get the balance factor of this ancestor node to check whether this node
		 * became unbalanced
		 */
		int balance = getBalance(node);

		// If this node becomes unbalanced, then there are 4 cases

		// Left Left Case
		if (balance > 1 && value < node.left.value)
			return rightRotate(node);

		// Right Right Case
		if (balance < -1 && value > node.right.value)
			return leftRotate(node);

		// Left Right Case
		if (balance > 1 && value > node.left.value) {
			node.left = leftRotate(node.left);
			return rightRotate(node);
		}

		// Right Left Case
		if (balance < -1 && value < node.right.value) {
			node.right = rightRotate(node.right);
			return leftRotate(node);
		}

		/* return the (unchanged) node pointer */
		return node;
	}

	private TreeNode rightRotate(TreeNode y) {
		TreeNode x = y.left;
		TreeNode T2 = x.right;

		// Perform rotation
		x.right = y;
		y.left = T2;

		// Update heights
		y.height = Math.max(height(y.left), height(y.right)) + 1;
		x.height = Math.max(height(x.left), height(x.right)) + 1;

		// Return new root
		return x;
	}

	private TreeNode leftRotate(TreeNode x) {
		TreeNode y = x.right;
		TreeNode T2 = y.left;

		// Perform rotation
		y.left = x;
		x.right = T2;

		// Update heights
		x.height = Math.max(height(x.left), height(x.right)) + 1;
		y.height = Math.max(height(y.left), height(y.right)) + 1;

		// Return new root
		return y;
	}

	// Get Balance factor of node N
	private int getBalance(TreeNode N) {
		if (N == null)
			return 0;
		return height(N.left) - height(N.right);
	}

	public void preOrder(TreeNode root) {
		if (root != null) {
			preOrder(root.left);
			System.out.printf("%d ", root.value);
			preOrder(root.right);
		}
	}

	private TreeNode minValueNode(TreeNode node) {
		TreeNode current = node;
		/* loop down to find the leftmost leaf */
		while (current.left != null)
			current = current.left;
		return current;
	}

	@Override
	public long getValues(long key) {
		int hash = (int) (key % SIZE);
		TreeNode root = table[hash];

		if (root == null)
			return -1;
		else if (root.value == key) {
			return key;
		} else {
			if (findNode(root, key, -1) != -1)
				return key;
			else
				return -1;
		}

	}

	private long findNode(TreeNode root, long key, long value) {
		if (root == null)
			return -1;
		findNode(root.left, key, value);
		if (root != null && root.value == key)
			return value = root.value;
		findNode(root.right, key, value);

		return value;
	}

	@Override
	public boolean remove(long key) {
		int hash = (int) (key % SIZE);
		if (table[hash] != null) {
			table[hash] = deleteNode(table[hash], key);
			entrySize--;
			return true;
		} else
			return false;
	}

	public TreeNode deleteNode(TreeNode root, long value) {

		if (root == null)
			return root;

		if (value < root.value)
			root.left = deleteNode(root.left, value);
		else if (value > root.value)
			root.right = deleteNode(root.right, value);
		else {

			if ((root.left == null) || (root.right == null)) {

				TreeNode temp;
				if (root.left != null)
					temp = root.left;
				else
					temp = root.right;

				// No child case
				if (temp == null) {
					temp = root;
					root = null;
				} else // One child case
					root = temp; // Copy the contents of the non-empty child

				temp = null;
			} else {
				TreeNode temp = minValueNode(root.right);

				root.value = temp.value;

				root.right = deleteNode(root.right, temp.value);
			}
		}

		if (root == null)
			return root;

		// STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
		root.height = Math.max(height(root.left), height(root.right)) + 1;

		// STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
		// this node became unbalanced)
		int balance = getBalance(root);

		// If this node becomes unbalanced, then there are 4 cases

		// Left Left Case
		if (balance > 1 && getBalance(root.left) >= 0)
			return rightRotate(root);

		// Left Right Case
		if (balance > 1 && getBalance(root.left) < 0) {
			root.left = leftRotate(root.left);
			return rightRotate(root);
		}

		// Right Right Case
		if (balance < -1 && getBalance(root.right) <= 0)
			return leftRotate(root);

		// Right Left Case
		if (balance < -1 && getBalance(root.right) > 0) {
			root.right = rightRotate(root.right);
			return leftRotate(root);
		}

		return root;
	}

	@Override
	public void allKeys() {
		allKeys = new long[SIZE];
		counter = 0;
		for (int i = 0; i < SIZE; i++) {
			TreeNode entry = table[i];
			avlTreeTraversal(entry);
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

	// function to print the inorder traversal
	static void avlTreeTraversal(TreeNode root) {

		if (root == null)
			return;

		avlTreeTraversal(root.left);
		allKeys[counter++] = root.value;
		avlTreeTraversal(root.right);

	}

	@Override
	public long nextKey(long key) {
		int hash = (int) (key % SIZE);
		if (table[hash] != null)
			return findNextParent(table[hash], key, -1);
		else
			return -1;
	}
	
	static long findNextParent(TreeNode node, long val, long parent) {
		if (node == null)
			return -1;

		// If current node is the required node
		if (node.value == val) {
			if(node.right != null)
				nextKey = node.right.value;
		} else {
			findNextParent(node.left, val, node.value);
			findNextParent(node.right, val, node.value);
		}
		return nextKey;
	}

	@Override
	public long prevKey(long key) {
		int hash = (int) (key % SIZE);
		if (table[hash] != null)
			return findParent(table[hash], key, -1);
		else
			return -1;
	}

	static long findParent(TreeNode node, long val, long parent) {
		if (node == null)
			return -1;
		
		if (node.value == val) {
			prevKey = parent;
		} else {
			findParent(node.left, val, node.value);
			findParent(node.right, val, node.value);
		}
		return prevKey;
	}

	@Override
	public void rangeKeys(long key1, long key2) {
		System.out.println();
		for (int j = 0; j < SIZE; j++) {
			if (table[j] != null) {
				countKeyInRange(table[j], key1, key2);
			}
		}

		System.out.println(rangedCounter + " student found in this range (" + key1 + " - " + key2 + ")");
	}

	static void countKeyInRange(TreeNode root, long k1, long k2) {

		if (root == null)
			return;

		avlTreeTraversal(root.left);
		if (k1 <= root.value && root.value <= k2) {
			allKeys[counter++] = root.value;
			System.out.println(root.value + " ");
			rangedCounter++;
		}
		avlTreeTraversal(root.right);

	}

	@Override
	public LinkedHashEntry[] copyArrayADT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node copyLinkedListDT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getSize() {
		return entrySize;
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
		
		return table;
	}

}
