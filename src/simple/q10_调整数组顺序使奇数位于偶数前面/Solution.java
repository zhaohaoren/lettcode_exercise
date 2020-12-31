package simple.q10_调整数组顺序使奇数位于偶数前面;

import java.util.Arrays;

/**
 * 我的思路：
 * 从前往后找偶数，找到偶数再从后往前找奇数，找到2个数做个交换
 * <p>
 * 题解：
 * 这题目可以2种解法：
 * 1. 我的那种应该叫做：首尾双指针
 * 2. 还有快慢指针：2个指针从头开始遍历，1个遇到了奇数停下来，1个遇到偶数停下来，然后2个做个交换
 * 其实和收尾双指针一样的，慢的指针从头开始找偶数，块的指针不断往后面去找到奇数，然后做交换，
 * 也就是我是从后往前取奇数，而他是从前往后取奇数。
 * <p>
 * get新的点：判断奇偶数 使用&1 == 1 表示是奇数 ==0 表示是偶数
 *
 * @author zhaohaoren
 */
class Solution {

    public int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        for (; i < nums.length; i++) {
            if (i >= j) {
                break;
            }
            if ((nums[i] & 1) == 0) {
                for (; j > i; j--) {
                    if ((nums[j] & 1) == 1) {
                        int tmp = nums[i];
                        nums[i] = nums[j];
                        nums[j] = tmp;
                        break;
                    }
                }
            }
        }
        return nums;
    }
}