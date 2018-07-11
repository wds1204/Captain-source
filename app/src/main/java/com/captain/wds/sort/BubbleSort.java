package com.captain.wds.sort;

/**
 * Created by wds on 2018/3/30.
 */
// 冒泡算法
public class BubbleSort {

	public static void main(String[] args) {
        String c = "[\"a\",\"b\",\"c\",\"d\"]";
        System.out.println("c =" + c);
        String substring = c.substring(1, c.length() - 1);
        System.out.println("substring=" + substring);

        String s1 = substring.replaceAll("\"", "");

        String[] split = s1.split(",");

        for (String s : split) {
            System.out.println("s=" + s);

        }
//		int[] arr = { 6, 3, 8, 2, 9, 1 };
//
//		for (int i = 0; i < arr.length - 1; i++) {
//			for (int j = 0; j < arr.length - 1 - i; j++) {
//				if (arr[j] > arr[j + 1]) {
//					swap(arr, j, j + 1);
//				}
//			}
//		}
//		for (int i : arr) {
//
//			System.err.println("i=" + i);
//		}
	}

	///////////////////////////////////////////////////////////////////////////
	// 冒泡排序的基本思想是，对相邻的元素进行两两比较，顺序相反则进行交换，这样，
	// 每一趟会将最小或最大的元素“浮”到顶端，最终达到完全有序
	///////////////////////////////////////////////////////////////////////////
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static void bubblesort(int array[]) {
		for (int i = 0; i < array.length; i++) {

			for (int j = 0; j < array.length - 1; j++) {
				if (array[i] > array[j + 1]) {
					swap(array, i, j + 1);

				}
			}
		}
	}
	public void insertionSort(int array[]) {
		int i, j, t = 0;
		for (i = 1; i < array.length; i++) {
			t = array[i];
			for (j = i - 1; j >= 0 && t < array[j]; j--)
				array[j + 1] = array[j];
			array[j + 1] = t;
		}
	}

}
