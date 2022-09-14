# User Guide
Dukey is a desktop chatbot app for managing and storing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI).

- [Quick Start]()
- [Features]()
  - [Adding a todo task : ```todo```]()
  - [Adding a deadline Task: ```deadline```]()
  - [Adding an event Task: ```event```]()
  - [Marking a task: ```mark```]()
  - [Unmarking a task: ```unmark```]()
  - [Deleting a task: ```delete```]()
  - [Finding a task by description: ```find```]()
  - [Listing all tasks: ```list```]()
  - [Exiting the program: ```bye```]()
  - [Saving the data]()
- [FAQ]()
- [Command Summary]()

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest dukey.jar from [here]().
3. Copy the file to the folder you want to use as the home folder for your AddressBook.
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.  
![start image](./images/start.png)
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:
    - list : Lists all the tasks.
    - deadline submit assignment /by 25 Sep 2022 : Adds a deadline task submit assignment (by:25 Sep 2022) to the list.
    - event family outing /at 30 Sep 2022 18:30 : Adds an event task family outing (at: 30 Sep 2022 18:30)
    - mark 2 : Marks the task at index 1 on the list as done.
    - delete 1 : Deletes the first at index 2 on the list.
    - find cat : Finds a;; task with the matching keyword cat
    - bye : Exits the app.
6. Refer to the Features below for details of each command.

## Features 
Notes about the command format:  
Words in ```{curly brackets}``` are the parameters to be supplied by the user.  
e.g. in ```todo {description}``` , ```{description}``` is a parameter which can be used as ```todo homework```

### Viewing help: help
Displays a list of available commands with examples.

### Adding a todo task: ```todo```
Adds a todo task to the task list.  
Format: ```todo {description} ```   
Example: ```todo buy bananas```

### Adding a deadline task: ```deadline```
Adds a deadline task to the task list.    
Format: ```deadline {description} /by {dd-MMM-yyyy HH:mm}```  
Example: ```deadline reflection 1 /by 13-Sep-2022 18:00```

### Adding an event task: ```event```
Adds a deadline task to the task list.  
Format: ```event {description} /at {dd-MMM-yyyy HH:mm}```  
Example: ```event project meeting /at 18 Sep 2022 19:00```

### Marking a task: ```mark```
Marks a task in the list as completed.  
Format: ```mark {index}``` Example: ```mark 2``` marks the task at index 2 of the list    
Expected outcome:
```
[T][ ] buy apples
[E][X] Team meeting (at: 2022/08/22 06.00PM)
```
```[X]```indicates a completed task

### Unmarking a task: ```unmark```
Marks a task in the list as incompleted.
Format: ```unmark {index}``` Example: ```unmark 2``` unmarks the task at index 2 of the list  
Expected outcome:
```
[T][ ] buy apples
[E][ ] Team meeting (at: 2022/08/22 06.00PM)
```
```[ ]``` indicates a incompleted task 

### Deleting a task: ```delete```
Deletes a task from the task list.
Format: ```delete {index}```   
Example: ```delete 2``` deletes the task at index 2 of the list

### Finding a task by description: find
Finds all tasks matching keyword.  
Format: ```find {keyword}```

❗ All task description with the matching sequence of characters will be returned. e.g. ```ta``` will match with ```presentation``` and ```task```.  
❗ The search is case-insensitive. e.g. ```TASK``` will match ```task```.  
Example: ```find TASK```  
![find image](./images/find.png)

### Listing all tasks: list
Lists all the tasks in the task list.  
Format: list

### Exiting the program: bye
Exits the program immediately.  
Format: bye

### Saving the data
Dukey saves data automatically in the hard disk after any valid command that changes the data. There is no need to save manually.

## FAQ
Q: How do I transfer my data to another Computer?  
A: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Dukey chatbot located at ```[JAR file location]/data/tasks.txt```.

## Command Summary
| Action | Format, Example |
| -------| ----------------|
| Help   | ```help```
| Todo   | ```todo {description}```  e.g., ```todo buy pears ```
| Deadline | ```deadline {description} /by {dd-MMM-yyyy HH:mm}```  e.g., ```deadline reflection 1 /by 25-Sep-2022 16:00```
| Event | ```event {description} /at {dd-MMM-yyyy HH:mm}```  e.g., ```event project meeting /at 15-Dec-2022 20:00```
| Mark | ```mark {index}```  e.g., ```mark 1```
| Unmark | ```unmark {index}```  e.g., ```unmark 1```
| Delete | ```delete {index}```  e.g., ```delete 1```
| Find | ```find {keyword}```  e.g., ```find task```
| List | ```list```
| Exit | ```bye```
