import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        boolean acceptingInput = true;
        System.out.println("Hello from Duke");
        System.out.println("What can I do for you?");
        ArrayList<Task> storage = new ArrayList<>();

        while (acceptingInput) {
            Scanner inputScanner = new Scanner(System.in);
            String totalString  = inputScanner.nextLine();
            String[] inputStringArray = totalString.split(" ");

            if (totalString.equals("bye")){
                acceptingInput = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (totalString.equals("list")) {
                for (int i = 0; i < storage.size(); i++) {
                    int index = i+ 1;
                    System.out.println(index + ". " + storage.get(i));
                }
            } else if (inputStringArray[0].equals("mark")) {
                    int index = Integer.parseInt(inputStringArray[1]) - 1;
                    storage.get(index).markAsDone();
                    System.out.println("Nice! I've marked this task as done");
                    System.out.println(storage.get(index));

            } else if (inputStringArray[0].equals("unmark")){
                    int index = Integer.parseInt(inputStringArray[1]) - 1;
                    storage.get(index).unmark();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(storage.get(index));
            } else {


                if (inputStringArray[0].equals("todo")) {
                    String[] nameArray = Arrays.asList(inputStringArray).subList(1,inputStringArray.length).toArray(new String[0]);
                    String taskName = Arrays.stream(nameArray).reduce("", (a,b) -> a + " "+ b);
                    Todo newTask = new Todo(taskName);
                    storage.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + storage.size() + " tasks in the list");
                } else if (inputStringArray[0].equals("deadline")){
                    int splitter = Arrays.asList(inputStringArray).indexOf("/by");

                    String[] nameArray = Arrays.asList(inputStringArray).subList(1,splitter).toArray(new String[0]);
                    String taskName = Arrays.stream(nameArray).reduce("", (a,b) -> a + " "+ b);

                    String[] deadlineArray = Arrays.asList(inputStringArray).subList(splitter + 1,inputStringArray.length).toArray(new String[0]);
                    String deadlineName = Arrays.stream(deadlineArray).reduce("", (a,b) -> a + " "+ b);

                    Deadlines newTask = new Deadlines(taskName, deadlineName);
                    storage.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + storage.size() + " tasks in the list");

                } else if (inputStringArray[0].equals("event")){
                    int splitter = Arrays.asList(inputStringArray).indexOf("/at");

                    String[] nameArray = Arrays.asList(inputStringArray).subList(1,splitter).toArray(new String[0]);
                    String taskName = Arrays.stream(nameArray).reduce("", (a,b) -> a + " "+ b);

                    String[] eventArray = Arrays.asList(inputStringArray).subList(splitter + 1,inputStringArray.length).toArray(new String[0]);
                    String eventName = Arrays.stream(eventArray).reduce("", (a,b) -> a + " "+ b);

                    Event newTask = new Event(taskName,eventName);
                    storage.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask);
                    System.out.println("Now you have " + storage.size() + " tasks in the list");

                } else {
                    System.out.println("No suitable name for that task");
                }
            }
        }

    }

    public static boolean isNumeric(String string) {
        int intValue;

        if(string == null || string.equals("")) {
            System.out.println("String cannot be parsed, it is null or empty.");
            return false;
        }

        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Input String cannot be parsed to Integer.");
        }
        return false;
    }
}
