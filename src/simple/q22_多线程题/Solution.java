package simple.q22_多线程题;

import java.util.stream.IntStream;

/**
 * 给定一个数组[1,2,3,4,5,6,7,8,9....,15]，要求遍历数组，遇到可以同时被3和5整除的数字，打印C；遇到仅能被5整除的数字，打印B；遇到仅能被3整除的数字，打印A；其他打印数字本身；
 * 要求四个线程，每一个线程执行一个打印方法。
 */

class Solution {

    private static final int[] ARRAY = IntStream.range(1, 16).toArray();

    private synchronized void printA() {

    }

    private void printB() {

    }

    private void printC() {
        System.out.println(1);
    }

    private void printNum() {

    }
}

