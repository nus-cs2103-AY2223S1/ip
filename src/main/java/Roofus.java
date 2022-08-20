import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

import java.util.List;
import java.util.ArrayList;

public class Roofus {
    static String LINESEP = "****************************************";
    
    private static String STORAGEPATH = "./data/roofus.txt";
    private File storage = new File(STORAGEPATH);
    
    private List<Task> tasks = new ArrayList<>();

    void greet() {
        System.out.println(LINESEP);
        System.out.println("Hello I'm Roofus\n" + "What can I do for you?");
        System.out.println(LINESEP);
    }
    
    void load() throws FileNotFoundException {
        Scanner sc = new Scanner(this.storage);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String taskType = line.substring(0, 6);
            boolean isComplete = taskType.charAt(4) == 'X';
            Task thisTask = new Task("initialised");
            if (taskType.charAt(1) == 'T') {
                thisTask = new ToDo(line.substring(6));
            } else {
                if (taskType.charAt(1) == 'D') {
                    String[] descTimeDetails = line.substring(6).split("by:", 2);
                    String description = descTimeDetails.length < 1 ? " " :
                            descTimeDetails[0].substring(0, descTimeDetails[0].length() - 1);
                    String time = descTimeDetails.length < 2 ? " " :
                            descTimeDetails[1].substring(0, descTimeDetails[1].length() - 1);
                    thisTask = new Deadline(description, time);
                } else {
                    String[] descTimeDetails = line.substring(6).split("at:", 2);
                    String description = descTimeDetails.length < 1 ? " " : 
                            descTimeDetails[0].substring(0, descTimeDetails[0].length() - 1);
                    String time = descTimeDetails.length < 2 ? " " : 
                            descTimeDetails[1].substring(0, descTimeDetails[1].length() - 1);
                    thisTask = new Event(description, time);    
                }
            }
            if (isComplete) {
                thisTask.setDone();
            }
            this.tasks.add(thisTask);
        }
    }
    
    void save() throws IOException {
        FileWriter editor = new FileWriter(STORAGEPATH);
        for (Task t : tasks) {
            editor.write(t.toString() + "\n");
        }
        editor.close();
    }

    void addTask(Task task) {
        tasks.add(task);
        String reply = String.format("%s\nGot it. I've added this task:\n%s\n" +
                "Now you have %d tasks in the list.\n%s", LINESEP, task.toString(),
                tasks.size(), LINESEP);
        System.out.println(reply);
    }

    void signOff() {
        System.out.println(String.format("%s\nBye. Hope to see you again soon!\n%s",
                LINESEP, LINESEP));
    }

    void list() {
        System.out.println(LINESEP);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            System.out.println(index + "." + tasks.get(i).toString());
        }
        System.out.println(LINESEP);
    }

    void mark(int index) {
        tasks.get(index - 1).mark();
    }

    void unMark(int index) {
        tasks.get(index - 1).unmark();
    }

    void delete(int index) {
        Task task = tasks.get(index - 1);
        tasks.remove(index - 1);
        System.out.println(LINESEP);
        System.out.println(String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.",
                task.toString(), tasks.size()));
        System.out.println(LINESEP);
    }

    static void errMessage(String message) {
        System.out.println(LINESEP);
        System.out.println(message.toUpperCase());
        System.out.println(LINESEP);
    }

    enum Command {
        BYE, LIST, TODO, EVENT, DEADLINE,
        MARK, UNMARK, DELETE
    }

    public static void main(String[] args) {
        Roofus roofus = new Roofus();
        try {
            roofus.load();
        } catch (FileNotFoundException err) {
            errMessage("Required file not found\nRoofus cannot load storage data");
        }
        roofus.greet();
        Scanner sc = new Scanner(System.in);
        boolean isRunning = true;
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            Scanner sc2 = new Scanner(input);
            Command command = Command.valueOf(sc2.next().toUpperCase());
            switch (command) {
                case BYE:
                    roofus.signOff();
                    isRunning = false;
                    break;
                case LIST:
                    roofus.list();
                    break;
                case DELETE:
                    try {
                        int index = Integer.parseInt(sc2.next());
                        if (index > roofus.tasks.size() || index < 1) {
                            //handles index out of bounds exception
                            throw new RoofusException("Hey! It's not even in this list!");
                        }
                        roofus.delete(index);
                    } catch (RoofusException err) {
                        errMessage(err.getMessage());
                    } catch (Exception err) {
                        errMessage("Sorry could not understand you!");
                    }
                    break;
                case MARK:
                    try {
                        int index1 = Integer.parseInt(sc2.next());
                        if (index1 > roofus.tasks.size() || index1 < 1) {
                            throw new RoofusException("Hey! It's not even in this list!");
                        }
                        roofus.mark(index1);
                    } catch (RoofusException err) {
                        errMessage(err.getMessage());
                    } catch (Exception err) {
                        errMessage("Sorry could not understand you!");
                    }
                    break;
                case UNMARK:
                    try {
                        int index2 = Integer.parseInt(sc2.next());
                        if (index2 > roofus.tasks.size() || index2 < 1) {
                            throw new RoofusException("Hey! It's not even in this list!");
                        }
                        roofus.unMark(index2);
                    } catch (RoofusException err) {
                        errMessage(err.getMessage());
                    } catch (Exception err) {
                        errMessage("Sorry could not understand you!");
                    }
                    break;
                case TODO:
                    try {
                        if (!sc2.hasNextLine()) {
                            //handles no such element exception
                            throw new RoofusException("Huh? To do what?");
                        }
                        roofus.addTask(new ToDo(sc2.nextLine()));
                    } catch (RoofusException err) {
                        errMessage(err.getMessage());
                    } catch (Exception err) {
                        errMessage("Sorry could not understand you!");
                    }
                    break;
                case DEADLINE:
                    try {
                        String details = sc2.nextLine();
                        String[] separate = details.split("/by", 2);
                        roofus.addTask(new Deadline(separate[0], separate[1]));
                    } catch (ArrayIndexOutOfBoundsException err) {
                        errMessage("Oops! Your deadline task isn't clear.");
                    } catch (NoSuchElementException err) {
                        errMessage("Huh?! What deadline?");
                    }
                    break;
                case EVENT:
                    try {
                        String details2 = sc2.nextLine();
                        String[] separate = details2.split("/at", 2);
                        roofus.addTask(new Event(separate[0], separate[1]));
                    } catch (ArrayIndexOutOfBoundsException err) {
                        errMessage("Oops! Your event task isn't clear.");
                    } catch (NoSuchElementException err) {
                        errMessage("Huh?! What event?");
                    }
                    break;
                default:
                    System.out.println(String.format("%s\nPlease key in valid commands only\n%s",
                            LINESEP, LINESEP));
            }
            if (!isRunning) {
                break;
            }
        }
        try {
            roofus.save();
        } catch (IOException err) {
            errMessage("file not saved");
        }
    }
}
