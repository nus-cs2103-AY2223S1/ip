package task;

/**
 * The ToDos class which is a subclass of Task,
 * encapsulates ToDos objects
 *
 * @author  Wee Xin Yang, Markus
 * @version 0.1
 * @since   2022-8-24
 */
public class ToDos extends Task {

    public ToDos(String todo) {

        super(" " + todo);
        assert todo != null : "Please input a ToDo task!";
    }

    /**
     * Returns a String object representing this ToDos' value.
     *
     * @return the string representation of the specified ToDo
     */
    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}
