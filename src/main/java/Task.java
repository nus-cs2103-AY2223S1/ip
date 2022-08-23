import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Task {
    private final String TASK;
    private Boolean completionStatus;
    private String completionIcon;
    private LocalDate date;
    private LocalTime time;
    Task(List<String> task) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions( "The description of a task cannot be empty.");
        }
        this.TASK = this.parseTask(task);
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    Task(List<String> task, String taskName) throws DekuExceptions {
        if (task.size() == 0) {
            throw new DekuExceptions("The description of a " + taskName + " cannot be empty.");
        }
        this.TASK = this.parseTask(task);
        this.completionStatus = false;
        this.completionIcon = "[ ]";
    }

    public LocalDate getDate() {
        return this.date;
    }


    private String parseTask(List<String> task) throws DekuExceptions {
        String date = "";
        String misc = "";
        String time = "";
        String output = "";
        boolean isDateTime = false;
        while (!task.isEmpty()) {
            String top = task.remove(0);
            if (top.charAt(0) == '/') {
                isDateTime = true;
                output += "(" + top.substring(1) + ": ";
                continue;
            }
            if (isDateTime) {
                if (date.equals("")) {
                    date = top;
                } else {
                    time = top;
                }
                misc += top + " ";
            } else {
                output += top + " ";
            }
        }
        if (!misc.equals("")) {
            misc = misc.substring(0, misc.length() - 1);
        }
        if (isDateTime) {
            parseDate(date);
            if (this.date == null) {
                return output + misc + ")";
            }
            output += " " + this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
            if (time.equals("")) {
                return output + ")";
            }
            parseTime(time);
            if (this.time == null) {
                return output + " " + time + ")";
            }
            return output + " " + this.time.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
        }
        return output.substring(0, output.length() - 1);
    }

    private void parseDate(String dateString) throws DekuExceptions {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[dd/MM/yyyy][dd-MM-yyyy][yyyy-MM-dd]");
            this.date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeException e) {
            this.date = null;
            System.out.println("Invalid date format! I will add this task, some functionalities might not work!\n" +
                    "Currently supports: dd/MM/yyyy | dd-MM-yyyy | yyyy-MM-dd |\n" +
                    "Example: 23/08/2022");
        }
    }

    private void parseTime(String timeString) throws DekuExceptions {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[HH:mm][HHmm]");
            this.time = LocalTime.parse(timeString, formatter);
        } catch (DateTimeException e) {
            if (this.date == null) {
                return;
            }
            this.time = null;
            System.out.println("Please input a valid time format! I will add this task, some functionalities might not work!\n" +
                    "Currently supports 24 hour format: HH:mm | HHmm |\n" +
                    "Example: 1800");
        }
    }
    void setCompletionStatus(boolean set) {
        completionStatus = set;
        if (completionStatus) {
            completionIcon = "[X]";
        } else {
            completionIcon = "[ ]";
        }
    }

    @Override
    public String toString() {
        return completionIcon + " - " + TASK;
    }
}
