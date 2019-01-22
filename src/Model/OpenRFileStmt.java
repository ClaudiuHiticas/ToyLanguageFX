package Model;

import Exceptions.*;

import java.io.*;

public class OpenRFileStmt implements IStmt {
    private String var_file_id;
    private String filename;

    public OpenRFileStmt(String var_file_id, String filename) {
        this.var_file_id = var_file_id;
        this.filename = filename;
    }

    @Override
    public PrgState execute(PrgState state) throws FileAlreadyUsed, FileDoesntExist, FileNotFoundException {
        MyIRandIntKeyDict<ITuple<String, BufferedReader>> fileTable = state.getFileTable();
        for (ITuple<String, BufferedReader> act : fileTable.values())
            if (act.getFirst().equals(this.filename))
                throw new FileAlreadyUsed();
        File f = new File(this.filename);
        if (!f.exists())
            throw new FileDoesntExist();
        MyIDictionary<String, Integer> symTbl = state.getSymTable();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename));
        int fileTableId = fileTable.add(new Tuple<>(this.filename, bufferedReader));
        symTbl.add(var_file_id,fileTableId);
        return null;
    }

    @Override
    public String toString(){
        return "openRFile("+this.var_file_id+",\""+this.filename + "\")";
    }
}
