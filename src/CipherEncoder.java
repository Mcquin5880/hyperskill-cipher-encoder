import java.util.Scanner;

public class CipherEncoder {

    public static void main(String[] args) {

        System.out.print("Input string:\n");

        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            System.out.println("\nThe result:");

            for (char c : input.toCharArray()) {
                String binary = Integer.toBinaryString(c);
                String paddedBinary = String.format("%7s", binary).replace(' ', '0');
                System.out.printf("%c = %s%n", c, paddedBinary);
            }
        }
    }
}
