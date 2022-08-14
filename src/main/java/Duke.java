

import java.util.ArrayList;
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
            } else {
                addWord(userInput);
                continue;
            }
        }
    }

    public static void addWord (String word){
        Integer index = INPUT_LIST.size();
        INPUT_LIST.add(new Task(word));
        System.out.println("added: " + word);
    }

    private static void markDone(Integer index){
        Task task = INPUT_LIST.get(index-1);
        task.setDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
    }

    private static void markNotDone(Integer index){
        Task task = INPUT_LIST.get(index-1);
        task.setNotDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
    }

    public static void printList(){
        for(int i = 0; i<INPUT_LIST.size(); i++){
            Integer index = i+1;
            System.out.println(index + ". " + INPUT_LIST.get(i));
        }
    }
}
