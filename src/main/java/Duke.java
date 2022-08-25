import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> tasks = new ArrayList<Task>();

        String filePath = "data/duke.txt";

        String timeInputPattern = "dd-MM-yyyy HHmm";
        String deadlineTimeInputPattern = "dd-MM-yyyy";

        try {

            File f = new File(filePath);
            Scanner fileScanner = new Scanner(f);

            while (fileScanner.hasNext()) {
                String[] input = fileScanner.nextLine().split(" \\| ");
                Task task = new Task("");

                switch (input[0]) {
                    case "D":
                        task = new Deadline(input[2], LocalDateTime.parse(input[3]));
                        break;

                    case "E":
                        task = new Event(input[2], LocalDateTime.parse(input[3]));
                        break;

                    case "T":
                        task = new Todo(input[2]);
                        break;
                }

                if (input[1].equals("1")) {
                    task.mark();
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner sc = new Scanner(System.in);
        boolean exit = false;


        while (!exit) {
            String input = sc.nextLine();
            String command = input.contains(" ") ? input.split(" ", 2)[0] : input;
            String parameters = input.contains(" ") ? input.split(" ", 2)[1] : "";
            switch (command) {

                case "list":
                    if (tasks.size() == 0) {
                        System.out.println("You have nothing to do!");
                        break;
                    }
                    System.out.println("Here are the tasks in your list:");
                    tasks.forEach((task) -> {
                        int itemNumber = tasks.indexOf(task) + 1;
                        String result = String.format("%d: %s", itemNumber, task);
                        System.out.println(result);
                    });
                    break;

                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    exit = true;
                    break;

                case "mark":

                    try {

                        String itemString = input.split(" ")[1];
                        int itemNumber = Integer.parseInt(itemString) - 1;
                        Task task = tasks.get(itemNumber);

                        if (task.isDone()) {
                            System.out.println("This task is already marked as done!");
                        } else {
                            task.mark();

                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(task);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid item to mark!");
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found!");
                    }
                    break;

                case "unmark":

                    try {

                        String itemString = input.split(" ")[1];
                        int itemNumber = Integer.parseInt(itemString) - 1;
                        Task task = tasks.get(itemNumber);

                        if (!task.isDone()) {
                            System.out.println("This task is already marked as not done yet!");
                        } else {
                            task.unmark();

                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println(task);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid item to mark!");
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found!");
                    }
                    break;

                case "deadline":

                    try {

                        String taskName = parameters.split(" /by ")[0];
                        String by = parameters.split(" /by ")[1];


                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeInputPattern);
                        LocalDateTime byDateTime = LocalDateTime.parse(by, formatter);
                        Task task = new Deadline(taskName, byDateTime);
                        tasks.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid date/time!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Please input the date and time in the following format: " + timeInputPattern);
                    }
                    break;

                case "event":

                    try {

                        String taskName = parameters.split(" /at ")[0];
                        String at = parameters.split(" /at ")[1];

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeInputPattern);
                        LocalDateTime byDateTime = LocalDateTime.parse(at, formatter);
                        Task task = new Event(taskName, byDateTime);
                        tasks.add(task);

                        System.out.println("Got it. I've added this task:");
                        System.out.println(task);
                        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid date/time!");
                    } catch (DateTimeParseException e) {
                        System.out.println("Please input the date and time in the following format: " + timeInputPattern);
                    }
                    break;

                case "todo":
                    Task task = new Todo(parameters);
                    tasks.add(task);

                    System.out.println("Got it. I've added this task:");
                    System.out.println(task);
                    System.out.printf("Now you have %d task in the list.%n", tasks.size());
                    break;

                case "delete":

                    try {

                        String itemString = input.split(" ")[1];
                        int itemNumber = Integer.parseInt(itemString) - 1;
                        Task taskToRemove = tasks.get(itemNumber);

                        tasks.remove(taskToRemove);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println(taskToRemove);
                        System.out.printf("Now you have %d tasks in the list.%n", tasks.size());


                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Please input a valid item to mark!");
                    } catch (NumberFormatException e) {
                        System.out.println("Please input a number!");
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Task not found!");
                    }
                    break;

                case "deadlines":
                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(deadlineTimeInputPattern);
                        LocalDate deadlineDate = LocalDate.parse(parameters, formatter);

                        boolean[] hasElements = {false};

                        tasks
                                .stream()
                                .filter(t -> t.getDateTime().getYear() == deadlineDate.getYear())
                                .filter(t -> t.getDateTime().getDayOfYear() == deadlineDate.getDayOfYear())
                                .forEach((t) -> {
                                    int itemNumber = tasks.indexOf(t) + 1;
                                    String result = String.format("%d: %s", itemNumber, t);
                                    hasElements[0] = true;
                                    System.out.println(result);
                                });

                        if (!hasElements[0]) {
                            System.out.println("You do not have any deadlines for this date!");
                        }

                    } catch (DateTimeParseException e) {
                        System.out.println("Please input the date and time in the following format: " + deadlineTimeInputPattern);
                    }
                    break;

                default:
                    System.out.println("Invalid command!");
                    break;

            }
        }

        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks) {
                fw.write(task.getWriteString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
}
