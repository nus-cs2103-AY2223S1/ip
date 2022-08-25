/**
 * ToDos class creates todos
 * @author Shaune Ang
 */
public class ToDos extends Task{
    /**
     * Creates ToDos from user input
     * @param name name of task
     */
    ToDos(String name) {
        super(name);
    }

    /**
     * ToDos constructor for task loaded from saved file
     * @param name name of task
     * @param status completed status of task
     */
    ToDos(String name, boolean status) {
        super(name, status);
    }

    /**
     * String format for displaying todos tasks
     * @return string format for displaying event task
     */
    @Override
    public String toString() {
        return String.format("[T] %s", super.toString());
    }
}
