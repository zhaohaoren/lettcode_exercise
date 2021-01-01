package medium.q7_剪绳子;


import anno.Method;

/**
 * 思路:
 * 1. 列出所有的剪法（最笨的可以计算所有的剪法，这里的所有指的是7,1 1,7 都算一次）
 * - 长度为n的话，那么最多就可以砍n-1刀
 * - 那么所有的可能：
 * -- 1 刀 就可以放在 C(n-1,1) 种剪法
 * -- ...
 * -- n-1 到就是 C(n-1, n-1) = 1 种剪法
 * - 所以一共有C(n-1, 1) + ... C(n-1, n-1) 种剪法 == 这个能算么？怎么算来着？(⊙_⊙)?
 * 2. 找出乘积最大的那个值
 * ---------------------------
 * 是否有数学的方式直接求解的？或者有什么数学规律在这乘积最大剪法里面。
 * <p>
 * 题解：
 * 果然如我所料：数学算法：https://leetcode-cn.com/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
 * 利用立一个算术几何均值不等式 =>  可以推论出尽可能等分，得到的乘积最大
 * 那么新问题，等分尺度多大？1的话就是最小了，通过一列计算求极值，得出尺度为3面积最大，所以：
 * n <= 3 : 因为n>1 所以减去一刀1 和 n-1 返回n-1
 * n > 3 :
 * 先按3分还还余几？
 * 余0：直接就是均分 3^n/3
 * 余1: 需要拆出一个3和1 整合为 2x2  因为2x2>3x1
 * 余2: 不要再减了，就 3^(n/3)x2
 * 这样复杂度就是O(1)
 * END: 数学方法虽然好，但是没有通用性，也很容易忘。
 * --------------------递归法--------------------------------
 * 这个是我应该能想出来的。。。。 就是没要剪开一刀的时候都2种情况：剪开和不减，剪开就递归，然后求这2个最大值，取最大值。
 * ---------------------动态规划-------------------------------------
 */
public class Solution {

    /**
     * 列出所有的可能
     * 可能结果其实是一棵树：
     * 2刀有几种， 2刀之后，两半都可以来一刀，往下递归。
     * 注意：在任意一步上我们都可以2个选择： 1. 继续减下去， 2 不减了，就使用当前的状态做乘积
     * 所以递归开始回弹的时候，我们需要取这2种方法的最大值，
     * 比如最后剪到2的时候，我们可以减成1x1 也可以就是2不动了。 继续减成1x1就是继续往下递归
     * 所以递归的函数 F(n)=max(i*(n-i),i* F(n-i))
     * --- 超时
     */
    @Method(1)
    public int cuttingRope(int n) {
        if (n <= 2) {
            // 不能再切了，或者说只有一种切法1x1
            return 1;
        }
        // 存放结果
        int res = 0;
        // 长度为n的绳子，可以从2开始切，一共也会有n-2个切法
        // 因为1*F(n-1) 和 1 *（n-1） F(n-1) >= (n-1) 就不可能剪出一个1出来的，除非初始就是2或者3 不然 你如果剪出1个1，那么就等于没有价值，也就等于n-1长度的绳子有怎么减最大
        // （其实尾部也是一样的不是吗？所以应该i<n-1也行）
        for (int i = 2; i < n; i++) {
            // 这max是获取递归下面最终获取到的最大结果  即：继续减下去的结果 i * cuttingRopeMath(n - i) 和 不再继续减下去 i * (n - i) 哪一个大
            int max = Math.max(i * cuttingRopeMath(n - i), i * (n - i));
            if (max > res) {
                //这个比较是 在这一层上 一共有n-2种切法，这n-2种切法 的最大值
                res = max;
            }
        }
        return res;
    }


    /**
     * 递归记忆化
     * 空间换时间
     * <p>
     * 当递归 i* f(n-i) 的时候，我们会重复计算很多次f(n-i) 比如：n=4 ->1xf(3) = 1x1xf(2) 和 2xf(2) 那么这个f(2)就算2次了，我们可以第二次算这个使用记录值
     */
    @Method(2)
    public int cuttingRopeBetter(int n) {
        int[] member = new int[n + 1];
        member[2] = 1;
        return cut(member, n);
    }

    public int cut(int[] member, int n) {
        // 之前已经算过了f(n)
        if (member[n] != 0) {
            return member[n];
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            int max = Math.max(i * cut(member, n - i), i * (n - i));
            if (max > res) {
                res = max;
            }
        }
        // 这个算出来的结果放入member记录
        member[n] = res;
        return res;
    }


    /**
     * 记住几个点：1. 等分长度为3的乘积最大 2. 除以三除不完剩下的要分类讨论
     */
    @Method(3)
    public int cuttingRopeMath(int n) {
        if (n <= 3) {
            return n - 1;
        }
        int a = n / 3, b = n % 3;
        if (b == 0) {
            return (int) Math.pow(3, a);
        }
        if (b == 1) {
            return (int) Math.pow(3, a - 1) * 4;
        }
        return (int) Math.pow(3, a) * 2;
    }

    /**
     * 动态方程
     * 状态定义：dp[i]:表示长度为i的剪短后的最大乘积;
     * 初始状态：dp[2] = 1;
     * 状态转移方程：dp[i] = Math.max(dp[i], Math.max(j * dp[i-j], j * (i - j))); 其中 1<j<i
     * 返回值：dp[n]
     * 其思想核心和递归的很像。
     */
    @Method(4)
    public int cuttingRopeDP(int n) {
        int[] dp = new int[n + 1];
        // 初始状态
        dp[2] = 1;
        // 状态转移方程
        for (int i = 3; i < n + 1; i++) {
            for (int j = 2; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j * dp[i - j], j * (i - j)));
            }
        }
        // 返回值
        return dp[n];
    }
}