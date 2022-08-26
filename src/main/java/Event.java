public class Event extends Task{
    private String dateAndTime;

    public Event(String name, String dateAndTime) {
        super(name);
        this.dateAndTime = dateAndTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (by: " + this.dateAndTime + ")";
    }

    private String[] splitIntoDateAndTime(String string) {
        String[] token = string.split("", 2);
        return token;
    }

    private String getDateFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);
        return token[0];
    }

    private String getTimeFromInput(String input) {
        String[] token = splitIntoDateAndTime(input);
        return token[1];
    }
}
