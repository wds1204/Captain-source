package com.captain.wds.sort;

/**
 * Created by wds on 2018/4/2.
 */
// 选择排序
public class SelectSort {

	public static void main(String[] args) {
		int[] ints = {11, 3, 42, 2, 34, 323, 24, 1321, 232};
		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + "\t");
		}
		System.out.println("\n" + "/*=======================*/");

		selectSort(ints);
		for (int i = 0; i < ints.length; i++) {
			System.out.print(ints[i] + "\t");
		}

	}
	/**
	 * 交换元素
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	/* ===========直接选择排序============ */

	///////////////////////////////////////////////////////////////////////////
	// 直接选择排序：第一趟从n个元素的数据序列中找到最大或者最小的元素并放在在头部或者尾部，
	// 下一趟在n-1个元素中选择最大或者最小的元素放在头部或者尾部，依次循环，直到n个元素数据列表，
	// 完成有序的排序
	///////////////////////////////////////////////////////////////////////////
	private static void selectSort(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {// 内存循环
				if (arr[i] > arr[j]) {
					swap(arr, i, j);
				}
			}
		}
	}

	/* ===========堆排序============ */

	public static void sort(int []arr){
		//1.构建大顶堆
		for(int i=arr.length/2-1;i>=0;i--){
			//从第一个非叶子结点从下至上，从右至左调整结构
			adjustHeap(arr,i,arr.length);
		}
		//2.调整堆结构+交换堆顶元素与末尾元素
		for(int j=arr.length-1;j>0;j--){
			swap(arr,0,j);//将堆顶元素与末尾元素进行交换
			adjustHeap(arr,0,j);//重新对堆进行调整
		}

	}

	/**
	 * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
	 * @param arr
	 * @param i
	 * @param length
	 */
	public static void adjustHeap(int []arr,int i,int length){
		int temp = arr[i];//先取出当前元素i
		for(int k=i*2+1;k<length;k=k*2+1){//从i结点的左子结点开始，也就是2i+1处开始
			if(k+1<length && arr[k]<arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
				k++;
			}
			if(arr[k] >temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
				arr[i] = arr[k];
				i = k;
			}else{
				break;
			}
		}
		arr[i] = temp;//将temp值放到最终的位置
	}

	

}
