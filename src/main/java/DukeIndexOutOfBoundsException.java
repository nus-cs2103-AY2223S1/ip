public class DukeIndexOutOfBoundsException extends DukeException{
    
    public DukeIndexOutOfBoundsException(int size) {
        super(String.format("Please choose a number between 1 and %d", size));
    }
}
