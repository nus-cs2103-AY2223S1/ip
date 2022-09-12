# User Guide

## Quick Start
1. Ensure you have Java `11` installed in your Computer.
2. Download the latest from [here](https://github.com/craeyeons/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your Jean.
4. Double-click the file to start Jean.

## Features 

### todo

Adds a todo task into the list.

Format: 

`todo TaskDescription`

Example of usage:

`todo CS2103T ip`

Expected outcome:

The todo task is added to the list.

```
    added / ajouté:
        [T][] CS2103T ip
    You now have 1 task(s)!
    Vous avez 1 tâche(s)!
```

### deadline

Adds a deadline task into the list.

Format:

`deadline TaskDescription /by YYYY-MM-DD HHmm`

Example of usage:

`deadline assignment1 /by 2022-09-15 2100`

Expected outcome:

The deadline task is added to the list.

```
    added / ajouté:
        [D][] assignment1 (by: Sep 15 2022 21:00)
    You now have 1 task(s)!
    Vous avez 1 tâche(s)!
```

### event

Adds a event task into the list.

Format:

`event TaskDescription /at time`

Example of usage:

`event meeting /at tomorrow night`

Expected outcome:

The event task is added to the list.

```
    added / ajouté:
        [E][] assignment1 (at: tomorrow)
    You now have 1 task(s)!
    Vous avez 1 tâche(s)!
```

### mark

Marks a task as done.

Format:

`mark INDEX`

Example of usage:

`mark 1`

Expected outcome:

The task at index 1 is marked as done.

```
    I have marked it as done:
    Je l'ai marqué comme fait:
        [T][X] CS2103T ip
```

### unmark

Unmarks a task as incomplete.

Format:

`unmark INDEX`

Example of usage:

`unmark 1`

Expected outcome:

The task at index 1 is unmarked as incomplete.

```
    I have marked it as undone:
    Je l'ai marqué comme défait:
        [T][] CS2103T ip
```

### list

Lists all tasks from the task list.

Format:

`list`

Example of usage:

`list`

Expected outcome:

All tasks in the list.

```
Here are your tasks!
Voici vos tâches!
    1.  [T][] CS2103T ip
    2.  [D][] assignment1 (by: Sep 15 2022 21:00)
    3.  [E][] assignment1 (at: tomorrow)
```

### delete

Deletes a task from the task list.

Format:

`delete INDEX`

Example of usage:

`delete 1`

Expected outcome:

The task at index 1 is deleted.

```
    I have deleted the task:
    Je l'ai supprimé:
        [T][] CS2103T ip
    You now have 2 tasks remaining!"
    Il vous reste maintenant 2 tâches!
```

### find

Finds a task from the task list, matching the keyword.

Format:

`find keyword`

Example of usage:

`find CS2103T`

Expected outcome:

Tasks with the keyword CS2103T will be listed.

```
There are 2 matches!
Il y a 2 correspondances!
    1. [T][] CS2103T ip
    2. [E][] CS2103T meeting (at: tomorrow 2100)
```

### sort

Sorts the task list in alphabetical order.

Format:

`sort`

Example of usage:

`sort`

Expected outcome:

The task list will be sorted in alphabetical order.

```
The list has been sorted!
La liste a été triée!
```

### bye

Terminates Jean.

Format:

`bye`

Example of usage:

`bye`

Expected outcome:

Jean will terminate.

```
    Goodbye! See you soon!
    Au revoir! À tout à l'heure!
```
