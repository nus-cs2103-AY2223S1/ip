public class Event extends Task {
    private String dateTime;

    private Event(String taskText, String dateTime) {
        super(taskText.trim());
        this.dateTime = dateTime.trim();
    }

    public static Event of(String taskText, String source) {
        if (source.contentEquals("FILE")) {
            String[] detailArr = taskText.replace('|', '/').split("/", 3);
            Event event = new Event(detailArr[1], detailArr[2]);
            if (detailArr[0].contains("X")) {
                event.done();
            }
            return event;
        } else {
            String eventDetails = taskText.substring(0, taskText.indexOf('/'));
            String dateTime = taskText.substring(taskText.indexOf('/')+3);
            return new Event(eventDetails, dateTime);
        }
    }

    @Override
    public String toString(){
        //ui: (at: date)
        return "E" + super.toString() + " | " + dateTime;
    }
}
