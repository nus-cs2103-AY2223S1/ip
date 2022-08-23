import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) throws DukeException {
        try {
            this.taskList = taskList;
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        }
    }

    public static ArrayList<Task> getTaskList() {
        return taskList;
    }

        public static void addTaskToArray(String s, Task.TYPE type) {
            Task t;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            try {
                switch (type) {
                case DEADLINE:
                    if (s.length() < 1) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String[] splitStringDL = s.split(" /by ");
                    if (splitStringDL.length < 2) {
                        throw new DukeException("☹ Deadline requires a BY time typed correctly.");
                    }
                    String taskStringDL = splitStringDL[0];
                    String by = splitStringDL[1];
                    //for date only
                    String[] dateDeadlineOnly = by.split(" ");
                    if (dateDeadlineOnly.length == 1) {
                        throw new DukeException("Time required!");
                    } else {
                        LocalDateTime dateBy = LocalDateTime.parse(by, formatter);
                        t = new Deadline(taskStringDL, dateBy);
                        break;
                    }

                case TODO:
                    if (s.length() < 1) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    t = new Todo(s);
                    break;

                case EVENT:
                    if (s.length() < 1) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String[] splitStringTD = s.split(" /at ");

                    if (splitStringTD.length < 2) {
                        System.out.println(splitStringTD.length);
                        throw new DukeException("☹ Event requires an AT time typed correctly.");
                    }
                    String taskStringTD = splitStringTD[0];
                    String at = splitStringTD[1];
                    String[] dateEventOnly = at.split(" ");
                    if (dateEventOnly.length == 1) {
                        throw new DukeException("Time required!");
                    } else {
                        LocalDateTime dateAt = LocalDateTime.parse(at, formatter);
                        t = new Event(taskStringTD, dateAt);
                        break;
                    }

                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                taskList.add(t);
                UI.printAddition(t);
            } catch (DateTimeParseException e) {
                throw new DukeException("Date/Time not in correct format!");
            }
        }

    public static int taskListLength() {
        return taskList.size();
    }

    public static void deleteTaskfromArray(String taskNumber) {
        String numberToRemove = taskNumber.replaceAll("[^0-9]", "");
        int numberToRemoveInt = Integer.parseInt(numberToRemove) - 1;
        Task t = taskList.get(numberToRemoveInt);
        taskList.remove(numberToRemoveInt);
        UI.getLINE();
        System.out.println("Noted. I've removed this task:\n " + " " + t.toString() +
                "\nNow you have " + taskListLength() + " tasks in the list.");
        UI.getLINE();
    }

    //method to mark as done
    public static void markAsDone(String removeTaskNumberString) {
        String numberToRemove = removeTaskNumberString.replaceAll("[^0-9]", "");
        int numberToRemoveInt = Integer.parseInt(numberToRemove) - 1;
        if (numberToRemoveInt == taskListLength()) {
            throw new DukeException("Number larger than current list.");
        }
        Task tsk = taskList.get(numberToRemoveInt);
        tsk.markAsDone();
        UI.markAsDoneUI(tsk);
    }

    public static void markAsUndone(String addTaskNumberString) {
        String numberToAddAgain = addTaskNumberString.replaceAll("[^0-9]", "");
        int numberToRemoveInt = Integer.parseInt(numberToAddAgain) - 1;
        if (numberToRemoveInt == taskListLength()) {
            throw new DukeException("Number larger than current list.");
        }
        Task tsk = taskList.get(numberToRemoveInt);
        tsk.markAsUndone();
        UI.markAsUndoneUI(tsk);
    }


}
