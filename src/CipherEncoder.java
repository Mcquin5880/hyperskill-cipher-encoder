import java.util.Scanner;

public class CipherEncoder {

    public static void main(String[] args) {

        System.out.print("Input string:\n");

        try (Scanner sc = new Scanner(System.in)) {
            String input = sc.nextLine();
            System.out.println("\nThe result:");

            StringBuilder binaryString = new StringBuilder();
            for (char c : input.toCharArray()) {
                String binary = Integer.toBinaryString(c);
                String paddedBinary = String.format("%7s", binary).replace(' ', '0');
                binaryString.append(paddedBinary);
            }

            String encoded = encodeBinary(binaryString.toString());

            System.out.println(encoded);
        }
    }

    private static String encodeBinary(String binary) {

        StringBuilder sb = new StringBuilder();
        char[] chars = binary.toCharArray();

        char currentBit = chars[0];
        int count = 1;

        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == currentBit) {
                count++;
            } else {
                appendEncodedSegment(sb, currentBit, count);
                sb.append(" ");
                currentBit = chars[i];
                count = 1;
            }
        }

        appendEncodedSegment(sb, currentBit, count);
        return sb.toString();
    }

    private static void appendEncodedSegment(StringBuilder sb, char bit, int length) {
        if (bit == '1') {
            sb.append("0 ");
        } else {
            sb.append("00 ");
        }
        sb.append("0".repeat(length));
    }
}
