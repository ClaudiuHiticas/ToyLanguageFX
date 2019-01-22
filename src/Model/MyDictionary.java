package Model;


import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MyDictionary<Key, Value> implements MyIDictionary<Key, Value> {
    private HashMap<Key, Value> dictionary = new HashMap<>();

    public MyDictionary() {

    }

    @Override
    public void add(Key key, Value value) {
        this.dictionary.put(key, value);
    }


    @Override
    public boolean isDefined(Key id) {
        return this.dictionary.containsKey(id);
    }

    @Override
    public void update(Key id, Value val) {
        this.dictionary.put(id, val);

    }

    @Override
    public Collection<Value> values() {
        return this.dictionary.values();
    }

    @Override
    public Collection<Key> keys() {
        return this.dictionary.keySet();
    }


    @Override
    public Value lookup(Key id) {
        return this.dictionary.get(id);
    }


    @Override
    public void clear() {
        this.dictionary.clear();
    }

    @Override
    public String toString() {
        if (this.dictionary.isEmpty())
            return "";
        StringBuilder res = new StringBuilder();
        for (HashMap.Entry<Key, Value> entry : this.dictionary.entrySet())
            if (entry.getValue() instanceof Tuple)
                res.append(entry.getKey()).append(" --> ").append(((Tuple) entry.getValue()).getFirst()).append("\n");
            else
                res.append(entry.getKey()).append(" --> ").append(entry.getValue()).append("\n");

        return res.toString();
    }

    @Override
    public MyIDictionary<Key, Value> clone() {
        MyIDictionary<Key, Value> dictClone = new MyDictionary<>();
        for(Key key : this.dictionary.keySet())
            dictClone.add(key, this.dictionary.get(key));
        return dictClone;
    }

    @Override
    public Map<Key, Value> toMap() {
        return this.dictionary;
    }

}
