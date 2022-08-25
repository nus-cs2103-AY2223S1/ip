public class Event extends Task {

    //private final String date;

    protected Event(String name, String date) {
        super(name, date);
        type = "E";
    }

    @Override
    public String stringType() {
        return "event";
    }

}
