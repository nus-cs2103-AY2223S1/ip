package duke.tasks;

import duke.dukeexception.DateTimeFormatException;

/**
 * Sub-class of task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * Constructs a ToDo object given description string.
     * @param name The name of task.
     * @return The generated ToDo Task.
     * @throws DateTimeFormatException
     */
    public static ToDo addTask(String name) throws DateTimeFormatException {
        ToDo newToDo = new ToDo(name);
        System.out.println("       " + newToDo.printSelf());
        return newToDo;
    }


    @Override
    public String printSelf() throws DateTimeFormatException {
        return "[T]" + super.printSelf();
    }

    @Override
    public String recordString() {
        return "T | " + super.recordString();
    }
}
