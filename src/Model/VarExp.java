package Model;

import Exceptions.VariableNotFound;

public class VarExp extends Exp {
    private String id;

    public VarExp(String id) {
        this.id = id;
    }


    int eval(MyIDictionary<String, Integer> tbl, MyIRandIntKeyDict<Integer> heap) throws VariableNotFound {
        if(!tbl.isDefined(id)) throw new VariableNotFound();
        return tbl.lookup(id);
    }

    @Override
    public String toString() {
        return id;
    }


}