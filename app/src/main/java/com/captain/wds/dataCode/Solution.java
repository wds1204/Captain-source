package com.captain.wds.dataCode;

public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.printN(1000);
//        for(int i = 0; i <= 10000; i++) {
//            System.out.println("i="+i);
//
//        }
    }

    private void printN(int N){
        if(N==0) return;
        System.out.println("N="+N);
        N=N-1;
        printN(N);

    }
}
