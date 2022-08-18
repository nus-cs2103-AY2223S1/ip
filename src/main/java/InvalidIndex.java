public class InvalidIndex extends DukeException {
    private int index;

    public InvalidIndex(int i) {
        this.index = i;
    }

    @Override
    public String toString() {
        return "There is no task at index " + index;
    }
    
}
