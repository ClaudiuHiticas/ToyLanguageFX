package View;

import Controller.Controller;
import Model.*;
import Repository.Repository;
import Services.PrgStateService;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;


public class Main extends Application {

    private static Repository createRepository(IStmt statement) {
        Generator generator = new Generator();
        String logPath = "C:/Users/Claudiu/IdeaProjects/ToyLanguageFX/log.txt";
        MyIStack<IStmt> myStack1 = new MyStack<>();
        MyIDictionary<String, Integer> myDictionary1 = new MyDictionary<>();
        MyIList<Integer> myList1 = new MyList<>();
        MyIRandIntKeyDict<ITuple<String, BufferedReader>> fileTable1 = new MyRandIntKeyDict<>(generator);
        MyIRandIntKeyDict<Integer> heap = new MyRandIntKeyDict<>(generator);
        PrgState state = new PrgState(myStack1, myDictionary1, myList1, fileTable1, heap, statement, 1);
        return new Repository(state, logPath);

    }


    @Override
    public void start(Stage primaryStage) {

        //EX 1:
        //v=2;Print(v)
        IStmt ex1 = new CompStmt(new AssignStmt("v", new ConstExp(2)), new PrintStmt(new
                VarExp("v")));


        IStmt ex2 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(2),
                        new ArithExp("*", new ConstExp(3), new ConstExp(5)))),
                new CompStmt(new AssignStmt("b", new ArithExp("+", new VarExp("a"), new
                        ConstExp(1))), new PrintStmt(new VarExp("b"))));

        IStmt ex3 = new CompStmt(new AssignStmt("a", new ArithExp("-", new ConstExp(2), new
                ConstExp(2))),
                new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ConstExp(2)), new
                        AssignStmt("v", new ConstExp(3))), new PrintStmt(new VarExp("v"))));
        /*openRFile(var_f,"test.in");
        readFile(var_f,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)*/

        IStmt ex4 = new CompStmt(
                new OpenRFileStmt("var_f", "C:/Users/Claudiu/IdeaProjects/ToyLanguageFX/test.in"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));

        /*
        openRFile(var_f,"test.in");
        readFile(var_f+2,var_c);print(var_c);
        (if var_c then readFile(var_f,var_c);print(var_c)
        else print(0));
        closeRFile(var_f)
         */

        IStmt ex5 = new CompStmt(
                new OpenRFileStmt("var_f", "C:/Users/Claudiu/IdeaProjects/ToyLanguageFX/test.in"),
                new CompStmt(new ReadFileStmt(new VarExp("var_f+2"), "var_c"), new CompStmt(
                        new PrintStmt(new VarExp("var_c")), new CompStmt(new IfStmt(new VarExp("var_c"),
                        new CompStmt(new ReadFileStmt(new VarExp("var_f"), "var_c"), new PrintStmt(new VarExp("var_c"))),
                        new PrintStmt(new ConstExp(0))), new CloseRFileStmt(new VarExp("var_f"))))));

        //v=10;new(v,20);new(a,22);print(v
        IStmt ex6 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new PrintStmt(new VarExp("v")))));

        // v=10;new(v,20);new(a,22);print(100+rH(v));print(100+rH(a))
        IStmt ex7 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new CompStmt(new PrintStmt(new ArithExp("+", new ConstExp(100), new ReadHeapExp("v")))
                                        , new PrintStmt(new ArithExp("+", new ConstExp(100), new ReadHeapExp("a"))))
                        )));

        // v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a))
        IStmt ex8 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new CompStmt(new WriteHeapStmt("a", new ConstExp(30))
                                        , new CompStmt(new PrintStmt(new VarExp("a"))
                                        , new PrintStmt(new ReadHeapExp("a")))))));

        //v=10;new(v,20);new(a,22);wH(a,30);print(a);print(rH(a));a=0
        IStmt ex9 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("v", new ConstExp(20)),
                        new CompStmt(new NewStmt("a", new ConstExp(22)),
                                new CompStmt(new WriteHeapStmt("a", new ConstExp(30))
                                        , new CompStmt(new PrintStmt(new VarExp("a"))
                                        , new CompStmt(new PrintStmt(new ReadHeapExp("a")),
                                        new AssignStmt("a", new ConstExp(0))))))));

        //10 + (2<6) evaluates to 11

        IStmt ex10 = new CompStmt(new AssignStmt("a",
                new ArithExp("+", new ConstExp(10), new BoolExp("<", new ConstExp(2),
                        new ConstExp(6)))), new PrintStmt(new VarExp("a")));

        // (10+2)<6
        IStmt ex11 = new PrintStmt(new BoolExp("<",
                new ArithExp("+", new ConstExp(10), new ConstExp(2)), new ConstExp(2)));

        //v=6; (while (v-4) print(v);v=v-1);print(v)
        IStmt ex12 = new CompStmt(new AssignStmt("v", new ConstExp(6)),
                new CompStmt(new WhileStmt(new BoolExp(">", new ArithExp("-", new VarExp("v"), new ConstExp(4)), new ConstExp(0)),
                        new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v", new ArithExp("-", new VarExp("v"), new ConstExp(1))))),
                        new PrintStmt(new VarExp("v")))
        );

         /*
         v=10;new(a,22);
         fork(wH(a,30);v=32;print(v);print(rH(a)));
         print(v);print(rH(a))
         */

        IStmt forkStmt = new CompStmt(
                new WriteHeapStmt("a", new ConstExp(30)),
                new CompStmt(new AssignStmt("v", new ConstExp(32)),
                        new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))
                ));
        IStmt ex13 = new CompStmt(new AssignStmt("v", new ConstExp(10)),
                new CompStmt(new NewStmt("a", new ConstExp(22)), new CompStmt(
                        new ForkStmt(forkStmt), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp("a")))
                )));

        IStmt ex14 = new CompStmt(new AssignStmt("v1", new ConstExp(2)),
                     new CompStmt(new AssignStmt("v2", new ConstExp(3)),
                     new IfStmt(new VarExp("v1"), new PrintStmt(new MulExp(new VarExp("v1"), new VarExp("v2"))),
                                  new PrintStmt(new VarExp("v1")))));

        IStmt ex15 = new CompStmt(new AssignStmt("v", new ConstExp(20)),
                     new CompStmt(new WaitStmt(10), new PrintStmt(new ArithExp("*", new VarExp("v"), new ConstExp(10)))));


        IStmt ex16 = new CompStmt(new AssignStmt("v", new ConstExp(0)),
                             new ForStmt(new AssignStmt("v", new ConstExp(1)),
                                         new BoolExp("<", new VarExp("v"), new ConstExp(10)),
                                         new AssignStmt("v", new ArithExp("+", new VarExp("v"), new ConstExp(1))),
                             new PrintStmt(new VarExp("v"))));


        IStmt ex17 =new CompStmt(new AssignStmt("v", new ConstExp(20)), new CompStmt(
                new ForStmt(new AssignStmt("v", new ConstExp(0)),
                        new BoolExp("<", new VarExp("v"), new ConstExp(3)), new AssignStmt(
                        "v",
                        new ArithExp("+", new VarExp("v"), new ConstExp(1))),
                        new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")),
                                new AssignStmt("v",
                                        new ArithExp("+", new VarExp("v"),
                                                new ConstExp(1)))))),
                new PrintStmt(new ArithExp("*", new ConstExp(10), new VarExp("v")))));


        /*a=1;b=2;c=5;
        switch(a*10)
        (case (b*c) print(a);print(b))
        (case (10) print(100);print(200))
        (default print(300));
        print(300)*/
        IStmt ex18 = new CompStmt(new AssignStmt("a", new ConstExp(1)),
                     new CompStmt(new AssignStmt("b", new ConstExp(2)),
                     new CompStmt(new AssignStmt("c", new ConstExp(5)),
                     new CompStmt(
                                  new SwitchStmt(new ArithExp("*", new VarExp("a"), new ConstExp(10)),
                                                 new ArithExp("*", new VarExp("b"), new VarExp("c")),
                                                 new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))),
                                                 new ConstExp(10),
                                                 new CompStmt(new PrintStmt(new ConstExp(100)), new PrintStmt(new ConstExp(200))),
                                                 new PrintStmt(new ConstExp(300))),
                new PrintStmt(new ConstExp(300))))));



        try{
            List<IStmt> list = new ArrayList<>();
            list.add(ex1);
            list.add(ex2);
            list.add(ex3);
            list.add(ex4);
            list.add(ex5);
            list.add(ex6);
            list.add(ex7);
            list.add(ex8);
            list.add(ex9);
            list.add(ex10);
            list.add(ex11);
            list.add(ex12);
            list.add(ex13);
            list.add(ex14);
            list.add(ex15);
            list.add(ex16);
            list.add(ex17);
            list.add(ex18);
            VBox root = new VBox(7);
            root.getChildren().add(new Label("Choose the program"));


            //ListView
            ObservableList<IStmt> observableList = FXCollections.observableArrayList(list);
            ListView<IStmt> prgList = new ListView<>(observableList);
            prgList.setCellFactory(new Callback<ListView<IStmt>, ListCell<IStmt>>() {
                @Override
                public ListCell<IStmt> call(ListView<IStmt> param) {
                    return new ListCell<IStmt>() {
                        @Override
                        protected void updateItem(IStmt e, boolean empty) {
                            super.updateItem(e, empty);
                            if (e == null || empty)
                                setText("");
                            else
                                setText(e.toString());
                        }
                    };
                }
            });

            root.getChildren().add(prgList);

            Scene scene = new Scene(root, 600, 450);

            primaryStage.setScene(scene);
            primaryStage.setTitle("Examples");
            primaryStage.show();


            prgList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<IStmt>() {
                @Override
                public void changed(ObservableValue<? extends IStmt> observable, IStmt oldValue, IStmt newValue) {

                    try{
                        FXMLLoader loader = new FXMLLoader();
                        loader.setLocation(getClass().getResource("DialogWindow.fxml"));
                        VBox root = loader.load();
                        PrgStateService prgStateService = new PrgStateService(createRepository(newValue));
                        Controller ctrl = loader.getController();

                        ctrl.setService(prgStateService);
                        prgStateService.addObserver(ctrl);

                        Stage dialogStage = new Stage();
                        dialogStage.setTitle("Run example dialog");
                        dialogStage.initModality(Modality.APPLICATION_MODAL);
                        dialogStage.setScene(new Scene(root));
                        dialogStage.show();

                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }




    }

    public static void main(String[] args) {
        launch(args);
    }
}
