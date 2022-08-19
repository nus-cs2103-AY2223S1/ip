public class DukeIndexOutOfBoundsException extends DukeException{
    
    public DukeIndexOutOfBoundsException(int size) {
        super(String.format("Please choose a number from 1 to %d", size));
    }
}
