package Services;

import Model.IStmt;
import Model.PrgState;
import Model.Tuple;
import Repository.IRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PrgStateService implements Observable<PrgState>{

    private List<Observer<PrgState>> observers = new ArrayList<>();
    private IRepository repo;

    public PrgStateService(IRepository repo) {
        this.repo = repo;
    }

    public IRepository getRepo() {
        return repo;
    }

    public List<PrgState> getAll(){
        return new ArrayList<>(this.repo.getPrgList());
    }

    public List<String> getOutList(){
        List<String> mList = new ArrayList<>();
        for(int i = 0; i < this.repo.getPrgList().get(0).getOut().size(); ++ i)
            mList.add(String.valueOf(this.repo.getPrgList().get(0).getOut().fromIndex(i)));
        return mList;
    }

    public List<Tuple<Integer, String>> getFileList() {
        List<Tuple<Integer, String>> mList = new ArrayList<>();
        for(Integer x : this.repo.getPrgList().get(0).getFileTable().keys())
            mList.add(new Tuple<>(x, this.repo.getPrgList().get(0).getFileTable().lookup(x).getFirst()));
        return mList;
    }

    public List<Map.Entry<Integer, Integer>> getHeapList() {
        System.out.println(repo.getPrgList().get(0).getHeap().getContent().entrySet());
        return new ArrayList<>(repo.getPrgList().get(0).getHeap().getContent().entrySet());
    }

    @Override
    public void addObserver(Observer<PrgState> o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer<PrgState> o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observers)
            o.update( this);
    }
}
