package main.java;

public class DukeException extends Exception {
    private String content;

    public DukeException(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return this.content;
    }
}
