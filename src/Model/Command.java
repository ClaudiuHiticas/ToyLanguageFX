package Model;

import java.io.FileNotFoundException;

public abstract class Command {
    private String key, description;

    Command(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public abstract void execute() throws FileNotFoundException;

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
}