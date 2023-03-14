package utils.implemetclass;

import utils.exception.MapException;
import utils.inteface.MyMap;

/**
 * @author: kangzhaoxin
 * @Date: 2021/12/5 13:35
 */
public class MySimpleMap<K,V> implements MyMap<K,V> {


    private final Node<K,V>[]  nodes = new Node[10];
    private int size = 0;

    public MySimpleMap() {
    }

    @Override
    public void put(K key, V value) {

        int index = key.hashCode()%10;
        if(index<0)index = -index;//index.hashCode() 有可能是负数
        Entered<K,V> entered = new Entered<K, V>(key, value);
        Node<K,V> newNode = new Node<>(entered);
        if(nodes[index]==null) {//数组位置上为空
            nodes[index] = newNode;
        }else{
            boolean flag = false;//若为TRUE，则代表添加的key值已经存在
            Node<K,V> tempHead = nodes[index];
            while(tempHead != null && !tempHead.entered.key.equals(key)){
                tempHead = tempHead.next;
            }
            if(tempHead != null){
                flag = true;
            }

            if(flag){
                tempHead.entered.value = value;
                return;
            }else{
                Node<K,V> head = nodes[index] ;
                while(head.next != null){
                    head = head.next;
                }
                head.next = newNode;
                newNode.last = head;
            }
        }
        size++;
    }

    @Override
    public void remove(K key) {

        int index = key.hashCode()%nodes.length;
        if(index<0)index = -index;
        Node<K,V> head = nodes[index];
        while(head != null&&!head.entered.key.equals(key)){
            head = head.next;
        }
        if(head==null) {throw new MapException("所删对象不存在");}
        else{
            if(head.last == null&&head.next ==null){//为数组位子上的唯一node
                nodes[index] = null;
            }else if(head.last == null){//为链表的第一个但下面有node
                Node<K,V> next = head.next ;
                nodes[index] = next;
                next.last = null;
            }else if(head.next == null){//为链表最后一个且上面有node
                Node<K,V> last = head.last;
                last.next = null;
            }else{//一般情况，上面和下面都有node
                Node<K,V> last = head.last ;
                Node<K,V> next = head.next ;
                last.next = next;
                next.last = last;
            }
        }
        size--;

    }

    @Override
    public V get(K key) {

        int index = key.hashCode()%nodes.length;
        if(index<0)index = -index;
        Node<K,V> head = nodes[index];
        while(head!= null&&!head.entered.key.equals(key)){
            head = head.next;
        }
        if(head!=null){
            return head.entered.value;
        }else{
            throw new MapException("没有要查询的key值");
        }

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(K key) {
        int index = key.hashCode()%nodes.length;
        if(index<0)index = -index;
        Node<K,V> head = nodes[index];
        while(head!= null&&!head.entered.key.equals(key)){
            head = head.next;
        }
        return head != null;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (Node<K, V> node : nodes) {
            Node<K, V> head = node;
            if (head != null) {
                while (head != null) {
                    sb.append(head.entered.key).append(":").append(head.entered.value).append("\n");
                    head = head.next;
                }
            }
        }
        return sb.toString();
    }

    /*
    构造方法没有泛型，类才有
     */
    private static class Node<K,V> {
      private Node<K,V> last ;
        private Node<K,V> next ;
        private Entered<K,V> entered;

        public Node(Entered<K,V> entered) { this.entered = entered; }
        public Node(){}
    }


    private static class Entered<K,V>{
        K key;
        V value;

        public Entered(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
