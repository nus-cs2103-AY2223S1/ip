import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;

public class Duke {

    private ArrayList<Task> log;
    private BufferedWriter writer;
    private BufferedReader reader;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }

    public Duke() {
        //default constructor
    }

    public void run() {
        try {
            String logo = " ____                 \n"
                    + "|  _ \\ _ _ _ __ _____ \n"
                    + "| | | |  _  | |/ / _ \\\n"
                    + "| |_| | |_| |   /  __/\n"
                    + "|____/ \\__,_|\\_/ \\___|\n";
            System.out.println("Hello! I'm\n" + logo);
            System.out.println("What can I do for you?");

            File d = new File("data");
            if (!d.getAbsoluteFile().exists()) {
                new File("data").mkdirs();
            }

            // Creating the file reader
            reader = new BufferedReader(new FileReader("data\\duke.txt"));
            File f = new File("data\\duke.txt");
            if (f.getAbsoluteFile().exists()) {
                load();
            } else {
                log = new ArrayList<>();
            }
            reader.close();

            //just in case user exits without any command
            save();

            // Creating the scanner to get input
            Scanner scan = new Scanner(System.in);

            printLine();
            String firstWord = "";
            String restWord = "";

            boolean isMultipleWords = false;

            while (true) {
                try {
                    String s = scan.nextLine();
                    int indexOfSpace = s.indexOf(' ');
                    isMultipleWords = indexOfSpace > -1;
                    firstWord = s;
                    restWord = "";
                    if (isMultipleWords) {
                        firstWord = s.substring(0, indexOfSpace);
                        restWord = s.substring(indexOfSpace).trim();
                    }

                    if (s.equals("bye")) {
                        scan.close();
                        writer.close();
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    } else if (s.equals("list")) {
                        int count = 1;
                        System.out.println("Here are the tasks in your list:");
                        for (Task item : log) {
                            System.out.println(count + ". " + item.toString());
                            count++;
                        }
                    } else {
                        if (firstWord.equals("mark")) {
                            if (!isMultipleWords) {
                                throw new DukeException("Index of task to mark required");
                            }
                            int index = Integer.parseInt(restWord) - 1; //array starts from 0
                            Task temp = log.get(index);
                            temp.Mark();
                            System.out.println("This task is now done: \n" + temp);
                            save();
                        } else if (firstWord.equals("unmark")) {
                            if (!isMultipleWords) {
                                throw new DukeException("Index of task to unmark required");
                            }
                            int index = Integer.parseInt(restWord) - 1; //array starts from 0
                            Task temp = log.get(index);
                            temp.Unmark();
                            System.out.println("This task is now not done: \n" + temp);
                            save();
                        } else if (firstWord.equals("delete")) {
                            if (!isMultipleWords) {
                                throw new DukeException("Index of task to delete required");
                            }
                            Task temp = log.get(Integer.parseInt(restWord) - 1);
                            log.remove(Integer.parseInt(restWord) - 1);
                            System.out.println("This task is now removed: \n" + temp);
                            save();
                        } else {
                            if (firstWord.equals("todo")) {
                                log.add(new Todo(restWord, false));
                                System.out.println("added todo: " + restWord);
                                save();
                            } else if (firstWord.equals("deadline")) {
                                if (!restWord.contains("/by")) {
                                    throw new DukeTaskException("Deadlines require command '/by' to signify time");
                                }
                                String by = dateFinder(restWord, "/by");
                                String name = nameFinder(restWord, "/by");
                                log.add(new Deadline(name, false, by));
                                System.out.println("added deadline: " + name);
                                save();
                            } else if (firstWord.equals("event") && restWord.contains("/at")) {
                                if (!restWord.contains("/at")) {
                                    throw new DukeTaskException("Events require command '/at' to signify time");
                                }
                                String at = dateFinder(restWord, "/at");
                                String name = nameFinder(restWord, "/at");
                                log.add(new Event(name, false, at));
                                System.out.println("added event: " + name);
                                save();
                            } else {
                                throw new DukeException("?? Unrecognised command");
                            }
                        }
                    }
                } catch (DukeTaskException e) {
                    System.out.println("Error occurred when creating task:" + e);
                } catch (DukeException e) {
                    System.out.println("Error occurred " + e);
                } catch (NumberFormatException e) {
                    System.out.println("Error occurred: Could not identify index");
                } finally {
                    printLine();
                }
            }
        } catch (DukeException e) {
            System.out.println("Error occurred during loading: " + e);
        } catch (IOException e) {
            System.out.print("Error occurred with saving/loading: " + e);
        }
    }

    private void printLine() {
        System.out.println("--------------------------------------");
    }

    private void save() throws IOException {
        writer = new BufferedWriter(new FileWriter("data\\duke.txt", false));
        for(int i = 0; i < log.size(); i++) {
            writer.write(log.get(i).saveString());
            writer.newLine();
        }
        writer.close();
    }

    private void load() throws IOException, DukeException {
        log = new ArrayList<>();
        String s;
        char first;
        while ((s = reader.readLine()) != null) {
            first = s.charAt(0);
            parse(first, s);
        }
    }

    private void parse(char c, String s) throws DukeException {
        if (c == 'T') {
            log.add(Todo.load(s));
        } else if (c == 'D') {
            log.add(Deadline.load(s));
        } else if (c == 'E') {
            log.add(Event.load(s));
        } else {
            //should not reach here
        }
    }

    private String dateFinder(String restWord, String flag) {
        return restWord.substring(restWord.indexOf(flag) + flag.length()).trim();
    }

    private String nameFinder(String restWord, String flag) {
        return restWord.substring(0, restWord.indexOf(flag)).trim();
    }
}









/*
       //Unused:

            try {
                String s = scan.nextLine();
                int indexOfSpace = s.indexOf(' ');
                isMultipleWords = indexOfSpace > -1;
                firstWord = s;
                restWord = "";
                if (isMultipleWords) {
                    firstWord = s.substring(0, indexOfSpace);
                    restWord = s.substring(indexOfSpace).trim();
                }
                System.out.println("--------------------------------------");

                switch(firstWord) {
                    case "bye":
                        scan.close();
                        System.out.println("Bye. Hope to see you again soon!");
                        break LOOP;
                    case "list":
                        int count = 1;
                        System.out.println("Here are the tasks in your list:");
                        for (Task item : log) {
                            System.out.println(count + ". " + item.toString());
                            count++;
                        }
                        break;
                    case "mark":
                        if (!isMultipleWords) {
                            throw new DukeException("Index of task to mark required");
                        }
                    {
                        int index = Integer.parseInt(restWord) - 1; //array starts from 0
                        Task temp = log.get(index);
                        temp.Mark();
                        System.out.println("This task is now done: \n" + temp);
                    }
                    case "unmark":
                        if (!isMultipleWords) {
                            throw new DukeException("Index of task to unmark required");
                        }
                        int index = Integer.parseInt(restWord) - 1; //array starts from 0
                        Task temp = log.get(index);
                        temp.Unmark();
                        System.out.println("This task is now not done: \n" + temp);
                }
*/