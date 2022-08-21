import java.time.LocalDate;

public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    public Deadline(String description, String by) {
        super(description);
        if (by.charAt(4) == '-' && by.charAt(7) == '-') {
            this.date = LocalDate.parse(by);
        }
        else {
            this.by = by;
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
                    by = "Jan " + day + " " + year;
                    break;
                case 2:
                    by = "Feb " + day + " " + year;
                    break;
                case 3:
                    by = "Mar " + day + " " + year;
                    break;
                case 4:
                    by = "Apr " + day + " " + year;
                    break;
                case 5:
                    by = "May " + day + " " + year;
                    break;
                case 6:
                    by = "Jun " + day + " " + year;
                    break;
                case 7:
                    by = "Jul " + day + " " + year;
                    break;
                case 8:
                    by = "Aug " + day + " " + year;
                    break;
                case 9:
                    by = "Sep " + day + " " + year;
                    break;
                case 10:
                    by = "Oct " + day + " " + year;
                    break;
                case 11:
                    by = "Nov " + day + " " + year;
                    break;
                case 12:
                    by = "Dec " + day + " " + year;
                    break;
                default:
                    System.out.println("Invalid month");
            }
        }
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}