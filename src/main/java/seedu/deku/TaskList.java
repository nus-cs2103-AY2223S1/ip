package seedu.deku;

import java.time.LocalDate;

import java.util.ArrayList;

/**
 * Represents a list of tasks for Deku.
 */
public class TaskList {

    public static ArrayList<Task> taskList;

    /**
     * Creates a list of tasks for Deku.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Represents a list of tasks for Deku.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Returns ArrayList<Task> of tasks of TaskList object.
     *
     * @return task list.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }


    /**
     * Adds Task to this.tasklist.
     *
     * @param taskType Either a "todo", "deadline" or "event"
     * @param input User input
     * @throws DekuException
     */
    public static String addTask(String taskType, String input) throws DekuException {
        String addLine = "Got it. I've added this task:";
        switch (taskType) {
            case "todo":
                ToDo todo = addToDo(input);
                taskList.add(todo);
                addLine += ("\n  " + todo);
                break;
            case "deadline":
                Deadline deadline = addDeadline(input);
                taskList.add(deadline);
                addLine += ("\n  " + deadline);
                break;
            case "event":
                Event event = addEvent(input);
                taskList.add(event);
                addLine += ("\n  " + event);
                break;

        }
        // edge case of 1 task
        addLine += String.format("\nNow you have %s tasks in the list.", taskList.size());
        return addLine;

    }


    private static ToDo addToDo(String input) throws DekuException {
        String[] removeTaskType = input.split("todo ");
        String description = String.join("", removeTaskType);
        if (description.equals("todo")) {
            throw new DekuException("");
        }
        return new ToDo(description);

    }

    private static Deadline addDeadline(String input) {
        // deadline return book /by 2-12-2019 1800
        System.out.println(input);
        String[] removeTaskType = input.split("deadline ");
        for (int i=0; i< removeTaskType.length; i++ ){
            System.out.println(removeTaskType[i]);
        }
        String desAndBy = String.join("", removeTaskType);
        System.out.println(desAndBy);
        String[] sliceByDesAndBy = desAndBy.split(" /by ");
        String description = sliceByDesAndBy[0];

        String dueDateAndTime = sliceByDesAndBy[1];
        System.out.println("description: "+ description);
        System.out.println("due date and time " + dueDateAndTime);
        String[] dateAndTime = dueDateAndTime.split(" ");
        System.out.println(dateAndTime.length);
        if (dateAndTime.length == 2) {
            String dueDate = dateAndTime[0];
            String dueTime = dateAndTime[1];
            System.out.println("dueDate: " + dueDate);
            System.out.println("dueTime: "+ dueTime);
            String dueDateString = dueDate + " " + dueTime;
            LocalDate localDate = Storage.getLocalDate(dueDate);
            System.out.println(new Deadline(description, localDate, dueTime, dueDateString));
            return new Deadline(description, localDate, dueTime, dueDateString);

        } else {
            String dueDate = dateAndTime[0];
            LocalDate localDate = Storage.getLocalDate(dueDate);
            return new Deadline(description, localDate, dueDate);
        }
    }

    private static Event addEvent(String input) {
        String[] removeTaskType = input.split("event ");
        String desAndBy = String.join("", removeTaskType);
        String[] sliceByDesAndBy = desAndBy.split(" /at ");
        String description = sliceByDesAndBy[0];
        String dueTime = sliceByDesAndBy[1];
        return new Event(description, dueTime);

    }

    /**
     * Removes tasks at ith index of this.tasklist, assuming the this.tasklist starts from index 1.
     *
     * @param num index of task in this.tasklist.
     */
    public String deleteTask(int num) {
        String deleteLine = "Noted. I've removed this task:";
        deleteLine += "\n  " + this.taskList.get(num - 1);
        this.taskList.remove(num - 1);
        deleteLine += String.format("\nNow you have %s tasks in the list.", this.taskList.size());
        return deleteLine;
    }


    public String rescheduleTask(int num, ArrayList<String> rescheduleDateAndTime) {
        String line = "Noted. I've snoozed this task:";
        line += "\n  " + this.taskList.get(num - 1) + "\nto\n  ";
        if (this.taskList.get(num - 1) instanceof Deadline) {
            Deadline currTask = (Deadline) this.taskList.get(num - 1);
            System.out.println(currTask);
            String dueDateString = rescheduleDateAndTime.get(0);
            LocalDate newDate = Storage.getLocalDate(rescheduleDateAndTime.get(0));
            rescheduleDateAndTime.remove(0);
            String newTime = String.join(" ", rescheduleDateAndTime);
            String dueDateTimeString = dueDateString + " " + newTime;
            System.out.println(newTime);
            if (!newTime.equals("")) {
                this.taskList.set(num - 1, Deadline.reschedule(currTask, newDate, newTime,dueDateTimeString));
            } else {
                this.taskList.set(num - 1, Deadline.reschedule(currTask, newDate, dueDateTimeString));
            }
            line += this.taskList.get(num - 1);
        } else if (this.taskList.get(num - 1) instanceof Event) {
            Event currTask = (Event) this.taskList.get(num - 1);
            String newDate = String.join(" ", rescheduleDateAndTime);
            this.taskList.set(num - 1, Event.reschedule(currTask, newDate));
            line += this.taskList.get(num - 1);
        }
        line += String.format("\nNow you have %s tasks in the list.", this.taskList.size());
        return line;
    }

    /**
     * Marks Task at ith index of this.tasklist as done, assuming the this.tasklist starts from index 1.
     *
     * @param num index of task in this.tasklist.
     */
    public void markTask(int num) {
        Task task = this.taskList.get(num - 1);
        task.mark();
    }

    /**
     * Marks Task at ith index of this.tasklist as undone, assuming the this.tasklist starts from index 1.
     *
     * @param num index of task in this.tasklist.
     */
    public void unmarkTask(int num) {
        Task task = this.taskList.get(num - 1);
        task.unmark();
    }

    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < this.taskList.size(); i++) {
            Task task = this.getTaskList().get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    /**
     * Prints tasks in data/deku.txt when Deku is initialized.
     */
    public String printList() {
        String list = "Here are the tasks in your list:";
        for (int i = 0; i < this.taskList.size(); i++) {
            list += String.format("\n%s. %s", i + 1, this.taskList.get(i));
        }
        return list;
    }
}
