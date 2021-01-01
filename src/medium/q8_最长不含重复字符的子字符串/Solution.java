package medium.q8_最长不含重复字符的子字符串;

import java.util.HashMap;

/**
 * @author zhaohaoren
 */
public class Solution {

    public static int lengthOfLongestSubstring(String s) {

        int maxLen = 0;
        int len = 0;
        int lastStart = 0;

        HashMap<Character, Integer> charMap = new HashMap<>(16);

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charMap.containsKey(c) && charMap.get(c) >= lastStart) {
                if (len > maxLen) {
                    maxLen = len;
                }
                lastStart = charMap.get(c);
                len = i - charMap.get(c);
            } else {
                len++;
            }
            charMap.put(c, i);
        }
        if (maxLen < len) {
            maxLen = len;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(" "));
    }
}