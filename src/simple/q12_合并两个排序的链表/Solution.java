package simple.q12_合并两个排序的链表;

import anno.Method;

/**
 * 我的思路：
 * 定一个Node，然后一个循环每次都比较结尾的2个节点，将小的那个放在Node后面，然后继续
 * 一直到其中一个出现空了，就将另一个节点全部append到Node后面
 * 所以需要一个标记原始的res引用，和一个不断往后移的游标引用tmp
 * <p>
 * 题解：
 * 思路大体都是一样的！还有一种递归，以及一种更加简洁的写法
 * <p>
 * 这题目套路题：一个伪头结点，一个游标引用，然后循环比较尾部，一旦有一个节点为空了就退出，将不为空的补到结果上去。
 *
 * @author zhaohaoren
 */
class Solution {

    @Method(1)
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode res = new ListNode(0);
        ListNode tmp = res;
        while (true) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
            }
            tmp = tmp.next;
            if (l1 == null) {
                tmp.next = l2;
                break;
            }
            if (l2 == null) {
                tmp.next = l1;
                break;
            }
        }
        return res.next;
    }

    /**
     * ☆☆☆☆☆☆☆☆☆☆☆☆☆☆
     */
    @Method(2)
    public ListNode mergeTwoListsBetter(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        // 小小的优化点：只有2个都不为空的时候才循环，当一个为空了就循环退出，然后将不为空的覆盖在后面
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        // 这样append
        cur.next = l1 != null ? l1 : l2;
        // 这样简化了：1.我while中的判断 2.开头也不用那样判断了
        return dum.next;
    }

    @Method(3)
    public ListNode mergeTwoListsRecursive(ListNode l1, ListNode l2) {
        // l1 遍历到头了，将l2全部返回
        if (l1 == null) {
            return l2;
        }
        // 同理
        if (l2 == null) {
            return l1;
        }
        // 递归方式就是，那个小就将另一个往这个上面插入。（就不需要了伪头结点）
        if (l1.val <= l2.val) {
            // 如果l1小，那么一l1为起点，l1的next指向 l1.next 和 l2 最小的那个
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            // 如果l1大，那么一l2为起点，l2的next指向 l2.next 和 l1 最小的那个
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}