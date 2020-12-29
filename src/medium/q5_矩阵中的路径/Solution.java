package medium.q5_矩阵中的路径;

/**
 * TODO: 疑意题
 * 思路:
 * 没想出来，其实也是因为多虑了，题目也没有描述清楚，看题解大部分都是路径是连续的，没有说路径不连续的情况（题目只是说了包含，没有说顺序及连续性）
 * 这题目给的题解和我对题目意思的理解难度差了很多。
 * <p>
 * 题解：
 * DFS+回溯法
 * DFS： 就是深度优先搜索
 * 回溯： 当向下遍历的那个不满足的时候，回到上一个，走另外一个路径
 * <p>
 * 我们先开始遍历这个二维数组，当发现第一个数字和给定word第一个字符匹配的时候，开始进入深度优先遍历dfs。
 * 深度遍历就是往上下左右四个方向探路，如果已经走过的路以及数组的边界，那么就返回false表明路走不通。
 * 只要能走通就可以一直递归下去，知道word全部被匹配完。
 * 如果出现了 比如要ABCD  结果 矩阵中有路线 ABCC ABCD  到B的时候有2个C（当然可以是多个）。那么先走了CC的那个会不断的返回false，
 * 到了B这一级，B会继续换条路往下走。
 * <p>
 * 这种题解前提就是路径是连续的。如果不连续的话，走到断了的地方就全部返回false了。
 */
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        // 遍历这个数组去匹配第一个字符
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (dfs(board, words, i, j, 0)) {
                    // 如果dfs发现了路径就返回
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 主要就是对比word[k]和二维数组中的元素做比较，如果相同就往下一个，如果不同就返回false
     *
     * @param board 二维数组
     * @param word  需要找到的路径
     * @param i     当前二维数组走到的位置的i值
     * @param j     当前二维数组走到的位置的j值
     * @param k     word已经遍历到的位置
     */
    public boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        // 如果触碰到了边界 || 当前字符和需要的字符不匹配的话，说明这条路走不通
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) {
            return false;
        }
        // *** 核心 *** 递归弹出的条件，words最后一个字符匹配也找到了
        if (k == word.length - 1) {
            return true;
        }
        // 因为遍历后的节点是不能再走的，所以将其设置为'\0'，这样永远无法匹配就等于不会走这条路。
        // 注意一点：当前遍历是认为这条路走得通的，但是最后发现走不通了，回来的时候还需要将这个数改回去。
        board[i][j] = '\0';
        // **** 核心的核心 ****
        // 这是递归的关键，就是如果发现当前字符是匹配的，那么就递归他的上下左右方向。
        if (dfs(board, word, i + 1, j, k + 1) || // 向右
                dfs(board, word, i - 1, j, k + 1) || // 向做
                dfs(board, word, i, j + 1, k + 1) || // 向下
                dfs(board, word, i, j - 1, k + 1)) { // 向上
            return true;
        }
        // 如果走了发现最红没走通，将数值改回去
        board[i][j] = word[k];
        return false;
    }
}