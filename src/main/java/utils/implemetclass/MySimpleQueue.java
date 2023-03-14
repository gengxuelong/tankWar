package utils.implemetclass;

import utils.exception.QueueException;
import utils.inteface.MyQueue;

/**
 * @author: kangzhaoxin
 *
 */
public class MySimpleQueue<T> implements MyQueue<T> {
    private utils.inteface.MyList<T> list = new MyLinkedList<>();
    int size = 0;
    @Override
    public void offer(T value) {
        list.add(value);
        size++;
    }

    @Override
    public T peek() {
        if(size == 0){
            throw new QueueException("队列为空，无法获得队头元素");
        }
        return list.get(0);
    }

    @Override
    public T poll() {
        if(size == 0){
            throw new QueueException("队列为空，无法弹出元素");
        }
        T res = list.get(0);
        list.remove(res);
        size--;
        return res;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }
}
