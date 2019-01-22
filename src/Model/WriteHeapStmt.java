package Model;

import Exceptions.*;


public class WriteHeapStmt implements IStmt {
    private String var_name;
    private Exp expression;

    public WriteHeapStmt(String var_name, Exp expression) {
        this.var_name = var_name;
        this.expression = expression;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> heap = state.getHeap();
        if (!symTbl.isDefined(var_name)) throw new VariableNotFound();
        int heapAddress = symTbl.lookup(var_name);
        if (!heap.isDefined(heapAddress)) throw new VariableNotFound();
        int evaluated = expression.eval(symTbl, heap);
        heap.update(heapAddress, evaluated);
        return null;
    }

    @Override
    public String toString() {
        return "wh(" + this.var_name + "," + this.expression.toString() + ")";
    }
}
