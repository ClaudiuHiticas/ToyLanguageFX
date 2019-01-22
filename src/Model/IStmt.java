package Model;

import Exceptions.*;

import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileAlreadyUsed, FileDoesntExist, IOException, FileNotOpened;
}
