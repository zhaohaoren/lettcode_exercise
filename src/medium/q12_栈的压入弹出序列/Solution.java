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
 *
 * @author zhaohaoren
 */
class Solution {


    @Method(1)
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed) {
            // num 入栈
            stack.push(num);
            // 循环判断与出栈
            while(!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }


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