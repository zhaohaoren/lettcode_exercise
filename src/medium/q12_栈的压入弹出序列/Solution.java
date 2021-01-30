package medium.q12_栈的压入弹出序列;

import anno.Method;

import java.util.Stack;

/**
 * 我的思路：
 * 1. 如何保证栈的弹出没有错？
 * 找规律得出的：每弹出的一个数字，后面弹出的数字一定是原栈中和他相邻的2个数字（对应着2种弹法：一放入就弹，弹出前面的元素）
 * 所以遍历出栈的数组，看看他后面的元素在栈中是不是相邻的。所有比对过的数字，我们将其设置为第一个数字，这样来获取相邻
 * ↑ 思路是错的 比如 [0, 2, 1] [0, 1, 2]
 * 2. 试着弹一遍
 * 题解：
 * 自己想复杂了！
 * 其实就是对着弹出栈的队列模拟一下弹栈顺序：（通过将push栈自己尝试压入一个辅助栈来比较pop栈顺序）
 * 1. 需要借助一个辅助栈，先按照顺序将push栈顺序压入辅助栈中。
 * 2. 在取出push压入辅助栈时候，每次都对比下pop栈，如果一样，就要先弹出（代表pop栈弹出push栈的时候，在这个位置也会弹出） 然后pop栈比较下一位
 * 3. 一直到最后发现栈的尾都一样，说明成功模拟，即得证！
 *
 * 这种操作感觉像是抑或，通过2个重复操作还原真实操作。
 *
 * @author zhaohaoren
 */
class Solution {


    @Method(1)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // 辅助栈
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed) {
            // num 入栈
            stack.push(num);
            // 每次压入辅助栈一个数字，就比较下pop的元素，如果相等就再pop出来，不等就继续
            while(!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                // 弹出pop后，索引+1，比较pop下一位
                i++;
            }
        }
        // 上面如果循环结束了辅助栈 没有弹尽，说明顺序存在问题，无法模拟，即顺序有问题
        return stack.isEmpty();
    }


    /**
     * 我的错误版本：
     * 整个思路如果细想就站不住脚
     */
    @Deprecated
    public boolean validateStackSequencesWrong(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) {
            return false;
        }

        int[] foundIndex = new int[pushed.length];

        for (int i = 0; i < popped.length - 1; i++) {
            int cur = popped[i];
            int next = popped[i + 1];
            // 看看next是否在pushed中和cur相邻
            for (int j = 0; j < pushed.length; j++) {
                if (pushed[j] == cur) {
                    boolean flag = false;
                    // 找前一个
                    int preIndex = j - 1;
                    while (preIndex >= 0) {
                        if (foundIndex[preIndex] != 0) {
                            preIndex--;
                            continue;
                        }
                        // 找到了相邻的前一个
                        if (pushed[preIndex] == next) {
                            flag = true;
                            // 找过的位置下次不再找
                            foundIndex[j] = 1;
                        }
                        break;
                    }
                    // 前一个就能匹配上，就往下走了
                    if (flag) {
                        continue;
                    }
                    // 找后一个
                    int lastIndex = j + 1;
                    while (lastIndex < pushed.length) {
                        if (foundIndex[lastIndex] != 0) {
                            lastIndex++;
                            continue;
                        }
                        // 找到了相邻的后一个
                        if (pushed[lastIndex] == next) {
                            flag = true;
                            // 找过的位置下次不再找
                            foundIndex[j] = 1;
                        }
                        break;
                    }
                    if (!flag) {
                        return false;
                    }
                }
            }

        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
//        System.out.println(new Solution().validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1}));
//        System.out.println(new Solution().validateStackSequences(new int[]{0, 2, 1}, new int[]{0, 1, 2}));
    }
}