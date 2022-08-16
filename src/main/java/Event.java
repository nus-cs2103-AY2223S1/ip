public class Event extends Task {

    private String at;

    public Event(String description, String at) throws DukeException{
        super(description);
        if (at == null) {
            throw new DukeException("☹ OOPS!!! Please follow the format </at date>.");
        }
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + at + ")";
    }
}
