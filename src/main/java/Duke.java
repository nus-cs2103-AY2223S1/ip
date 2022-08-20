import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // Initialise the task list
    private static ArrayList<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        main();
    }

    /**
     * Prints out the greeting message.
     */
    private static void greetUser() {
        System.out.println("Hello! I'm Jukebox :)\n" + "What can I do for you today?");
    }

    /**
     * Prints out the exit message.
     */
    private static void exitJukebox() {
        System.out.println("Aww... OK, Hope to see you again!");
    }

    /**
     * Main logic handler.
     */
    public static void main() {
        // Variables
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        // Logic handler
        while (true) {
            input = sc.nextLine();
            String[] inputWordsSplit = input.split(" ", 2);
            String commandWord = inputWordsSplit[0];
            CommandWord command = null;

            // Updating the command word, catching any invalid inputs
            try {
                command = CommandWord.getCommand(commandWord);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }

            // Print out goodbye message and exits the program
            if (command == CommandWord.BYE) {
                exitJukebox();
                break;
            }

            // Prints out the task list
            if (command == CommandWord.LIST) {
                printList(taskList);
                continue;
            }

            // Marks or unmarks the given task
            if (command == CommandWord.MARK || command == CommandWord.UNMARK) {
                markUnmarkTask(command, inputWordsSplit);
                continue;
            }

            // Add a new task based on the given keyword
            if (command == CommandWord.TODO || command == CommandWord.DEADLINE || command == CommandWord.EVENT) {
                try {
                    addNewTask(command, inputWordsSplit[1]);
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("test");
                }
            }
        }
    }

        /**
         * Prints out the task list iteratively.
         * @param taskList Input task list
         */
        private static void printList (ArrayList < Task > taskList) {
            if (!taskList.isEmpty()) {
                System.out.println("These are your current tasks! :)");
                int i = 1;
                for (Task t : taskList) {
                    String s = String.format("%d.%s", i, t);
                    System.out.println(s);
                    i++;
                    if (t == null) {
                        break;
                    }
                }
            } else {
                System.out.println("No tasks have been added yet!");
            }
        }

        /**
         * Prints the current number of tasks in the task list.
         * @param taskList Input task list
         */
        private static void printCurrentTasks (ArrayList <Task> taskList) {
            System.out.println(String.format("And... that makes %d task(s) in your list! :)", taskList.size()));
        }

        /**
         * Marks or unmarks the task.
         * @param command Input command word
         * @param inputWordsSplit String array of the command word with the task number
         */
        private static void markUnmarkTask (CommandWord command, String[]inputWordsSplit){
            int taskNumber = Integer.parseInt(inputWordsSplit[1]);
            Task task = taskList.get(taskNumber - 1);
            if (command == CommandWord.MARK) {
                task.mark();
                System.out.println(String.format("Goodjob! This task is now completed :)\n" + "%s", task.toString()));
            } else {
                task.unmark();
                System.out.println(String.format("Oh... OK, I'll mark this task as uncompleted!\n" + "%s", task.toString()));
            }
        }

        /**
         * Handles the adding of the 3 different kinds of tasks.
         * @param command Input command word
         * @param contents Input task name
         */
        private static void addNewTask (CommandWord command, String contents){
            Task newTask;
            if (command == CommandWord.TODO) {
                newTask = new Todo(contents);
            } else if (command == CommandWord.DEADLINE) {
                newTask = new Deadline(contents);
            } else {
                newTask = new Event(contents);
            }
            taskList.add(newTask);
            System.out.println("Okay!\n" + "Added: " + newTask);
        }

        /**
         * Enum for the command word
         */
        private enum CommandWord {
            LIST("list"),
            MARK("mark"),
            UNMARK("unmark"),
            TODO("todo"),
            DEADLINE("deadline"),
            EVENT("event"),
            BYE("bye");

            // String field for comparing to the input to the enum
            private final String command;

            /**
             * Constructor for the CommandWord enum
             * @param command User input command
             */
            CommandWord(String command) {
                this.command = command;
            }

            /**
             * Compares the input command to the enum.
             * @param input Input command string
             * @return CommandWord from the enum that matches the input command
             * @throws IllegalArgumentException When an invalid command is inputted
             */
            private static CommandWord getCommand(String input) throws IllegalArgumentException {
                for (int i = 0; i < CommandWord.values().length; i++) {
                    CommandWord temp = CommandWord.values()[i];
                    if (input.equals(temp.command)) {
                        return temp;
                    }
                }
                throw new IllegalArgumentException("There is no such command called " + input + "!\n" + "Please try again :)");
            }
        }
    }

