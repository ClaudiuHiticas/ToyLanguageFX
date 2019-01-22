package Model;

import Exceptions.DivisionByZero;
import Exceptions.OperatorNotFound;
import Exceptions.VariableNotFound;

public class MulExp extends Exp {

    Exp exp1;
    Exp exp2;

    public MulExp(Exp exp1, Exp exp2){
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    @Override
    int eval(MyIDictionary<String, Integer> tbl, MyIRandIntKeyDict<Integer> heap) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        int result;
        int e1 = exp1.eval(tbl, heap);
        int e2 = exp2.eval(tbl, heap);
        result = (e1*e2)-(e1+e2);
        return result;
    }

    @Override
    public String toString() {
        return "MUL(" + exp1.toString() + ", " + exp2.toString() + ")";
    }
}
