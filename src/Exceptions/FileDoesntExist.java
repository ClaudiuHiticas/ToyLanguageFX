package Exceptions;

public class FileDoesntExist extends Exception {
    public FileDoesntExist() {super("The file doesn't exist or is a directory");}
}
