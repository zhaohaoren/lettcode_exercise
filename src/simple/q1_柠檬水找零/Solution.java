package simple.q1_柠檬水找零;

/**
 * 我的思路：
 * 首先这是个简单题。
 * 因为钱的额度是固定的，只有5，10，20。那么遇到的情况就只有3种，每一次遇到的前，就要看前面剩多少钱
 * 对每次新拿到的钱，之前5元和10元的张数可以确保我们能完成找零。
 * 所以存储这个状态很重要，于是设置2个变量：一个设置5元张数，一个设置10元张数。（20的不用设置，不可能再找零20的）
 * <p>
 * 题解：
 * 思路差不多，但是有一些比较好的写法，让代码更加清爽。(虽然jdk1.8之后不建议这么写了，但是刷lettcode这些，看着代码还是挺库的)
 */
class Solution {

    /**
     * mine
     */
    public boolean lemonadeChange(int[] bills) {
        int fiveCnt = 0;
        int tenCnt = 0;
        for (int bill : bills) {
            switch (bill) {
                case 5:
                    fiveCnt++;
                    break;
                case 10:
                    if (fiveCnt < 1) {
                        return false;
                    }
                    fiveCnt--;
                    tenCnt++;
                    break;
                case 20:
                    if (fiveCnt < 1 || (fiveCnt < 3 && tenCnt < 1)) {
                        return false;
                    }
                    if (tenCnt < 1) {
                        fiveCnt -= 3;
                    } else {
                        tenCnt--;
                        fiveCnt--;
                    }
                    break;
                default:
            }
        }
        return true;
    }


    /**
     * network
     */
    public boolean better(int[] bills) {
        int five = 0, ten = 0;
        for (int i : bills) {
            if (i == 5) five++;
            else if (i == 10) {five--; ten++;}
            else if (ten > 0) {ten--; five--;}
            else five -= 3;
            if (five < 0) return false;
        }
        return true;
    }
}