package Model;

import Exceptions.*;

public class PrintStmt implements IStmt {
    private Exp exp;

    public PrintStmt(Exp v) {
        this.exp = v;
    }

    public String toString() {
        return "print(" + exp.toString() + ")";
    }

    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIList<Integer> out = state.getOut();
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> heap = state.getHeap();
        int val = exp.eval(symTbl,heap);
        out.add(val);
        return null;
    }
}