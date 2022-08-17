import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duck {
    public static void main(String[] args) {
        System.out.println("Hello! Got any grapes?");
        Scanner scanner = new Scanner(System.in);
        String word = "";
        ArrayList<Todo> list = new ArrayList<>();
        while (!word.equals("bye")) {
            word = scanner.nextLine();
            if (word.toUpperCase().equals("LIST")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            } else {
                try {
                    String[] arr = word.split(" ", 2);
                    String command = arr[0];
                    String arguments = arr[1];

                    switch (command.toUpperCase()) {
                        case "TODO":
                            Todo newTodo = new Todo(arguments);
                            list.add(newTodo);
                            System.out.println("Added todo " + newTodo + " Quack!");
                            break;
                        case "DEADLINE":
                            String[] deadlineArgs = arguments.split("/by");
                            Deadline newDeadline = new Deadline(deadlineArgs[0], deadlineArgs[1]);
                            list.add(newDeadline);
                            System.out.println("Added new Deadline " + newDeadline + " Quack!");
                            break;
                        case "EVENT":
                            String[] eventArgs = arguments.split("/at");
                            Event newEvent = new Event(eventArgs[0], eventArgs[1]);
                            list.add(newEvent);
                            System.out.println("Added new Event " + newEvent + " Quack!");
                            break;
                        case "MARK":
                            Todo currentUnmarkedItem = list.get(Integer.parseInt(arguments) - 1);
                            currentUnmarkedItem.completeTask();
                            System.out.println("Quack, marked! " + currentUnmarkedItem);
                            break;
                        case "UNMARK":
                            Todo currentMarkedItem = list.get(Integer.parseInt(arguments) - 1);
                            currentMarkedItem.unCompleteTask();
                            System.out.println("Quack, unmarked! " + currentMarkedItem);
                            break;
                        default:
                            System.out.println("Quack!?! What does that even mean!?!?!");
                            break;
                    }
                } catch (ArrayIndexOutOfBoundsException a) {
                    if (word.toUpperCase().contains("TODO") ||
                            word.toUpperCase().contains("DEADLINE") ||
                            word.toUpperCase().contains("EVENT"))
                    {
                        System.out.println("Quack!!!!! " + word.toUpperCase() + " Arguments cannot be blank!");
                    } else {
                        System.out.println("Speak properly! Quack!");
                    }
                }
            }
            /*if (word.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            } else if(word.contains("unmark")) {
                String[] split= word.split("\\s+");
                Todo currentTask = list.get(Integer.parseInt(split[1]) - 1);
                currentTask.unCompleteTask();
                System.out.println("Quack, unmarked! " + currentTask);
            } else if(word.contains("mark")) {
                String[] split = word.split("\\s+");
                Todo currentTask = list.get(Integer.parseInt(split[1]) - 1);
                currentTask.completeTask();
                System.out.println("Quack, marked! " + currentTask);
            }  else {
                System.out.println("added: " + word);
                list.add(new Todo(word));
            }*/
        }
        System.out.println("Quack!");
    }
}
