package com.captain.wds.sort;

public class Test {
    public static void main(String[] args) {


        int[] _char = new int[122];

        String msg="wemaketheairdirtyandincreasethecarbondioxide";
        char[] chars = msg.toCharArray();

        for(int i = 0; i <chars.length ; i++) {
            System.out.print((int)chars[i]+"\t");
            _char[(int)chars[i]]++;
        }
        System.out.println();

        for(int i = 0; i < _char.length; i++) {
            System.out.print(_char[i]+"\t");
        }
    }
}
