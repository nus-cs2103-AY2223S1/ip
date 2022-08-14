

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
            if (userInput.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                scanner.close();
                break;
            } else if( userInput.equals("list")){
                printList();
                continue;
            } else if(split.length == 2 && split[0].equals("mark") && isNumeric(split[1])) {
                markDone(Integer. parseInt(split[1]));
                continue;
            } else if(split.length == 2 && split[0].equals("unmark") && isNumeric(split[1])) {
                markNotDone(Integer. parseInt(split[1]));
                continue;
            } else if (split[0].equals("todo")){
                addTodo(split);
                continue;
            } else if (split[0].equals("deadline")){
                addDeadline(split);
                continue;
            } else if (split[0].equals("event")){
                addEvent(split);
                continue;
            }
        }
    }
    public static void addTodo(String[] input){
        String taskName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
        addTask(new Todo(taskName));
    }

    public static void addDeadline(String[] input) {
        int indexOfDate = findDate(input);
        String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
        String date = String.join(" ", Arrays.copyOfRange(input, indexOfDate +1 , input.length));
        addTask(new Deadline(taskName,date));
    }

    public static void addEvent(String[] input){
        int indexOfDate = findDate(input);
        String taskName = String.join(" ", Arrays.copyOfRange(input, 1, indexOfDate));
        String date = String.join(" ", Arrays.copyOfRange(input, indexOfDate +1 , input.length));
        addTask(new Event(taskName,date));
    }

    public static void addTask (Task task){
        Integer index = INPUT_LIST.size();
        INPUT_LIST.add(task);
        System.out.println("Got it. I've added this task:\n " + task.toString() + "\nNow you have " + INPUT_LIST.size() +" tasks in the list.");
    }

    private static void markDone(Integer index){
        Task task = INPUT_LIST.get(index-1);
        task.setDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + task.toString());
    }

    private static void markNotDone(Integer index){
        Task task = INPUT_LIST.get(index-1);
        task.setNotDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(" " + task.toString());
    }

    public static void printList(){
        for(int i = 0; i<INPUT_LIST.size(); i++){
            Integer index = i+1;
            System.out.println(index + ". " + INPUT_LIST.get(i));
        }
    }

    public static int findDate(String[] split){
        for(int i = 0; i<split.length; i++){
            if(split[i].equals("/by") || split[i].equals("/at")){
                return i;
            }
        }
        return -1;
    }
}
