package com.captain.wds.sort;

/**
 * Created by wds on 2018/3/30.
 */
// 开始插入排序
public class InsertSort {

	///////////////////////////////////////////////////////////////////////////
	// 插入排序的思想，先给定一个排好序的序列，再让后续的值与前面已排好序的值依次比较，如果小于前面的值就插入在前列，一直这样循环的操作后续的值。
	// 比如把第一个元素作为有序的值，第二个元素和第一个元素比较，根据比较结果，
	// 排序两者的位置使之有序，第三个元素和前两个元素比较，根据排序结果使之有序，直到第N个元素完成排序。
	///////////////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		int[] data = { 11, 3, 42, 2, 34, 323, 24, 1321, 232 };

		insetSort(data);
		for (int datum : data) {
			System.out.print(datum + "\t");
		}

	}

	public static void insetSort(int[] data) {
		for (int i = 1; i < data.length; i++) {
			int temp = data[i];// 从第二个元素开始
			int j = i - 1;

			while (j >= 0 && temp < data[j]) {
				data[j + 1] = data[j];
				j--;
			}
			data[j + 1] = temp;// 直到要插入的元素不小于第j个元素,temp

		}
	}

}
