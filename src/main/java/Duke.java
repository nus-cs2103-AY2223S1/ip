import java.util.Scanner;

public class Duke {

    static int index = 1;
    static Task[] userInputs = new Task[100];

    public static void main(String[] args) {

        System.out.println("Hello! I'm SoCCat \nWhat can I do for you?");
        Scanner input = new Scanner(System.in);
        String currInput = input.nextLine();

        while (!currInput.equals("bye")) {
            if (currInput.equals("list")) {
                getList();
            }  else {
                String[] words = currInput.split(" ", 2);
                if (words.length < 2) {
                    System.out.println("added: " + currInput);
                    userInputs[index] = new Task(currInput);
                    index++;
                } else {
                    String firstHalf = words[0];
                    String secondHalf = words[1];

                    if (firstHalf.equals("todo")) {
                        System.out.println(Task.newTask());
                        userInputs[index] = new ToDos(secondHalf);
                        System.out.println(userInputs[index] + "\n" + numberOfTasks());
                        index++;

                    } else if (firstHalf.equals("deadline")) {
                        System.out.println(Task.newTask());
                        String[] taskDetails = currInput.split(" /by ", 2);
                        String task = taskDetails[0];
                        String deadline = taskDetails[1];
                        userInputs[index] = new Deadlines(task, deadline);
                        System.out.println(userInputs[index] + "\n" + numberOfTasks());
                        index++;

                    } else if (firstHalf.equals("event")) {
                        System.out.println(Task.newTask());
                        String[] taskDetails = currInput.split(" /at ", 2);
                        String task = taskDetails[0];
                        String eventTime = taskDetails[1];
                        userInputs[index] = new Events(task, eventTime);
                        System.out.println(userInputs[index] + "\n" + numberOfTasks());
                        index++;

                    } else {

                        if (words[0].equals("mark")) {
                            int taskIndex = Integer.parseInt(secondHalf);
                            if (taskIndex <= index) {
                                System.out.println(userInputs[taskIndex].mark());
                            }
                        } else if (words[0].equals("unmark")) {
                            int taskIndex = Integer.parseInt(secondHalf);
                            if (taskIndex <= index) {
                                System.out.println(userInputs[taskIndex].unmark());
                            }
                        } else {
                            System.out.println("added: " + currInput);
                            userInputs[index] = new Task(currInput);
                            index++;
                        }
                    }
                }
            }
                currInput = input.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static String numberOfTasks() {
        return "Now you have " + index +  (index < 2 ? " task" : " tasks") + " in your list.";
    }

    public static void getList() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 1; i < index; i++) {
//            System.out.println(i + ".[" + userInputs[i].getStatusIcon() + "] " + userInputs[i].getDescription());
            System.out.println(i + "." + userInputs[i]);

        }
    }

}
