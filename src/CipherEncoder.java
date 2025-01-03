import java.util.Scanner;

public class CipherEncoder {

    public static void main(String[] args) {

        // temp code from previous stage

//        System.out.print("Input string:\n");
//
//        try (Scanner sc = new Scanner(System.in)) {
//            String input = sc.nextLine();
//            System.out.println("\nThe result:");
//
//            StringBuilder binaryString = new StringBuilder();
//            for (char c : input.toCharArray()) {
//                String binary = Integer.toBinaryString(c);
//                String paddedBinary = String.format("%7s", binary).replace(' ', '0');
//                binaryString.append(paddedBinary);
//            }
//
//            String encoded = encodeBinary(binaryString.toString());
//
//            System.out.println(encoded);
//        }

        Scanner sc = new Scanner(System.in);

        System.out.println("Input encoded string:");
        String encoded = sc.nextLine();
        String binaryStr = decodeToBinary(encoded);
        String decoded = decodeBinaryStr(binaryStr);

        System.out.println("\nThe result:");
        System.out.println(decoded);

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

        for (int i = 0; i < parts.length - 1; i += 2) {
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
