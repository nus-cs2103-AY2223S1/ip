

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING = "Hello from\n"  + LOGO + "\nHow can I help you ?\n" ;
    private static final List<Task> INPUT_LIST = new ArrayList<>();

    private static boolean isNumeric(String input) {
        return input.matches("^[0-9]*$");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(GREETING);

        while(scanner.hasNext()){
            String userInput = scanner.nextLine();
            String[] split = userInput.split(" ");
            try {
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                } else if (userInput.equals("list")) {
                    printList();
                } else if (split.length == 2 && split[0].equals("mark") && isNumeric(split[1])) {
                    markDone(Integer.parseInt(split[1]));
                } else if (split.length == 2 && split[0].equals("unmark") && isNumeric(split[1])) {
                    markNotDone(Integer.parseInt(split[1]));
                } else if (split.length == 2 && split[0].equals("delete") && isNumeric(split[1])) {
                    delete(Integer.parseInt(split[1]));
                } else if (split[0].equals("todo")) {
                    addTodo(split);
                } else if (split[0].equals("deadline")) {
                    addDeadline(split);
                } else if (split[0].equals("event")) {
                    addEvent(split);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
    private static void addTodo(String[] input) throws DukeException {
        String taskName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
        if(taskName.equals("")){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        addTask(new Todo(taskName));
    }

    private static void addDeadline(String[] input) throws DukeException{
        int indexOfDate = findDate(input);
        if( indexOfDate == -1){
            throw new DukeException("☹ OOPS!!! Please add a date for your deadline with /by.");
        } else {
            String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
            String date = String.join(" ", Arrays.copyOfRange(input, indexOfDate +1 , input.length));
            if( taskName.equals("")){
                throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            if( date.equals("")){
                throw new DukeException("☹ OOPS!!! The date of a deadline cannot be empty.");
            }
            addTask(new Deadline(taskName,date));
        }
    }

    private static void addEvent(String[] input) throws DukeException{
        int indexOfDate = findDate(input);
        if( indexOfDate == -1){
            throw new DukeException("☹ OOPS!!! Please add a date for your event with /at.");
        } else {
            String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
            String date = String.join(" ", Arrays.copyOfRange(input, indexOfDate +1 , input.length));
            if( taskName.equals("")){
                throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
            }
            if( date.equals("")){
                throw new DukeException("☹ OOPS!!! The date of an event cannot be empty.");
            }
            addTask(new Event(taskName,date));
        }
    }

    private static void addTask (Task task){
        Integer index = INPUT_LIST.size();
        INPUT_LIST.add(task);
        System.out.println("Got it. I've added this task:\n " + task.toString() + "\nNow you have " + INPUT_LIST.size() +" tasks in the list.");
    }

    private static Task getTask(Integer index) throws DukeException{
        if(index < 1 || index > INPUT_LIST.size()){
            throw new DukeException("☹ OOPS!!! That index is out of bounds.");
        }
        return INPUT_LIST.get(index-1);
    }
    
    private static void markDone(Integer index) throws DukeException{
        Task task = getTask(index);
        task.setDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }

    private static void markNotDone(Integer index) throws DukeException{
        Task task = getTask(index);
        task.setNotDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task.toString());
    }

    private static void delete(Integer index) throws DukeException{
        Task task = getTask(index);
        System.out.println(" Noted. I've removed this task:");
        System.out.println(" " + INPUT_LIST.get(index-1).toString() +"\nNow you have " + (INPUT_LIST.size() - 1) +" tasks in the list.");
        INPUT_LIST.remove(task);
    }

    private static void printList(){
        for(int i = 0; i<INPUT_LIST.size(); i++){
            Integer index = i+1;
            System.out.println(index + ". " + INPUT_LIST.get(i));
        }
    }

    private static int findDate(String[] split) {
        for(int i = 0; i<split.length; i++){
            if(split[i].equals("/by") || split[i].equals("/at")){
                return i;
            }
        }
        return -1;
    }
}
