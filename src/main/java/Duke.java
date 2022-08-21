import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();


    public static void readFile() throws FileNotFoundException {
        try {
            File file ,txt;
            FileReader fr;
            BufferedReader br;

            file = new File("./data/");
            if (!file.exists()) {
                file.mkdir();
            }

            txt = new File("./data/duke.txt");
            if (!txt.exists()) {
                txt.createNewFile();
            }
            fr = new FileReader(txt);

            br = new BufferedReader(fr);
            String line;

            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }

                String[] info = line.split(" / ", 4);
                switch (info[0]) {
                    case "T":
                        tasks.add(new Todo(TaskType.TODO, info[2], info[1].equals("1")));
                        break;
                    case "D":
                        tasks.add(new Deadline(TaskType.TODO, info[2], info[1].equals("1"), info[3]));
                        break;
                    case "E":
                        tasks.add(new Event(TaskType.TODO, info[2], info[1].equals("1"), info[3]));
                        break;
                    default:
                        break;
                }

            }
        } catch (FileNotFoundException msg) {
            System.out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(ArrayList<Task> tasks) throws IOException {
        try {
            File writeF = new File("./data/duke.txt");
            if(!writeF.exists()) {
                writeF.createNewFile();
            }
            FileWriter fw = new FileWriter(writeF);
            for (int i = 0; i < tasks.size(); i ++) {
                Task curr = tasks.get(i);
                String type = "";
                String marked;
                String name;
                String time = "";

                marked = curr.isMarked()
                        ? "1 / "
                        : "0 / ";
                name = curr.getName() + " / ";

                switch (curr.getTaskType()) {
                    case TODO:
                        type = "T / ";
                        time = "";
                        break;
                    case DEADLINE:
                        type = "D / ";
                        Deadline dl = (Deadline) curr;
                        time = dl.getTime();
                        break;
                    case EVENT:
                        type = "E / ";
                        Event event = (Event) curr;
                        time = event.getTime();
                        break;
                    default :
                        break;
                }

                String line = type + marked + name + time + "\n";

                fw.write(line);
            }

            fw.close();

        } catch (FileNotFoundException msg) {
            System.out.println(msg);
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        readFile();

        Command cmd = new GreetCmd("");
        cmd.execute("", tasks);

        Echo start = new Echo();
        start.echo(tasks);

        writeFile(tasks);
    }
}
