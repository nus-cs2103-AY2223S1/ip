package duke;

<<<<<<< HEAD
public class IncompleteParamException extends DukeException{
    public IncompleteParamException(String message){
=======
public class IncompleteParamException extends DukeException {
    public IncompleteParamException(String message) {
>>>>>>> branch-A-CodingStandard
        super(message);
    }

    @Override
<<<<<<< HEAD
    public String toString(){
        return String.format("%s Your '%s' is incomplete!",super.toString(),this.getMessage());
=======
    public String toString() {
        return String.format("%s Your '%s' is incomplete!", super.toString(), this.getMessage());
>>>>>>> branch-A-CodingStandard
    }
}
