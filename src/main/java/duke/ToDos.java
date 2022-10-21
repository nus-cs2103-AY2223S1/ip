package duke;

public class ToDos extends Task {

    /**
     * Inheritance
     * @param description
     */
    public ToDos(String description) {
        super(description);
    }

    public ToDos(String testTest, int x) {
        super(testTest);
    }

    /**
     * Gives you ToDo description
     * @return the description of the todo object
     */
    public String getToDoDescirption() {return description;}

    /**
     * Returns symbol [T] which is used for printing and reading
     * @return String [T]
     */
    public static String getItem() {
        return "[T]";
    }

}
