Requirements (not implemented yet): inline code

# Introducing Pixel -- your new chatbot to manage tasks

> “Sometimes it is the people no one can imagine anything of who do the things no one can imagine.”
― Alan Turing ([Source](https://www.goodreads.com/author/quotes/87041.Alan_Turing#:~:text=%E2%80%9CSometimes%20it%20is%20the%20people,things%20no%20one%20can%20imagine.%E2%80%9D&text=%E2%80%9CWe%20can%20only%20see%20a,that%20needs%20to%20be%20done.%E2%80%9D&text=%E2%80%9CI'm%20afraid%20that%20the,by%20some%20in%20the%20future. "Source link"))

This is a greenfield Java project for the NUS Computer Science module CS2103 Software Engineering. <br />

-----

## Pixel frees your mind of having to remember things you need to do.
#### Benefits of using Pixel:
* Text-based
     * You just need to follow the prompts and type down the instructions
         * Very fast!
 * _Easy to use!_ <br />

### All you need to do is,

1. download it from [here](https://www.youtube.com/watch?v=gfHXYKX85jE "hey").
2. double-click it.
3. `add` your tasks.
4. let it manage your tasks for you 😉 <br />

#### And it is FREE! <br />

### Features:
- [ ] Managing tasks (add/ delete/ find tasks)
- [ ] Managing deadlines
- [ ] Marking tasks as done/ unmark tasks
- [ ] **Reminders** (coming soon)

Prerequisites: JDK 11, update Intellij to the most recent version.

If you Java programmer, you can use it to practice Java too. Here's the main method in the class Pixel:

 ``` java
 public static void main(String[] args) {
   Pixel test = new Pixel("C:/!Education/CS2103/gitFolderOne/data/pixel.txt");
   System.out.println(UserInterface.GREETING_MESSAGE + UserInterface.PROMPT_MESSAGE);
   test.run();
 }
 ```
 
 Greeting message:
 ```
Hello! I'm Pixel! 
You can input the following commands 
 todo/ event/ deadline + <task description> + /by or /at + <due> 
 ***date format for due has to be in <yyyy-MM-dd(SPACE)HHmm(24h)> format 
 list -- lists out all the tasks 
 mark <index of task in the list> -- to mark as done 
 unmark <index of task in the list> -- to mark as not done 
 delete <index of task in the list> -- to delete that particular task 
 find <query> -- Find all tasks with description containing query 
 end -- leaves the chatbot and closes the programme 
   Your input: 
   
 ```


------

# Previous Instructions
## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```
