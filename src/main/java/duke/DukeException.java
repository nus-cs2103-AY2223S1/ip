package duke;

public class DukeException extends Exception {
    
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
    
    @Override
    public String toString() {
        String horizontalLine = "-------------------------";
        return horizontalLine + "\n" + super.toString() + "\n" + horizontalLine;
    }
    
}
