import java.util.Scanner;

public class John {
    public static void main(String[] args) {
        String name = "John";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        Task[] list = new Task[100];
        int count = 0;
        while(!line.equals("bye")){
            String[] words = line.split(" ");
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++){
                    int num = i + 1;
                    System.out.println(num + "." +'[' + list[i].getStatusIcon() + "] "+ list[i].description);
                }
            }
            else if (words[0].equals("mark")) {
                int item = Integer.parseInt(words[1]) - 1;
                list[item].markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list[item]);
            }
            else if (words[0].equals("unmark")) {
                int item = Integer.parseInt(words[1]) - 1;
                list[item].markAsUndone();
                System.out.println("OK, I've marked this task as not done yet: ");
                System.out.println(list[item]);
            }
            else {
                Task t = new Task(line);
                list[count] = t;
                count++;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
