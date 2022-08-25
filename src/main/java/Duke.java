import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    public static LocalDateTime processDateTime(String stringDateTime) {
        LocalDateTime dateTime = LocalDateTime.now();
        String date = "None";
        String time = "None";
        if (stringDateTime.length() > 9) {
            date = stringDateTime.substring(0, stringDateTime.indexOf(" "));
            time = stringDateTime.substring(stringDateTime.indexOf(" ") + 1);
        } else if (stringDateTime.length() == 8) {
            date = stringDateTime;
        } else {
            time = stringDateTime;
        }
        try {
            System.out.println(date);
            System.out.println(time);
            if (!date.equals("None") && !time.equals("None")) {
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMdd");
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
                LocalDate localDate = LocalDate.parse(date, formatDate);
                LocalTime localTime = LocalTime.parse(time, formatTime);
                return LocalDateTime.of(localDate, localTime);
            } else if (!date.equals("None")) {
                DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyyMMdd");
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
                LocalDate localDate = LocalDate.parse(date, formatDate);
                LocalTime localTime = LocalTime.parse("0000", formatTime);
                return LocalDateTime.of(localDate, localTime);
            } else {
                DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HHmm");
                LocalTime localTime = LocalTime.parse(time, formatTime);
                return LocalDateTime.of(LocalDate.now(), localTime);
            }
        } catch (DateTimeParseException exception) {
            System.out.println(exception);
            System.out.println("Please enter date and time in YYYYMMDD HHMM format");
        }
        return dateTime;
    }
    public static void saveData(ArrayList<Task> list) {
        String home = System.getProperty("user.home");
        Path path = Paths.get(home, "data", "duke");
        ArrayList<String> textArray = new ArrayList<>();
        for (Task task : list) {
            String entry = task.printText();
            textArray.add(entry);
        }
        try {
            Files.createDirectories(path);
            File file = new File(path.toString(), "task-list.txt");
            OutputStream os = new FileOutputStream(file);
            String text = String.join("\n", textArray);
            byte[] bytes = text.getBytes();
            os.write(bytes);
            os.close();
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }
    public static void main(String[] args) {
        String indent = "     ";
        String divider = "  ____________________________________________________________\n";
        String logo = indent + "____        _        \n"
                    + indent + "|  _ \\ _   _| | _____ \n"
                    + indent + "| | | | | | | |/ / _ \\\n"
                    + indent + "| |_| | |_| |   <  __/\n"
                    + indent + "|____/ \\__,_|_|\\_\\___|\n\n";
        String openingStatement = indent + "Hello! I'm Duke.\n"
                                + indent + "What can I do for you?\n";
        System.out.println(divider + logo + openingStatement + divider);

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.contains("list")) {
                ArrayList<Task> processedTaskList = new ArrayList<>();
                String command = "Here are the tasks in your list";
                if (input.length() > 5) {
                    String stringDateTime = input.substring(5);
                    LocalDate date = processDateTime(stringDateTime).toLocalDate();
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
                    command += " for " + date.format(format);
                    for (Task task : new ArrayList<>(taskList)) {
                        String taskCode = task.getCode();
                        if (taskCode == "E") {
                            if (((Event) task).getRawDateTime().toLocalDate().equals(date)) {
                                processedTaskList.add(task);
                            }
                        } else if (taskCode == "D") {
                            if (((Deadline) task).getRawDateTime().toLocalDate().equals(date)) {
                                processedTaskList.add(task);
                            }
                        }
                    }
                } else {
                    processedTaskList = taskList;
                }
                System.out.print(divider);
                System.out.print(indent + command + ":\n");
                if (processedTaskList.size() == 0) {
                    System.out.println(indent + "[No tasks available]");
                }
                for (int index = 0; index < processedTaskList.size(); ++index) {
                    System.out.print(indent);
                    System.out.print(index + 1);
                    System.out.println(". " + processedTaskList.get(index));
                }
                System.out.println(divider);
            } else if (input.contains("mark")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                    if (input.contains("unmark")) {
                        taskList.get(taskIndex).unmarkAsDone();
                        System.out.println(divider + indent + "Okay, I've marked this task as not done yet:");
                        System.out.println(indent + indent + taskList.get(taskIndex) + "\n" + divider);
                    } else {
                        taskList.get(taskIndex).markAsDone();
                        System.out.println(divider + indent + "Nice! I've marked this as done:");
                        System.out.println(indent + indent + taskList.get(taskIndex) + "\n" + divider);
                    }
                    saveData(taskList);
                } catch (StringIndexOutOfBoundsException exception) {
                    System.out.println(divider + indent + "Wait, which task are you referring to?\n"
                            + divider);
                } catch (NumberFormatException exception) {
                    System.out.println(divider + indent + "Wait, which task are you referring to?\n"
                            + divider);
                } catch (Exception exception) {
                    System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                }
            } else if (input.contains("delete")) {
                try {
                    int indexToDelete = Integer.parseInt(input.substring(input.length() - 1)) - 1;
                    Task removedTask = taskList.remove(indexToDelete);
                    int numberOfTasks = taskList.size();
                    System.out.println(divider + indent + "Noted, I've removed this task:");
                    System.out.println(indent + indent + removedTask.toString());
                    System.out.println(indent + "Now you have " + numberOfTasks + " tasks in your list.\n" + divider);
                    saveData(taskList);
                } catch (StringIndexOutOfBoundsException exception) {
                    System.out.println(divider + indent + "Wait, which task do you want to delete?\n" + divider);
                } catch (Exception exception) {
                    System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                }
            } else {
                boolean response = false;
                if (input.contains("event")) {
                    try {
                        int indexOfDateTime = input.indexOf("/at");
                        String stringDateTime = input.substring(indexOfDateTime + 4);
                        LocalDateTime dateTime = processDateTime(stringDateTime);
                        String eventDescription = input.substring(6, indexOfDateTime - 1);
                        Event newTask = new Event(dateTime, eventDescription);
                        response = taskList.add(newTask);
                    } catch (StringIndexOutOfBoundsException exception) {
                        System.out.println(divider + indent + "Warning: The description and time " +
                                "cannot be empty.\n" + divider);
                    } catch (Exception exception) {
                        System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                    }
                } else if (input.contains("todo")) {
                    try {
                        String toDoDescription = input.substring(5);
                        ToDo newToDo = new ToDo(toDoDescription);
                        response = taskList.add(newToDo);
                    } catch (StringIndexOutOfBoundsException exception) {
                        System.out.println(divider + indent + "Warning: The description cannot be empty.\n"
                            + divider);
                    } catch (Exception exception) {
                        System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                    }
                } else if (input.contains("deadline")) {
                    try {
                        int indexOfDateTime = input.indexOf("/by");
                        String stringDateTime = input.substring(indexOfDateTime + 4);
                        LocalDateTime dateTime = processDateTime(stringDateTime);
                        String deadlineDescription = input.substring(9, indexOfDateTime - 1);
                        Deadline newDeadline = new Deadline(dateTime, deadlineDescription);
                        response = taskList.add(newDeadline);
                    } catch (StringIndexOutOfBoundsException exception) {
                        System.out.println(divider + indent + "Warning: The description and time" +
                                " cannot be empty.\n" + divider);
                    } catch (Exception exception) {
                        System.out.println(divider + indent + "Error: " + exception + "\n" + divider);
                    }
                } else {
                    System.out.println(divider + indent + "Oops, sorry! I don't know what that means :(\n" + divider);
                }
                if (response) {
                    int numberOfTasks = taskList.size();
                    Task newTask = taskList.get(numberOfTasks - 1);
                    System.out.println(divider + indent + "Got it. I've added this task:");
                    System.out.println(indent + indent + newTask.toString());
                    System.out.println(indent + "Now you have " + numberOfTasks + " tasks in your list.\n" + divider);
                    saveData(taskList);
                }
            }
        }

        String exitStatement = "Bye. Hope to see you again soon! :)";
        System.out.println(divider + indent + exitStatement + "\n" + divider);
        scanner.close();
    }
}
