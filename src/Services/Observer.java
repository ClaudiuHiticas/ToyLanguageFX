package Services;

public interface Observer<T> {
    void update(Observable<T> observable);
}
