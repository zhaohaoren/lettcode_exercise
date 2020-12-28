package simple.q9_删除链表的节点;

/**
 * 我的思路：
 * 遍历删除，控制好边界
 * <p>
 * 题解：
 * 题目的意思其实是删除第一个相同的节点，我的写法其实是删除所有的相同的节点。
 * 1. 我这种
 * 2. 使用递归
 *
 * @author zhaohaoren
 */
public class Solution {

    /**
     * 当涉及链表删除的时候一定都要关联2个游标？
     * 1个记录上一次的（删除节点需要这个操作），1个记录当前的（为了结果能保留head）
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        // 1 保留head 2 移动遍历链表
        ListNode curr = head;
        // 保留上一个Node
        ListNode pre = null;

        while (curr != null) {
            //如果相同就删除节点
            if (curr.val == val) {
                //但此时发现pre可能为空，所以这时候为pre单独再处理下
                if (pre == null) {
                    // 删除头结点
                    head = head.next;
                } else {
                    pre.next = curr.next;
                    curr.next = null;
                }
            }
            // 往后遍历
            pre = curr;
            curr = curr.next;
        }
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}