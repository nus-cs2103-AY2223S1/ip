import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Scruffles {
    private static void printList(String filePath) throws FileNotFoundException {
        File newFile = new File(filePath);
        Scanner scan = new Scanner(newFile);
        while (scan.hasNext()) {
            System.out.println(scan.nextLine());
        }
    }

    private static void loadArray(String filePath, ArrayList<Task> array) throws FileNotFoundException {
        File newFile = new File(filePath);
        Scanner scan = new Scanner(newFile);
        while (scan.hasNext()) {
            String taskString = scan.nextLine();
            String[] inputArray = taskString.split(" / ");
            Task task = new Task("");
            boolean isDone = false;

            if (inputArray[1].equals("X")) {
                isDone = true;
            }

            if (inputArray[0].equals("T")) {
                task = new Todo(inputArray[2], isDone);
            } else if (inputArray[0].equals("D")) {
                String[] deadline = inputArray[2].split(" /by ");
                task = new Deadline(deadline[0], deadline[1], isDone);
            } else if (inputArray[0].equals("E")) {
                String[] event = inputArray[2].split(" /at ");
                task = new Event(event[0], event[1], isDone);
            } else {
                continue;
            }

            array.add(task);
        }
    }

    private static void addToList(String filePath, String input) throws IOException {
        File newFile = new File(filePath);
        newFile.createNewFile();
        FileWriter fileWriter = new FileWriter(filePath, true);
        fileWriter.write(input);
        fileWriter.close();
    }
    public static void main(String[] args) {

        System.out.println("woof! I'm scruffles the task tracking doggo\n" + "what can I woof for you today?");

        Scanner sc = new Scanner(System.in);
        String input = "";

        String textPath = "/Users/shamustan/Desktop/University/AY22:23 S1/CS2103T/list.txt";

        ArrayList<Task> list = new ArrayList<Task>(100);
        int taskCount = 0;

        try {
            printList(textPath);
            loadArray(textPath, list);
        } catch (FileNotFoundException e) {
            System.out.println("woof file not found woof!");
        }

        while (true) {

            input = sc.nextLine();

            if (input.equals("bye")) {
                try {
                    for (int i = 0; i < list.size(); i++) {
                        String textInput = "";
                        String isDone = "O / ";
                        if (list.get(i).isDone) {
                            isDone = "X / ";
                        }

                        if (list.get(i) instanceof Todo) {
                            textInput = "T / " + isDone + list.get(i).taskName + "\n";
                        } else if (list.get(i) instanceof Deadline) {
                            textInput = "D / " + isDone + list.get(i).taskName + " /by " + ((Deadline) list.get(i)).by + "\n";
                        } else if (list.get(i) instanceof Event) {
                            textInput = "E / " + isDone + list.get(i).taskName + " /at " + ((Event) list.get(i)).at + "\n";
                        }
                        addToList(textPath, textInput);
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("woof see you again woof!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < list.size(); i++) {
                    String output = (i + 1) + "." + list.get(i).toString();
                    System.out.println(output);
                }
            } else if (input.startsWith("mark")) {
                try {
                    if (input.equals("mark") || input.equals("mark ")) {
                        throw new DescriptionEmptyException("grrrr >:( you need to mark something woof woof!");
                    } else {
                        input = input.replaceFirst("mark ", "");
                        int listRef = Integer.parseInt(input) - 1;
                        if (listRef >= list.size() || listRef < 0) {
                            throw new OutOfBoundsException(listRef + 1);
                        } else {
                            list.get(listRef).setDone();
                            System.out.println("woof! the task is now marked as done woof!");
                            System.out.println(list.get(listRef).toString());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("grrrr >:( you need to input an integer woof woof!");
                } catch (DescriptionEmptyException | OutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("todo")) {
                try {
                    if (input.equals("todo") || input.equals("todo ")) {
                        throw new DescriptionEmptyException();
                    } else {
                        input = input.replaceFirst("todo ", "");
                        list.add(new Todo(input));

                        System.out.println("woof! the task is added woof!");
                        System.out.println(list.get(taskCount).toString());
                        System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                        taskCount++;
                    }
                } catch (DescriptionEmptyException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    if (input.equals("deadline") || input.equals("deadline ")) {
                        throw new DescriptionEmptyException();
                    } else if (!input.contains(" /by ")) {
                        throw new DescriptionEmptyException("grrrr >:( when is your deadline?? woof woof!");
                    } else {
                        input = input.replaceFirst("deadline ", "");
                        String[] inputArray = input.split(" /by ");
                        list.add(new Deadline(inputArray[0], inputArray[1]));

                        System.out.println("woof! the task is added woof!");
                        System.out.println(list.get(taskCount).toString());
                        System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                        taskCount++;
                    }
                } catch (DescriptionEmptyException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    if (input.equals("event") || input.equals("event ")) {
                        throw new DescriptionEmptyException();
                    } else if (!input.contains(" /at ")) {
                        throw new DescriptionEmptyException("grrrr >:( when is your event?? woof woof!");
                    } else {
                        input = input.replaceFirst("event ", "");
                        String[] inputArray = input.split(" /at ");
                        list.add(new Event(inputArray[0], inputArray[1]));

                        System.out.println("woof! the task is added woof!");
                        System.out.println(list.get(taskCount).toString());
                        System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                        taskCount++;
                    }
                } catch (DescriptionEmptyException e) {
                    System.out.println(e.getMessage());
                }
            } else if (input.startsWith("delete")) {
                try {
                    if (input.equals("delete") || input.equals("delete ")) {
                        throw new DescriptionEmptyException("grrrr >:( you need to delete something woof woof!");
                    } else {
                        input = input.replaceFirst("delete ", "");
                        int listRef = Integer.parseInt(input) - 1;
                        if (listRef >= list.size() || listRef < 0) {
                            throw new OutOfBoundsException(listRef + 1);
                        } else {
                            String deletedTask = list.get(listRef).toString();
                            list.remove(listRef);
                            System.out.println("woof! the task is now deleted woof!");
                            System.out.println(deletedTask);
                            System.out.println("you now have " + (taskCount - 1) + " tasks in the list woof!");

                            taskCount--;
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("grrrr >:( you need to input an integer woof woof!");
                } catch (DescriptionEmptyException | OutOfBoundsException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    throw new UnknownArgumentException(input);
                } catch (UnknownArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}