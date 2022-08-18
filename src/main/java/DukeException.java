public class DukeException extends Exception{

    public DukeException(String message) {
        super(message);
    }
    @Override
    public String toString() {
        String border = "##############################################";
        return border + "\n" + super.getMessage() + "\n" + border;
    }
}
