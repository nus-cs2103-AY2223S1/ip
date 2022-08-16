public class MarkException extends DukeException{
    public MarkException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString() + "please input a valid task number";
    }
}
