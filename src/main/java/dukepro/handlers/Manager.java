package dukepro.handlers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Function;

import dukepro.StorableObjects;

/**
 * Class for Manager
 */
public class Manager <T extends StorableObjects> {
    private GeneralStorage<T> storage;
    private ArrayList<T> storedObjects;
    private String decorator;

    /**
     * Constructor for Manager.
     *
     * @param dir  The directory for
     *             stored information regarding T.
     * @param pathName The path for the stored .txt
     *                 file for information
     *                 regarding stored T objects.
     * @param decoder The name of type T in String form.
     * @return Manager.
     */
    public Manager(String dir, String pathName, Function<? super String, ? extends T> decoder, String decorator) {
        storedObjects = new ArrayList<>();
        storage = new GeneralStorage<>(dir, pathName);
        storage.readfile(this, decoder);
        this.decorator = decorator;
    }

    /**
     * Adds a T to the ArrayList of
     * stored T.
     *
     * @param t  A T to be added.
     * @return String.
     */
    public String add(T t) {
        storedObjects.add(t);
        storage.add(t);
        String ret = "Got it. I've added this " + decorator + ": \n" + t + "\n Now you have "
                + numStored() + " " + decorator + " in your list";

        return ret;
    }

    /**
     * Add T to list of stored T without
     * printing to GUI interface. This is
     * used for loading stored T from the
     * .txt file.
     *
     * @param t  A T read from the .txt file.
     */
    public void addNoPrint(T t) {
        storedObjects.add(t);
    }

    /**
     * Displays all stored T in this Manager.
     *
     * @return String.
     */
    public String showList() {
        String ret = "Here are the " + decorator + " in your list: \n";
        for (int i = 0; i < storedObjects.size(); i++) {
            int counter = i + 1;
            ret = ret + counter + ". " + storedObjects.get(i) + "\n";
        }
        return ret;
    }

    /**
     * Deletes a T from the list of T stored
     * by the Manager.
     *
     * @param n  The index of T to be deleted.
     * @return String.
     */
    public String delete(int n) {
        T deleted = this.storedObjects.remove(n - 1);
        storage.rewriteFile(this.storedObjects);
        String ret = "The following " + this.decorator + " has been deleted:\n" + deleted;
        return ret;
    }

    /**
     * Returns all strings that match the
     * user's search query.
     *
     * @param match The user's query.
     * @return String.
     */
    public String find(String match) {
        String ret = "The following tasks match your search:\n";

        for (int i = 0; i < storedObjects.size(); i++) {
            T currT = storedObjects.get(i);
            if (currT.getMatching(match)) {
                ret += currT + "\n";
            }
        }
        return ret;
    }

    /**
     * Returns the number of T stored
     * by this Manager.
     *
     * @return int.
     */
    public int numStored() {
        return storedObjects.size();
    }

    /**
     * Tells the storage that the user session
     * is about to end, and to write all existing
     * data to the .txt file.
     */
    public void closePW() {
        storage.end();
    }

    /**
     * Returns all T with dates matching
     * the user's search query.
     *
     * @param localDate The user's date query.
     * @return String.
     */
    public String showDate(LocalDate localDate) {
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String ret = "These tasks are due on " + date + "\n";
        for (int i = 0; i < storedObjects.size(); i++) {
            if (storedObjects.get(i).compareDate(localDate)) {
                ret += storedObjects.get(i) + "\n";
            }
        }
        return ret;
    }

    /**
     * Uses a function on all T in
     * the ArrayList.
     *
     * @param func The Function to be applied.
     * @return S.
     */
    public <S> S operateOnList(Function<? super ArrayList<? extends T>, ? extends S> func) {
        return func.apply(storedObjects);
    }

    public void updateFile() {
        storage.rewriteFile(this.storedObjects);
    }
}
