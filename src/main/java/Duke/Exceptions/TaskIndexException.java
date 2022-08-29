package Duke.Exceptions;


public class TaskIndexException extends DukeException {

    @Override
    public String toString(){
        return " ☹ OOPS!!! The description of a todo cannot be empty.";
    }
}
