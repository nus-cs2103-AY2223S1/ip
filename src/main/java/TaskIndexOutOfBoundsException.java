public class TaskIndexOutOfBoundsException extends DukeException {
    public TaskIndexOutOfBoundsException(int providedIndex, int maxIndex) {
        super(maxIndex == 0 ? "You have no tasks!" :
                "The provided task index " + providedIndex + " is out of range! Accepted Range: [1, " + maxIndex + "]");
    }
}