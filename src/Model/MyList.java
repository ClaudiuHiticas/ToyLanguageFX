package Model;


import java.util.ArrayList;
import java.util.List;

public class MyList<T> implements MyIList<T> {
    private List<T> list = new ArrayList<>();

    public MyList() {
    }

    public void add(T var) {
        this.list.add(var);
    }

    @Override
    public T fromIndex(int index) {
        return this.list.get(index);
    }


    @Override
    public String toString() {
        if (this.list.isEmpty())
            return "";
        StringBuilder res = new StringBuilder();
        for (T elem : this.list)
            res.append(elem).append("\n");

        return res.toString();
    }

    @Override
    public void clear() {
        this.list.clear();
    }

    @Override
    public int size() {
        return this.list.size();
    }

}
