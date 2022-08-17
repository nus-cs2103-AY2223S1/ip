import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    private static void addTask(String taskType, String input) {
        System.out.println("Got it. I've added this task:");
        switch(taskType){
            case "todo":
                String[] removeTaskType = input.split("todo ");
                String description = String.join("", removeTaskType);
                Task todo = new ToDo(description);
                list.add(todo);
                System.out.println("  " + todo);
                break;
            case "deadline":
                String[] removeTaskType2 = input.split("deadline ");
                String desAndBy = String.join("", removeTaskType2);
                String[] sliceByDesAndBy = desAndBy.split(" /by ");
                String description2 = sliceByDesAndBy[0];
                String dueTime = sliceByDesAndBy[1];
                Task deadline = new Deadline(description2, dueTime);
                list.add(deadline);
                System.out.println("  " + deadline);
                break;
            case "event":
                String[] removeTaskType3 = input.split("event ");
                String desAndBy2 = String.join("", removeTaskType3);
                String[] sliceByDesAndBy2 = desAndBy2.split(" /at ");
                String description3 = sliceByDesAndBy2[0];
                String dueTime2 = sliceByDesAndBy2[1];
                Task event = new Event(description3, dueTime2);
                list.add(event);
                System.out.println("  " + event);
                break;

            }

            // edge case of 1 task
            String numTask = String.format("Now you have %s tasks in the list.", list.size());
            System.out.println(numTask);

    }

    private static void printList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            String line = String.format("%s. %s", i + 1, list.get(i));
            System.out.println(line);
        }
    }

    private static void markTask(int num) {
        Task task = list.get(num - 1);
        task.mark();
    }

    private static void unmarkTask(int num) {
        Task task = list.get(num - 1);
        task.unmark();
    }

    public static void main(String[] args) {
        String intro = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println("____________________________________________________");
        System.out.println(intro);
        System.out.println("____________________________________________________");

        Scanner scanner = new Scanner(System.in);


        while (!scanner.hasNext("bye")) {
            String input = scanner.nextLine();
            String[] inputArr = input.split(" ");
            System.out.println("____________________________________________________");
            if (inputArr[0].equals("list")) {
                printList();
            } else if (inputArr[0].equals("mark")) {
                int taskNum = Integer.parseInt(inputArr[1]);
                try {
                    markTask(taskNum);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist!");
                }
                String output = String.format("Nice! I've marked this task as done:\n%s", list.get(taskNum - 1));
                System.out.println(output);
            } else if (inputArr[0].equals("unmark")) {
                int taskNum = Integer.parseInt(inputArr[1]);
                try {
                    unmarkTask(taskNum);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Task does not exist!");
                }
                String output = String.format("OK, I've marked this task as not done yet:\n%s", list.get(taskNum - 1));
                System.out.println(output);
            } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")){
                addTask(inputArr[0], input);
            }

            System.out.println("____________________________________________________");
        }

        System.out.println("____________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________");
        scanner.close();
    }
}
