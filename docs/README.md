# User Guide for Duke chatbot(Made by Lan Jingbo, Jerry)

## Introduction

### Manage your tasks into the system

You can add three types of tasks (Todo, Event, and Deadline) into it, and you can also delete them.

### A good time-managing habit!

This chatbot aims for good time-managing habits.

## Usage Process in Detail

### `Keyword` - Describe each requirement

#### Add tasks

You can add three types tasks, and mark them as done or not finished.

Expected outcome: You successfully added one task to the tasklist, but it is signed as uncompleted.

Examples:
```
todo go to lecture
event exam /at 2022-10-01
deadline due /by 2022-10-01
```

Now, you can see the list by type in:
`list`
and the expected result is following:
```
1.[T][N] go to lecture
2.[E][N] exam (at: Oct. 01 2022)
3.[D][N] due (by: Oct. 01 2022)
```
For this expected results, `N` means have not done yet, if you run this following:
`mark 2`
The who list will be :
```
>> list
###
1.[T][N] go to lecture
2.[E][Y] exam (at: Oct. 01 2022)
3.[D][N] due (by: Oct. 01 2022)
```
mark and unmark is the same logic.

You can also search for your target result by use "search" function
Example:
```
>> search go
###
1.[T][N] go to lecture
```

You can delete tasks that are not useful, for example:
```
>> delete 1
ok I will delete the task [T][N] go to lecture right now!
now you have 2 tasks in the list

>> list
1.[E][Y] exam (at: Oct. 01 2022)
2.[D][N] due (by: Oct. 01 2022)
```

If you encounter other problems during using this lovely app, kindly run:
```
help
```
The duke chatbot will help you!

# Thank you for using my app!
