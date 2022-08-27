package duke;

<<<<<<< HEAD
public class OutOfListException extends DukeException{
    public OutOfListException(String message){
=======
public class OutOfListException extends DukeException {
    public OutOfListException(String message) {
>>>>>>> branch-A-CodingStandard
        super(message);
    }

    @Override
<<<<<<< HEAD
    public String toString(){
        return String.format("%s The number entered is either negative or out of list",super.toString());
=======
    public String toString() {
        return String.format("%s The number entered is either negative or out of list", super.toString());
>>>>>>> branch-A-CodingStandard
    }
}
