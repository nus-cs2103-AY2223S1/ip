package dukepro.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import dukepro.StorableObjects;

/**
 * Class for GeneralStorage which enables user to
 * store items.
 */
public class GeneralStorage<T extends StorableObjects> {
    private File directory;
    private File storedList;
    private FileWriter fileWriter;
    private PrintWriter printWriter;

    /**
     * Constructor for GeneralStorage.
     *
     * @param dir A String representing directory of
     *            stored file.
     * @param pathName A String representing the
     *                 pathname of the stored .txt
     *                 file.
     * @return A GeneralStorage.
     */
    public GeneralStorage(String dir, String pathName) {
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

    /**
     * Reads from Stored txt file into the Manager.
     *
     * @param manager The Manager to read data into.
     * @param decoder A Function that translates String
     *                input read from the file to T which
     *                is accepted by the manager.
     * @return boolean.
     */
    public boolean readfile(Manager<? super T> manager, Function<? super String, ? extends T> decoder) {
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

    /**
     * Writes the object T to the file.
     *
     * @param t A T to write to the file.
     */
    public void add(T t) {
        printWriter.println(t.toFileForm());
    }

    /**
     * Rewrites all file data to reflect latest
     * updates from the manager.
     *
     * @param objects The ArrayList of T.
     * @return boolean.
     */
    public boolean rewriteFile(ArrayList<? extends T> objects) {
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
            printWriter.println(t.toFileForm());
        }
        return true;
    }

    /**
     * Closes the PrintWriter, writes to
     * relevant .txt files.
     *
     */
    public void end() {
        this.printWriter.close();
    }
}
