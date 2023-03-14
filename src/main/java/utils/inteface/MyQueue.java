package utils.inteface;

public interface MyQueue<T> {
    public void offer(T t);
    public T peek();
    public T poll();
    public boolean isEmpty();
}
