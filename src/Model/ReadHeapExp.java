package Model;

import Exceptions.VariableNotFound;

public class ReadHeapExp extends Exp {
    private String var_name;

    public ReadHeapExp(String var_name) {
        this.var_name = var_name;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIRandIntKeyDict<Integer> heap) throws VariableNotFound {
        if (!tbl.isDefined(var_name)) throw new VariableNotFound();
        int heapAddress = tbl.lookup(var_name);
        if (!heap.isDefined(heapAddress)) throw new VariableNotFound();
        return heap.lookup(heapAddress);
    }

    @Override
    public String toString() {
        return "rH(" + this.var_name + ")";
    }
}
