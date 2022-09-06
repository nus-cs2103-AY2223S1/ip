package duke;

import duke.exception.DukeException;
import duke.task.Task;

import java.time.LocalDate;
import java.time.LocalTime;

import static java.time.temporal.ChronoUnit.*;

public class Reminder {
    private final TaskList taskList;
    private final LocalDate nowDate;
    private final LocalTime nowTime;

    public Reminder() throws DukeException {
        this.taskList = this.loadTaskList();
        this.nowDate = LocalDate.now();
        this.nowTime = LocalTime.now();
    }

    private TaskList loadTaskList() throws DukeException {
        return new TaskList(new Storage("duke.txt").load());
    }
    public String getReminder() throws DukeException {
        try {
            Task firstComingTask = this.getFirstComingTask();
            String countdown = this.getCountDown(firstComingTask);
            return "REMINDER: The coming task is\n" +
                    firstComingTask + "\n" +
                    "COUNTDOWN: " + countdown;
        } catch (NullPointerException e) {
            //throw new DukeException("Null pointer exception!");
            return "No reminder for now :)";
        }
    }

    private Task getFirstComingTask() {
        Task nearestTask = null;
        long shortestDayBetween = Long.MAX_VALUE;
        long shortestTimeBetween = Long.MAX_VALUE;
        for (Task t : this.taskList.getList()) {
            if (t.getDate() == null && t.getTime() == null) {
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
        return this.nowDate.until(task.getDate()) + " "
                + this.nowTime.until(task.getTime(), HOURS) + " "
                + this.nowTime.until(task.getTime(), MINUTES);
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
