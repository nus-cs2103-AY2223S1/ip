package Duke;

import java.io.*;
import java.util.Scanner;


public class Storage {
    private String path;

    public Storage(String path){
        this.path = path;
    }

    public void save() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            String tasks = "";
            for (Task task : TaskList.taskList) {
                tasks += task.storeToString() + "\n";
            }
            writer.write(tasks);
            writer.close();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void load() throws IOException{

        File file = new File(this.path);
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner sc = new Scanner(file);



        while (sc.hasNext()) {
            String event = sc.nextLine();
            String type = event.substring(0,1);
            String status = event.substring(2,3);
            Task cur = null;

            switch (type) {
                case "T":
                    cur = new ToDo(event.substring(4));
                    break;
                case "D":
                    int indexofdeadline = event.lastIndexOf("|") + 1;
                    String deadline = event.substring(indexofdeadline);
                    String deadlinedescription = event.substring(4,indexofdeadline-1);
                    cur = new Deadline(deadlinedescription,deadline);
                    break;
                case "E":
                    int indexofdate = event.lastIndexOf("|") + 1;
                    String date = event.substring(indexofdate);
                    String eventdescription = event.substring(4,indexofdate-1);
                    cur = new Event(eventdescription,date);
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            if (status.equals("1")) {
                cur.markAsDone();
            }
            System.out.println(cur.toString());
            TaskList.taskList.add(cur);
        }
        sc.close();
        }
    }

