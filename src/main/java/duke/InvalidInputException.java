package duke;

<<<<<<< HEAD
public class InvalidInputException extends DukeException{
    public InvalidInputException(String message){
=======
public class InvalidInputException extends DukeException {
    public InvalidInputException(String message) {
>>>>>>> branch-A-CodingStandard
        super(message);
    }

    @Override
<<<<<<< HEAD
    public String toString(){
        return String.format("%s I'm sorry, but I don't know what that means :-(",super.toString());
=======
    public String toString() {
        return String.format("%s I'm sorry, but I don't know what that means :-(", super.toString());
>>>>>>> branch-A-CodingStandard
    }
}
