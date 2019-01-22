package Model;

public class Tuple<T1,T2> implements ITuple<T1,T2> {
    private T1 first;
    private T2 second;

    public Tuple(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public T1 getFirst() {
        return this.first;
    }

    @Override
    public T2 getSecond() {
        return this.second;
    }

    @Override
    public boolean verifyString(String toVerify) {
        return false;
    }

    @Override
    public String toString(){
        return "<" + first.toString() + ", " +second.toString() + ">";
    }
}
