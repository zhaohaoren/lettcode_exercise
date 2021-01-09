package simple.q13_反转链表;

import anno.Method;

/**
 * 我的思路：
 * 1. 递归方式
 * 2. 遍历一次做交换
 * 思路：
 * - 先申明一个游标节点，来标记当前处理的那个node
 * - 过程就是不断的从head剥离出一个节点，然后将这个节点的next指向tail
 * - 所以我们剥离head的一个节点的时候需要就行他原来指向的next节点，让当前cursor处理完了可以继续回到新的位置
 *
 * <p>
 * 题解：思路大体差不多，但是我做这个花的时间超过了10分钟太长了！
 *
 * @author zhaohaoren
 */
class Solution {

    @Method(1)
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode tail = null;
        while (cur != null) {
            // 在cur将head的next指向变化之前，先将其原来的指向保存下来
            head = cur.next;
            // 开始交换：游标下一个指向被剥离出来逆序的tail节点
            cur.next = tail;
            // tail链表新增了当前节点，此时tail再置为逆序的头结点
            tail = cur;
            // 游标进入下节点往后继续
            cur = head;
        }
        return tail;
    }


    @Method(2)
    public ListNode reverseListRecursive(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}