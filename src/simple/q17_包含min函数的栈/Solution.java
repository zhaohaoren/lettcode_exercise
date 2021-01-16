package simple.q17_包含min函数的栈;

import java.util.ArrayList;

/**
 * 我的思路：
 * 说实在的没想出来！想要实现O（1） 除了数学算法，hash算法，也想到了使用辅助栈。
 * 但是没有自己思考因为数据结构是栈的特性。
 * <p>
 * 题解：
 * 需要一个辅助栈B，专门存放当前主栈A中最小的元素，即如果push的时候，只有发现了push的元素是小于B栈顶元素才push进去B
 * 这样求min的时候我直接返回B栈顶元素就行了。
 * 当pop的时候，最关键了，如果AB此时元素都在栈顶。那么2个都要同时抛出。
 * <p>
 * 为什么我没想出来：
 * 1. 对min的思考比较混乱，min其实就是取最小！但是他不会弹出。如果第一个放1，后面放3,5,3,2 这些乱序的都无所谓，只要没有pop到1，那么这个栈的最小元素始终都是1
 * 我也不知道当时是怎么想的，硬是在想这个辅助栈B应该和A元素相同，只不过排序的（这根本无法做到O(1)） 主要min只是需要知道最小元素，
 * 你在push的过程中，你可以获取到最小的元素！只要这个元素没有被pop，那么它始终都是这个栈的最小元素。
 */
class MinStack {


    /**
     * 这里可以直接使用栈去做的，没必要我这么麻烦。
     */
    ArrayList<Integer> stackA;
    ArrayList<Integer> stackB;
    int index;
    int indexB;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        stackA = new ArrayList<>();
        stackB = new ArrayList<>();
        index = -1;
        indexB = -1;
    }

    public void push(int x) {
        stackA.add(x);
        index++;
        if (stackB.size() == 0 || stackB.get(indexB) >= x) {
            stackB.add(x);
            indexB++;
        }
    }

    public void pop() {
        if (stackA.size() == 0) {
            return;
        }
        if (stackB.get(indexB).equals(stackA.get(index))) {
            // 这个remove int的移除的是索引，Integer移除的是这个元素里面的该integer值相等的对象。。。。
            stackB.remove(indexB);
            indexB--;
        }
        stackA.remove(index);
        index--;
    }

    public int top() {
        return stackA.get(Math.max(0, index));
    }

    public int min() {
        return stackB.get(Math.max(0, indexB));
    }

    public static void main(String[] args) {

        MinStack minStack = new MinStack();
        minStack.push(2147483646);
        minStack.push(2147483646);
        minStack.push(2147483647);
        minStack.top();
        minStack.pop();
        minStack.min();
        minStack.pop();
        minStack.min();
        minStack.pop();
        minStack.push(2147483647);
        minStack.top();
        minStack.min();
        minStack.push(-2147483648);
        minStack.top();
        minStack.min();
        minStack.pop();
        minStack.min();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.min();
 */