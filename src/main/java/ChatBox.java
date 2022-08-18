import java.util.Scanner;
/**
 * A class ChatBox to simulate the chat features of the "Duke" by interacting
 * with the user-inputs and responding accordingly.
 */
public class ChatBox {
    //An array of Tasks to store all the instances of Task
    private Task[] tasks_memory = new Task[100];
    //To keep track of the number of Task objects added into the tasks_memory array
    private int array_index = 0;

    public ChatBox() {
    }

    /**
     * This is the method that enables the interactive feature for Luke. It keeps track
     * of user-inputs and respond accordingly based on the different inputs (Marking, Unmarking,
     * Listing, Adding, etc).
     */
    public void Reply() {
        //The important keywords to check against with the user-input
        String EndLoop = "bye";
        String ListTasks = "list";
        String Marking = "mark";
        String Unmarking = "unmark";

        //The Strings representing the 3 types of Tasks
        String TODO = "todo";
        String DEADLINE = "deadline";
        String EVENT = "event";

        while (true) {
            //An instance of Scanner to keep track of the user's input
            Scanner reader = new Scanner(System.in);

            //The entire statement (user's input)
            String UserInput = reader.nextLine();

            //To obtain the first word in the user-input, used to check for keyword "mark"
            // and "unmark" later on
            String[] array = UserInput.split(" ", 2);
            String FirstText = array[0];

            //To end the program if the user-input is "bye"
            if (UserInput.equals(EndLoop)) {
                System.out.println("__________________________________________________");
                System.out.println("Adios Amigo! See you soon!");
                System.out.println("__________________________________________________");
                break;
            }

            //To list out all the tasks if the user-input is "list"
            if (UserInput.equals(ListTasks)) {
                System.out.println("__________________________________________________");
                System.out.println("Here are the tasks in your to-do list:");
                for (int i = 0; i < array_index; i++) {
                    System.out.printf("%d. " + tasks_memory[i].toString() + "\n", i + 1);
                }
                System.out.println("__________________________________________________");
                continue;
            }

            //To mark a task when the user-input starts with "mark"
            if (FirstText.equals(Marking)) {
                int task_num = Integer.parseInt(array[1]);
                tasks_memory[task_num - 1].markTaskDone();
                System.out.println("__________________________________________________");
                System.out.println("Good Job! I have marked this task as done:");
                System.out.println("  " + tasks_memory[task_num - 1].toString());
                System.out.println("__________________________________________________");
                continue;
            }

            //To unmark a task when the user-input starts with "unmark"
            if (FirstText.equals(Unmarking)) {
                int task_num = Integer.parseInt(array[1]);
                tasks_memory[task_num - 1].markTaskUndone();
                System.out.println("__________________________________________________");
                System.out.println("Alright! I have marked this task as not done yet:");
                System.out.println("  " + tasks_memory[task_num - 1].toString());
                System.out.println("__________________________________________________");
                continue;
            }

            //To store the string representation of the categorised task
            String task_description = "";

            //To keep track of whether the user-input categorised the task into the 3 categories
            boolean specified_task = false;

            if (FirstText.equals(TODO)) { //If it is a todo task
                specified_task = true;
                tasks_memory[array_index] = new Todo(array[1]);
                task_description = "  " + tasks_memory[array_index].toString();
            } else if (FirstText.equals(DEADLINE)) { //If it is a deadline task
                specified_task = true;
                String task_info = array[1];
                String[] info_arr = task_info.split(" /by ", 2);
                tasks_memory[array_index] = new Deadline(info_arr[0], info_arr[1]);
                task_description = "  " + tasks_memory[array_index].toString();
            } else if (FirstText.equals(EVENT)) { //If it is an event task
                specified_task = true;
                String task_info = array[1];
                String[] info_arr = task_info.split(" /at ", 2);
                tasks_memory[array_index] = new Event(info_arr[0], info_arr[1]);
                task_description = "  " + tasks_memory[array_index].toString();
            }

            //Printing the output on display
            System.out.println("__________________________________________________");
            if (specified_task) { //If the task is categorised (3 categories), we will print accordingly
                System.out.println("Got it. I have added this task:");
                System.out.println(task_description);
            } else { //Still supports the old way of adding tasks without categories
                //Adding a new task to the list of task and displaying task added & old printing output
                System.out.println("added: " + UserInput);
                tasks_memory[array_index] = new Task(UserInput);
            }

            if (array_index == 0) { //For a single task
                System.out.println("Now you have " + (array_index + 1) + " task in the list.");
            } else { //For multiple tasks
                System.out.println("Now you have " + (array_index + 1) + " tasks in the list.");
            }

            System.out.println("__________________________________________________");

            array_index++;
        }

    }
}
