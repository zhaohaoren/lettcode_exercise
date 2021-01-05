package simple.q11_链表中倒数第k个节点;

/**
 * 我的思路：
 * 1. 递归回弹
 * 2. 放入栈里面，然后pop
 * 3. 最笨的：遍历2次，1次计总数，1次计k | 链表逆序后输出
 * <p>
 * 题解：
 * 快慢指针：
 * 快的走k步
 * 然后慢的走，当k走到头的时候，慢指针的位置就是k的位置。
 *
 * @author zhaohaoren
 */
class Solution {


    /**
     * 我在这被卡了一下，k在递归弹出--操作不会影响到外部的k，所以需要能引用传递的东西。
     * 但是把这种全局变量给忘了
     */
    int size = 0;

    public ListNode getKthFromEnd(ListNode head, Integer k) {
        if (head.next != null) {
            ListNode res = getKthFromEnd(head.next, k);
            if (res != null) {
                return res;
            }
        }
        size++;
        if (k == size) {
            return head;
        } else {
            // 不是他就返回null
            return null;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }


    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        System.out.println(new Solution().getKthFromEnd(n1, 2).val);
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode former = head, latter = head;
        for(int i = 0; i < k; i++) {
            former = former.next;
        }
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }

}