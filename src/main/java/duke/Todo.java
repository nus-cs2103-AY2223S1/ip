package duke;

/**
 * Encapsulates a Todo Class
 */
public class Todo extends Task{


    /**
     * Constructs a Todo class
     * @param description The description of the Todo task
     */
    public Todo(String description){
        super(description);
    }

    /**
     * Returns the parsed version of the deadline to be stored in the text file.
     * @return the parsed Todo
     */
    @Override
    public String parseTask() {
        return "T" + super.parseTask();
    }

    /**
     * Returns the string representation of the Todo
     * @return The string representation of the Todo
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
