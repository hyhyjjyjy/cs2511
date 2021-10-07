package splitter;

import java.util.Scanner;

public class Splitter {

    public static void main(String[] args) {
        System.out.println("Enter a sentence specified by spaces only: ");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        //System.out.printf("%s\n", str);
        String[] strs = str.split(" ");
        for (String new_str: strs) {
            System.out.println(new_str);
        }
        scan.close();
        // Add your code
    }
}
