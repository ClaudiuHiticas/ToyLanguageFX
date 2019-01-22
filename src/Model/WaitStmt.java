package Model;

import Exceptions.*;

import java.io.IOException;

public class WaitStmt implements IStmt {

    int number;

    public WaitStmt(int number) {
        this.number = number;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileAlreadyUsed, FileDoesntExist, IOException, FileNotOpened {

        MyIStack<IStmt> stk = state.getStk();

        if(number != 0){
            stk.push(new PrintStmt(new ConstExp(number)));
            stk.push(new WaitStmt(number - 1));
        }

        return null;
    }

    @Override
    public String toString() {
        return "Wait(" + number + ") ";
    }
}
