package Duke.Tasks;
import java.time.LocalDateTime;

/**
 * Class that extends from Task and represents an Todo task.
 */
public class ToDo extends Task {

    /**
     * Public constructor of Todo class.
     * @param description The description of the Todo task.
     */
    public ToDo(String description){
        super(description);
    }

    /**
     * Public constructor of Todo class.
     * @param description The description of Todo task.
     * @param isDone The status of the Todo task.
     */
    public ToDo(String description, boolean isDone) { super(description, isDone);}
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String save() { return String.format("T | %b | %s\n", super.getIsDone(), this.description); }

    @Override
    public String getTaskType() { return "ToDo" ; }

    @Override
    public LocalDateTime getDateTime() { return null; }

}
