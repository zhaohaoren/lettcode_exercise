package medium.q9_剪绳子2;

/**
 * 和q7_剪绳子一样，只是注意大数运算，需要每一步取余
 *
 * @author zhaohaoren
 */
class Solution {

    public int cuttingRope(int n) {
        if (n == 2) {
            return 1;
        }
        if (n == 3) {
            return 2;
        }
        long res = 1;
        while (n > 4) {
            res *= 3;
            // 每次循环都认为他可能数据越界int的范围，就给他取模
            res = res % 1000000007;
            // 每一次乘以3等于绳子砍掉长度3
            n -= 3;
        }
        // 最后会余下几种情况 n = 3 和 n = 4 和 n = 2 （n不可能为1，1+3=4在所以在7的时候减4就会跳出循环）
        // n = 3 则x3
        // n = 4 则 应该 3,1 变为 2,2 即 4
        // n = 2 则x2
        // 所以直接xn就行了
        return (int) (res * n % 1000000007);
    }
}