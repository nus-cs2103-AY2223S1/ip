package main.java;

public class ToDo extends Task {

    public ToDo(String action) {
        super(action);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
