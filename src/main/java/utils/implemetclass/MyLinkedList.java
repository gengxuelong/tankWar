package utils.implemetclass;

import utils.exception.LinkedException;
import utils.inteface.MyList;

import java.util.Iterator;

/**
 * @author: kangzhaoxin
 * @Date:2021/12/6 21:29
 *
 * 双向列表类
 */
public class MyLinkedList<T> implements MyList<T>,Iterable<T> {

    private final Node<T> head = new Node<>(null, null, null);
    private final Node<T> tail = new Node<>(null,null,null);
    private int size = 0;

    public MyLinkedList(){
        head.setNext(tail);
        tail.setLast(head);
    }
    @Override
    public void add(T value) {
        Node<T> newNode = new Node<>(value, null, null);
        Node<T> theLast = tail.getLast();
        theLast.setNext(newNode);
        newNode.setLast(theLast);
        tail.setLast(newNode);
        newNode.setNext(tail);
        size++;
    }

    @Override
    public void remove(T value) {
        if(value ==null)return;
        Node<T> tmpHead = head;
        while(tmpHead != tail &&!value.equals(tmpHead.value)) tmpHead = tmpHead.getNext();
        if(tmpHead == tail){
            throw new LinkedException("所删除的元素不存在");
        }else{
            Node<T> last = tmpHead.last;
            Node<T> next = tmpHead.next;
            last.next = next ;
            if(next != null)//如果要删除的元素在最后一位则next为零
               next.last = last;
            tmpHead = null;//提醒虚拟机所删元素所在的node对象已经无引用指向，尽快收回
        }
        size--;
    }

    @Override
    public void removeWithNoException(T value) {
        try{
            remove(value);
        } catch (LinkedException ignored) {
        }
    }

    @Override
    public T get(int index) {
        if(index>size-1){
            throw new LinkedException("索引越界异常");
        }
        Node<T> tmpHead = head;
        for(int i = 0;i <= index;i++){
            if(tmpHead.next!= null)
                tmpHead = tmpHead.next;
        }

        if(tmpHead.value==null)return null;
        return tmpHead.value;

    }

    @Override
    public int size() {
        return size;
    }
    @Override
    public void removeByIndex(int index){
        if(index>size-1){
            throw new LinkedException("索引越界异常");
        }
        Node<T> tmpHead = head;
        for(int i = 0;i <= index;i++){
            tmpHead = tmpHead.next;
        }
        Node<T> last = tmpHead.last;
        Node<T> next = tmpHead.next;
        last.setNext(next);
        next.setLast(last);
        size--;
    }

    @Override
    public void removeAll(MyList<T> other) {
        for(int i = 0; i<other.size(); i++){
            T t = other.get(i);
            try{
                remove(t);
            } catch (LinkedException ignored) {
            }

        }
    }

    @Override
    public void addAll(MyList<T> other) {//将other列表中本列表未含的元素添加至本列表
        for(T t:other){
            boolean flag = true;
            for(T myT:this){
                if(t.equals(myT)){
                    flag = false;
                    break;
                }
            }
            if(flag){
                this.add(t);
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator(head);
    }


    private class MyIterator implements Iterator<T>{

        Node<T> node ;
        public MyIterator(Node<T> node){
            this.node = node;
        }
        @Override
        public boolean hasNext() {//先hasNext判断，再执行next，具体逻辑可以随便编写
            if(node!=null&&node.getNext()!=null)
                 return( node.getNext().getValue() != null);
            else return false;
        }

        @Override
        public T next() {
           if(node.getNext().getValue() == null){
               try {
                   throw new NoSuchFieldException();
               } catch (NoSuchFieldException e) {
                   e.printStackTrace();
               }
           }
           T value = node.getNext().getValue();
           node = node.getNext();
           return value;
        }
    }



    private static class Node<T>{
        private T value;
        private Node<T> next;
        private Node<T> last;

        public Node(T value,Node<T> last, Node<T> next) {
            this.value = value;
            this.last = last;
            this.next = next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }

        public Node<T> getLast() {
            return last;
        }

        public void setLast(Node<T> last) {
            this.last = last;
        }
    }

}
