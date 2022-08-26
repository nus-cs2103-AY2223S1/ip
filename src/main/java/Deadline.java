public class Deadline extends Task {
    private String dateTime;

    private Deadline(String taskText, String dateTime) {
        super(taskText.trim());
        this.dateTime = dateTime.trim();
    }

    public static Deadline of(String taskText, String source) {
        if (source.contentEquals("FILE")) {
            String[] detailArr = taskText.replace('|', '/').split("/", 3);
            Deadline deadline = new Deadline(detailArr[1], detailArr[2]);
            if (detailArr[0].contains("X")) {
                deadline.done();
            }
            return deadline;
        } else {
            String eventDetails = taskText.substring(0, taskText.indexOf('/'));
            String dateTime = taskText.substring(taskText.indexOf('/')+3);
            return new Deadline(eventDetails, dateTime);
        }
    }

    @Override
    public String toString(){
        //ui : (by: date)
        return "D" + super.toString() + " | " + dateTime;
    }
}