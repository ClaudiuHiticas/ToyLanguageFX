package Model;

public interface MyIList<T> {
    void add(T var);
    T fromIndex(int index);
    String toString();
    void clear();
    int size();
}
