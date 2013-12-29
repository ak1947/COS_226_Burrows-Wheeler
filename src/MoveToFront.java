public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {

        char charAtBeginning = 0;
        int[] indexOfChar = new int[256];

        for (int i = 0; i < 256; i++)
            indexOfChar[i] = i;

        while (!BinaryStdIn.isEmpty()) {
            char readChar = BinaryStdIn.readChar(8);
            // swap first and current char
            int currentCharIndex = indexOfChar[readChar];
            BinaryStdOut.write(currentCharIndex, 8);
            indexOfChar[readChar] = 0;
            indexOfChar[charAtBeginning] = currentCharIndex;
            charAtBeginning = readChar;
        }
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {

        int charAtBeginning = 0;
        int[] charAtPosition = new int[256];

        for (int i = 0; i < 256; i++)
            charAtPosition[i] = i;

        while (!BinaryStdIn.isEmpty()) {
            char charPosition = BinaryStdIn.readChar(8);
            // print char at position
            BinaryStdOut.write(charAtPosition[charPosition], 8);
            charAtBeginning = charAtPosition[0];
            charAtPosition[0] = charAtPosition[charPosition];
            charAtPosition[charPosition] = charAtBeginning;
        }

    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");

    }
}