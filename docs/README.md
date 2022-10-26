# User Guide


# DukePro

> “Your mind is for having ideas, not holding them.” – David Allen ([source](https://dansilvestre.com/productivity-quotes))
#### DukePro frees your mind of having to remember things you need to do. It's,
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use
#### All you need to do is,
1. download it from [here](https://nus-cs2103-ay2223s1.github.io/website/schedule/week4/project.html).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you :smiley:
## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

## Getting started

1. Ensure you have Java `11` or above installed.


2. Download the latest `duke.jar` from [here](https://github.com/benjytan45678/ip/releases).


3. Copy the file to the folder you want to use as the home folder for Duke.


4. Double-click the file to start the application.


5. Enter a command in the command box to execute it.

## Commands

### Bye

Exits out of the programme

Format: `bye`




### Mark

Marks a task as completed

Format: `mark [task number]` where task number is an integer and represents position of task in the list

Example:

![mark](mark.png)
### Unmark

Unmark a task as uncompleted

Format: `unmark [task number]` where task number is an integer and represents position of task in the list

Example:

![unmark](unmark.png)
### List

Shows the list of tasks recorded by Duke

Format: `list`

Example:

![list](list.png)

### Delete

Deletes the task from the list

Format: `delete [task number]` where task number is an integer and represents position of task in the list

Example:

![delete](delete.png)
### Todo

Adds a todo task into the list

Format: `todo [task name]`

Example:

![todo](todo.png)

### Deadline

Adds a task with a deadline into the list

Format: `deadline [task name] /by [deadline]` where deadline is in YYYY-MM-DD (date) HH-MM (time) format

Example:

![deadline](deadline.png)
### Event

Adds an event of a certain timing into the list

Format: `deadline [task name] /at [timing]` where timing is in YYYY-MM-DD (date) HH-MM (time) format

Example:

![event](event.png)
### Find

Finds a list of tasks which contains the keyword

Format: `find [keyword]`

Example:

![find](find.png)

### Add contact

Add a contact into a list of contacts stored by Duke

Format: `addContact [name] [handphone number]`

Example:

![addContact](addContact.png)

### Delete contact

Delete a contact from a list of contacts stored by Duke

Format: `deleteContact [name]`

Example:

![deleteContact](deleteContact.png)

### Contact List

Shows the list of contacts stored by Duke

Format: `contactList`

Example:

![contactList](contactList.png)

### Find contact

Format: `findContact [name]`

Example: 

![findContact](findContact.png)


## Product Snapshot
![Ui](Ui.png)

