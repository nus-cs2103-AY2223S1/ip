package ava.task;

/**
 * Class to represent "Bye" tasks.
 */
public class Bye extends Task {
    /**
     * The constructor for Bye task.
     */
    public Bye() {
        super("bye", false);
        this.isBye = true;
    }
}
