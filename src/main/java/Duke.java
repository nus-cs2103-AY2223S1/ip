import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException, IOException {
        File myFile = new File("src/main/data/Duke.txt");
        boolean doesExist = myFile.exists();
        myFile.createNewFile();
        Scanner fsc = new Scanner(myFile);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfTasks = new ArrayList<>();
        System.out.println("Hello! I'm Don\nHow may I help you?");

        if (doesExist) {
            while (fsc.hasNextLine()) {
                String currLine = fsc.nextLine();
                String[] currLineArr = currLine.split(" \\| ");
                if (currLineArr[0].equals("T")) {
                    Todo td = new Todo(currLineArr[2]);
                    if (currLineArr[1].equals("1")) {
                        td.markAsDone();
                    }
                    listOfTasks.add(td);
                }
                if (currLineArr[0].equals("D")) {
                    Deadline d = new Deadline(currLineArr[2], currLineArr[3]);
                    if (currLineArr[1].equals("1")) {
                        d.markAsDone();
                    }
                    listOfTasks.add(d);
                }
                if (currLineArr[0].equals("E")) {
                    Event e = new Event(currLineArr[2], currLineArr[3]);
                    if (currLineArr[1].equals("1")) {
                        e.markAsDone();
                    }
                    listOfTasks.add(e);
                }
            }

            while (sc.hasNextLine()) {
                String reply = sc.nextLine();
                String[] response = reply.split(" ");
                Response firstWord = Response.valueOf(response[0].toUpperCase());
                switch (firstWord) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        break;

                    case LIST:
                        System.out.println("Here are the tasks in your list:");
                        for (int index = 0; index < listOfTasks.size(); index++) {
                            System.out.println(index + 1 + "." + listOfTasks.get(index).toString());
                        }
                        break;

                    case MARK:
                        try {
                            int taskIndex = Integer.parseInt(response[1]) - 1;
                            listOfTasks.get(taskIndex).markAsDone();
                            System.out.println("Nice! I've marked this task as done:\n" + "[" + listOfTasks.get(taskIndex).getStatusIcon() + "] " + listOfTasks.get(taskIndex).getDescription());
                            writeToTextFile(listOfTasks, myFile);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! You're missing an index for mark.", e);
                        }
                        break;

                    case UNMARK:
                        try {
                            int taskIndex = Integer.parseInt(response[1]) - 1;
                            listOfTasks.get(taskIndex).markAsNotDone();
                            System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + listOfTasks.get(taskIndex).getStatusIcon() + "] " + listOfTasks.get(taskIndex).getDescription());
                            writeToTextFile(listOfTasks, myFile);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! You're missing an index for unmark.", e);
                        }
                        break;

                    case TODO:
                        try {
                            String toDoTaskDescription = reply.substring(5);
                            Todo toDoTask = new Todo(toDoTaskDescription);
                            listOfTasks.add(toDoTask);
                            System.out.println("Got it. I've added this task:\n" + toDoTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                            writeToTextFile(listOfTasks, myFile);
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.", e);
                        }
                        break;

                    case DEADLINE:
                        try {
                            String deadlineDescriptionWithDate = reply.substring(9);
                            String deadlineDescription = deadlineDescriptionWithDate.split(" /by ")[0];
                            String deadlineDate = deadlineDescriptionWithDate.split(" /by ")[1];
                            Deadline deadlineTask = new Deadline(deadlineDescription, deadlineDate);
                            listOfTasks.add(deadlineTask);
                            System.out.println("Got it. I've added this task:\n" + deadlineTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                            writeToTextFile(listOfTasks, myFile);
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.", e);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! You're missing some descriptions for your deadline.", e);
                        }
                        break;

                    case EVENT:
                        try {
                            String eventDescriptionWithDate = reply.substring(6);
                            String eventDescription = eventDescriptionWithDate.split(" /at ")[0];
                            String eventDate = eventDescriptionWithDate.split(" /at ")[1];
                            Event eventTask = new Event(eventDescription, eventDate);
                            listOfTasks.add(eventTask);
                            System.out.println("Got it. I've added this task:\n" + eventTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                            writeToTextFile(listOfTasks, myFile);
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.", e);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! You're missing some descriptions for your event.", e);
                        }
                        break;

                    case DELETE:
                        try {
                            int deleteIndex = Integer.parseInt(response[1]) - 1;
                            Task deletedTask = listOfTasks.get(deleteIndex);
                            listOfTasks.remove(deleteIndex);
                            System.out.println("Noted. I've removed this task:\n" + deletedTask + "\nNow you have " + listOfTasks.size() + (listOfTasks.size() == 1 ? " task " : " tasks ") + "in the list.");
                            writeToTextFile(listOfTasks, myFile);
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new DukeException("☹ OOPS!!! You're missing an index for delete.", e);
                        }
                        break;

                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }
    private static void writeToTextFile(ArrayList<Task> arr, File file) throws IOException {
        PrintWriter toWrite = new PrintWriter(file);
        String resultToWrite = "";

        for (int i = 0; i < arr.size(); i++) {
            String taskString = arr.get(i).toString();
            String[] taskArr = taskString.split("]");
            String taskToString = "";
            if (taskArr[0].equals("[T")) {
                taskToString += "T | ";
                if (taskArr[1].equals("[✓")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                taskToString += taskArr[2];
            }

            if (taskArr[0].equals("[D")) {
                taskToString += "D | ";
                if (taskArr[1].equals("[✓")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                String[] subTaskArr = taskArr[2].split("\\(by:");
                taskToString += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length()-1);
            }

            if (taskArr[0].equals("[E")) {
                taskToString += "E | ";
                if (taskArr[1].equals("[✓")) {
                    taskToString += "1 |";
                } else {
                    taskToString += "0 |";
                }
                String[] subTaskArr = taskArr[2].split("\\(at:");
                taskToString += subTaskArr[0] + "|" + subTaskArr[1].substring(0, subTaskArr[1].length()-1);
            }

            taskToString = taskToString + "\n";
            resultToWrite = resultToWrite + taskToString;
        }
        toWrite.write(resultToWrite);
        toWrite.close();
    }
}
