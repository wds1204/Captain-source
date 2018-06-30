package com.captain.wds.sort;

/**
 * Created by wds on 2018-6-25.
 */

public class BinarySearch {
    private static int NOT_FOUND = -1;

    public static void main(String[] args) {
		System.out.println(gcd(122, 8));
	}
    //循环从high-low=N-1开始，并在high-low<=1结束
    //每次循环后high-low的值至少将该次循环前的值折半
    //于是循环的次数最多为[log(N-1)]+2。(例如，若high-low=128，则在各次迭代后high-low的
    // 最大值是64，32,16,8,4,2,1，0，-1)因此运行时间是O(logN),与此等价，我们也可以写出运行事件的递推公式
    // )
    /**
     *  二分查找法
     * @param a  排好的序的数组
     * @param x
     * @param <AnyType>
     * @return
     */
	public static <AnyType extends Comparable<? super AnyType>> int binarySearch(AnyType[] a, AnyType x) {

		int raw = 0, high = a.length - 1;
		while (raw <= high) {
			int mid = (raw + high) / 2;

			if (a[mid].compareTo(x) < 0) {
			    raw=mid+1;

			} else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
			}

		}
		return NOT_FOUND;
	}

	/**
	 * 最大公因数
	 * @param m
	 * @param n
	 * @return
	 */
	public static long gcd(long m,long n){//122  8
		while (n != 0) {
			long rem=m%n; //2
			m=n;   //8
			n=rem;  //2
		}
		return m;
	}

}
