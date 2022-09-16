package duke;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.time.temporal.ChronoUnit.MINUTES;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;

import duke.exception.DukeException;
import duke.task.Task;

/**
 * Prompt the first coming undone task when the app is launched.
 */
public class Reminder {
    private static final int MINUTES_IN_AN_HOUR = 60;
    private final TaskList taskList;
    private final LocalDate nowDate;
    private final LocalTime nowTime;
    private final Ui ui;

    /**
     * Constructs Reminder instance.
     *
     * @throws DukeException if taskList is failed to load.
     */
    public Reminder() throws DukeException {
        this.taskList = this.loadTaskList();
        this.nowDate = LocalDate.now();
        this.nowTime = LocalTime.now();
        this.ui = new Ui();
    }

    private TaskList loadTaskList() throws DukeException {
        return new TaskList(new Storage("mytasklist.txt").load());
    }

    /**
     * Get the first coming and undone task in the future from the TaskList.
     *
     * @return String of beautified task and the time remaining to complete the task.
     */
    public String getReminder() {
        try {
            Task firstComingTask = this.getFirstComingUndoneTask();
            String countdown = this.getCountDown(firstComingTask);
            return "REMINDER: The coming task is\n"
                    + this.ui.beautyWrapTask(firstComingTask) + "\n"
                    + "COUNTDOWN: " + countdown;
        } catch (NullPointerException e) {
            return "No reminder for now :)";
        }
    }

    private Task getFirstComingUndoneTask() {
        Task nearestTask = null;
        long shortestDayBetween = Long.MAX_VALUE;
        long shortestTimeBetween = Long.MAX_VALUE;
        for (Task t : this.taskList.getList()) {
            if (t.isMarked() || (t.getDate() == null && t.getTime() == null)) {
                continue;
            }
            if (this.isFutureDate(t)) {
                long daysBetween = DAYS.between(this.nowDate, t.getDate());
                if (daysBetween < shortestDayBetween) {
                    shortestDayBetween = daysBetween;
                    nearestTask = t;
                } else if (daysBetween == shortestDayBetween) {
                    long timeBetween = SECONDS.between(this.nowTime, t.getTime());
                    if (timeBetween < shortestTimeBetween) {
                        shortestTimeBetween = timeBetween;
                        nearestTask = t;
                    }
                }
            }
        }
        return nearestTask;
    }

    private String getCountDown(Task task) {
        int year = 0;
        int month = 0;
        int day = 0;
        Period periodDate = this.nowDate.until(task.getDate());
        String periodString = periodDate.toString();
        int number = 0;
        for (int i = 0; i < periodString.length(); i++) {
            char c = periodString.charAt(i);

            switch (c) {
            case 'P':
                break;
            case 'Y':
                year = number;
                break;
            case 'M':
                month = number;
                break;
            case 'D':
                day = number;
                break;
            default:
                number = Character.getNumericValue(c);
                break;
            }
        }
        String countdownString = "";
        if (year != 0) {
            countdownString += year + " Year, ";
        }
        if (month != 0) {
            countdownString += month + " Month, ";
        }
        if (day != 0) {
            countdownString += day + " Day, ";
        }

        long periodMinutes = this.nowTime.until(task.getTime(), MINUTES);
        long hour = periodMinutes / MINUTES_IN_AN_HOUR;
        long minute = periodMinutes % MINUTES_IN_AN_HOUR;
        if (hour != 0) {
            countdownString += hour + " Hour, ";
        }
        if (minute != 0) {
            countdownString += minute + " Minute ";
        }
        return countdownString;
    }


    private boolean isFutureDate(Task task) {
        if (task.getDate().isAfter(this.nowDate)) {
            return true;
        } else if (task.getDate().isEqual(this.nowDate)
                && task.getTime().isAfter(this.nowTime)) {
            return true;
        } else {
            return false;
        }
    }
}
