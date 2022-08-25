import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

public class Storage {
    private File file;
    private BufferedReader br;
    private ArrayList<Task> al;

    public Storage(String fileName) throws IOException {
        this.file = Path.of(fileName).toFile();
        this.br = new BufferedReader(new FileReader(file));

        if (file.exists()) {
            this.load();
        } else {
            if (file.createNewFile()) {
                this.load();
            } else {
                throw new IOException("File failed creation!");
            }
        }
    }
    public ArrayList<Task> load() throws IOException {
        String ln = this.br.readLine();
        while (ln != null) {
            this.add(ln);
            ln = br.readLine();
        }
        return al;
    }

    public void add(String ln) {
        String tag = String.valueOf(ln.charAt(1));
        String desc = null;
        int id = -1;
        boolean done = false;
        Task toAdd = null;
        if (String.valueOf(ln.charAt(4)).equals("X")) {
            done = true;
        }
        if (tag.equals("T")) {
            toAdd = new Todo(ln.substring(7));
        } else if (tag.equals("D")) {
            id = ln.indexOf("(by:");
            toAdd = new Deadline(ln.substring(7,id - 1), ln.substring(id + 5 , ln.length() - 1));
        } else if (tag.equals("E")) {
            id = ln.indexOf("(at:");
            String timeAttr = ln.substring(id + 5, id + 21)
                    + " " + ln.substring(ln.length() - 6, ln.length() - 1);
            toAdd = new Event(ln.substring(7, id - 1), timeAttr);
        } else {
            toAdd = new Task(ln.substring(6));
        }
        if (done) {
            toAdd.markAsDone();
        }
        al.add(toAdd);
    }
    public void save() throws IOException {
        FileWriter fw = new FileWriter(file.getAbsolutePath());
        for (Iterator<Task> it = al.iterator(); it.hasNext();) {
            Task curr = it.next();
            fw.write(curr.toString() + "\r\n");
        }
        fw.close();
    }
}
