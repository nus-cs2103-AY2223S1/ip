public class EmptyToDoDescriptionException extends  Exception{

    public EmptyToDoDescriptionException(){
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
