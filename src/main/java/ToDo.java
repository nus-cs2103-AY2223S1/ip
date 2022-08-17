public class ToDo extends Task {

    public ToDo(String description) throws DukeException{
        super(description);
        if (description.isBlank()) {
            throw new DukeException("Take me seriouslyy :( What do you want to do?");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
