package bob;

import java.time.LocalDate;

public class Parser {

    public enum keyword {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, REMOVE, FILTER
    }

    public boolean toExit = false;

    //referenced https://github.com/Donovan9617/ip/blob/master/src/main/java/Duke/Parser.java for structure
    public void parse(String reply, TaskList tasks, Storage storage, Ui ui) {
        String[] splitReply = reply.split(" ");
        String command = splitReply[0].toLowerCase();
        switch(command) {
            case "bye":
                ui.sayGoodbye();
                this.toExit = true;
                break;
            case "list":
                ui.displayTaskList(tasks);
                break;
            case "mark":
                try {
                    int index = Integer.valueOf(splitReply[1]);
                    tasks.markTask(index, true);
                    ui.displayMarked(tasks, index);
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.displayError("which task to mark?");
                }
                break;
            case "unmark":
                try {
                    int index = Integer.valueOf(splitReply[1]);
                    tasks.markTask(index, false);
                    ui.displayUnMarked(tasks, index);
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
                    ui.displayRemoved(tasks, removedTask);
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    ui.displayError("which task to remove?");
                }
                break;
            case "filter":
                try {
                    LocalDate date = LocalDate.parse(splitReply[1]);
                    ui.displayFiltered(date, tasks.filterTask(splitReply[1]));
                    storage.save(tasks);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("what date do you want to filter?");
                }
                break;
            case "todo":
                try {
                    String taskName = splitReply[1];
                    ToDo newTask = new ToDo(taskName);
                    tasks.addTask(newTask);
                    ui.displayAddedTask(tasks, newTask);
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! What's the todo again?");
                }
                break;
            case "deadline":
                try {
                    String taskName = splitReply[1];
                    String date = splitReply[3];
                    Deadline newTask = new Deadline(taskName, LocalDate.parse(date));
                    tasks.addTask(newTask);
                    ui.displayAddedTask(tasks, newTask);
                    storage.save(tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oops! What's the deadline again?");
                }
                break;
            case "event":
                try {
                    String taskName = splitReply[1];
                    String date = splitReply[3];
                    Event newTask = new Event(taskName, LocalDate.parse(date));
                    tasks.addTask(newTask);
                    ui.displayAddedTask(tasks, newTask);
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
