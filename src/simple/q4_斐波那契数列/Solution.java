package simple.q4_斐波那契数列;

/**
 * 我的思路：
 * 1. 是一个简单题，因为之前递归好记，所以首选了递归，结果超时了（递归时间真的好长）
 * 2. 走非递归方式，无非就是自己开始累加，注意两个问题点：
 * - 1. 边界问题，循环累加多少次结束
 * - 2. 这题算是一个变形了，需要取模，（这里帮我温故了一下取模的作用：得到的数不会大于模数）。并且是每次计算结果都是取模的
 * 上面2个都简单，感觉没有算法的思想，都有一定的缺点（或内存或遍历次数）
 * 3. 递归，在递归最内部申请内存，然后递归弹出的时候，不断往里面塞数值。所以需要 一个数组，一个长度，一个index计数。
 * <p>
 * 题解：
 * 都差不多
 */
class Solution {

    public static int fib(int n) {
        if (n <= 1) {
            return n;
        }
        // 递归只记住： fn = fn-1 + fn-2
        return fib(n - 1) + fib(n - 2);
    }

    public static int fibNoRec(int n) {
        if (n <= 1) {
            return n;
        }
        int f0 = 0;
        int f1 = 1;
        // 核心1 如果把我不住，就使用排比法也能推论出来
        while (n > 1) {
            //核心2 f0 存前一个数 f1 存后一个数 计算的时候记得交换位置（当然你可以用加减法或者抑或方式去做啦）
            int tmp = f1;
            f1 = (f1 + f0) % 1000000007;
            f0 = tmp;
            n--;
        }
        return f1;
    }

    public static void main(String[] args) {
        System.out.println(fibNoRec(45));
        System.out.println(fib(45));
    }
}