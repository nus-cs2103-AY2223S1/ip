package ren.task;

/**
 * Todo Task represents an action that needs to be done.
 */
public class Todo extends Task {
    /**
     * Constructor for a Todo Task.
     *
     * @param description Description of the Task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a Todo Task constructed with data read from File.
     *
     * @param data Data of a Todo Task.
     * @return Todo Task or null if data is corrupted.
     */
    public static Todo readData(String[] data) {
        if (data.length < 3) {
            return null;
        }
        Todo newTodo = new Todo(data[2]);
        if (data[1].equals("X")) {
            newTodo.setDone(true);
        }
        return newTodo;
    }

    /**
     * Returns the Todo Task information for writing to a File.
     *
     * @return String with Todo Task information.
     */
    @Override
    public String writeData() {
        String symbol = this.isDone ? "X" : " ";
        return "T|" + symbol + "|" + this.description;
    }

    /**
     * Returns the string representation of this Todo Task.
     *
     * @return String Representation.
     */
    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[T][" + symbol + "] " + this.description + "\n";
    }
}
