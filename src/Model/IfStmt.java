package Model;

import Exceptions.DivisionByZero;
import Exceptions.OperatorNotFound;
import Exceptions.VariableNotFound;

public class IfStmt implements IStmt {
    private Exp condition;
    private IStmt thenS;
    private IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {
        this.condition = e;
        this.thenS = t;
        this.elseS = el;
    }

    public String toString() {
        return "IF(" + condition.toString() + ") THEN (" + thenS.toString()
                + ") ELSE (" + elseS.toString() + ")";
    }

    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Integer> symtbl = state.getSymTable();
        MyIRandIntKeyDict<Integer> heap = state.getHeap();
        if (this.condition.eval(symtbl, heap) == 0) {
            stk.push(elseS);
        } else {
            stk.push(thenS);
        }
        return null;
    }

}