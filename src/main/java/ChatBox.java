import java.util.ArrayList;
import java.util.Scanner;

//An enum class for the types of input
enum InputType {
    TODO,
    DEADLINE,
    EVENT,
    MARK,
    UNMARK,
    DELETE,
    LIST,
    BYE,
    WRONG
}

/**
 * A class ChatBox to simulate the chat features of the "Duke" by interacting
 * with the user-inputs and responding accordingly.
 */
public class ChatBox {

    //Use Java ArrayList collection to store all the instances of Task
    private ArrayList<Task> task_list = new ArrayList<>();

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
        String Delete = "delete";

        //The Strings representing the 3 types of Tasks
        String TODO = "todo";
        String DEADLINE = "deadline";
        String EVENT = "event";

        //An instance of Scanner to keep track of the user's input
        Scanner reader = new Scanner(System.in);


        while (true) {
            InputType input_type = null;

            try {

                //The entire statement (user's input)
                String UserInput = reader.nextLine();

                if (UserInput.isEmpty() || UserInput.isBlank()) {
                    String error_msg = "__________________________________________________\n" +
                            "Please enter valid inputs, empty strings or blanks are not valid!\n" +
                            "__________________________________________________";
                    throw new DukeException(error_msg);
                }

                //To obtain the first word in the user-input, used to check for keyword "mark"
                // and "unmark" later on
                String[] array = UserInput.split(" ", 2);
                String FirstText = array[0];

                if (UserInput.equals(EndLoop)) {
                    input_type = InputType.BYE;
                } else if (UserInput.equals(ListTasks)) {
                    input_type = InputType.LIST;
                } else if (FirstText.equals(Delete)) {
                    input_type = InputType.DELETE;
                } else if (FirstText.equals(Marking)) {
                    input_type = InputType.MARK;
                } else if (FirstText.equals(Unmarking)) {
                    input_type = InputType.UNMARK;
                } else if (FirstText.equals(TODO)) {
                    input_type = InputType.TODO;
                } else if (FirstText.equals(DEADLINE)) {
                    input_type = InputType.DEADLINE;
                } else if (FirstText.equals(EVENT)) {
                    input_type = InputType.EVENT;
                } else {
                    input_type = InputType.WRONG;
                }


                switch (input_type) {
                    case BYE:
                        System.out.println("__________________________________________________");
                        System.out.println("Adios Amigo! See you soon!");
                        System.out.println("__________________________________________________");
                        break;

                    case LIST:
                        System.out.println("__________________________________________________");
                        System.out.println("Here are the tasks in your to-do list:");
                        for (int i = 0; i < task_list.size(); i++) {
                            System.out.printf("%d. " + task_list.get(i).toString() + "\n", i + 1);
                        }
                        System.out.println("__________________________________________________");
                        break;

                    case DELETE:
                        //Exception: Throw an error when user tries to delete from an empty list
                        if (task_list.isEmpty()) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! There are no task left to be deleted!\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }

                        //Exception: Throw an error when the second half after "delete" keyword is blank
                        if (array.length == 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! The task number for deleting must be specified!\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }

                        int task_num = Integer.parseInt(array[1]);
                        //Exception: Throw an error when the second half after "delete" keyword is greater than task_list
                        if (task_num > task_list.size() || task_num < 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! There is no such task number!\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }

                        System.out.println("__________________________________________________");
                        System.out.println("Noted. I have removed this task:");
                        System.out.println("  " + task_list.get(task_num - 1).toString());
                        task_list.remove(task_num - 1);
                        System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        System.out.println("__________________________________________________");
                        break;

                    case MARK:
                        //Exception: Throw an error when the second half after "mark" keyword is blank
                        if (array.length == 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! The task number for marking must be specified!\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }

                        task_num = Integer.parseInt(array[1]);
                        //Exception: Throw an error when the second half after "mark" keyword is greater than task_list
                        if (task_num > task_list.size() || task_num < 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! There is no such task number!\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }

                        task_list.get(task_num - 1).markTaskDone();
                        System.out.println("__________________________________________________");
                        System.out.println("Good Job! I have marked this task as done:");
                        System.out.println("  " + task_list.get(task_num - 1).toString());
                        System.out.println("__________________________________________________");
                        break;

                    case UNMARK:
                        //Exception: Throw an error when the second half after "unmark" keyword is blank
                        if (array.length == 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! The task number for un-marking must be specified!\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }

                        task_num = Integer.parseInt(array[1]);
                        //Exception: Throw an error when the second half after "unmark" keyword is greater than task_list
                        if (task_num > task_list.size() || task_num < 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! There is no such task number!\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }

                        task_list.get(task_num - 1).markTaskUndone();
                        System.out.println("__________________________________________________");
                        System.out.println("Alright! I have marked this task as not done yet:");
                        System.out.println("  " + task_list.get(task_num - 1).toString());
                        System.out.println("__________________________________________________");
                        break;

                    case TODO:
                        String task_description = "";
                        //Exception: Throw an error when the second half after "todo" keyword is blank
                        if (array.length == 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! The description of a todo cannot be empty.\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }
                        task_list.add(new Todo(array[1]));
                        task_description = "  " + task_list.get(task_list.size() - 1).toString();
                        System.out.println("__________________________________________________");
                        System.out.println("Got it. I have added this task:");
                        System.out.println(task_description);

                        if (task_list.size() == 1) { //For a single task
                            System.out.println("Now you have 1 task in the list.");
                        } else { //For multiple tasks
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        }
                        System.out.println("__________________________________________________");
                        break;

                    case DEADLINE:
                        //Exception: Throw an error when the second half after "deadline" keyword is blank
                        if (array.length == 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! The description of a deadline cannot be empty.\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }
                        String task_info = array[1];
                        String[] info_arr = task_info.split(" /by ", 2);
                        task_list.add(new Deadline(info_arr[0], info_arr[1]));
                        task_description = "  " + task_list.get(task_list.size() - 1).toString();
                        System.out.println("__________________________________________________");
                        System.out.println("Got it. I have added this task:");
                        System.out.println(task_description);

                        if (task_list.size() == 1) { //For a single task
                            System.out.println("Now you have 1 task in the list.");
                        } else { //For multiple tasks
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        }
                        System.out.println("__________________________________________________");
                        break;

                    case EVENT:
                        //Exception: Throw an error when the second half after "event" keyword is blank
                        if (array.length == 1) {
                            String error_msg = "__________________________________________________\n" +
                                    "OOPS!!! The description of a event cannot be empty.\n" +
                                    "__________________________________________________";
                            throw new DukeException(error_msg);
                        }
                        task_info = array[1];
                        info_arr = task_info.split(" /at ", 2);
                        task_list.add(new Event(info_arr[0], info_arr[1]));
                        task_description = "  " + task_list.get(task_list.size() - 1).toString();
                        System.out.println("__________________________________________________");
                        System.out.println("Got it. I have added this task:");
                        System.out.println(task_description);

                        if (task_list.size() == 1) { //For a single task
                            System.out.println("Now you have 1 task in the list.");
                        } else { //For multiple tasks
                            System.out.println("Now you have " + task_list.size() + " tasks in the list.");
                        }
                        System.out.println("__________________________________________________");
                        break;

                    case WRONG:
                        //Exception: When the user-input does not specify within the 3 different tasks mentioned above
                        String error_msg = "__________________________________________________\n" +
                                "OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                                "__________________________________________________";
                        throw new DukeException(error_msg);
                }

            } catch (DukeException | ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
