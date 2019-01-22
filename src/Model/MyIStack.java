package Model;

import java.util.Stack;

public interface MyIStack<T> {

    T pop();
    void clear();
    void push(T v);

    boolean isEmpty();
    String toString();

    Stack<T> toStack();
    int size();
}