import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class Duke {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String straightLine = "  ----------------------------------------------------------------------------------";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> listOfThings = new ArrayList<>(100);

        try {
            printFileContents("./src/main/java/KiwiList.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File has yet to be created, creating for you now!");
        }



        System.out.println("\nHello from\n" + logo);

        System.out.println("Hello! I'm KiwiQE :) \nWhat can I do for you? \n");

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("Bye")) {

            if (input.equalsIgnoreCase("list")) {
                if (listOfThings.isEmpty()) {
                    System.out.println(straightLine + "\n" + "  Nothing to do currently ehe\n" + straightLine);
                    input = sc.nextLine();
                    continue;
                }

                System.out.println(straightLine);

                int freshIndex = 1;

                for (Task task : listOfThings) {
                    task.setIndex(freshIndex);
                    freshIndex++;
                }

                for (Task task : listOfThings) {
                    task.printTask();
                }

                System.out.println(straightLine + "\n");

                input = sc.nextLine();
                continue;
            }

            if (input.startsWith("mark")) {

                try {
                    char i = input.charAt(5);
                    int index = Character.getNumericValue(i);
                    Task t = listOfThings.get(index - 1);

                    t.markDone();

                    input = sc.nextLine();
                    continue;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to mark! Please input more ;-;\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  HEY THERE'S NO SUCH TASK! >:(\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }

            }

            if (input.startsWith("unmark")) {

                try {

                    char i = input.charAt(7);
                    int index = Character.getNumericValue(i);
                    Task t = listOfThings.get(index - 1);

                    t.markUndone();

                    input = sc.nextLine();
                    continue;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to unmark! Please input more ;-;\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  HEY THERE'S NO SUCH TASK! >:(\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }

            }

            if (input.startsWith("delete")) {

                try {

                    char i = input.charAt(7);
                    int index = Character.getNumericValue(i);
                    Task t = listOfThings.get(index - 1);

                    t.printDeleted();
                    System.out.println("  Now you have " + (listOfThings.size() - 1)+ " left\n" + straightLine + "\n");

                    listOfThings.remove(index - 1);
                    input = sc.nextLine();
                    continue;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to delete! Please input more ;-;\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Can't delete something that isn't there...\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }

            }

            if (input.startsWith("todo")) {

                try {
                    String task = input.substring(5);

                    ToDo newToDo = new ToDo(false, task, listOfThings.size() + 1);
                    listOfThings.add(newToDo);

                    newToDo.printAdded();
                    input = sc.nextLine();
                    continue;
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to make a to do! Please input more ;-;\n"
                                        + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }

            }

            if (input.startsWith("deadline")) {

                try {
                    int indexOfSlash = input.indexOf("/");
                    String dateStr = input.substring(indexOfSlash + 4, indexOfSlash + 13);
                    String time = input.substring(indexOfSlash + 14);
                    Parser p = new Parser();
                    String newTime = p.parseTime(time);
                    DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive().parseLenient()
                            .appendPattern("[yyyy-MM-dd]")
                            .appendPattern("[M/dd/yyyy]")
                            .appendPattern("[M/d/yyyy]")
                            .appendPattern("[MM/dd/yyyy]")
                            .appendPattern("[MMM dd yyyy]");
                    DateTimeFormatter df = builder.toFormatter(Locale.ENGLISH);
                    LocalDate d1 = LocalDate.parse(dateStr, df);
                    String task = input.substring(9, indexOfSlash);
                    Deadline newDeadline = new Deadline(false, task, listOfThings.size() + 1, d1, newTime);
                    listOfThings.add(newDeadline);

                    newDeadline.printAdded();

                    input = sc.nextLine();
                    continue;

                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to make a deadline! Please input more ;-;\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }
            }

            if (input.startsWith("event")) {

                try {

                    int indexOfSlash = input.indexOf("/");
                    String date = input.substring(indexOfSlash + 4, indexOfSlash + 13);
                    String time = input.substring(indexOfSlash + 14);
                    Parser p = new Parser();
                    String newTime = p.parseTime(time);
                    DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder()
                            .parseCaseInsensitive().parseLenient()
                            .appendPattern("[yyyy-MM-dd]")
                            .appendPattern("[M/dd/yyyy]")
                            .appendPattern("[M/d/yyyy]")
                            .appendPattern("[MM/dd/yyyy]")
                            .appendPattern("[MMM dd yyyy]");
                    DateTimeFormatter df = builder.toFormatter(Locale.ENGLISH);
                    LocalDate d1 = LocalDate.parse(date, df);
                    String task = input.substring(6, indexOfSlash);
                    Event newEvent = new Event(false, task, listOfThings.size() + 1, d1, newTime);
                    listOfThings.add(newEvent);

                    newEvent.printAdded();

                    input = sc.nextLine();
                    continue;


                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println(straightLine + "\n  Insufficient information to make a event! Please input more ;-;\n"
                            + straightLine + "\n");
                    input = sc.nextLine();
                    continue;
                }
            }


            System.out.println(straightLine + "\n  What do you mean by Justin Bieber plays~\n" + straightLine + "\n");

            input = sc.nextLine();
        }

        for (Task task: listOfThings) {
            File f = new File("./src/main/java/KiwiList.txt");
            if (!f.exists()) {
                try {
                    f.createNewFile();
                    writeToFile("./src/main/java/KiwiList.txt", task.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("You can't create a file!");
                }
            } else {
                try {
                    appendToFile("./src/main/java/KiwiList.txt", task.toString() + System.lineSeparator());
                } catch (IOException e) {
                    System.out.println("You can't append!");
                }
            }
        }

        System.out.println(straightLine + "\n  さよなら, goodbye\n" + straightLine);

    }
}
