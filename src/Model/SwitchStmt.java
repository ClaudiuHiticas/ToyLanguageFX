package Model;

import Exceptions.*;

import java.io.IOException;

public class SwitchStmt implements IStmt {

    private Exp condition;
    private Exp exp1;
    private Exp exp2;
    private IStmt stmt1;
    private IStmt stmt2;
    private IStmt defaultStmt;

    public SwitchStmt(Exp condition, Exp exp1,IStmt stmt1, Exp exp2, IStmt stmt2, IStmt defaultStmt)
    {
        this.condition = condition;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.defaultStmt = defaultStmt;
    }


    @Override
    public PrgState execute(PrgState state) throws VariableNotFound, OperatorNotFound, FileAlreadyUsed, FileDoesntExist, IOException, FileNotOpened, DivisionByZero {

        MyIStack<IStmt> stk = state.getStk();

        IStmt newStmt = new IfStmt( new BoolExp("==",condition , exp1), stmt1,
                new IfStmt(new BoolExp("==", condition, exp2), stmt2, defaultStmt));

        stk.push(newStmt);

        return null;
    }

    public String toString()
    {
        return "switch(" + condition.toString() + ")\n" +
                "(case (" + exp1.toString() + ") " + stmt1.toString() + ")\n" +
                "(case (" + exp2.toString() + ") " + stmt2.toString() + ")\n" +
                "(default " + defaultStmt.toString() + ");";
    }
}
