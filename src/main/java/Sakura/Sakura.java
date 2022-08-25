package Sakura;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Sakura {
    static String DIV = "________________________________________________________________";
    static String DIV2 = "============================================================";
    static boolean inProgress = true;
    private static String DIR = "data";
    private static String FILENAME = "Sakura.txt";
    private static String DATAPATH = String.valueOf(Paths.get(Sakura.DIR, Sakura.FILENAME));
    static TaskManager taskManager = new TaskManager();

    private static void loadData() {
        File database = new File(Sakura.DATAPATH);
        try {
            Scanner fileReader = new Scanner(database);
            while (fileReader.hasNextLine()) {
                String entry = fileReader.nextLine();
                readEntry(entry);
            }
        } catch (FileNotFoundException e) {
            createDatabase();
        }
    }

    private static void readEntry(String entry) {
        String[] data = entry.split("\\|");
        Task dataTask = null;
        if (data[0].equals("T")) {
            dataTask = new Todo(data[2]);
        } else if (data[0].equals("D")) {
            dataTask = new Deadline(data[2], data[3]);
        } else if (data[0].equals("E")) {
            dataTask = new Event(data[2], data[3]);
        } else {
            SakuraException.databaseError();
        }
        if (Integer.parseInt(data[1]) == 1) {
            assert dataTask != null;
            dataTask.markDone();
        }
        taskManager.tasks.add(dataTask);
    }

    private static void createDatabase() {
        File database = new File(Sakura.DATAPATH);
        File dir = new File(Sakura.DIR);
        boolean wasSuccessful = dir.mkdir();
        if (wasSuccessful) {
            try {
                boolean fileCreated = database.createNewFile();
                if (!fileCreated) {
                    throw new IOException("Unable to create file at specified path. It already exists");
                }
            } catch (IOException e) {
                System.out.println("Error when creating the database!");
            }
        }
    }

    private static void saveData() throws IOException {
        FileWriter fw = new FileWriter(Sakura.DATAPATH, false);
        BufferedWriter bw = new BufferedWriter(fw);
        for (Task task : taskManager.tasks) {
            bw.write(task.stringifyTask());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    private static void greet() {
        System.out.println(DIV);
        System.out.println("How may I serve you today, Senpai?");
        System.out.println("\t" + DIV2 + "\n");
    }

    private static void exit() {
        try {
            saveData();
            System.out.println("\tBye Senpai! It was a pleasure serving you, see you again soon!");
            System.out.println("\t" + DIV2 + "\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void Start(String input, TaskManager taskManager) {
        if (input.equals("bye")) {
            inProgress = false;
            exit();
        } else if (input.equals("list")) {
            taskManager.showAllTask();
        } else if (input.toLowerCase().startsWith("mark")) {
            taskManager.markTask(input);
        } else if (input.toLowerCase().startsWith("unmark")) {
            taskManager.unmarkTask(input);
        } else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event")) {
            taskManager.addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            taskManager.deleteTask(input);
        } else {
            SakuraException.genericTask();
        }
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        while (inProgress) {
            String command = sc.nextLine();
            System.out.println("\t" + DIV);
            Start(command, taskManager);
            System.out.println("\t" + DIV + "\n");
        }
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        String logo =
                  "   ▄████████    ▄████████    ▄█   ▄█▄ ███    █▄     ▄████████    ▄████████             ▄■▄          \n"
                + "  ███    ███   ███    ███   ███ ▄███▀ ███    ███   ███    ███   ███    ███       ▄■██■█   █■██■▄    \n"
                + "  ███    █▀    ███    ███   ███▐██▀   ███    ███   ███    ███   ███    ███      ██  ▄  ▀▄▀  ▄  ██   \n"
                + "  ███          ███    ███  ▄█████▀    ███    ███  ▄███▄▄▄▄██▀   ███    ███      ▀█▄  ▀▄ █ ▄▀  ▄█▀   \n"
                + "▀███████████ ▀███████████ ▀▀█████▄    ███    ███ ▀▀███▀▀▀▀▀   ▀███████████         █■■((■))■■█      \n"
                + "         ███   ███    ███   ███▐██▄   ███    ███ ▀███████████   ███    ███      ▄█▀  ▄▀ █ ▀▄  ▀█▄   \n"
                + "   ▄█    ███   ███    ███   ███ ▀███▄ ███    ███   ███    ███   ███    ███      ██  ▀  ▄▀▄  ▀  ██   \n"
                + " ▄████████▀    ███    █▀    ███   ▀█▀ ████████▀    ███    ███   ███    █▀        ▀■██■█   █■██■▀    \n"
                + "                            ▀                      ███    ███                          ▀■▀          \n";


        System.out.println("Hello! This is \n" + logo + "\nat your service!!");
        Sakura.greet();
        loadData();
        Sakura.run();
    }
}
