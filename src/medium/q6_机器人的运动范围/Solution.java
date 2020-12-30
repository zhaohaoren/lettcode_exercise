package medium.q6_机器人的运动范围;

import java.util.HashSet;
import java.util.Set;

/**
 * 思路:
 * 和之前DFS的思路一样，深度优先，上下左右递归，递归后的结果看最长的那个，然后开始递归弹栈
 * !!! 题目意思讲的又不是很明确，但是给出的答案可以推理出题目的意思：
 * 题目其实本质的意思是这个mxn的方格符合数位之和小于k的连续的路径全部找出来。
 * 那么就是dfs：从0，0开始 只要能往下走就+1，走不通了回去，看上一个还有没有其他的路径，有的话继续
 * --- 这么写着写着 我自己已经恶心了，虽然实现了，又想到的方法：
 * 我从（0,0）开始，对这些点的可达性做分析，会发现可达的路径刚好就是对角线
 * 比如 从(0,0)开始的路径 有（1,0） (0,1)  刚好就是2x2的对角线上
 * 那么我就不断遍历对角线上的点，只要该点小于k，那么就一定是可达。最后统计小于k的总数
 * >>> 这思路是错的！！！
 * 1. 如果对角线上全不成立，那么说明无法再往下了，路径就此断了
 * 2. 比如：如果对角线上最下边的无法通过，最上边的可以通过 然后下一条对角线是刚好相反，那么我这么弄就是错的
 * A B C
 * D E F
 * G H K
 * 假如有这种情况：A D G C 是小于k的，但是C是不可达的！
 * <p>
 * 题解：
 * https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/solution/ji-qi-ren-de-yun-dong-fan-wei-by-leetcode-solution/
 * 1. 这是一个*搜索*问题：搜索问题需要*周游矩阵*
 * 2. 对于这类问题2种解法：（2种搜索算法）
 * -递归DFS【对于搜索问题更多是采用DFS】 （我的第一个思路是对的，只是不够优化）
 * -基于队列BFS
 * STEP2：对问题进行模式转换
 * 1. 坐标（i,j）看成当前状态
 * 2. 递归搜索 上下左右
 * 3. 有个地方记录访问过的全局状态
 * 优化：搜索方向可以缩减为向右或向下
 * 因为可以观察下，向下和向右可以访问到矩阵中所有的节点。
 */
class Solution {

    public int movingCountBetter(int m, int n, int k) {
        // 他是定义了一个bitmap来就访问的元素的
        boolean[][] visited = new boolean[m][n];
        // 机器人从[0,0]坐标开始移动
        return dfsBetter(m, n, k, visited, 0, 0);
    }

    public int dfsBetter(int m, int n, int k, boolean[][] visited, int x, int y) {
        // 递归终止条件
        if ((numPlaceSum(x) + numPlaceSum(y) > k) || x < 0 || x >= m || y < 0 || y >= n || visited[x][y]) {
            return 0;
        }
        // 将该格子标记为已经访问过
        visited[x][y] = true;
        // 仅考虑向下和向右的移动方向
        // 其实和我的方法异曲同工：我那么多if只是为了少往回走1次，但其实只需要管往下和往右就行了
        return 1 + dfsBetter(m, n, k, visited, x, y + 1)
                + dfsBetter(m, n, k, visited, x + 1, y);
        // 回溯的返回过程
    }


    public int movingCount(int m, int n, int k) {
        // 第一个只能走右边或者下边
        return dfs(0, 0, m, n, k, -1, new HashSet<>());
    }

    /**
     * 走到某一格开始递归
     *
     * @param i    当前格子的 行号
     * @param j    当前格子的 列号
     * @param m    总行
     * @param n    总列
     * @param k    给的k
     * @param from 0 up 1 down 2 left 3 right
     */
    public int dfs(int i, int j, int m, int n, int k, int from, Set<Integer> walked) {
        // 递归到头的条件
        if (i >= m || i < 0 || j >= n || j < 0 || numPlaceSum(i) + numPlaceSum(j) > k || walked.contains(i * n + j)) {
            return 0;
        }
        System.out.println("===> 走通" + i + " " + j);
        walked.add(i * n + j);
        int count = 1;
        // 如果没有走到头的话，就在当前各自的基础下上下左右走
        // 如果上一步是从哪里来的，原来的路就别走了
        if (from != 0) {
            // 向上走
            // 从下边来
            count += dfs(i - 1, j, m, n, k, 1, walked);
        }
        if (from != 1) {
            // 向下走
            // 从上边来
            count += dfs(i + 1, j, m, n, k, 0, walked);
        }
        if (from != 2) {
            // 向左走
            // 从右边来
            count += dfs(i, j - 1, m, n, k, 3, walked);
        }
        if (from != 3) {
            // 向右走
            // 从左边来
            count += dfs(i, j + 1, m, n, k, 2, walked);
        }
        // 取最大的那个加上自己本身的格子
        return count;
    }

    private int numPlaceSum(int num) {
        int res = 0;
        do {
            res += num % 10;
            num = num / 10;
        } while (num > 0);
        return res;
    }


    /**
     * 错误的
     */
    @Deprecated
    public int movingCount2(int m, int n, int k) {
        int i = 0;
        int j = 0;
        int count = 0;
        while (true) {
            int x = i;
            int y = j;
            // 遍历对角线,边界退出
            while (x < m && y < n && x >= 0 && y >= 0) {
                if (numPlaceSum(x) + numPlaceSum(y) <= k) {
                    System.out.println("ok的对角线：" + x + " " + y);
                    count++;
                } else {
                    System.out.println("bad的对角线：" + x + " " + y);
                }
                // 注意下：对角线x和y之和是相同的，但是他们的位数之和可不一定
                // 对角线下一个元素
                x--;
                y++;
            }
            // 没一条对角线遍历完了，去下一个对角线
            if (i < m - 1) {
                // 竖直方向上对角线遍历到头
                i++;
            } else {
                // 竖着遍历结束，遍历底部一些对角线
                j++;
            }
            // 最后一个元素就退出
            if (j >= n) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
//        System.out.println(new Solution().movingCount(2, 3, 1));
//        System.out.println(new Solution().movingCount(3, 2, 17));
        System.out.println(new Solution().movingCount2(16, 8, 4));
    }
}