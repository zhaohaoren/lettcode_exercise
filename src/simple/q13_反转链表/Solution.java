package simple.q13_反转链表;

import anno.Method;

/**
 * 我的思路：
 * 1. 递归方式
 * - 遍历最后一个开始返回，返回的节点
 * 2. 遍历一次做交换
 * 思路：
 * - 先申明一个游标节点，来标记当前处理的那个node
 * - 过程就是不断的从head剥离出一个节点，然后将这个节点的next指向tail
 * - 所以我们剥离head的一个节点的时候需要就行他原来指向的next节点，让当前cursor处理完了可以继续回到新的位置
 *
 * <p>
 * 题解：思路大体差不多，但是我做这个花的时间超过了10分钟太长了！
 * <p>
 * 记忆： 2个游标： （一个移动，一个返回） | 循环体内4步走： 1. 标记下次的地址，节点剥离指向返回的节点，游标走向下一步
 * 递归记忆：
 * - 递归返回条件：next为空，及给的链表就是空的则node为空也返回
 * - 开始递归函数：返回结果为头结点，
 * - 将当前节点，放在其指向的下一个节点的后面，然后将当前节点的next置为null
 * - return 头结点
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
        // 终止条件，递归到了尾结点 ， head == null  这个主要是为防止head初始就是空的，head.next == null 才是递归到尾结点
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseListRecursive(head.next);
        // 以倒数第二个为例： 此时head也是倒数第二个节点，head.next 其实就是newHead（但是要返回翻转的链表，所以newHead不能动，要把引用一直传递下去）
        // head.next.next 就是讲当前节点放在其后面节点的后面，即最后一个节点的后面
        head.next.next = head;
        // 将当前节点和原始链表剥离，因为递归的原因，倒数第三个节点依然指向了正确的节点，所以可以直接剥离
        head.next = null;
        return newHead;
        // 递归的本质就是 找到最后一个节点，将其置为头结点：newHead，然后递归出栈的时候，开始将原始链表拆出节点并变换方向。
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}