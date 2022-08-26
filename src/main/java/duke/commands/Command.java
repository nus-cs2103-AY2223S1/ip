package duke.commands;

public enum Command {
    LIST, // lists all duke.tasks
    MARK, // mark a task
    UNMARK, // unmark a task
    TODO, // create a toDo task
    EVENT, // create an event
    DEADLINE, // create a deadline
    DELETE, // delete a task
}