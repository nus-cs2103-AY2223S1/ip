package duke.logic.task;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.DukeEncoder;


/**
 * Represents a to-do task.
 */
public class ToDo extends Task {
    /**
     * Constructor for to-do.
     *
     * @param detail String.
     */
    public ToDo(String detail) {
        super(detail);
    }

    /**
     * Constructor for to-do.
     *
     * @param detail String.
     */
    public ToDo(String detail, boolean isDone) {
        super(detail, isDone);
    }

    /**
     * Add a to-do task.
     *
     * @param userInput text the user typed.
     * @param workList
     */
    public static String add(ArrayList<Task> workList, String userInput) {
        try {
            userInput.substring(6);
            String detail = userInput.substring(5);
            ToDo todo = new ToDo(detail);
            workList.add(todo);
            // Update Storage
            DukeEncoder.rewriteList(workList);
            return Task.add(workList, userInput) + todo + "\n"
                + updateNumOfTask(workList);
        } catch (StringIndexOutOfBoundsException e) {
            return new DukeException.EmptyTodoException().throwDukeException();
        }
    }
    /**
     * Returns String form of the task.
     * @return String.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String.
     */
    @Override
    public String storedData() {
        return "T" + "|" + super.storedData();
    }
}
