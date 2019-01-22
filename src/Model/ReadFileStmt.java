package Model;

import Exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;


public class ReadFileStmt implements IStmt {
    private Exp exp_file_id;
    private String var_name;

    public ReadFileStmt(Exp exp, String var_name) {
        this.exp_file_id = exp;
        this.var_name = var_name;
    }

    @Override
    public PrgState execute(PrgState state) throws DivisionByZero, VariableNotFound, OperatorNotFound, FileNotOpened, IOException {
        MyIDictionary<String, Integer> symTable = state.getSymTable();
        MyIRandIntKeyDict<Integer> heap = state.getHeap();
        int file_id = this.exp_file_id.eval(symTable,heap);
        ITuple<String, BufferedReader> fileTable = state.getFileTable().lookup(file_id);
        if (fileTable == null)
            throw new FileNotOpened();
        String line = fileTable.getSecond().readLine();
        int value;
        if (line == null)
            value = 0;
        else
            value = Integer.parseInt(line);
        if (symTable.isDefined(var_name))
            symTable.update(var_name, value);
        else
            symTable.add(var_name, value);
        return null;
    }

    @Override
    public String toString(){
        return "readFile(" + exp_file_id.toString() + "," + var_name + ")";
    }
}
