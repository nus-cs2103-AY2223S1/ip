import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private List<Task> tasks;

    public Duke(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    private void run(Scanner reader) throws DukeException {
        while (true) {
            String input = reader.nextLine();
            String[] strings = input.split(" ", 2);
            if (strings.length <= 1) {
                singleCommand(strings);
            } else {
                doubleCommand(strings);
            }
            try {
                save();
            } catch (IOException e) {
                System.out.println("Error!: " + e);
            }
        }
    }


    public static void main(String[] args) {
        ArrayList<Task> tasks = read();
        Duke duke = new Duke(tasks);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner reader = new Scanner(System.in);
        while (true) {
            try {
                duke.run(reader);
            } catch (DukeException e) {
                System.out.println(e);
            }
        }
    }

    private void singleCommand(String[] arg) throws DukeException {
        switch (arg[0]) {
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                System.exit(0);
            case "list":
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i + 1 + ". " + tasks.get(i));
                }
                return;
            default:
                throw new InputException();
        }
    }

    private void doubleCommand(String[] arg) throws DukeException {
        switch (arg[0]) {
            case "mark":
                if (arg.length == 1) {
                    throw new MarkException();
                } else {
                    try {
                        int num = Integer.parseInt(arg[1]);
                        if (num > tasks.size()) {
                            throw new MarkException();
                        } else {
                            tasks.get(num - 1).setDone(true);
                            System.out.println("Nice! I've marked this task as done:\n" + tasks.get(num - 1));
                        }
                    } catch (NumberFormatException e) {
                        throw new MarkException();
                    }
                }
                break;

            case "unmark":
                if (arg.length == 1) {
                    throw new MarkException();
                } else {
                    try {
                        int num = Integer.parseInt(arg[1]);
                        if (num > tasks.size()) {
                            throw new MarkException();
                        } else {
                            tasks.get(num - 1).setDone(false);
                            System.out.println("Ok, I've marked this task as not done yet:\n" + tasks.get(num - 1));
                        }
                    } catch (NumberFormatException e) {
                        throw new MarkException();
                    }
                }
                break;

            case "deadline":
                if (arg.length == 1 || arg[1].isEmpty()) {
                    throw new TaskException();
                } else {
                    String[] split = arg[1].split("/", 2);
                    if (split.length < 2) {
                        throw new TimeException();
                    } else {
                        try {
                            if (split[1].substring(3).length() < 11) {
                                split[1] = split[1] + " 23:59";
                            }
                            LocalDateTime time = LocalDateTime.parse(split[1].substring(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            Task task = new Deadline(split[0], time);
                            tasks.add(task);
                            System.out.println("Got it. I've added this task:\n" + task);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        } catch (DateTimeParseException e) {
                            System.out.println("Please use the specified date-time format: yyyy-MM-dd HH:mm, or yyyy-MM-dd if you want the time to be 23:59");
                        }
                    }
                }
                break;

            case "todo":
                if (arg.length == 1) {
                    throw new TaskException();
                } else {
                    Task task = new ToDo(arg[1]);
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:\n" + task);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list");
                }
                break;

            case "event":
                if (arg.length == 1) {
                    throw new TaskException();
                } else {
                    String[] split = arg[1].split("/", 2);
                    if (split.length < 2) {
                        throw new TimeException();
                    } else {
                        try {
                            if (split[1].substring(3).length() < 11) {
                                split[1] = split[1] + " 23:59";
                            }
                            LocalDateTime time = LocalDateTime.parse(split[1].substring(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
                            Task task = new Event(split[0], time);
                            tasks.add(task);
                            System.out.println("Got it. I've added this task:\n" + task);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        } catch (DateTimeParseException e) {
                            System.out.println("Please use the specified date-time format: yyyy-MM-dd HH:mm, or yyyy-MM-dd if you want the time to be 23:59");
                        }
                    }
                }
                break;

            case "delete":
                if (arg.length == 1) {
                    throw new MarkException();
                } else {
                    try {
                        int num = Integer.parseInt(arg[1]);
                        if (num > tasks.size()) {
                            throw new MarkException();
                        } else {
                            System.out.println("Noted, I've removed this task:\n" + tasks.get(num - 1));
                            tasks.remove(num - 1);
                            System.out.println("Now you have " + tasks.size() + " tasks in the list");
                        }
                    } catch (NumberFormatException e) {
                        throw new MarkException();
                    }
                }
                break;

            default:
                throw new InputException();
        }
    }

    private void save() throws IOException {
        //duke.txt will always exist since we have already created it upon loading up the duke program
        //however, users may delete the directory and hence we still need to check for the existence

        //reference from https://stackoverflow.com/a/28620461
        String savePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "duke";
        File saveLocation = new File(savePath);
        if (!saveLocation.exists()) {
            saveLocation.mkdir();
            File myFile = new File(savePath, "duke.txt");
            PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile));
            for (int i = 0; i < tasks.size(); i++) {
                textFileWriter.write(tasks.get(i).toString() + "\n");
            }
            textFileWriter.close();
        } else {
            File myFile = new File(savePath, "duke.txt");
            PrintWriter textFileWriter = new PrintWriter(new FileWriter(myFile));
            for (int i = 0; i < tasks.size(); i++) {
                textFileWriter.write(tasks.get(i).toString() + "\n");
            }
            textFileWriter.close();
        }
    }

    private static ArrayList<Task> read() {
        ArrayList<Task> tasks = new ArrayList<>();
        String path = System.getProperty("user.dir") + System.getProperty("file.separator") + "duke";
        File saveLocation = new File(path);
        if (!saveLocation.exists()) {
            saveLocation.mkdir();
            File myFile = new File(path, "duke.txt");
            return tasks;
        } else {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(path, "duke.txt")));
                String line;
                try {
                    //reference from https://stackoverflow.com/a/16104650
                    while ((line = reader.readLine()) != null) {
                        parseLine(line, tasks);
                    }
                } catch (IOException e) {
                    System.out.println("Error: " + e);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e );
                System.out.println("Don't worry I'll create the file when you create your task list!");
            }
        }
        return tasks;
    }

    private static void parseLine(String txt, ArrayList<Task> list) {
        char eventType = txt.charAt(1);
        char done = txt.charAt(4);
        boolean isDone = done == 'X';
        //regex reference from https://stackoverflow.com/a/17779833
        String[] event = txt.substring(7).split("\\(");
        switch (eventType) {
            case 'T':
                list.add(new ToDo(event[0]));
                break;
            case 'D':
                String time = event[1].substring(0,event[1].indexOf(")"));
                list.add(new Deadline(event[0], time));
                break;
            case 'E':
                time = event[1].substring(0,event[1].indexOf(")"));
                list.add(new Event(event[0], time));
                break;
        }
        list.get(list.size() - 1).setDone(isDone);
    }
}