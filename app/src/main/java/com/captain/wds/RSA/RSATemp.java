package com.captain.wds.RSA;

/**
 * Created by wds on 2018-5-14.
 */

public class RSATemp {
    public static void main(String[] args) {
        int p = 17;
        int q = 19;
        //求N ：准备两个质数p，q。这两个数不能太小，太小则会容易破解，将p乘以q就是N
        int N = p * q;
        //求L  ：L 是 p－1 和 q－1的最小公倍数
        int L = lcm(q - 1, p - 1);
        //求E：E必须满足两个条件：E是一个比1大比L小的数，E和L的最大公约数为1

    }

    /**
     * 获取两个数的最小公倍数
     * @param a
     * @param b
     * @return
     */
    public static int lcm(int a, int b){
        int m = a, n = b;   //a,b的值要保留到最后相乘，故用m,n来计算
        int t = m % n;
        while(t != 0){
            m = n;
            n = t;
            t = m % n;
        }
        //此处跳出while时，n的值即a,b的最大公约数
        int l = a * b / n;
        return l;
    }




}

