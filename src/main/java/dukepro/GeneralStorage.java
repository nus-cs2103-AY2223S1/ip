package dukepro;

import dukepro.exceptions.DukeException;
import dukepro.handlers.Decoder;
import dukepro.handlers.Storage;
import dukepro.handlers.TasksManager;
import dukepro.tasks.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class GeneralStorage<T extends StorableObjects> {
    private File directory;
    private File storedList;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    public GeneralStorage(String dir, String pathName) { // creates new files if needed
        try {
            directory = new File(dir);
            if (!directory.exists()) {
                directory.mkdir();
            }

            storedList = new File(pathName);
            if (!storedList.exists()) {
                storedList.createNewFile();
            }
            fileWriter = new FileWriter(this.storedList, true);
            printWriter = new PrintWriter(fileWriter);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public boolean readfile(Manager<? super T> manager, Function<String, T> decoder) {
        try {
            Scanner sc = new Scanner(this.storedList);
            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                manager.addNoPrint(decoder.apply(data));
            }
            sc.close();
            return true;
        } catch (FileNotFoundException e) {
            System.out.println("error reading file");
            return false;
        }
    }

    public void add(T t) {
        printWriter.println(t.fileForm());
    }

    public boolean rewriteFile(ArrayList<T> objects) {
        printWriter.flush();
        try {
            printWriter = new PrintWriter(new FileWriter(this.storedList, false));
            printWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }

        printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < objects.size(); i++) {
            T t = objects.get(i);
            printWriter.println(t.fileForm());
        }
        return true;
    }

    public void end() {
        this.printWriter.close();
    }
}
