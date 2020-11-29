package SortingAlgo;

import java.util.*; 

public class RadixSort { 

	public static long getMax(long arr[], int n) 
	{ 
		long mx = arr[0]; 
		for (int i = 1; i < n; i++) 
			if (arr[i] > mx) 
				mx = arr[i]; 
		return mx; 
	} 


	public static void countSort(long arr[], int n, int exp) 
	{ 
		long output[] = new long[n]; 
		int i; 
		int count[] = new int[10]; 
		Arrays.fill(count, 0); 


		for (i = 0; i < n; i++) 
			count[(int) ((arr[i] / exp) % 10)]++; 

		for (i = 1; i < 10; i++) 
			count[i] += count[i - 1]; 

		for (i = n - 1; i >= 0; i--) { 
			output[count[(int) ((arr[i] / exp) % 10)] - 1] = arr[i]; 
			count[(int) ((arr[i] / exp) % 10)]--; 
		} 

		for (i = 0; i < n; i++) 
			arr[i] = output[i]; 
	} 

	public static long[] radixsort(long arr[], int n) 
	{ 
		long m = getMax(arr, n); 

		for (int exp = 1; m / exp > 0; exp *= 10) 
			countSort(arr, n, exp);
		
		return arr; 
	} 


} 



