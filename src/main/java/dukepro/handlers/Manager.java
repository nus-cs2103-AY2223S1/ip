package dukepro.handlers;

import dukepro.StorableObjects;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Function;

public class Manager <T extends StorableObjects> {
    private GeneralStorage<T> storage;
    private ArrayList<T> storedObjects = new ArrayList<>();
    private String decorator;

    public Manager(String dir, String pathName, Function<String, T> decoder, String decorator) {
        storage = new GeneralStorage<>(dir, pathName);
        storage.readfile(this, decoder);
        this.decorator = decorator;
    }

    public String add(T t) {
        storedObjects.add(t);
        storage.add(t);
        String ret = "Got it. I've added this " + decorator + ": " + t + "\n Now you have "
                + numStored() + " " + decorator + " in your list";

        return ret;
    }

    public void addNoPrint(T t) {
        storedObjects.add(t);
    }

    public String showList() {
        String ret = "Here are the " + decorator + " in your list: \n";
        for (int i = 0; i < storedObjects.size(); i++) {
            int counter = i + 1;
            ret = ret + counter + ". " + storedObjects.get(i) + "\n";
        }
        return ret;
    }

    public String markAsDone(int n) {
        T done = this.storedObjects.get(n - 1);
        done.markAsDone();

        storage.rewriteFile(this.storedObjects);
        String ret = "Nice! I've marked this task as done:\n" + done;
        return ret;
    }

    public String delete(int n) {
        T deleted = this.storedObjects.remove(n - 1);
        storage.rewriteFile(this.storedObjects);
        String ret = "The following task has been deleted:\n" + deleted;
        return ret;
    }

    public String find(String match) {
        String ret = "The following tasks match your search:\n";

        for (int i = 0; i < storedObjects.size(); i++) {
            T currT = storedObjects.get(i);
            if (currT.getMatching(match)) {
                ret += currT;
            }
        }
        return ret;
    }

    public int numStored() {
        return storedObjects.size();
    }

    public void closePW() {storage.end();}

    public String showDate(LocalDate localDate) {
        String ret = "These tasks are due on " + localDate + "\n";
        for (int i = 0; i < storedObjects.size(); i++) {
            if (storedObjects.get(i).compareDate(localDate)) {
                ret += storedObjects.get(i) + "\n";
            }
        }
        return ret;
    }
}
