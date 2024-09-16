# User Guide

**Ditto!** is an extension of Duke which helps you keep track of your daily tasks.

To begin the application, you can either:

1. Double-click on the JAR file (might not work for MacOS), or
2. Navigate to the directory where the JAR file is stored and run `java -jar Ditto.java`

## Features 

You can perform the following operations: 

1. [Add](https://nseah21.github.io/ip/#feature-1) tasks as `todo`, `event`, or `deadline`. 
2. [Delete](https://nseah21.github.io/ip/#feature-2) tasks 
3. [List](https://nseah21.github.io/ip/#feature-3) tasks
4. [Mark](https://nseah21.github.io/ip/#feature-4) tasks as done
5. [Mark](https://nseah21.github.io/ip/#feature-5) tasks as undone
6. [Search](https://nseah21.github.io/ip/#feature-6) for tasks
7. [Postpone](https://nseah21.github.io/ip/#feature-7) deadlines *(new!)*
7. [Close](https://nseah21.github.io/ip/#feature-8) the GUI 

### Feature 1

A `todo` is an open task which only requires a description. The information you put in this description is flexible and totally up to you!

An `event` is a task which will take place at a certain place or time. 

A `deadline` is a task with a due date. 

You can add tasks with the `todo`, `event`, or `deadline` command! 

### Feature 2

Cleared a task and want to remove it from the list? Just `delete` the task!

### Feature 3

You can `list` your tasks to see all your commitments at a glance!

### Feature 4

If you have completed a task, but want it to persist in your list, just `mark` the task as done and go about your day!

### Feature 5

Missed out on one part of a task you already cleared? Don't worry, just mark the task as undone with `unmark`.

### Feature 6

Have a ton of things to do? You can `find` your tasks easily by specifying a keyword! Stream-based searches are *really* fast!

### Feature 7

Drowning in deadlines? Just do yourself a favour and `postpone` them...

### Feature 8

Exit the application from the text box! No need for your mouse... just say `bye`!


## Usage

### `todo` - Add a todo to your list

Creates a new `todo` and adds it to your list. 

Format: `todo <description>`

Example: `todo visit the doctor`

### `event` - Add an event to your list

Creates a new `event` and adds it to your list.

Format: `event <description> /at <place or time>`

Example: `event concert /at Esplanade`

### `deadline` - Add a deadline to your list

Creates a new `deadline` and adds it to your list.

Format: `deadline <description> /by <datetime in yyyy-mm-dd hhmm format>`

Example: `deadline code review /by 2022-09-16 2200`

### `delete` - Deletes a task from your list

Deletes a task from your list using the task id number.

Format: `delete <task id>`

Example: `delete 2` will delete the second task in your list

### `list` - List all tasks in the list

Lists all the tasks in the list with their corresponding id and completion status. 

Format: `list`

Expected output: 

```
1. [T][] visit the doctor
2. [E][] concert (at: Esplanade)
3. [D][] code review (by: Sep 16 2022 10.00pm)
```

### `mark` - Marks a task in the list as done

Sets the status of the task as done. 

Format: `mark <task id>`

Example: `mark 2`

Expected output when calling `list`:

```
1. [T][] visit the doctor
2. [E][X] concert (at: Esplanade)
3. [D][] code review (by: Sep 16 2022 10.00pm)
```

### `unmark` - Marks a task in the list as not done

Sets the status of the task as not done.

Format: `unmark <task id>`

Example: `unmark 2`

Expected output when calling `list`:

```
1. [T][] visit the doctor
2. [E][] concert (at: Esplanade)
3. [D][] code review (by: Sep 16 2022 10.00pm)
```

### `find` - Finds tasks in your list that match the input keyword

Searches the task list for matching tasks based on the specified keyword.

Format: `find <keyword>`

Example: `find co`

Expected output:

```    
2. [E][] concert (at: Esplanade)
3. [D][] code review (by: Sep 16 2022 10.00pm)
```

### `postpone` - Postpones a deadline in the list to a later date

Updates the due date of the specified task with a later date.

Format: `postpone <task id> /to <later date>`

Example: `postpone 3 /to 2022-10-01 2300`

Expected output when calling `list`:

```    
1. [T][] visit the doctor
2. [E][] concert (at: Esplanade)
3. [D][] code review (by: Oct 1 2022 11.00pm)
```

### `bye` - Exits the application

Terminates the application with a goodbye message.

Format: `bye`

Expected output:

```    
Bye! Hope to see you again soon!
```

And there you go! You are ready to use **Ditto!**

### Acknowledgements 

The initial repository for this project was forked from [this repo](https://github.com/nus-cs2103-AY2223S1/ip). Credits go to *damithc* and *j-lum* for the base code.

The GUI for **Ditto!** was also implemented with heavy reference to the JavaFX tutorial on the [SE education website](https://se-education.org/guides/tutorials/javaFx.html).  