package Exceptions;

public class FileAlreadyUsed extends Exception {
    public FileAlreadyUsed() {
        super("This file was already used");
    }
}
