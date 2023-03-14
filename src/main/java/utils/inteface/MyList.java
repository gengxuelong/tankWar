package utils.inteface;

/**
 * 自定义List类
 * @param <T>
 */
public interface MyList<T> extends Iterable<T> {
    public void add(T value);
    public void remove(T value);
    public void removeWithNoException(T value);
    public T get(int index);
    public int size();
    public void removeAll(MyList<T> other);
    public void addAll(MyList<T> other);
    public void removeByIndex(int index);

}
