package Tasks;

import Tasks.Task;

public class ToDo extends Task {

    public ToDo(String description){
        super(description);
    }

    public ToDo(String description, boolean IsDone) { super(description, IsDone);}

    @Override
    public String toString(){
        return "T | " + super.toString();
    }

}
