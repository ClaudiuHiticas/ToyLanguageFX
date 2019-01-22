package Repository;

import Model.MyList;
import Model.PrgState;

import java.util.List;


public interface IRepository {
    void logPrgStateExec(PrgState pr);
    String getFilePath();
    List<PrgState> getPrgList();
    void setPrgList(List<PrgState> list);

    void reset();

    int size();
}
