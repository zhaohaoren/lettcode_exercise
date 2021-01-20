package simple.q19_数组中出现次数超过一半的数字;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的思路：
 * 1. 遍历数组，一个map存放数字的次数，累加次数大于数组一半的时候退出遍历
 * 题解：
 * 2. 数组排序法： 数组排序完后，中间的那个数必然是那个数
 * 3. 摩尔投票法：时间和空间复杂度分别为 O(N) 和 O(1)
 * 好好解释下这个摩尔投票法:
 * 我的感觉就是多数派投票，那么多数的投票的数一定多。
 * 所以我们默认第一个就是 多数派， 后面开始投票，后面的数和其相同 我们票数+1 不同 我们票数-1
 * 当票数=0 时候，说明前面遍历完的 多数派=少数派。
 * 既然多数派> 总数的一半，所以后面多数派也一定>少数派。
 * 我们相当于，前面打平了，后面的数组重新开始。 第一个认为多数派，然后后面投票，为0时，再以第一个开始，直到遍历所有。那么当前指定的那个多数派，就是结果。
 * <p>
 * 在知乎上看到的对摩尔投票法的理解：
 * <p>
 * 为什么答案能写那么长呢。。。核心就是对拼消耗。玩一个诸侯争霸的游戏，假设你方人口超过总人口一半以上，并且能保证每个人口出去干仗都能一对一同归于尽。最后还有人活下来的国家就是胜利。那就大混战呗，最差所有人都联合起来对付你（对应你每次选择作为计数器的数都是众数），或者其他国家也会相互攻击（会选择其他数作为计数器的数），但是只要你们不要内斗，最后肯定你赢。最后能剩下的必定是自己人。
 * <p>
 * 作者：知乎用户 链接：https://www.zhihu.com/question/49973163/answer/617122734 来源：知乎 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * <p>
 * 对拼消耗！！！ 杀敌1千自损1千，最终剩下的就是胜利者
 */

class Solution {

    public int majorityElement(int[] nums) {
        Map<Integer, Integer> numTimesMap = new HashMap<>();
        for (int num : nums) {
            Integer times = numTimesMap.get(num);
            if (times == null) {
                numTimesMap.put(num, 1);
            } else {
                if (times + 1 > nums.length / 2) {
                    return num;
                }
                numTimesMap.put(num, times + 1);
            }
        }
        return nums[0];
    }

    public int majorityElementBetter(int[] nums) {
        int res = 0, votes = 0;
        for (int num : nums) {
            // 如果票数为0，那么从当前位置重新开始
            if (votes == 0) {
                res = num;
                votes++;
            } else {
                // votes += 1 if num == x else -1 可以这么缩写，但是实际开发不推崇
                if (res == num) {
                    votes++;
                } else {
                    votes--;
                }
            }
        }
        return res;
    }
}