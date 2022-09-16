package duke;

public class PriorityLevel {

    public enum Priority {
        LOW,
        MEDIUM,
        HIGH,
        URGENT
    }


    public static Priority getPriorityString(String level) {
        switch (level) {
        case "0":
            return Priority.LOW;
        case "1":
            return Priority.MEDIUM;
        case "2":
            return Priority.HIGH;
        case "3":
            return Priority.URGENT;
        default:
            System.out.println("wrong input");
            return Priority.LOW;
        }
    }
}