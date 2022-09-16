# User Guide
***DukeBruv*** is a **desktop app** for **managing tasks**, optimised for use via 
*Command Line Interface* (CLI) while still having the benefits of a
*Graphical User Interface* (GUI). ***DukeBruv*** is a quick and easy way
to manage your tasks and it can be even faster than traditional
GUI apps.

---
## Quickstart
1. Ensure you have Java 11 installed in your computer.
2. Download the latest Duke.jar from [here - add link]()
3. Copy the file to the folder you want to use as the *home folder*
for your DukeBruv.
4. Double-click the file to start the app. The GUI as shown should appear
in a few moments.
   (add pic below)
5. Type the command of your choice in the textbox in the GUI and press
Enter to execute it. You can start by saying hi!
6. Refer to the Features tab below for the full list of commands.

---
## Features 


### Getting help: `help`

This command will give you a link which will lead you here to this page! You can then look at the list of commands to clear your queries.

### Marking your tasks: `mark` or `unmark`

To mark or unmark, simply type 'mark' or 'unmark' followed by
the index of the item in the list you want to mark or unmark.
e.g. mark 2\n

### Listing your tasks: `list`

To list out the items, simply type 'list'.\
e.g `list`

### Removing tasks: `delete`

To remove an item from your list, simply type 'delete' followed by
the index of the item in the list you want to remove.\
e.g. `delete 3`

### Finding tasks: `find`

To find the items in the list with a particular keyword,
simply type 'find' followed by the keyword.\
e.g. `find books`, this command will return you all the items
with the keyword `books` in it

### Tasks with a deadline: `deadline`

For tasks of 'deadline' type, meaning tasks with a deadline,
simply type 'deadline' followed by a space then your task.
Then add the phrase '/by' followed by your deadline.\
e.g. `deadline read books /by Friday 3pm.`

### Tasks with a timing: `event`

For tasks of 'event' type, meaning tasks with a timing,
simply type 'event' followed by a space then your task.
Then add the phrase '/at' followed by your timing.\
e.g. `event book signing /at Monday 2pm - 4pm.`

### Other tasks with no timings or deadlines: `todo`

For tasks of 'todo' type, meaning tasks with no time attribute involved,
simply type 'todo' followed by a space then your task.\
e.g. `todo read books`

### Greetings `hi` or `bye`

You can also say 'hi' or 'bye' to me because I'm friendly like that.

### Editing your data file

DukeBruv data are saved as a txt file [JAR file location]/data/duke.txt. 
Advanced users are welcome to update data directly by editing that data file.

---
##FAQ

Q: How do I transfer my data to another Computer?\
A: Install the app in the other computer and overwrite the empty data file it creates 
with the file that contains the data of your previous AddressBook home folder.

---
## Command Summary

| Action   | Format                              | Examples                         |
|----------|-------------------------------------|----------------------------------|
| help     | -                                   | `help`                           |
| mark     | mark [index]                        | `mark 3`                         |
| unmark   | unmark [index]                      | `unmark 2`                       |
| list     | -                                   | `list`                           |
| delete   | delete [index]                      | `delete 1`                       |
| find     | find [keyword]                      | `find books`                     |
| deadline | deadline [task] /by<br/> [deadline] | `deadline return books /by 3pm`  |
| event    | event [task] /at<br/> [timing]      | `event book signing /at 2pm-4pm` |
| todo     | todo [task]                         | `todo read books`                |
| hi       | -                                   | `hi`                             |
| bye      | -                                   | `bye`                            |
