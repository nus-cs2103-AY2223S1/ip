import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A chatbot named Tako that
 * supports various tasks.
 *
 * @author Alvin Tan Fu Long
 */
public class Tako {
    private static List<Task> tasks = new ArrayList<>();
    private static Path tasksPath;

    private enum Command {
        BYE, LIST, MARK, TODO, DEADLINE, EVENT, DELETE;

        public static boolean contains(String s) {
            for (Command c : Command.values()) {
                if (c.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static String taskToFileFormat(Task task) {
        String s = task.toString();
        char taskType = s.charAt(1);
        int isDone = s.charAt(4) == ' ' ? 0 : 1;
        String description = s.substring(7);
        if (taskType == 'D' || taskType == 'E') {
            description = description.substring(0, description.length() - 1);
            description = taskType == 'D'
                    ? description.replaceFirst("\\(by:", "|")
                    : description.replaceFirst("\\(at:", "|");
        }
        return String.format("%c | %d | %s", taskType, isDone, description);
    }

    private static void saveToFile(Task task) throws IOException {
       FileWriter fw = new FileWriter(tasksPath.toString(), true);
       BufferedWriter bw = new BufferedWriter(fw);
       bw.write(taskToFileFormat(task));
       bw.newLine();
       bw.close();
    }

    private static void saveTasksToFile() throws IOException {
        BufferedWriter bw = Files.newBufferedWriter(tasksPath);
        for (Task task : tasks) {
            bw.write(taskToFileFormat(task));
            bw.newLine();
        }
        bw.close();
    }

    private static Task fileFormatToTask(String line) {
        String[] splitLine = line.split(" \\| ");
        String taskType = splitLine[0];
        Task task = null;
        switch (taskType) {
        case "T":
            task = new Todo(splitLine[2]);
            break;
        case "D":
            task = new Deadline(splitLine[2], splitLine[3]);
            break;
        case "E":
            task = (new Event(splitLine[2], splitLine[3]));
            break;
        default:
            break;
        }
        if (task != null) {
            if (splitLine[1].equals("1")) {
                task.markAsDone();
            }
        }
        return task;
    }

    private static void load() throws IOException {
        String home = System.getProperty("user.home");
        Path dataDir = Paths.get(home, "Tako","Data");
        if (!Files.isDirectory(dataDir)) {
            Files.createDirectories(dataDir);
        }
        tasksPath = Paths.get(dataDir.toString(), "Tako.txt");
        if (!Files.isRegularFile(tasksPath)) {
            Files.createFile(tasksPath);
        }

        BufferedReader br = Files.newBufferedReader(tasksPath);
        String line = br.readLine();
        while (line != null) {
            tasks.add(fileFormatToTask(line));
            line = br.readLine();
        }
        br.close();
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm Tako.\nWhat do you want?");
        Scanner sc = new Scanner(System.in);
        try {
            load();
            while (sc.hasNext()) {
                String input = sc.nextLine().trim();
                String[] splitInput = input.split(" ", 2);
                String stringCommand = splitInput[0].toUpperCase();
                Command command;
                if (Command.contains(stringCommand)) {
                    command = Command.valueOf(stringCommand);
                } else {
                    throw new InvalidInputException();
                }

                switch (command) {
                case BYE:
                    if (splitInput.length == 1) {
                        System.out.println("Bye, until next time...");
                        sc.close();
                        return;
                    } else {
                        throw new InvalidInputException();
                    }
                case LIST:
                    if (splitInput.length == 1) {
                        for (int i = 0; i < tasks.size(); i++) {
                            Task task = tasks.get(i);
                            System.out.printf("%d.%s\n", i + 1, task);
                        }
                    } else {
                        throw new InvalidInputException();
                    }
                    break;
                case MARK:
                    try {
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        } else if (splitInput.length == 2) {
                            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
                            if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
                                throw new InvalidRangeException();
                            }
                            Task task = tasks.get(taskNumber);
                            task.markAsDone();
                            saveTasksToFile();
                            System.out.println("marked: " + task);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("The task number to mark is invalid.");
                    } catch (InvalidRangeException e) {
                        System.out.println("The task number to mark does not exist.");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("The task number to mark cannot be empty.");
                    }
                    break;
                case TODO:
                    if (splitInput.length == 1) {
                        throw new EmptyDescriptionException("todo");
                    }
                    Todo todo = new Todo(splitInput[1]);
                    tasks.add(todo);
                    saveToFile(todo);
                    System.out.println("added: " + todo);
                    System.out.println("Total tasks: " + tasks.size());
                    break;
                case DEADLINE:
                    if (splitInput.length == 1) {
                        throw new EmptyDescriptionException("deadline");
                    }
                    String[] splitDeadline = splitInput[1].split(" /by ", 2);
                    if (splitDeadline.length == 2) {
                        Deadline deadline = new Deadline(splitDeadline[0], splitDeadline[1]);
                        tasks.add(deadline);
                        saveToFile(deadline);
                        System.out.println("added: " + deadline);
                        System.out.println("Total tasks: " + tasks.size());
                    } else {
                        throw new EmptyDescriptionException("deadline's date/time");
                    }
                    break;
                case EVENT:
                    if (splitInput.length == 1) {
                        throw new EmptyDescriptionException("event");
                    }
                    String[] splitEvent = splitInput[1].split(" /at ", 2);
                    if (splitEvent.length == 2) {
                        Event event = new Event(splitEvent[0], splitEvent[1]);
                        tasks.add(event);
                        saveToFile(event);
                        System.out.println("added: " + event);
                        System.out.println("Total tasks: " + tasks.size());
                    } else {
                        throw new EmptyDescriptionException("event's date/time");
                    }
                    break;
                case DELETE:
                    try {
                        if (splitInput.length == 1) {
                            throw new EmptyDescriptionException();
                        } else if (splitInput.length == 2) {
                            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
                            if (taskNumber < 0 || taskNumber > tasks.size() - 1) {
                                throw new InvalidRangeException();
                            }
                            Task task = tasks.remove(taskNumber);
                            saveTasksToFile();
                            System.out.println("deleted: " + task);
                            System.out.println("Total tasks: " + tasks.size());
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("The task number to delete is invalid.");
                    } catch (InvalidRangeException e) {
                        System.out.println("The task number to delete does not exist.");
                    } catch (EmptyDescriptionException e) {
                        System.out.println("The task number to delete cannot be empty.");
                    }
                    break;
                default:
                    throw new InvalidInputException();
                }
            }
        } catch (EmptyDescriptionException | InvalidInputException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}