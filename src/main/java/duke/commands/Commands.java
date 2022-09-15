package duke.commands;

/*
 * Commands is an enum that contains all the valid commands that the user can input.
 */
public enum Commands {
    bye("bye"),
    list("list"),
    delete("delete"),
    todo("todo"),
    deadline("deadline"),
    event("event"),
    mark("mark"),
    unmark("unmark"),
    find("find");

    public String text;

    /*
     * Constructor for the Commands enum.
     */
    Commands(String command) {
        this.text = command;
    }
}
