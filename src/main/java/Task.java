import java.util.Scanner;

public class Task {
    private String description;
    private boolean done;

    public Task(Scanner options) throws MissingOptions {
        if (options.hasNext()) {
            this.description = options.nextLine().substring(1);
            this.done = false;
        } else {
            throw new MissingOptions("xxxxx");
        }
    }

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        done = true;
    }
    
    public void unmark() {
        done = false;
    }

    private String check() {
        return done ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return check() + " " + description;
    }
}
