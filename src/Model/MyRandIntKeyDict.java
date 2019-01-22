package Model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyRandIntKeyDict<Value> extends MyIRandIntKeyDict<Value> {
    public MyRandIntKeyDict(Generator gen) {
        super(gen);
    }

    @Override
    Integer add(Value value) {
        Integer genNr = this.gen.getGeneratedNumber();
        this.dictionary.put(genNr, value);
        return genNr;
    }

    @Override
    boolean isDefined(Integer id) {
        return this.dictionary.containsKey(id);
    }

    @Override
    void update(Integer id, Value val) {
        this.dictionary.put(id, val);
    }

    @Override
    Collection<Value> values() {
        return this.dictionary.values();
    }

    @Override
    public Collection<Integer> keys() {
        return this.dictionary.keySet();
    }

    @Override
    public Value lookup(Integer id) {
        return this.dictionary.get(id);
    }

    @Override
    void delete(Integer id) {
        this.dictionary.remove(id);
    }

    @Override
    void clear() {
        this.dictionary.clear();
    }

    @Override
    public Map<Integer, Value> getContent() {
        return this.dictionary;
    }

    @Override
    public void setContent(Map<Integer, Value> content) {
        this.dictionary = (HashMap<Integer, Value>) content;
    }

    @Override
    public String toString() {
        if (this.dictionary.isEmpty())
            return "";
        StringBuilder res = new StringBuilder();
        for (HashMap.Entry<Integer, Value> entry : this.dictionary.entrySet())
            if (entry.getValue() instanceof Tuple)
                res.append(entry.getKey()).append(" --> ").append(((Tuple) entry.getValue()).getFirst()).append("\n");
            else
                res.append(entry.getKey()).append(" --> ").append(entry.getValue()).append("\n");

        return res.toString();
    }
}
