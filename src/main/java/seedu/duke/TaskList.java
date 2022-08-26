package seedu.duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaskList {

    public static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public LocalDate getLocalDate(String date) {
        String[] dateDetails = date.split("-");
        String day = dateDetails[0];
        String month = dateDetails[1];
        String year = dateDetails[2];
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        List<String> list = Arrays.asList(year, month, day);

        String dateToParse = String.join("-", list);
        System.out.println(dateToParse);
        return LocalDate.parse(dateToParse);
    }

    public static void addTask(String taskType, String input) throws DukeException {

        switch (taskType) {
            case "todo":
                String[] removeTaskType = input.split("todo ");
                String description = String.join("", removeTaskType);
                if (description.equals("todo")) {
                    throw new DukeException("");
                }
                Task todo = new ToDo(description);
                taskList.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                break;
            case "deadline":
                String[] removeTaskType2 = input.split("deadline ");
                String desAndBy = String.join("", removeTaskType2);
                String[] sliceByDesAndBy = desAndBy.split(" /by ");
                String description2 = sliceByDesAndBy[0];
                String dueDateAndTime = sliceByDesAndBy[1];
                String[] dateAndTime = dueDateAndTime.split(" ");
                System.out.println(dateAndTime.length);
                if (dateAndTime.length == 2) {
                    String dueDate = dateAndTime[0];
                    String dueTime = dateAndTime[1];
                    LocalDate localDate = Storage.getLocalDate(dueDate);
                    Task deadline = new Deadline(description2, localDate, dueTime);
                    taskList.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                } else {
                    String dueDate = dateAndTime[0];
                    LocalDate localDate = Storage.getLocalDate(dueDate);
                    Task deadline = new Deadline(description2, localDate);
                    taskList.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                }
                break;
            case "event":
                String[] removeTaskType3 = input.split("event ");
                String desAndBy2 = String.join("", removeTaskType3);
                String[] sliceByDesAndBy2 = desAndBy2.split(" /at ");
                String description3 = sliceByDesAndBy2[0];
                String dueTime2 = sliceByDesAndBy2[1];
                Task event = new Event(description3, dueTime2);
                taskList.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                break;

        }

        // edge case of 1 task
        String numTask = String.format("Now you have %s tasks in the list.", taskList.size());
        System.out.println(numTask);

    }

    public void deleteTask(int num) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + this.taskList.get(num - 1));
        this.taskList.remove(num - 1);
        String numTask = String.format("Now you have %s tasks in the list.", this.taskList.size());
        System.out.println(numTask);
    }

    public void markTask(int num) {
        Task task = this.taskList.get(num - 1);
        task.mark();
    }

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

    public void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            String line = String.format("%s. %s", i + 1, this.taskList.get(i));
            System.out.println(line);
        }
    }


//    public String toString() {
//        String output = String.join("\n", this.taskList);
//    }

}
