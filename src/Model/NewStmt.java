package Model;

import Exceptions.*;


public class NewStmt implements IStmt {
    private String var_name;
    private Exp expression;

    public NewStmt(String var_name, Exp expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> heap = state.getHeap();
        int evaluated = expression.eval(symTbl, heap);

        int heapId = heap.add(evaluated);
        if (symTbl.isDefined(var_name))
            symTbl.update(var_name, heapId);
        else
            symTbl.add(var_name, heapId);
        return null;
    }

    @Override
    public String toString() {
        return "newStmt(" + this.var_name + ", " + this.expression.toString() + ")";
    }
}
