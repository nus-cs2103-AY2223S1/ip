package duke.Tasks;

import duke.DukeException.DateTimeFormatException;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

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
