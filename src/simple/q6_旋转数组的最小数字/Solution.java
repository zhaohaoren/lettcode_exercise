package simple.q6_旋转数组的最小数字;

/**
 * 我的思路：
 * 如果当前的数字大于后面的数字，那么第一次出现这种情况的后面一个数字就应该是最小的
 * <p>
 * 题解：
 * 都不用看题解了😁
 */
class Solution {
    public static int minArray(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                return numbers[i + 1];
            }
        }
        return numbers[0];
    }

    public static void main(String[] args) {
        System.out.println(minArray(new int[]{2, 2, 2, 0, 1}));
    }
}