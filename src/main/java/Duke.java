import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo);
        System.out.println("What can I do for you?");

        // Scanner to get input
        Scanner scan = new Scanner(System.in);

        ArrayList<Task> log = new ArrayList<>();

        System.out.println("--------------------------------------");

        int indexOfSpace;
        String s;
        String firstWord = "";
        String restWord = "";

        boolean isMultipleWords = false;

        while(true) {
            s = scan.nextLine();
            indexOfSpace = s.indexOf(' ');
            isMultipleWords = indexOfSpace > -1;
            if (isMultipleWords) {
                firstWord = s.substring(0, indexOfSpace);
                restWord = s.substring(indexOfSpace).trim();
            }

            System.out.println("--------------------------------------");
            if (s.equals("bye")) {
                scan.close();
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (s.equals("list")){
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task item : log) {
                    System.out.println(count + ". " + item.toString());
                    count++;
                }
            } else {
                if (isMultipleWords && firstWord.equals("mark")) {
                    int index = Integer.parseInt(restWord) - 1; //array starts from 0
                    Task temp = log.get(index);
                    temp.Mark();
                    System.out.println("This task is now done: \n" + temp);
                } else if (isMultipleWords && firstWord.equals("unmark")) {
                    int index = Integer.parseInt(restWord) - 1; //array starts from 0
                    Task temp = log.get(index);
                    temp.Unmark();
                    System.out.println("This task is now not done: \n" + temp);
                } else {
                    log.add(new Task(s, false));
                    System.out.println("added: " + s);
                }
            }
            System.out.println("--------------------------------------");
        }

    }
}

