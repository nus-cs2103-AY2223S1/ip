public class DukeTodoEmptyException extends DukeException{
    DukeTodoEmptyException() {
        super();
    }
    public String toString() {
      return "OOPS!!! The description of a todo cannot be empty.";
    }

}
