# ip
Duke

## Features

### ToDo, Event, Deadline
Types of Tasks that Duke supports. It Deadline and Event types can store deadlines

### Mark, Unmark, Update, Delete, List
Types of functions users can use to arrange their tasks

### GUI
An interact platorm for the users to interact with Duke

## Usage

```todo``` - Adds a ToDo task
A ToDo task will be created.

User input command:
Format to be specified: todo {task description}
```
todo borrow book
```
Expected outcome:
ToDo Task added to list and shows no of tasks in list


```deadline``` - Adds a Deadline task
A Deadline task will be created.

User input command:
Format to be specified: deadline {task description} /by {date}
```
deadline return book /by 2/12/2019 1800
```
Expected outcome:
Deadline Task added to list and shows no of tasks in list


```event``` - Adds a Deadline task
An Event task will be created.

User input command:
Format to be specified: event {task description} /by {date}
```
event return book /by 2/12/2019 1800
```
Expected outcome:
Event Task added to list and shows no of tasks in list


Expected date time format:
dd/mm/yyyy hhmm (eg: 25/11/2022 0900)


```mark``` - marks given task as done
Command would mark task as done

Example expected input:
```
mark 1
```

Expected outcome:
Task shown as done in list

```unmark``` - unmarks given task as done
Command would unmark task as done

Example expected input:
```
unmark 1
```

Expected outcome:
Task shown as not done in list


```delete``` - delete given task as done
Command would delete task

Example expected input:
```
delete 1
```

Expected outcome:
Task removed form list


```find``` - Finda all tasks specified
Command would find all task that contains the keyword specified

Example expected input:
```
find Anime
```

Expected outcome:
Prints out all task that contains the keyword


```list``` - Shows all task
Command would show all task user has in Duke

Exmaple expected input:
```
list
```

Expected outcome:
Prints out all the task in the list


```bye``` - Exits the program
Command exit user from the program

Exmaple expected input:
```
bye
```

Expected outcome:
exits the program and stores the output into duke.txt
