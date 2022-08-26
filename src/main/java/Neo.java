import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.File;
public class Neo {

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException, IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend + "\n");
        fw.close();
    }

    public static void main(String[] args) throws NeoException, IOException {

        List<String> arrayList = new ArrayList<String>();
        List<Task> arrayL = new ArrayList<Task>();
        String userText;

        Task[] tasks = new Task[100];

        File f = new File("/Users/richavm/Documents/NUS/Y2S1/CS2103T/data/Neo.txt");
        boolean isPresent = f.exists();
        if (!isPresent) {
            f.getParentFile().mkdir();
            f.createNewFile();
        }

        System.out.println("");
        System.out.println("Hello! I'm Neo");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter items you want to add to the list, if you want to quit enter bye");
            System.out.println("");
            System.out.print("Enter Your command: ");
            userText = sc.nextLine();
            System.out.println("");

            if (userText.equals("bye") || userText.equals("Bye")) {
                System.out.println("Exiting chatbot! Hope to see you again");
                break;
            }
            if (userText.equals("list") || userText.equals("List")) {
                for (int i = 0; i < arrayL.size(); i++) {
                    //int j = i + 1;
                    //Task temp = arrayL.get(i);
                    //System.out.println(j + "." + temp.toString());
                }
                    try {
                        printFileContents(f.getAbsolutePath());
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found");
                    }
            }
            String arr[];
            arr = userText.split(" ", 2);
            if (arr.length>1 && arr[0].equals("mark")){
                int tempi = Integer.valueOf(arr[1]);
                arrayL.get(tempi -1).setIsDone(true);
                System.out.println("Nice! I've marked this task as done");
                System.out.println(arrayL.get(tempi-1).toString());
            }
            if (arr.length>1 && arr[0].equals("unmark")){
                int tempi = Integer.valueOf(arr[1]);
                arrayL.get(tempi - 1).setIsDone(false);
                System.out.println("ok, I've marked this task as not done yet");
                System.out.println(arrayL.get(tempi-1).toString());
            }
            if (arr.length>1 && arr[0].equals("delete")){
                int tempi = Integer.valueOf(arr[1]);
                System.out.println("ok, I've deleted this take from array");
                System.out.println(arrayL.get(tempi-1).toString());
                arrayL.remove(tempi - 1);
            }
            else {
                try {
                    if (!userText.equals("list") && !userText.equals("List") && !arr[0].equals("unmark") && !arr[0].equals("mark") && !arr[0].equals("delete")) {
                        if (arr.length > 1 && arr[0].equals("deadline")) {
                            String tempi = arr[1];
                            String arri[];
                            arri = tempi.split("/by ");
                            String temp2 = arri[0];
                            String temp3 = arri[1];
                            Deadline d = new Deadline(temp2, temp3);
                            arrayL.add(d);
                            System.out.println("Added: " + d.toString());
                            try {
                                appendToFile(f.getAbsolutePath(), d.toString() + "\n");
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                        }

                        if (arr.length > 1 && arr[0].equals("event")) {
                            String tempi = arr[1];
                            String arri[];
                            arri = tempi.split("/at ");
                            String temp2 = arri[0];
                            String temp3 = arri[1];
                            Event e = new Event(temp2, temp3);
                            arrayL.add(e);
                            System.out.println("Added: " + e.toString());
                            try {
                                appendToFile(f.getAbsolutePath(), e.toString() + "\n");
                            } catch (IOException i) {
                                System.out.println("Something went wrong: " + i.getMessage());
                            }
                        }
                        if (arr.length > 1 && arr[0].equals("todo")) {
                            String tempi = arr[1];
                            ToDo td = new ToDo(tempi);
                            arrayL.add(td);
                            System.out.println("Added: " + td.toString());
                            try {
                                appendToFile(f.getAbsolutePath(), td.toString() + "\n");
                            } catch (IOException e) {
                                System.out.println("Something went wrong: " + e.getMessage());
                            }
                        }
                        if (arr.length == 1 && arr[0].equals("todo")) {
                            throw new NeoException("sorry todo cannot be empty");
                        }
                        if (!arr[0].equals("todo") && !arr[0].equals("event") && !arr[0].equals("deadline")) {
                            throw new NeoException("Sorry I don't know what that means");
                        }
                    }
                } catch (NeoException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}


