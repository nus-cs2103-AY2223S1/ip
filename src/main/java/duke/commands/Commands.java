package duke.commands;

public enum Commands {
    bye("bye"),
    list("list"),
    delete("delete"),
    todo("todo"),
    deadline("deadline"),
    event("event"),
    mark("mark"),
    unmark("unmark");

    public String text;

    Commands(String command) {
        this.text = command;
    }
}
