import java.util.List;

public class Tasks {

    public static void handleList(String input, List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int index = i + 1;
            String task = String.valueOf(tasks.get(i));
            System.out.println(index + ". " + task);
        }
    }

    public static void handleDeadline(String input, List<Task> tasks) {
        String[] arr1 = input.split("/");
        String[] arr2 = arr1[1].split("by ");
        String[] arr3 = arr1[0].split("deadline ");
        String date = arr2[1];
        Task deadline = new Deadline(arr1[0], date);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:\n" + deadline + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleTodo(String input, List<Task> tasks) {
        String[] arr0 = input.split("todo ");
        if (arr0.length == 1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
        } else {
            Task todo = new Todo(arr0[1]);
            tasks.add(todo);
            System.out.println("Got it. I've added this task:\n" + todo + "\n" +
                    "Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public static void handleEvent(String input, List<Task> tasks) {
        String[] arr1 = input.split("/from "); // [0]: event + name, [1]: timeframe
        String[] arr2 = arr1[1].split("/to "); // [0] from:..., [1] to:...
        String[] arr3 = arr1[0].split("event ");
        Task event = new Event(arr3[1], arr2);
        tasks.add(event);
        System.out.println("Got it. I've added this task:\n" + event + "\n" +
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void handleMark(String input, List<Task> tasks) {
        String[] parts = input.split(" ");
        if (parts.length == 2) {
            try {
                int index = Integer.parseInt(parts[1]);
                    Task thisTask = tasks.get(index - 1);
                    tasks.get(index - 1).toggleDone();
                    if (thisTask.getDone()) {
                        System.out.println("Nice! I've marked this task as done:" + "\n" + thisTask);
                    } else {
                        System.out.println("OK, I've marked this task as not done yet:" + "\n" + thisTask);
                    }
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("IndexOutOfBounds");
            } catch (NumberFormatException e) {
                System.out.println("NumberFormatException");
            }
        }
    }

    public static void handleDelete(String input, List<Task> tasks) {
        String[] parts1 = input.split(" ");
        int index = Integer.parseInt(parts1[1]);
        String deleted = String.valueOf(tasks.get(index - 1));
        tasks.remove(index - 1);
        System.out.println("Noted. I've removed this task:\n" + deleted + "\n"
                + "Now you have " + tasks.size() + " tasks in the list.");
    }
}

