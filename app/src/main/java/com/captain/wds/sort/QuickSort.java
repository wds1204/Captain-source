package com.captain.wds.sort;

/**
 * Created by wds on 2018/3/30.
 */
// 快速排序
public class QuickSort {

	public static void main(String[] args) {

		int[] ints = { 11, 3, 42, 2, 34, 323, 24, 1321, 232 };
		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + "\t");
		}
		System.out.println("\n" + "/*=======================*/");

		quickSort(ints, 0, ints.length - 1);
		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + "\t");
		}
	}

	///////////////////////////////////////////////////////////////////////////
	// 快速排序属于拿着元素找位置的算法。思路非常简单明了，
	// 首先给第一个元素找到它正确的位置并把它放置其中，
	// 此时该元素将原数组分为两半，左半边的元素都小于或等于它，
	// 右半边的元素都大于它，对这两个子数组重复刚才的操作，
	// 直到子数组中只有一个元素，此时排序完成。
	///////////////////////////////////////////////////////////////////////////

	/**
	 * @param s
	 * @param i
	 *            左边界
	 * @param j
	 *            右边界
	 */
	public static void quickSort(int[] s, int i, int j) {
		if (i < j) {
			int left = i, right = j, inser = s[i];
			while (left < right) {
				while (left < right && inser < s[right])
					right--;
				if (left < right) s[left++] = s[right];

				while (left < right && inser > s[left])
					left++;
				if (left < right) s[right--] = s[left];
			}
			s[left] = inser;

			quickSort(s, i, left - 1);
			quickSort(s, left + 1, j);
		}

	}

	public void quick(int []src,/*左边界*/int i,/*右边界*/int j) {

		if (i < j) {
			int left = i, right = j, inser = src[i];


			while (left < right) {

				while (left < right && inser < src[right]) right--;
				if (left < right) src[left++] = src[right];
				while (left<right&&inser>src[left]) left++;
				if(left<right) src[right--] = src[left];
			}

			src[left] = inser;

			quick(src, i, left - 1);
			quick(src, left + 1, j);
		}

	}

}
