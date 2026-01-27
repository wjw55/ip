import java.util.Scanner;

public class John {
    public static void main(String[] args) {
        String name = "John";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        String line;
        Scanner in = new Scanner(System.in);
        line = in.nextLine();
        String[] list = new String[100];
        int count = 0;
        while(!line.equals("bye")){
            if (line.equals("list")) {
                for (int i = 0; i < count; i++){
                    int num = i + 1;
                    System.out.println(num + ". " + list[i]);
                }
            }
            else {
                list[count] = line;
                count++;
                System.out.println("added: " + line);
            }
            line = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
