public class OutOfListException extends DukeException{
    public OutOfListException(String message){
        super(message);
    }

    @Override
    public String toString(){
        return String.format("%s The number entered is either negative or out of list",super.toString());
    }
}
