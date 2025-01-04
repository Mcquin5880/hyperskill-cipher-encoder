import java.util.Scanner;
import java.util.Set;

public class CipherEncoder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Please input operation (encode/decode/exit):");
            String command = sc.nextLine();

            if (command.equals("exit")) {
                System.out.println("Bye!");
                break;
            }

            if (!command.equals("encode") && !command.equals("decode")) {
                System.out.println("There is no '" + command + "' operation\n");
                continue;
            }

            if (command.equals("encode")) {
                System.out.println("Input string:");
                String input = sc.nextLine();
                StringBuilder binaryString = new StringBuilder();
                for (char c : input.toCharArray()) {
                    String binary = Integer.toBinaryString(c);
                    String paddedBinary = String.format("%7s", binary).replace(' ', '0');
                    binaryString.append(paddedBinary);
                }
                String encoded = encodeBinary(binaryString.toString());
                System.out.println("Encoded string:");
                System.out.println(encoded + "\n");
            }

            if (command.equals("decode")) {
                System.out.println("Input encoded string:");
                String encoded = sc.nextLine();

                if (!validateEncodedString(encoded)) {
                    System.out.println("Encoded string is not valid.\n");
                    continue;
                }

                String binaryStr = decodeToBinary(encoded);
                if (binaryStr.length() % 7 != 0) {
                    System.out.println("Encoded string is not valid.\n");
                    continue;
                }

                String decoded = decodeBinaryStr(binaryStr);
                System.out.println("Decoded string:");
                System.out.println(decoded + "\n");
            }
        }
    }

    private static boolean validateEncodedString(String input) {
        // Check for invalid characters
        Set<Character> validChars = Set.of('0', ' ');
        for (char c : input.toCharArray()) {
            if (!validChars.contains(c)) {
                return false;
            }
        }

        // Split into parts and validate structure
        String[] parts = input.split(" ");
        if (parts.length % 2 != 0) {
            return false;
        }

        for (int i = 0; i < parts.length; i += 2) {
            if (!parts[i].equals("0") && !parts[i].equals("00")) {
                return false;
            }
        }

        return true;
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

    private static String decodeToBinary(String encoded) {
        String[] parts = encoded.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < parts.length; i += 2) {
            String currBit = parts[i].equals("0") ? "1" : "0";
            int length = parts[i + 1].length();
            sb.append(currBit.repeat(length));
        }

        return sb.toString();
    }

    private static String decodeBinaryStr(String binaryStr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < binaryStr.length(); i += 7) {
            int asciiValue = Integer.parseInt(binaryStr.substring(i, i + 7), 2);
            sb.append((char) asciiValue);
        }

        return sb.toString();
    }
}
