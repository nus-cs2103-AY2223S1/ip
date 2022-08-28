package bob;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public boolean toExit = false;

    //referenced https://github.com/Donovan9617/ip/blob/master/src/main/java/Duke/Parser.java for structure
    public void parse(String reply, TaskList tasks, Storage storage, Ui ui) {
        String[] splitReply = reply.split(" ");
        String command = splitReply[0].toLowerCase();
        switch(command) {
            case "bye":
                ui.printLine();
                ui.sayGoodbye();
                ui.printLine();
                this.toExit = true;
                break;
            case "list":
                ui.printLine();
                ui.displayTaskList(tasks, "here are your tasks!");
                ui.printLine();
                break;
            case "mark":
                try {
                    int index = Integer.valueOf(splitReply[1]);
                    tasks.markTask(index, true);
                    ui.printLine();
                    ui.displayMarked(tasks, index);
                    ui.printLine();
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.displayError("which task to mark?");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.valueOf(splitReply[1]);
                    tasks.markTask(index, false);
                    ui.printLine();
                    ui.displayUnMarked(tasks, index);
                    ui.printLine();
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.displayError("which task to unmark?");
                }
                break;
            case "remove":
                try {
                    int index = Integer.valueOf(splitReply[1]);
                    Task removedTask = tasks.getTask(index);
                    tasks.removeTask(index);
                    ui.printLine();
                    ui.displayRemoved(tasks, removedTask);
                    ui.printLine();
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.displayError("which task to remove?");
                }
                break;
            case "filter":
                try {
                    LocalDate date = LocalDate.parse(splitReply[1]);
                    String response = "here are your tasks on " + "'"
                            + date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + "'";
                    ui.printLine();
                    ui.displayTaskList(tasks.filterTask(splitReply[1]), response);
                    ui.printLine();
                    storage.save(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("what date do you want to filter?");
                }
                break;
            case "find":
                try {
                    String searchWord = splitReply[1];
                    String response = "here are all tasks with" + " '" + searchWord + "'";
                    ui.printLine();
                    ui.displayTaskList(tasks.findTask(searchWord), response);
                    ui.printLine();
                    storage.save(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("what word do you want to search for?");
                }
                break;
            case "todo":
                try {
                    String taskName = reply.substring(5);
                    ToDo newTask = new ToDo(taskName);
                    tasks.addTask(newTask);
                    ui.printLine();
                    ui.displayAddedTask(tasks, newTask);
                    ui.printLine();
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! What's the todo again?");
                }
                break;
            case "deadline":
                try {
                    String[] temp = reply.split("/");
                    String taskName = temp[0].substring(9, temp[0].length() - 1);
                    String date = temp[1].substring(3);
                    Deadline newTask = new Deadline(taskName, LocalDate.parse(date));
                    tasks.addTask(newTask);
                    ui.printLine();
                    ui.displayAddedTask(tasks, newTask);
                    ui.printLine();
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! What's the deadline again?");
                }
                break;
            case "event":
                try {
                    String[] temp = reply.split("/");
                    String taskName = temp[0].substring(6, temp[0].length() - 1);
                    String date = temp[1].substring(3);
                    Event newTask = new Event(taskName, LocalDate.parse(date));
                    tasks.addTask(newTask);
                    ui.printLine();
                    ui.displayAddedTask(tasks, newTask);
                    ui.printLine();
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! When's your event again?");
                }
                break;
            default:
                System.out.println("Oops! What do you want to do?");

        }
    }
}
