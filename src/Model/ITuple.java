package Model;

public interface ITuple<T1,T2> {
    T1 getFirst();
    T2 getSecond();
    boolean verifyString(String toVerify);
}
