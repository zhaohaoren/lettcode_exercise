package simple.q18_第一个只出现一次的字符;

import anno.Method;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 我的思路：
 * 1. 定义一个26 长度的数组，遍历一遍，每个数组+1 xxxxx 题目没看清楚，是要第一个！
 * 2. 使用hash来做了，用linkhashmap保持有序
 * 题解：
 * 一样
 */

class Solution {

    @Method(1)
    public char firstUniqChar(String s) {
        if (s == null || s.length() == 0) {
            return ' ';
        }
        LinkedHashMap<Character, Integer> charMap = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            charMap.merge(c, 1, Integer::sum);
        }
        for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
            Character k = entry.getKey();
            Integer v = entry.getValue();
            if (v == 1) {
                return k;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
    }
}