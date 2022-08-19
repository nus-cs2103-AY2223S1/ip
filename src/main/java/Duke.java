import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Hello! I'm Snoopy");
        System.out.println("What can I do for you?");
        String input = myObj.nextLine();
        String[] words = input.split(" ");
        String response = words[0];
        ArrayList<Task> tasks = new ArrayList<>();
        int i = 0;
        while (!response.equals("bye")) {
            try {
                switch (response) {
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int a = 1; a <= i; a++) {
                            String output = a + "." + tasks.get(a - 1).toString();
                            System.out.println(output);
                        }
                        break;
                    case "mark": {
                        String taskNumber = words[1];
                        int number = Integer.parseInt(taskNumber);
                        tasks.get(number - 1).markAsDone();
                        break;
                    }
                    case "unmark": {
                        String taskNumber = words[1];
                        int number = Integer.parseInt(taskNumber);
                        tasks.get(number - 1).markNotDone();
                        break;
                    }
                    case "delete": {
                        String taskNumber = words[1];
                        int number = Integer.parseInt(taskNumber);
                        i--;
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tasks.get(number - 1));
                        System.out.println("Now you have " + i + " tasks in the list.");
                        tasks.remove(number - 1);

                        break;
                    }
                    case "deadline": {
                        int a = 2;
                        StringBuilder task = new StringBuilder(words[1]);
                        while (!words[a].equals("/by")) {
                            task.append(" ");
                            task.append(words[a]);
                            a++;
                        }
                        a++;
                        StringBuilder deadline = new StringBuilder(words[a]);
                        for (int b = a + 1; b < words.length; b++) {
                            deadline.append(" ");
                            deadline.append(words[b]);
                        }
                        LocalDate d1 = LocalDate.parse(deadline);
                        tasks.add(i, new Deadline(task.toString(), d1));
                        i++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(i-1));
                        System.out.println("Now you have " + i + " tasks in the list.");
                        break;
                    }
                    case "event": {
                        int a = 2;
                        StringBuilder task = new StringBuilder(words[1]);
                        while (!words[a].equals("/at")) {
                            task.append(" ");
                            task.append(words[a]);
                            a++;
                        }
                        a++;
                        StringBuilder deadline = new StringBuilder(words[a]);
                        for (int b = a + 1; b < words.length; b++) {
                            deadline.append(" ");
                            deadline.append(words[b]);
                        }
                        tasks.add(i, new Event(task.toString(), deadline.toString()));
                        i++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(i-1));
                        System.out.println("Now you have " + i + " tasks in the list.");

                        break;
                    }
                    case "todo": {
                        if (words.length <= 1) {
                            throw new EmptyTodoListException();
                        }
                        StringBuilder task = new StringBuilder(words[1]);
                        for (int a = 2; a < words.length; a++) {
                            task.append(" ");
                            task.append(words[a]);
                        }
                        tasks.add(i, new Todo(task.toString()));
                        i++;
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(i-1));
                        System.out.println("Now you have " + i + " tasks in the list.");
                        break;
                    }
                    default:
                        throw new InvalidCommandException();
                }
            } catch (InvalidCommandException | EmptyTodoListException e) {
                System.out.println(e.getMessage());
            }
            input = myObj.nextLine();
            words = input.split(" ");
            response = words[0];
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
