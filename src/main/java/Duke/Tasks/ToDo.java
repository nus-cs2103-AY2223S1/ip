package Duke.Tasks;


import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String description){
        super(description);
    }

    public ToDo(String description, boolean IsDone) { super(description, IsDone);}

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String save() { return null; }

    @Override
    public String getTaskType() { return "ToDo" ; }

    @Override
    public LocalDateTime getDateTime() { return null; }

}
