package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This class encapsulates parsed data.
 */
public class ParsedData {
    private final String DEFAULT_TIME_FORMAT = "dd/MM/yyyy HH:mm";
    private DateTimeFormatter formatter;
    private boolean isDone;
    private String task;
    private String during;
    private String timeText;
    private LocalDateTime dateTime;
    private int listIndex;

    /**
     * Constructor
     *
     * @param task Task.
     * @param during Time keyword.
     * @param time Time.
     */
    public ParsedData(String task, String during, String time) {
        this.task = task;
        this.during = during;
        try {
            this.formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
            this.dateTime = LocalDateTime.parse(time, this.formatter);
            this.timeText = time.toString().replaceAll("-", "/").
                    replace('T', ' ');
        } catch (DateTimeParseException e) {
            //System.out.println("You could enter the time in this format: dd/MM/yyyy HH:mm");
            this.timeText = time;
        }

    }

    /**
     * Constructor
     *
     * @param status Status.
     * @param task Task.
     * @param during Time keyword.
     * @param time Time.
     */
    public ParsedData(String status, String task, String during, String time) {
        this.task = task;
        this.during = during;
        try {
            this.formatter = DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT);
            this.dateTime = LocalDateTime.parse(time, this.formatter);
            this.timeText = this.dateTime.toString().replaceAll("-", "/").
                    replace('T', ' ');
        } catch (DateTimeParseException e) {
            System.out.println("You could enter the time in this format: dd/MM/yyyy HH:mm");
            this.timeText = time;
        }
        this.isDone = status.equals("X");
    }

    /**
     * Constructor
     *
     * @param task Task.
     */
    public ParsedData(String task) {
        this.task = task;
    }

    /**
     * Constructor
     *
     * @param listIndex Index.
     */
    public ParsedData(int listIndex) {
        this.listIndex = listIndex;
    }

    public String getTaskName() {
        return this.task;
    }

    public String getDuring() {
        return this.during;
    }

    public String getTimeText() {
        return this.timeText;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public boolean hasDateTime() {
        return this.dateTime != null;
    }

    public int getListIndex() {
        return listIndex;
    }
}
