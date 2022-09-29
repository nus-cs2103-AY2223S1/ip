# User Guide
Duke is your personal assistant that helps you to keep track of various things. It is optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your contact management tasks done faster than traditional GUI apps!

## Features 
Duke has several features that make it a great task tracker application. These features include:
1. Adding tasks (3 types of tasks: todo, deadline, event)
2. Deleting tasks
3. Marking tasks as done
4. Marking tasks as undone
5. Finding tasks
6. Listing tasks
7. Exiting the application

## Feature 1: Adding tasks
Duke allows you to add 3 types of tasks: todo, deadline, event.

## Feature 2: Deleting tasks
Duke allows you to delete tasks.

## Feature 3: Marking tasks as done
Duke allows you to mark tasks as done.

## Feature 4: Marking tasks as undone
Duke allows you to mark tasks as undone.

## Feature 5: Finding tasks
Duke allows you to find tasks in your list based on search keywords.

## Feature 6: Listing tasks
Duke allows you to list all tasks in your list.

## Feature 7: Exiting the application
Duke allows you to exit the application.

## Usage
### Adding tasks
Duke supports 3 types of tasks: todo, deadline, event. These tasks can be added to the task list.

#### Adding a todo task
A todo task is a task that has a description. It can be added to the task list using the following command:
```
todo <description>
```
Example of usage: 
```
todo eat apple
```

Expected outcome:
```
Got it. I've added this task:
[T][✘] eat apple
Now you have 1 tasks in the list.
```

#### Adding a deadline task
A deadline task is a task that has a description and a deadline. It can be added to the task list using the following command:
```
deadline <description> /by <deadline>
```
Example of usage: 
```
deadline return book /by 2020-02-20
```

Expected outcome:
```
Got it. I've added this task:
[D][✘] return book (by: Feb 20 2020)
Now you have 2 tasks in the list.
```

#### Adding an event task
An event task is a task that has a description and an event date. It can be added to the task list using the following command:
```
event <description> /at <event date>
```
Example of usage: 
```
event CS2103T Meeting /at 2020-02-20
```

Expected outcome:
```
Got it. I've added this task:
[E][✘] CS2103T Meeting (at: Feb 20 2020)
Now you have 3 tasks in the list.
```
### Deleting tasks
A task can be deleted from the task list using the following command:
```
delete <task number>
```
Example of usage: 
```
delete 1
```

Expected outcome:
```
Noted. I've removed this task:
[T][✘] eat apple
Now you have 2 tasks in the list.
```

### Marking tasks as done
A task can be marked as done using the following command:
```
done <task number>
```
Example of usage: 
```
done 1
```

Expected outcome:
```
Nice! I've marked this task as done:
[D][✓] return book (by: Feb 20 2020)
```

### Marking tasks as undone
A task can be marked as undone using the following command:
```
undone <task number>
```
Example of usage: 
```
undone 1
```

Expected outcome:
```
Noted. I've marked this task as undone:
[D][✘] return book (by: Feb 20 2020)
```

### Finding tasks
Tasks can be found using the following command:
```
find <keyword>
```
Example of usage: 
```
find Meeting
```

Expected outcome:
```
Here are the matching tasks in your list:
1. [E][✘] CS2103T meeting (at: Feb 20 2020)
```

### Listing tasks
Tasks can be listed using the following command:
```
list
```
Example of usage: 
```
list
```

Expected outcome:
```
Here are the tasks in your list:
1. [D][✘] return book (by: Feb 20 2020)
2. [E][✘] CS2103T Meeting (at: Feb 20 2020)
```

### Exiting the application
The application can be exited using the following command:
```
bye
```

Expected outcome:
```
Bye. Hope to see you again soon!
```
