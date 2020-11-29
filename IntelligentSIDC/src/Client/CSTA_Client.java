package Client;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import ADT.ArrayADT;
import ADT.IntelligentSIDC;
import ADT.iADTInterface;

public class CSTA_Client {

	public static iADTInterface arr = new ArrayADT(100);

	public static void main(String[] args) {

		IntelligentSIDC universalTable = new IntelligentSIDC();

//		for (int i = 0; i < 100; i++) {
//			long key = universalTable.generate();
//			insertValueToSIDC(universalTable, key);
//		}

		Scanner sc = null;

		try {
			readFromExistingFile(sc, universalTable);
			removeFromExistingFile(sc, universalTable);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}

//		//arr.remove(82560366);
//		universalTable.allkeys();
//		
//		if (arr.getValues(82560366) != -1)
//			System.out.print(82560366);
//		else
//			System.out.print("No found, may be deleted");
//		
//		universalTable.rangeKeys(33219245, 63031003);
//		
//		
//		System.out.println(universalTable.nextKey(33241626));
	
	}

	

	private static void readFromExistingFile(Scanner sc, IntelligentSIDC universalTable) throws FileNotFoundException {
		sc = new Scanner(new FileInputStream("CSTA_test_file1.txt")); // studentsId // CSTA_test_file1

		while (sc.hasNextLine()) {
			long value = Long.parseLong(sc.nextLine());
			insertValueToSIDC(universalTable, value);
		}

		sc.close();

	}
	private static void insertValueToSIDC(IntelligentSIDC universalTable, long value) {
		arr = universalTable.SetSIDCThreshold(arr.getSize(), arr);
		arr.add(value, value);
		System.out.println(value + " - " + arr.getValues(value) + " - " + arr.getClass() +" - size: " + arr.getSize());
	}
	
	private static void removeFromExistingFile(Scanner sc, IntelligentSIDC universalTable) throws FileNotFoundException {
		sc = new Scanner(new FileInputStream("CSTA_test_file1.txt")); // studentsId // CSTA_test_file1
		System.out.println("==================================");
		System.out.println("==================================");
		while (sc.hasNextLine()) {
			long value = Long.parseLong(sc.nextLine());
			removeValueSIDC(universalTable, value);
		}

		sc.close();

	}
	
	private static void removeValueSIDC(IntelligentSIDC universalTable, long value) {
		arr = universalTable.SetSIDCThreshold(arr.getSize(), arr);
		arr.remove(value);
		System.out.println(value + " - has been deleted from using class, " + arr.getClass()+ " - size: " + arr.getSize());
	}
	
	

}
