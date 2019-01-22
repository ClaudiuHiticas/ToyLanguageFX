package Model;


public class ForkStmt implements IStmt{
    private IStmt forkStmt;

    public ForkStmt(IStmt forkStmt) {
        this.forkStmt = forkStmt;
    }

    @Override
    public String toString() {
        return "fork(" + this.forkStmt + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        return new PrgState(new MyStack<>(),state.getSymTable().clone(),state.getOut(),
                state.getFileTable(),state.getHeap(), this.forkStmt,state.getId()*10);
    }
}
