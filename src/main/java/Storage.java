import java.io.*;

public class Storage {
    public OutputStream out;
    public String input;

    public Storage(String filePath) throws IOException {
        this.out = new FileOutputStream(filePath);
    }

    public void push(String list) throws IOException {
        this.input = list;
        this.out.write(input.getBytes());
        this.out.close();
    }

    public String load() {
        return this.input;
    }
}
