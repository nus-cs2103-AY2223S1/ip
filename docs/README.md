# User Guide
Duke is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the 
benefits of a Graphical User Interface (GUI). If you can type fast, Duke can get your task management done faster than 
traditional GUI apps.

## Features
You can add and manage three different types of tasks in Duke.

### Todo

Todos allow you to add tasks without any date attached. 

### Deadline

Deadlines allow you to add tasks that need to be done before a specific date.

### Event

Events allow you to add tasks that occur on a specific date. 

### List

You can view all your tasks in one place and sort them by date. 
Moreover, your tasks are saved even after you exit the app, and will be reloaded when you use the app again.     

## Usage

### `todo` - Adding a todo

Adds a todo to Duke. 

#### Example usage: 

`todo homework`

#### Expected outcome:

A todo is added to the list. 

```
I've added this task:
[T][] homework
```

<br/><br/>
### `deadline` - Adding a deadline

Adds a deadline to Duke.

- The keyword `/by` must be provided
- The provided date must be in the format `dd-mm-yyyy`

#### Example usage:

`deadline assignment /by 20-09-2022`

#### Expected outcome:

A deadline is added to the list.

```
I've added this task:
[D][] assignment (by: 20 Sep 2022)
```

<br/><br/>
### `event` - Adding an event

Adds an event to Duke.

- The keyword `/at` must be provided
- The provided date must be in the format `dd-mm-yyyy`

#### Example usage:

`event lecture /at 10-09-2022`

#### Expected outcome:

An event is added to the list.

```
I've added this task:
[E][] lecture (at: 10 Sep 2022)
```

<br/><br/>
### `list` - Listing all tasks

Displays all tasks saved in Duke. 

#### Example usage:

`list`

#### Expected outcome:

All tasks in the list are listed. 

```
Here are the tasks in your list:
1. [T][] homework
2. [D][] assignment (by: 20 Sep 2022)
3. [E][] lecture (at: 10 Sep 2022)
```

<br/><br/>
### `sort` - Sorting the task list

Sorts all tasks in the list by date. Todos are sorted last.

#### Example usage:

`sort`

#### Expected outcome:

All tasks in the list are sorted by date.

```
Here are the tasks in your list, sorted:
1. [E][] lecture (at: 10 Sep 2022)
2. [D][] assignment (by: 20 Sep 2022)
3. [T][] homework
```

<br/><br/>
### `mark` - Marking a task as done

Marks a task as done in Duke. The completed task will remain in the list. 

#### Example usage:

`mark 2`

#### Expected outcome:

The number 2 task in the list is marked as done.

```
Nice! I've marked this task as done:
[D][X] assignment (by: 20 Sep 2022)
```
<br/><br/>

### `unmark` - Marking a task as not done

Marks a task as not done yet in Duke.

#### Example usage:

`unmark 2`

#### Expected outcome:

The number 2 task in the list is marked as not done yet.

```
Okay, I've marked this task as not done yet:
[D][] assignment (by: 20 Sep 2022)
```
<br/><br/>

### `delete` - Deleting a task

Deletes a task from Duke. 

#### Example usage:

`delete 3`

#### Expected outcome:

The number 3 task in the list is deleted.  

```
I've removed this task:
[E][] lecture (at: 10 Sep 2022)
```

<br/><br/>
### `find` - Finding tasks

Finds all tasks containing the given keyword.

#### Example usage:

`find homework`

#### Expected outcome:

All tasks containing the word "homework" are listed. 

```
Here are the matching tasks in your list:
[T][] homework
```

<br/><br/>
### `bye` - Exiting Duke

Closes Duke. 

#### Example usage:

`bye`

#### Expected outcome:

Duke is closed.