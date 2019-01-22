package Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class MyIRandIntKeyDict<Value> {
    Generator gen;
    HashMap<Integer, Value> dictionary = new HashMap<>();

    MyIRandIntKeyDict(Generator gen) {
        this.gen = gen;
    }

    abstract Integer add(Value value);

    abstract boolean isDefined(Integer id);

    abstract void update(Integer id, Value val);

    abstract Collection<Value> values();

    public abstract Collection<Integer> keys();

    public abstract Value lookup(Integer id);

    abstract void delete(Integer id);

    abstract void clear();

    public abstract Map<Integer, Value> getContent();

    public abstract void setContent(Map<Integer, Value> content);
}
