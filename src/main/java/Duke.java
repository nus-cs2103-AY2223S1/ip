
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.time.LocalDate;



public class Duke {
    static Ui ui = new Ui();
    static Parser parser = new Parser();
    static Storage storage = new Storage();
    static TaskList taskList = new TaskList(new ArrayList<>(),ui);


    public static void main(String[] args) {
        new Duke().run();
    }
    public static void run() {
        storage.readFile("duke.txt",taskList);
        Scanner scanner = new Scanner(System.in);
        ui.showWelcome();


        while(scanner.hasNext()){
            String userInput = scanner.nextLine();
            try{
                String[] parsedInput = parser.parseInput(userInput);
                switch(parsedInput[0]) {
                    case "bye" :
                        ui.goodbyeMessage();
                        scanner.close();
                        break;
                    case "list" :
                        taskList.printTaskList();
                        break;
                    case "todo" :
                        handleTodo(parsedInput);
                        break;
                    case "deadline" :
                        handleDeadline(parsedInput);
                        break;
                    case "event" :
                        handleEvent(parsedInput);
                        break;
                    case "mark" :
                        taskList.markDone(Integer.parseInt((parsedInput[1])));
                        break;
                    case "unmark" :
                        taskList.markNotDone(Integer.parseInt((parsedInput[1])));
                        break;
                    case "delete" :
                        taskList.delete(Integer.parseInt((parsedInput[1])));
                        break;
                    case "date" :
                        taskList.getItemsOnDate(parsedInput);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
                storage.writeFile(taskList.getTaskList());

            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }


    }

    /**
     * Handles the input for a Todo task, and adds it to the Task List.
     * @param input The parsed Todo task
     */
    public static void handleTodo(String[] input) throws DukeException {
        String taskName = input[1];
        if(taskName.equals("")){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task addTask = new Todo(taskName);
        ui.printAddTaskMsg(addTask, taskList.size());
        taskList.addTask(addTask);
    }

    /**
     * Handles the input for a Deadline task, and adds it to the Task List.
     * @param input The parsed Deadline task
     */
    public static void handleDeadline(String[] input) throws DukeException{
        String taskName = input[1];
        String date = input[2];
        if( taskName.equals("")){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        Task addTask = new Deadline(taskName,date);
        ui.printAddTaskMsg(addTask, taskList.size());
        taskList.addTask(addTask);
    }


    /**
     * Handles the input for an Event task, and adds it to the Task List.
     * @param input The parsed Event task
     */
    public static void handleEvent(String[] input) throws DukeException{
        String taskName = input[1];
        String date = input[2];
        if( taskName.equals("")){
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }
        Task addTask = new Event(taskName,date);
        ui.printAddTaskMsg(addTask, taskList.size());
        taskList.addTask(addTask);
    }




}

