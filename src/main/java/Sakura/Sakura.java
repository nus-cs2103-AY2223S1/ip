package Sakura;

import java.io.*;
import java.nio.file.Paths;
import java.util.Scanner;

public class Sakura {
    static String DIV = "________________________________________________________________";
    static String DIV2 = "============================================================";
    static boolean inProgress = true;
    private static String DIR = "data";
    private static String FILENAME = "Sakura.txt";
    private static String DATAPATH = String.valueOf(Paths.get(Sakura.DIR, Sakura.FILENAME));
    static TaskList taskList = new TaskList();

    private static Ui ui;

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
        taskList.tasks.add(dataTask);
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
        for (Task task : taskList.tasks) {
            bw.write(task.stringifyTask());
            bw.newLine();
        }
        bw.close();
        fw.close();
    }


    private static void Start(String input, TaskList taskList) {
        if (input.equals("bye")) {
            inProgress = false;
            ui.exit();
        } else if (input.equals("list")) {
            Ui.showAllTask(taskList.tasks);
        } else if (input.toLowerCase().startsWith("mark")) {
            taskList.markTask(input);
        } else if (input.toLowerCase().startsWith("unmark")) {
            taskList.unmarkTask(input);
        } else if (input.toLowerCase().startsWith("todo") || input.toLowerCase().startsWith("deadline") || input.toLowerCase().startsWith("event")) {
            taskList.addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            taskList.deleteTask(input);
        } else {
            SakuraException.genericTask();
        }
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        while (inProgress) {
            String command = sc.nextLine();
            System.out.println("\t" + DIV);
            Start(command, taskList);
            System.out.println("\t" + DIV + "\n");
        }
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        ui.greet();
        loadData();
        Sakura.run();
    }
}
