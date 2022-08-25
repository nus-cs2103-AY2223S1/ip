import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;


public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    // parse out date
    // see if there is time included then will add datetime

//    static String getTimeString(String time) {
//        try {
//            final SimpleDateFormat sdf = new SimpleDateFormat("H:mm");
//            final Date dateObj = sdf.parse(time);
//            SimpleDateFormat output = new SimpleDateFormat("K:mm").format(dateObj);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }


    static LocalDate getLocalDate(String date) {
        String[] dateDetails = date.split("-");
        String day = dateDetails[0];
        String month = dateDetails[1];
        String year = dateDetails[2];
        if (day.length() == 1) {
            day = "0" + day;
        }
        if (month.length() == 1) {
            month = "0" + month;
        }
        List<String> list = Arrays.asList(year, month, day);

        String dateToParse = String.join("-", list);
        System.out.println(dateToParse);
        return LocalDate.parse(dateToParse);
    }

    private static void addTask(String taskType, String input) throws DukeException {

        switch (taskType) {
            case "todo":
                String[] removeTaskType = input.split("todo ");
                String description = String.join("", removeTaskType);
                if (description.equals("todo")) {
                    throw new DukeException("");
                }
                Task todo = new ToDo(description);
                list.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                break;
            case "deadline":
                String[] removeTaskType2 = input.split("deadline ");
                String desAndBy = String.join("", removeTaskType2);
                String[] sliceByDesAndBy = desAndBy.split(" /by ");
                String description2 = sliceByDesAndBy[0];
                String dueDateAndTime = sliceByDesAndBy[1];
                String[] dateAndTime = dueDateAndTime.split(" ");
                System.out.println(dateAndTime.length);
                if (dateAndTime.length == 2) {
                    String dueDate = dateAndTime[0];
                    String dueTime = dateAndTime[1];
                    LocalDate localDate = getLocalDate(dueDate);
                    Task deadline = new Deadline(description2, localDate, dueTime);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                } else {
                    String dueDate = dateAndTime[0];
                    LocalDate localDate = getLocalDate(dueDate);
                    Task deadline = new Deadline(description2, localDate);
                    list.add(deadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline);
                }
                break;
            case "event":
                String[] removeTaskType3 = input.split("event ");
                String desAndBy2 = String.join("", removeTaskType3);
                String[] sliceByDesAndBy2 = desAndBy2.split(" /at ");
                String description3 = sliceByDesAndBy2[0];
                String dueTime2 = sliceByDesAndBy2[1];
                Task event = new Event(description3, dueTime2);
                list.add(event);
                System.out.println("Got it. I've added this task:");
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

    private static void deleteTask(int num) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + list.get(num - 1));
        list.remove(num - 1);
        String numTask = String.format("Now you have %s tasks in the list.", list.size());
        System.out.println(numTask);
    }

    static void validate(String str, String type) throws DukeException {
        if (!type.equals("todo") && !type.equals("deadline") && !type.equals("event")) {
            throw new DukeException("");
        }
    }

    public static void main(String[] args) throws DukeException {
        String intro = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println("____________________________________________________");
        System.out.println(intro);
        System.out.println("____________________________________________________");

        Scanner scanner = new Scanner(System.in);


        try {
            while (scanner.hasNextLine()) {
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
                } else if (inputArr[0].equals("delete")) {
                    int taskNum = Integer.parseInt(inputArr[1]);
                    deleteTask(taskNum);
                } else if (inputArr[0].equals("todo") || inputArr[0].equals("deadline") || inputArr[0].equals("event")) {
                    try {
                        addTask(inputArr[0], input);
                    } catch (IndexOutOfBoundsException | DukeException e) {
                        String output = String.format("Oops!! The description of a %s cannot be empty", inputArr[0]);
                        System.out.println(output);
                    }
                } else if (inputArr[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________");
                    scanner.close();
                } else {
                    try {
                        validate(input, inputArr[0]);
                    } catch (DukeException e) {
                        System.out.println("Oh no!! I'm sorry, but I don't know what that means :(");
                    }
                }
            }
        } catch (IllegalStateException e) {
            // just catching error
        }
    }


}

