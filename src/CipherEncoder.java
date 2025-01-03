import java.util.Scanner;

public class CipherEncoder {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Input string:");
        String input = sc.nextLine();
        StringBuilder sb = new StringBuilder();

        for (char c : input.toCharArray()) {
            sb.append(c).append(" ");
        }

        System.out.println(sb);
    }
}
