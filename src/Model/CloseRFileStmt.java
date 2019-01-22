package Model;

import Exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStmt implements IStmt{
    private Exp exp_file_id;
    public CloseRFileStmt(Exp exp){
        this.exp_file_id = exp;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileNotOpened, IOException {
        MyIDictionary<String,Integer> symTable = state.getSymTable();
        MyIRandIntKeyDict<Integer> heap = state.getHeap();
        int file_id = this.exp_file_id.eval(symTable,heap);
        ITuple<String, BufferedReader> strBuffer = state.getFileTable().lookup(file_id);
        if(strBuffer == null)
            throw new FileNotOpened();
        strBuffer.getSecond().close();
        state.getFileTable().delete(file_id);
        return null;
    }
    public String toString(){
        return "closeRFile("+this.exp_file_id.toString() + ")";
    }
}
