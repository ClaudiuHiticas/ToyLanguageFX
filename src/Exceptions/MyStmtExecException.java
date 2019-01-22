package Exceptions;

public class MyStmtExecException extends Exception {
    public MyStmtExecException() {
        super("The execution stack is empty!");
    }
}
