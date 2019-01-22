package Model;

public class CompStmt implements IStmt {
    private IStmt first;
    private IStmt snd;

    public CompStmt(IStmt first,IStmt snd){
        this.first = first;
        this.snd = snd;
    }




    public String toString() {
        return "(" + first.toString() + "; " + snd.toString() + ") ";
    }

    public PrgState execute(PrgState state) {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
    }
}