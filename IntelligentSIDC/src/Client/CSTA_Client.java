package Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ADT.ArrayADT;
import ADT.IntelligentSIDC;
import ADT.TreeNode;
import ADT.iADTInterface;

public class CSTA_Client {

	public static iADTInterface arr = new ArrayADT(100);

	public static void main(String[] args) {

		IntelligentSIDC universalTable = new IntelligentSIDC();

		for (int i = 0; i < 1000; i++) {
			long key = universalTable.generate();
			insertValueToSIDC(universalTable, key);
		}
		// removeKeysToShrinkDB(universalTable);

		Scanner sc = null;

		try {
			readFromExistingFile(sc, universalTable);
			// removeFromExistingFile(sc, universalTable);
			//removeKeysToShrinkDB(universalTable);

		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

		// CHECK ALL KEYS IN ACCENDING ORDER
		System.out.println("\nAll the values in sorted order:");
		universalTable.allkeys();
		System.out.println("\n");
		
		// CHECK KEY RANGE
		System.out.println("KeyRange: ");
		universalTable.rangeKeys(33219245, 63031003);
		System.out.println("\n");

		// CHECK GETVALUES
		System.out.println("\nGetValue: ");
		long check = 82560366; // 82560366
		if (universalTable.getValues(check) != -1)
			System.out.println("The value " + check + " Founded.");
		else
			System.out.println("No found, may be deleted");
		System.out.println("\n");

		// CHEECK NEXTKEY AND PREVKEY
		System.out.println("NextKey: ");
		System.out.println("Next key of this key : " + check + " is " + universalTable.nextKey(check));
		System.out.println("\n");
		
		System.out.println("PrevKey: ");
		System.out.println("Previous key of this key : " + check + " is " + universalTable.prevKey(check));
		System.out.println("\n");

		// CHEECK REMOVE
		System.out.println("Remove: ");
		if(universalTable.remove(check))
			System.out.println("Value: " +check + " removed succesfully.");
		else System.out.println("Value: " +check + " did not found in the ADT DB.");
		System.out.println("\n");
		
		System.out.println("Getvalue again after remove: ");
		if (universalTable.getValues(check) != -1)
			System.out.println("The value " + check + " Founded.");
		else
			System.out.println("No found, may be deleted");
		System.out.println("\n");

	}

	@SuppressWarnings("unused")
	private static void removeKeysToShrinkDB(IntelligentSIDC universalTable) {
		TreeNode[] avlDB = universalTable.getFullAVLDB();
		if (avlDB != null) {
			for (int i = 0; i < avlDB.length; i++) {
				TreeNode entry = avlDB[i];
				if (entry != null) {
					avlTreeTraversalToDelete(entry, universalTable);
				}

				else
					continue;
			}
		}
	}

	static void avlTreeTraversalToDelete(TreeNode root, IntelligentSIDC sidc) {

		if (root == null)
			return;

		avlTreeTraversalToDelete(root.left, sidc);
		if (root != null && root.value > 0)
			removeValueSIDC(sidc, root.getValue());
		avlTreeTraversalToDelete(root.right, sidc);

	}

	private static void readFromExistingFile(Scanner sc, IntelligentSIDC universalTable) throws FileNotFoundException {
		sc = new Scanner(new FileInputStream("CSTA_test_file1.txt")); // studentsId // CSTA_test_file1

		while (sc.hasNextLine()) {
			long value = Long.parseLong(sc.nextLine());
			insertValueToSIDC(universalTable, value);
		}

		sc.close();

	}

	private static void insertValueToSIDC(IntelligentSIDC universalTable, long key) {
		arr = universalTable.SetSIDCThreshold(arr.getSize(), arr);
		universalTable.add(key, key);
		System.out.println(key + " - " + universalTable.getValues(key) + " - " + arr.getClass() + " - size: "
				+ universalTable.getSize());

	}

	@SuppressWarnings("unused")
	private static void removeFromExistingFile(Scanner sc, IntelligentSIDC universalTable)
			throws FileNotFoundException {
		sc = new Scanner(new FileInputStream("CSTA_test_file1.txt")); // studentsId // CSTA_test_file1
		System.out.println("===========================================================");
		System.out.println("DELETING ALL THE STORED VALUE TO CHECK SHRINKING APPROACH");
		System.out.println("===========================================================");
		while (sc.hasNextLine()) {
			long value = Long.parseLong(sc.nextLine());
			removeValueSIDC(universalTable, value);
		}

		sc.close();

	}

	private static void removeValueSIDC(IntelligentSIDC universalTable, long value) {
		arr = universalTable.SetSIDCThreshold(arr.getSize(), arr);
		universalTable.remove(value);
		if (arr.getSize() >= 1) {
			System.out
					.println(value + " - has been deleted from using, " + arr.getClass() + " - size: " + arr.getSize());
		}
	}

}
