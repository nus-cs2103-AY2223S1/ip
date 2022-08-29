import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
            boolean isDone = inputArray[1].equals("X");

            switch (inputArray[0]) {
                case "T":
                    task = new Todo(inputArray[2], isDone);
                    break;
                case "D":
                    task = new Deadline(inputArray[2], LocalDate.parse(inputArray[3]), isDone);
                    break;
                case "E":
                    task = new Event(inputArray[2], LocalDate.parse(inputArray[3]), LocalTime.parse(inputArray[4]),
                            LocalTime.parse(inputArray[5]), isDone);
                    break;
                default:
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

        ArrayList<Task> list = new ArrayList<>(100);
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
                    for (Task task : list) {
                        String textInput = "";
                        String isDone = "O / ";
                        if (task.isDone) {
                            isDone = "X / ";
                        }

                        if (task instanceof Todo) {
                            textInput = "T / " + isDone + task.taskName + "\n";
                        } else if (task instanceof Deadline) {
                            textInput = "D / " + isDone + task.taskName + " / " + ((Deadline) task).by + "\n";
                        } else if (task instanceof Event) {
                            textInput = "E / " + isDone + task.taskName + " / " + ((Event) task).at + " / "
                                    + ((Event) task).startTime + " / " + ((Event) task).endTime + "\n";
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
                if (list.isEmpty()) {
                    System.out.println("you have no tasks woof woof!");
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
                        LocalDate date = LocalDate.parse(inputArray[1]);
                        list.add(new Deadline(inputArray[0], date));

                        System.out.println("woof! the task is added woof!");
                        System.out.println(list.get(taskCount).toString());
                        System.out.println("you now have " + (taskCount + 1) + " tasks in the list woof!");

                        taskCount++;
                    }
                } catch (DescriptionEmptyException e) {
                    System.out.println(e.getMessage());
                } catch (DateTimeParseException e) {
                    System.out.println("grrrr >:( please input deadline in yyyy-mm-dd format woof woof!");
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
                } catch (DateTimeParseException e) {
                    System.out.println("grrrr >:( please input event date as 'yyyy-mm-dd from hh:mm to hh:mm' format woof woof!");
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