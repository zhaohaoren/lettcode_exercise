package simple.q20_两个链表的第一个公共节点;

import anno.Method;

/**
 * 我的思路：
 * 首先：如何判断相交呢？ 最粗暴的: 2个链表都走到尾看他们一不一样。
 * 如何获取第一个相交点：
 * 1. 求出2个链表长度，然后长的那个先走差额部分，然后一起走，判断是否相等，相等则为第一个相交点（O(3n)了）
 * 2. 借助栈，将2个链表都放入栈内，然后不断弹出，当不一样的时候，前一个被弹出的就是第一个相交点（空间复杂度高了）
 * 3. 递归怎么去做？
 * 题解：
 * 双指针，浪漫相遇:
 * 这种解法其实更多是套路了，不太好像。这里面蕴含着数学的思想：
 * 如果最初a=b的话，那么在a步内就会相遇，这是最好的情况。
 * 如果a!=b的话，那么A走a+b+L B的走法是b+a+L
 * 总之这种走法必会相遇的原因就是一个简单的数学公式：a+L+b=b+L+a  =>  a+b+L=b+a+L
 * 举个例子吧：
 * 2个马路长度为A:a+L+b 和 B:b+L+a ，a长b短就分别代表着a这段只能走路，b这段可以骑自行车，L这段则是只能坐公交。
 * 问：2个人一个在马路A一个在马路B，同时出发，2个人是否一定会同时到终点？
 * 即A和B走的路长度其实都是一样的，由A链和B链组合成的，但是走的顺序有差异，但是把这个链走完耗费的步数一定是一样的。
 */

class Solution {

    public static void main(String[] args) {
        ListNode n1 = null;
        ListNode n2 = null;
        if (n1 == n2){
            System.out.println("ok");
        }
    }


    /**
     * 题目不行！
     * 罗里吧嗦一大堆其实就是意思是，比较的是node的地址，而不是比较的值
     */
    @Method(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = 1, lenB = 1;
        ListNode tmpA = headA;
        ListNode tmpB = headB;
        while (tmpA.next != null) {
            lenA++;
            tmpA = tmpA.next;
        }
        while (tmpB.next != null) {
            lenB++;
            tmpB = tmpB.next;
        }
        if (tmpA != tmpB) {
            return null;
        }
        int step = Math.abs(lenA - lenB);
        ListNode tmpLong;
        ListNode tmpShort;
        if (lenA > lenB) {
            tmpLong = headA;
            tmpShort = headB;
        } else {
            tmpLong = headB;
            tmpShort = headA;
        }
        while (step > 0) {
            tmpLong = tmpLong.next;
            step--;
        }
        do {
            if (tmpLong == tmpShort) {
                return tmpShort;
            } else {
                tmpLong = tmpLong.next;
                tmpShort = tmpShort.next;
            }
        } while (tmpLong != null && tmpShort != null);
        return null;
    }


    @Method(2)
    public ListNode getIntersectionNodeBetter(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode n1 = headA;
        ListNode n2 = headB;

        while (n1 != n2) {
            n1 = n1 == null ? headB : n1.next;
            n2 = n2 == null ? headA : n2.next;
        }
        return n1;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}

