package simple.q4_用两个栈实现队列;

import java.util.Stack;

/**
 * 我的思路：
 * 一个队列专门用来append的，当需要删除的时候，就需要将最下面的一个拿出来，此时就使用另一个栈存放上面的元素，弹出后，再放回去。
 * 这个很显然最简单的思路了。肯定可以优化。
 * ！！！ 我第二个循环多此一举！！！
 * <p>
 * 题解：
 * 思路大都一致：只记录我的问题=> 新来的值我继续让其append到main就行了，让弹出从backup去取！让backup取空了，我们再从main里面弹数据。
 * 我这想法太烂了。。。
 * 核心：
 * 一个栈专门用来append
 * 一个栈专门用来delete
 * 什么时候不够了，就从另一个栈取拉取。
 */
public class CQueue {

    Stack<Integer> mainStack;
    Stack<Integer> backupStack;

    public CQueue() {
        mainStack = new Stack<>();
        backupStack = new Stack<>();
    }

    public void appendTail(int value) {
        mainStack.push(value);
    }

    public int deleteHead() {
        if (mainStack.isEmpty()) {
            return -1;
        }
        while (!mainStack.empty()) {
            backupStack.push(mainStack.pop());
        }
        int res = backupStack.pop();
        while (!backupStack.empty()) {
            mainStack.push(backupStack.pop());
        }
        return res;
    }

    public int deleteHeadGood() {
        if (backupStack.isEmpty()) {
            if (mainStack.isEmpty()) {
                return -1;
            }
            while (!mainStack.isEmpty()) {
                backupStack.push(mainStack.pop());
            }
        }
        return backupStack.pop();
    }
}