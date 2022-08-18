public class EmptyDescription extends DukeException {

    public EmptyDescription(String task) {
        super("description of a " + task + " cannot be empty!");
    }


}
