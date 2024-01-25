package cs2103t.ip.duke;

public class Statistics {
    private int doneCount;
    private int todoCount;
    private int eventCount;
    private int deadlineCount;

    public Statistics(int doneCount, int todoCount, int eventCount, int deadlineCount) {
        this.doneCount = doneCount;
        this.todoCount = todoCount;
        this.eventCount = eventCount;
        this.deadlineCount = deadlineCount;
    }

    public void addDone() {
        doneCount ++;
    }

    public void addTodo() {
        todoCount ++;
    }

    public void addEvent() {
        eventCount ++;
    }

    public void addDeadline() {
        deadlineCount ++;
    }

    public void delDone() {
        doneCount --;
    }

    public int getDoneCount() {
        return doneCount;
    }

    public int getTodoCount() {
        return todoCount;
    }

    public int getEventCount() {
        return eventCount;
    }

    public int getDeadlineCount() {
        return deadlineCount;
    }

    public String toString() {
        return String.format("DUKE STATISTICS \n" + "You have created: \n" + "%d Todo, %d Events, %d Deadlines. \n" +
                "You have completed %d tasks \n" + "in Duke's lifetime.", todoCount, eventCount, deadlineCount, doneCount);
    }
}