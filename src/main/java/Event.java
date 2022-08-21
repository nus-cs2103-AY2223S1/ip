import java.time.LocalDate;

public class Event extends Task {
    protected String eventTime;
    protected LocalDate date;

    public Event(String description, String eventTime) {
        super(description);
        if (eventTime.charAt(4) == '-' && eventTime.charAt(7) == '-') {
            this.date = LocalDate.parse(eventTime);
        }
        else {
            this.eventTime = eventTime;
        }

    }

    @Override
    public String toString() {
        if (date != null) {
            int year = date.getYear();
            int month = date.getMonthValue();
            int day = date.getDayOfMonth();
            switch (month) {
                case 1:
                    eventTime = "Jan " + day + " " + year;
                    break;
                case 2:
                    eventTime = "Feb " + day + " " + year;
                    break;
                case 3:
                    eventTime = "Mar " + day + " " + year;
                    break;
                case 4:
                    eventTime = "Apr " + day + " " + year;
                    break;
                case 5:
                    eventTime = "May " + day + " " + year;
                    break;
                case 6:
                    eventTime = "Jun " + day + " " + year;
                    break;
                case 7:
                    eventTime = "Jul " + day + " " + year;
                    break;
                case 8:
                    eventTime = "Aug " + day + " " + year;
                    break;
                case 9:
                    eventTime = "Sep " + day + " " + year;
                    break;
                case 10:
                    eventTime = "Oct " + day + " " + year;
                    break;
                case 11:
                    eventTime = "Nov " + day + " " + year;
                    break;
                case 12:
                    eventTime = "Dec " + day + " " + year;
                    break;
                default:
                    System.out.println("Invalid month");
            }
        }
        return "[D]" + super.toString() + " (eventTime: " + eventTime + ")";
    }
}