package simple.q3_从尾到头打印链表;

/**
 * 我的思路：
 * 1. 使用栈先存着然后弹出来
 * 2. 2次遍历：1次算长度，申请数组内存；1次遍历开始往里面塞
 * 上面2个都简单，感觉没有算法的思想，都有一定的缺点（或内存或遍历次数）
 * 3. 递归，在递归最内部申请内存，然后递归弹出的时候，不断往里面塞数值。所以需要 一个数组，一个长度，一个index计数。
 * <p>
 * 题解：
 * 题解都差不多没有啥更好的思路。
 */
class Solution {

    int len = 0;
    int[] res;
    int i = 0;

    /**
     * mine
     */
    public int[] reversePrint(ListNode head) {
        if (head == null) {
            return new int[0];
        }
        len++;
        if (head.next == null) {
            res = new int[len];
            res[i] = head.val;
            return res;
        }
        ListNode tmp = head;
        head = head.next;
        res = reversePrint(head);
        i++;
        res[i] = tmp.val;
        return res;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}