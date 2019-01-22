package Model;


import Exceptions.*;

public class ArithExp extends Exp {
    private Exp firstExp;
    private Exp sndExp;
    private String op; //1 stands for +, 2 for -, etc ....

    public ArithExp(String op, Exp e1, Exp e2) {
        this.firstExp = e1;
        this.sndExp = e2;
        this.op = op;
    }

    //override
    int eval(MyIDictionary<String, Integer> tbl, MyIRandIntKeyDict<Integer> heap) throws DivisionByZero, VariableNotFound, OperatorNotFound {
        int finalResult;
        int rez1 = this.firstExp.eval(tbl,heap);
        int rez2 = this.sndExp.eval(tbl,heap);
        switch (op) {
            case "+":
                finalResult = rez1 + rez2;
                break;
            case "-":
                finalResult = rez1 - rez2;
                break;
            case "*":
                finalResult = rez1 * rez2;
                break;
            case "//":
                if (rez2 == 0) throw new DivisionByZero();
                finalResult = rez1 / rez2;
                break;
            default:
                throw new OperatorNotFound();
        }
        return finalResult;
    }

    @Override
    public String toString() {
        return this.firstExp.toString() + this.op + this.sndExp.toString();
    }
}