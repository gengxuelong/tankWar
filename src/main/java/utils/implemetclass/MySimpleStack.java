package utils.implemetclass;

import utils.exception.StackException;
import utils.inteface.MyStack;

/**
 * @author: kangzhaoxin
 *
 */
public class MySimpleStack<T> implements MyStack<T> {

    private int size = 0;
    private final utils.inteface.MyList<T> list = new MyLinkedList<>();

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public T pop() {
        if(size == 0){
            throw new StackException("堆栈为空，无法弹出元素");
        }
        T res = list .get(size-1);
        list.removeByIndex(size-1);
        size--;
        return res;
    }

    @Override
    public T peek() {
        if(size == 0){
            throw new StackException("堆栈为空，无法弹出元素");
        }
        return list.get(size-1);
    }

    @Override
    public void push(T value) {
        list.add(value);
        size++;
    }

}
