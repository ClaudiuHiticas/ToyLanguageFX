package Exceptions;

public class VariableNotFound extends Exception {
    public VariableNotFound() {
        super("Variable was not found!");
    }
}
