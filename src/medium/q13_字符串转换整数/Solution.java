package medium.q13_字符串转换整数;

import java.util.ArrayList;

/**
 * 我的思路：
 * 一个逻辑思路都简单，但是边界处理情况很多的题目
 * 刷题收益不大，但是可以锻炼心细
 *
 * @author zhaohaoren
 */
class Solution {

    public static int myAtoi(String s) {
        ArrayList<Character> resList = new ArrayList<>();
        boolean hasNum = false;
        boolean hasFlag = false;
        char numFlag = ' ';
        for (int i = 0; i < s.toCharArray().length; i++) {
            char curChar = s.charAt(i);
            if (curChar == ' ' && !hasNum && !hasFlag) {
                // 前置空格
                continue;
            }
            if ((curChar == '-' || curChar == '+') && numFlag == ' ') {
                // 符号位
                numFlag = curChar;
                hasFlag = true;
                continue;
            }
            if (!Character.isDigit(curChar)) {
                // 非字符
                break;
            }
            if (resList.size() != 0 || curChar != '0') {
                resList.add(curChar);
            }
            if (numFlag == ' ') {
                numFlag = '+';
            }
            hasNum = true;
        }

        // 字符串转数字

        int numRes = 0;
        int wei = 1;
        if (numFlag == ' ') {
            numFlag = '+';
        }
        boolean endFlag = false;
        for (int i = resList.size() - 1; i >= 0; i--) {
            if (endFlag) {
                return numFlag == '+' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            int val = (int) resList.get(i) - 48;
            if (wei == 1000000000) {
                endFlag = true;
                if (val > 2) {
                    return numFlag == '+' ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
            }
            int addVal = val * wei;
            if (numFlag == '+' && numRes >= Integer.MAX_VALUE - addVal) {
                return Integer.MAX_VALUE;
            }
            if (numFlag == '-' && numRes <= Integer.MIN_VALUE + addVal) {
                return Integer.MIN_VALUE;
            }
            if (numFlag == '+') {
                numRes += addVal;
            } else {
                numRes -= addVal;
            }
            wei *= 10;
        }
        return numRes;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("4193 with words"));
    }
}