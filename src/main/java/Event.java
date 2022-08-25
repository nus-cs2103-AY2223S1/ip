public class Event extends Task {
    private String startTime;
    private String endTime;

    Event(String title, boolean isCompleted, String time) {
        this(title, isCompleted, time, time);
    }

    Event(String title, String time) {
        this(title, false, time, time);
    }

    Event(String title, boolean isCompleted, String startTime, String endTime) {
        super(title, isCompleted);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Event fromSaveFormat(String saveFormat) throws IllegalArgumentException {
        final String[] args = saveFormat.split(";;");
        if (args.length != 4) {
            throw new IllegalArgumentException("Incorrect save format: " + saveFormat);
        }
        return new Event(args[3], args[2].equals("1"), args[0], args[1]);
    }

    @Override
    public String toSaveFormat() {
        return String.format("E;;%s;;%s;;%s", startTime, endTime, super.toSaveFormat());
    }

    @Override
    public String toString() {
        return String.format("📆 %s (from %s to %s)", super.toString(), startTime, endTime);
    }
}
