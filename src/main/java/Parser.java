import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Parser {
    private TaskList tasklist;
    private Ui bot;
    private Storage storage;

    public Parser(TaskList tasklist,Ui bot,Storage storage){
        this.tasklist = tasklist;
        this.bot = bot;
         this.storage = storage;
    }


    public void readInput() throws DukeException, IOException {
        Scanner scanner = new Scanner(System.in);
        String str;
        do{
            str = scanner.next(); //This will check for the action word
            if(str.equals("bye")){
                break;
            }else if(str.equals("delete")) {
                int index = scanner.nextInt();
                scanner.nextLine();
                String deleted = tasklist.removeTask(index);
                bot.removeTask(tasklist.size(), deleted);
            }else if(str.equals("deadline")){
                String description = "";
                String dateline="";
                while(scanner.hasNext()){
                    String temp = scanner.next();
                    if(temp.equals("/by")){
                        break;
                    }
                    description = description + temp +" ";
                }
                dateline = scanner.nextLine();
                LocalDate d1 = LocalDate.parse(dateline.substring(1));
                Task task = new Deadline(description, d1);

                tasklist.addTask(task.toString()); //adding the task to the tasklist
                int total = tasklist.size();
                bot.addTask(total,task); //printing out the ui
            } else if(str.equals("todo")){
                String todoDes = scanner.nextLine();
                if(todoDes.equals("")){
                    bot.displayError();
                }
                Task task = new ToDo(todoDes);
                tasklist.addTask(task.toString()); //adding the task to the tasklist
                int total = tasklist.size();
                bot.addTask(total,task); //printing out the ui
            } else if(str.equals("event")){
                String description = "";
                String time="";
                while(scanner.hasNext()){
                    String temp = scanner.next();
                    if(temp.equals("/at")){
                        break;
                    }
                    description = description + temp +" ";
                }
                time = scanner.nextLine();
                LocalDate d1 = LocalDate.parse(time.substring(1));
                Task task = new Event(description, d1);
                tasklist.addTask(task.toString()); //this will add the task to the tasklist
                int total = tasklist.size();
                bot.addTask(total,task); //this will print out the task
            }
            else if(str.equals("list")){ //To print out the list, we read from the file first, then read from the current tasks
                bot.printTasks(tasklist.oldTasks,tasklist.newTasks);
                scanner.nextLine();
            }
            else if(str.equals("unmark")){
//                String strnum = scanner.next();
//                int num = Integer.valueOf(strnum);
//                newTasks.get(num-1).isDone = false;
//                bot.markTask(false);
//                System.out.println(newTasks.get(num-1).toString());
//                scanner.nextLine();
            }else if(str.equals("mark")){
//                String strnum = scanner.next();
//                int num = Integer.valueOf(strnum);
//                newTasks.get(num-1).isDone = true;
//                bot.markTask(true);
//                System.out.println(newTasks.get(num-1).toString());
//                scanner.nextLine();
            }
            else{
                storage.addTasks("data/Duke2.txt",tasklist.newTasks);
                bot.displayError();
            }

        }  while(!str.equals("bye"));
        if (str.equals("bye")) {
            storage.addTasks("data/Duke2.txt",tasklist.newTasks); //need to modify add task
            bot.goodBye();
        }
   }

}
