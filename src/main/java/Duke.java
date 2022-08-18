import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void DukeTask() throws DukeException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        List<Task> lst = new ArrayList<>();
        int count=0;
        String str;

        do{
            str = scanner.next(); //This will check for the action word
            if(str.equals("bye")){
                break;
            }else if(str.equals("delete")) {
                System.out.println("Noted. I've removed this task:");
                int index = scanner.nextInt();
                scanner.nextLine();
                Task deleted = lst.get(index-1);
                System.out.println(deleted.toString());
                lst.remove(index-1);
                count--;
                System.out.println("Now you have "+ count + " tasks in the list.");

            }else if(str.equals("deadline")){
                System.out.println("Got it. I've added this task: ");
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
                Task task = new Deadline(description, dateline);
                lst.add(task);
                count++;
                System.out.println(task.toString());
                System.out.println("Now you have "+ count + " tasks in the list.");
            } else if(str.equals("todo")){
                String todoDes = scanner.nextLine();
                if(todoDes.equals("")){
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty ");
                }
                System.out.println("Got it. I've added this task: ");
                Task task = new ToDo(todoDes);
                lst.add(task);
                count++;
                System.out.println(task.toString());
                System.out.println("Now you have "+ count + " tasks in the list.");
            } else if(str.equals("event")){
                System.out.println("Got it. I've added this task: ");
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
                Task task = new Event(description, time);
                lst.add(task);
                count++;
                System.out.println(task.toString());
                System.out.println("Now you have "+ count + " tasks in the list.");
            }
            else if(str.equals("list")){
                System.out.println("Here are the tasks in your list");
                for(int i=0;i<count;i++){
                    System.out.println(i+1 +"."+lst.get(i));
                }
                scanner.nextLine();
            }
            else if(str.equals("unmark")){
                String strnum = scanner.next();
                int num = Integer.valueOf(strnum);
                lst.get(num-1).isDone = false;
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(lst.get(num-1).toString());
                scanner.nextLine();
            }else if(str.equals("mark")){
                String strnum = scanner.next();
                int num = Integer.valueOf(strnum);
                lst.get(num-1).isDone = true;
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(lst.get(num-1).toString());
                scanner.nextLine();
            }
            else{
//                String remain = scanner.nextLine();
//                arr[count] = new Task(str + remain);
//                count++;
//                System.out.println("added: "+str + remain);
                throw new DukeException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

        }  while(!str.equals("bye"));

        if (str.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }

    }

    public static void main(String[] args)  {
          try {
              DukeTask();
          }catch(DukeException e) {
              System.out.println(e.getMessage());
          }
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);


    }
}
