<<<<<<< HEAD
import java.io.*;
import java.nio.file.Path;
=======
import java.text.ParseException;
import java.text.SimpleDateFormat;
>>>>>>> branch-Level-8
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Duck {
    /**
     * main function for running the Duck bot
     * Works by parsing through the user input and splitting it at first by whitespace
     * This allows the bot to read the command and go down the appropriate case
     * It also allows the program to keep track of the arguments the user gives
     * It also handles possible invalid inputs given by the user
     * **/
    public static void main(String[] args) throws IOException {
        java.nio.file.Path path = java.nio.file.Paths.get("data");
        File duckTxt = new File(String.valueOf(path), "duck.txt");
        if (!java.nio.file.Files.exists(Path.of(path + "duck.txt"))) {
            duckTxt.getParentFile().mkdirs();
            duckTxt.createNewFile();
        }

        ArrayList<Todo> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(duckTxt))) {
            String line;
            String[] readerArray;
            while ((line = reader.readLine()) != null) {
                readerArray = line.split(";");
                switch (readerArray[0]) {
                    case ("T"):
                        list.add(new Todo(readerArray[1], readerArray[2].equals("1")));
                        break;
                    case ("D"):
                        list.add(new Deadline(readerArray[1], readerArray[3].equals("1"), readerArray[2]));
                        break;
                    case ("E"):
                        list.add(new Event(readerArray[1], readerArray[3].equals("1"), readerArray[2]));
                        break;
                }
            }
        }
        System.out.println("Hello! Got any grapes?");
        Scanner scanner = new Scanner(System.in);
        String word = "";
<<<<<<< HEAD
        FileWriter writer = new FileWriter(duckTxt, false);
        while (!word.equals("bye")) {
            try {
                word = scanner.nextLine();
                if(word.contains(";")) throw new UnallowedCharacterException(";");
                if (word.toUpperCase().equals("BYE")) break;
                if (word.toUpperCase().equals("LIST")) {
                    if (list.size() == 0) {
                        throw new IndexOutOfBoundsException();
                    }
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println(i + 1 + ". " + list.get(i));
                    }
                } else {
                    String[] arr = word.split(" ", 2);
                    String command = arr[0];
                    String arguments = arr[1];
                    switch (command.toUpperCase()) {
                        case "TODO":
                            Todo newTodo = new Todo(arguments, false);
                            list.add(newTodo);
                            System.out.println("Added todo " + newTodo + " Quack!");
                            break;
                        case "DEADLINE":
                            String[] deadlineArgs = arguments.split("/by");
                            Deadline newDeadline = new Deadline(deadlineArgs[0].trim(), false, deadlineArgs[1].trim());
                            list.add(newDeadline);
                            System.out.println("Added new Deadline " + newDeadline + " Quack!");
                            break;
                        case "EVENT":
                            String[] eventArgs = arguments.split("/at");
                            Event newEvent = new Event(eventArgs[0].trim(), false, eventArgs[1].trim());
                            list.add(newEvent);
                            System.out.println("Added new Event " + newEvent + " Quack!");
                            break;
                        case "MARK":
                            Todo currentUnmarkedItem = list.get(Integer.parseInt(arguments) - 1);
                            currentUnmarkedItem.completeTask();
                            System.out.println("Quack, marked! " + currentUnmarkedItem);
                            break;
                        case "UNMARK":
                            Todo currentMarkedItem = list.get(Integer.parseInt(arguments) - 1);
                            currentMarkedItem.unCompleteTask();
                            System.out.println("Quack, unmarked! " + currentMarkedItem);
                            break;
                        case "DELETE":
                            Todo t = list.remove(Integer.parseInt(arguments) - 1);
                            System.out.println(t + "\n Is gone!! Quack!");
                            break;
                        default:
                            System.out.println("Quack!?! What does that even mean!?!?!");
                            break;
                    }

                }
            } catch (ArrayIndexOutOfBoundsException a) {
=======
        ArrayList<Todo> list = new ArrayList<>();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
        while (!word.equals("bye")) {
            try {
            word = scanner.nextLine();
            if (word.toUpperCase().equals("BYE")) break;
            if (word.toUpperCase().equals("LIST")) {
                if (list.size() == 0){
                    throw new IndexOutOfBoundsException();
                }
                for (int i = 0; i < list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else {
                String[] arr = word.split(" ", 2);
                String command = arr[0];
                String arguments = arr[1];
                switch (command.toUpperCase()) {
                case "TODO":
                    Todo newTodo = new Todo(arguments);
                    list.add(newTodo);
                    System.out.println("Added todo " + newTodo + " Quack!");
                    break;
                case "DEADLINE":
                    String[] deadlineArgs = arguments.split("/by");
                    Deadline newDeadline = new Deadline(deadlineArgs[0], dateFormatter.parse(deadlineArgs[1]));
                    list.add(newDeadline);
                    System.out.println("Added new Deadline " + newDeadline + " Quack!");
                    break;
                case "EVENT":
                    String[] eventArgs = arguments.split("/at");
                    Event newEvent = new Event(eventArgs[0], dateFormatter.parse(eventArgs[1]));
                    list.add(newEvent);
                    System.out.println("Added new Event " + newEvent + " Quack!");
                    break;
                case "MARK":
                    Todo currentUnmarkedItem = list.get(Integer.parseInt(arguments) - 1);
                    currentUnmarkedItem.completeTask();
                    System.out.println("Quack, marked! " + currentUnmarkedItem);
                    break;
                case "UNMARK":
                    Todo currentMarkedItem = list.get(Integer.parseInt(arguments) - 1);
                    currentMarkedItem.unCompleteTask();
                    System.out.println("Quack, unmarked! " + currentMarkedItem);
                    break;
                case "DELETE":
                    Todo t = list.remove(Integer.parseInt(arguments) - 1);
                    System.out.println(t + "\n Is gone!! Quack!");
                    break;
                default:
                    System.out.println("Quack!?! What does that even mean!?!?!");
                    break;
                }
            }
        }
            catch (ArrayIndexOutOfBoundsException a) {
>>>>>>> branch-Level-8
                if (word.toUpperCase().contains("TODO") ||
                        word.toUpperCase().contains("DEADLINE") ||
                        word.toUpperCase().contains("EVENT")) {
                    System.out.println("Quack!!!!! " + word.toUpperCase() + " Arguments are missing!");
                } else {
                    System.out.println("Speak properly! Quack!");
                }
            } catch (NumberFormatException n) {
                System.out.println("Invalid Arguments! Dummy!");
            } catch (IndexOutOfBoundsException i) {
                System.out.println("Item does not exist!! Quack!");
<<<<<<< HEAD
            } catch (UnallowedCharacterException e) {
                System.out.println("Character: "+ e.getMessage() + " is not allowed! Quack!!");
=======
            } catch (ParseException e) {
                System.out.println("Wrong date time format!!!");
>>>>>>> branch-Level-8
            }
        }
        for(Todo t : list){
            t.writeToFile(writer);
        }
        writer.close();
        System.out.println("Quack!");
    }

}
