# User Guide
#####   DukeSIU is a desktop app used to keep track of tasks and manage these tasks for you, optimized for use via **Command Line Interface** (CLI) while still having the benefit of a **Graphical User Interface** (GUI).
------------
###### - Quick start 
###### - Features
###### - FAQ
###### - Command Summary
------------
## Quick start
##### 1. Ensure you have Java 11 or above installed in your Computer.
##### 2. Download DukeSIU
##### 3. Run DukeSIU
##### 4. GUI like the one below should appear 
[![Ui](https://bahamas20.github.io/ip/Ui.png "Ui")](https://bahamas20.github.io/ip/Ui.png "Ui")
##### 5. Type a command and let DukeSIU do the rest of the job for you 🫡
##### 6. Examples of commands can be found below in the Feature section

------------
## Features
### View Help: 

    help
    
Shows a message with a link to the user guide.

### Exit program:


    bye
    
Shows a bye message and ends the program.

### View all tasks:


    list
Shows a list of all tasks currently

### Finishing a task:


    mark <task number that needs to be marked>
	
Follow the command with the task number to mark a certain task as finished.
Example:


    mark 3

### Task yet to be done:


    unmark <task number that still needs to be unmarked>
Follow this command with the task number you want to unmark as it is still not finished.
Example:


    unmark 4
    
### Remove Task:


    delete <task number that you want to remove>
Follow this command with the task number you want to remove from the task list.
Example:


    delete 2

### Add ToDo task:


    todo <task name>
Follow this command with the name of the task that you want to add to task list. 
Todo tasks only have task name assigned to it.
Example: adding a todo read book task


    todo read book
    
### Add Deadline task:


    deadline <task name> /by <YYYY-MM-DD>
Follow this command with the name of the task followed by the "/by" string and then 
a timing to finish this task by in the YYYY-MM-DD format.
Example: adding a write book deadline task


    deadline write book /by 2022-09-14

### Add Event task:


    event <task name> /at <Time>
Follow this command with the name of the task followed by the "/at" string and then
a timing in any format.
Example: adding a project meeting event task


    event project meeting /at August 3 2022 3.30pm

### Search for Task:


    find <keyword>
Follow this command with the keyword you are looking for in the list of tasks.
DukeSIU will return all tasks that contain this keyword.
Example: finding all tasks that is related to book


    find book
	

------------



## FAQ

**Q**: How do I clear all tasks already in data ?
**A**: Locate the duke.txt file in data/duke.txt and manually clear all previous data

**Q**: DukeSIU GUI does not run or appear 
**A**: Make sure Java 11 or above is installed, you can check your java version by 


    java -version
    
inputting this in terminal

------------
## Command Summary
|  Command | Format   | Example  |
| ------------ | ------------ | ------------ |
|  help | help  | -  |
| bye  | bye  | -  |
| list  |  list | -  |
|  mark | mark <task number that needs to be marked>  | mark 3   |
| unmark  | unmark <task number that still needs to be unmarked>  | unmark 4  |
|  delete | delete <task number that you want to remove>   | delete 2  |
| todo  | todo <task name>  | todo read book  |
|deadline   |  deadline <task name> /by <YYYY-MM-DD> | deadline write book /by 2022-09-14  |
|  event | event <task name> /at <Time>  | event project meeting /at August 3 2022 3.30pm  |
|  find | find <keyword>  |  find book |




