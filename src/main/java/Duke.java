import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Duke {
    public static String start = '\u2619' + " ";
    public static String sadFace = '\u2639' + " ";
    private static TaskList tasks = new TaskList();

    public static void main(String[] args) {

        File data = new File("src/main/data/");
        File list = new File("src/main/data/list.txt");

        //create a file to store the task list if it does not exist
        try {
            if (!list.exists()) {
                if (!data.exists()) {
                    data.mkdir();
                }
                list.createNewFile();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }

        //read contents from file containing the task list
        try {
            Scanner listSc = new Scanner(list);
            while (listSc.hasNext()) {
                String[] info = listSc.nextLine().split(" \\| ");
                tasks.addFromFile(info);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(start + "hi, i'm Duke!\n  what would you like to do today?");

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();
            String[] arr = input.split(" ", 2);
            if (arr[0].equals("bye")) {
                tasks.writeToFile();
                System.out.println(start + "bye! i hope to see you again soon! :)");
                break;
            }

            switch (arr[0]) {
            case "list":
                tasks.printList();
                break;
            case "mark":
                try {
                    tasks.mark(Integer.parseInt(arr[1]) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please enter an integer so i know which task to mark!");
                }
                break;
            case "unmark":
                try {
                    tasks.unmark(Integer.parseInt(arr[1]) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please enter an integer so i know which task to unmark!");
                }
                break;
            case "todo":
                try {
                    tasks.add(new ToDo(arr[1]));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please tell me the name of the todo task.");
                }
                break;
            case "deadline":
                try {
                    String[] dl = arr[1].split(" /by ", 2);
                    String[] datetime = dl[1].split(" ");
                    LocalDate day = LocalDate.parse(datetime[0], dateFormatter);
                    if (datetime.length == 1) {
                        tasks.add(new Deadline(dl[0], day));
                    } else {
                        tasks.add(new Deadline(
                                dl[0], day, LocalTime.parse(datetime[1], timeFormatter)));
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(
                            sadFace + "for tasks with deadlines, please tell me the name of the task, followed "
                                    + "by '/by',\n  and then the date/time it needs to be completed by\n"
                                    + "  in DD/MM/YYYY HHMM (time optional) format e.g. 27/02/2023 2359.");
                } catch (DateTimeParseException e) {
                    System.out.println(
                            sadFace + "please input a valid date and time in the format DD/MM/YYYY HHMM (time optional)");
                }
                break;
            case "event":
                try {
                    String[] info = arr[1].split(" /", 2);
                    String[] timings = info[1].split(" ", 2);
                    String[] dateTimeInfo = timings[1].split(" - ");
                    LocalDateTime startDateTime = LocalDateTime.parse(dateTimeInfo[0], dateTimeFormatter);
                    try {
                        LocalDateTime endDateTime = LocalDateTime.parse(dateTimeInfo[1], dateTimeFormatter);
                        tasks.add(new Event(info[0], timings[0], startDateTime, endDateTime));
                    } catch (DateTimeParseException e) {
                        tasks.add(new Event(
                                info[0], timings[0], startDateTime, LocalTime.parse(dateTimeInfo[1], timeFormatter)));
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(
                            sadFace + "for events, please tell me the name of the event, when it starts and when it ends."
                    );
                } catch (DateTimeParseException e) {
                    System.out.println(
                            sadFace + "please input valid starting and ending dates and times in the format\n"
                            + "  DD/MM/YYYY HHMM - DD/MM/YYYY HHMM (ending date optional)"
                    );
                }
                break;
            case "delete":
                try {
                    tasks.delete(Integer.parseInt(arr[1]) - 1);
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    System.out.println(sadFace + "please enter an integer so i know which task to delete!");
                }
                break;
            case "search":
                try {
                    tasks.search(LocalDate.parse(arr[1], dateFormatter));
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(
                            sadFace + "please enter the date you would like to search for in the format DD/MM/YYYY.");
                } catch (DateTimeParseException e) {
                    System.out.println(sadFace + "please enter a valid date in the format DD/MM/YYYY.");
                }
                break;
            default:
                System.out.println(sadFace + "sorry, i don't know what that means :(");
            }
        }
    }
}
