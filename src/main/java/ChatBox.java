import java.util.Scanner;

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
                System.out.println("    " + tasks_memory[task_num - 1].toString());
                System.out.println("__________________________________________________");
                continue;
            }

            //To unmark a task when the user-input starts with "unmark"
            if (FirstText.equals(Unmarking)) {
                int task_num = Integer.parseInt(array[1]);
                tasks_memory[task_num - 1].markTaskUndone();
                System.out.println("__________________________________________________");
                System.out.println("Alright! I have marked this task as not done yet:");
                System.out.println("    " + tasks_memory[task_num - 1].toString());
                System.out.println("__________________________________________________");
                continue;
            }

            //Adding a new task to the list of task and displaying task added
            System.out.println("__________________________________________________");
            System.out.println("added: " + UserInput);
            System.out.println("__________________________________________________");
            tasks_memory[array_index] = new Task(UserInput);
            array_index++;
        }

    }
}
