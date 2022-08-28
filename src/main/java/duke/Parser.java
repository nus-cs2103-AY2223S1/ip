package duke;

import duke.dukeExceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

public class Parser {
    public String parseInput(String input, TaskList tl) throws DukeException {
        String[] tokens = input.split(" ", 2);
        String command = tokens[0];
        String arguments = tokens.length == 2 ? tokens[1] : null;
        
        switch (command) {
            case "bye": 
                return "exit";
            case "list":
                return tl.listTasks();
            case "todo": {
                if (arguments == null) {
                    throw new DukeException("Todo description cannot be empty");
                }
                Task newTask = new ToDo(arguments.trim());
                tl.addTask(newTask);
                return "I've added: " + newTask + " you have " + tl.getTaskListSize() +" duke.tasks left";   
            }
            case "deadline": {
                if (arguments == null) {
                    throw new DukeException("duke.tasks.Deadline description cannot be empty");
                }
                
                String[] deadlineArgs = arguments.split("/by", 2);
                if (deadlineArgs.length == 1) {
                    throw new DukeException("duke.tasks.Deadline requires a /by date");
                }
                System.out.println(deadlineArgs[1]);
                Task newTask = new Deadline(deadlineArgs[0].trim(), deadlineArgs[1].trim());
                tl.addTask(newTask);
                return "I've added: " + newTask + " you have " + tl.getTaskListSize() +" duke.tasks left";
            }
            case "event": {
                if (arguments == null) {
                    throw new DukeException("duke.tasks.Event description cannot be empty");
                }

                String[] deadlineArgs = arguments.split("/at", 2);
                if (deadlineArgs.length == 1) {
                    throw new DukeException("duke.tasks.Event requires a /at date");
                }

                Task newTask = new Event(deadlineArgs[0].trim(), deadlineArgs[1].trim());
                tl.addTask(newTask);
                return "I've added: " + newTask + " you have " + tl.getTaskListSize() +" duke.tasks left";
            }
            case "mark": {
                try {
                    if (arguments == null) {
                        throw new DukeException("Please enter a valid task number");
                    }
                    int taskNo = Integer.parseInt(arguments) - 1;
                    Task taskTobeMarked = tl.getTask(taskNo);
                    taskTobeMarked.markDone();
                    return "I have marked: " + taskTobeMarked + " as done";
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new DukeException("Please enter a valid task number");
                }
            }
            case "unmark": {
                try {
                    if (arguments == null) {
                        throw new DukeException("Please enter a valid task number");
                    }
                    int taskNo = Integer.parseInt(arguments) - 1;
                    Task taskTobeMarked = tl.getTask(taskNo);
                    taskTobeMarked.markNotDone();
                    return "I have marked: " + taskTobeMarked + " as not done";
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new DukeException("Please enter a valid task number");
                }
            }
            case "delete": {
                try {
                    if (arguments == null) {
                        throw new DukeException("Please enter a valid task number");
                    }
                    int taskNo = Integer.parseInt(arguments) - 1;
                    Task taskTobeDeleted = tl.getTask(taskNo);
                    tl.removeTask(taskNo);
                    return "I have deleted: " + taskTobeDeleted;
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    throw new DukeException("Please enter a valid task number");
                }
            }
            case "find": {
//                try {
                    if (arguments == null) {
                        throw new DukeException("Please enter a keyword to search");
                    }
                    TaskList matchingTasks = new TaskList();
                    for (int i = 0; i < tl.getTaskListSize(); i++) {
                        if (tl.getTask(i).containsKeyword(arguments.trim())) {
                            matchingTasks.addTask(tl.getTask(i));
                        }
                    }
                    
                    return "Here are your matching tasks:\n" + matchingTasks.listTasks();
//                } catch ()
            }
            default:
                return "I don't know that command please enter a valid command";
        }
    }
}
