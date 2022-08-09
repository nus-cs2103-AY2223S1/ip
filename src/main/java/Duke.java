import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
public class Duke {

    private static List<Task> dukeInputs;
    private static final String ENDING_MESSAGE = "That's all? Hope to see you again soon :)";
    private static final String LIST_HEADER = "Here are the tasks in your list:";
    private static final String DELETE_HEADER = "Noted. I've removed this task:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";

    private static final String UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";


    private static final List<String> PERMISSIBLE_TASKS = new ArrayList<>(Arrays.asList("todo", "event", "deadline"));



    public static void main(String[] args)  {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("Hello from\n" + logo);


            System.out.println("Hello! I'm Duke \n" + "What can I do for you?");
            //Read the inputs, if not initialize new empty ArrayList;
            dukeInputs = SaveData.ReadItems();

            Scanner sc = new Scanner(System.in);
            String input;
            dukeInputs = SaveData.ReadItems();
            while (true) {
                // Read the input
                input = sc.nextLine();

                if (input.startsWith("mark")) {
                    //split by space, then the second value
                    int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
                    dukeInputs.get(taskIndex).setDone();
                    Task currentTask = dukeInputs.get(taskIndex);

                    System.out.println(DONE_MESSAGE);
                    System.out.println("  " + dukeInputs.get(taskIndex));

                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
                    dukeInputs.get(taskIndex).removeDone();
                    Task currentTask = dukeInputs.get(taskIndex);

                    System.out.println(UNDONE_MESSAGE);
                    System.out.println("  " + dukeInputs.get(taskIndex));

                } else if (input.equals("bye")) {
                    System.out.println(ENDING_MESSAGE);
                    SaveData.Save(dukeInputs);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(LIST_HEADER);
                    for (int i = 0; i < dukeInputs.size(); i++) {
                        Task currentTask = dukeInputs.get(i);
                        System.out.println(i + 1 + ". " + currentTask);
                    }
                } else if (input.startsWith("delete")) {
                    int taskIndex = Integer.valueOf(input.split(" ", 0)[1]) - 1;
                    Task deletedTask = dukeInputs.get(taskIndex);
                    dukeInputs.remove(taskIndex);
                    System.out.println(DELETE_HEADER);
                    System.out.println("  " + deletedTask);
                    System.out.println("Now you have " + dukeInputs.size() + " tasks in the list");
                }

                //handles the addition of tasks
                else {

                    try {
                        ValidateTask(input);
                    } catch (InvalidCommandException ICE) {
                        System.out.println(ICE);
                        System.out.println("This was your invalid command: " + input);
                    } catch (EmptyTaskException ETE) {
                        System.out.println(ETE);
                        String tempArr[] = input.split(" ", 0);
                        if (tempArr[0] == "todo") {
                            System.out.println("todo requires at least a task description");
                        } else {
                            System.out.println("Event/Deadline requires both a task description and a date");
                        }
                    }
                    Task newTask = GenerateTask(input);
                    dukeInputs.add(newTask);
                    System.out.println("Got it. I've added this task:\n" + " " + newTask);
                    System.out.println("Now you have " + dukeInputs.size() + " tasks in the list");

                }
            }
        }

        private static void ValidateTask(String input) throws InvalidCommandException, EmptyTaskException {
            String tempArr[] = input.split(" ", 0); //splits into words

            //first word is invalid
            if (! PERMISSIBLE_TASKS.contains(tempArr[0])) {
                throw new InvalidCommandException("I'm sorry, I don't understand what that means \n"
                       + "Please enter a valid response in the future");
            }

            if (tempArr.length <= 1) {
                throw new EmptyTaskException("The description of a task cannot be empty.");
            }

        }


        private static Task GenerateTask (String input) {
            String tempArr[] = input.split(" ", 2);
            if (input.startsWith("todo")) {
                return new Todo(tempArr[1]);
            } else if (input.startsWith("deadline")) {
                int firstSplit = tempArr[1].indexOf("/by");
                String eventName = tempArr[1].substring(0, firstSplit);
                String date = tempArr[1].substring(firstSplit + 3);
                return new Deadline(eventName, date);
            } else { //must be Event
                int firstSplit = tempArr[1].indexOf("/at");
                String eventName = tempArr[1].substring(0, firstSplit);
                String date = tempArr[1].substring(firstSplit + 3);
                return new Event(eventName, date);
            }
        }
}

