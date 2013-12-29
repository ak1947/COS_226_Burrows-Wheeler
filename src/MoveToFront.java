public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        int[] charAtIndex = new int[256];

        for (int i = 0; i < 256; i++)
            charAtIndex[i] = i;

        while (!BinaryStdIn.isEmpty()) {
            char readChar = BinaryStdIn.readChar(8);
            int currentCharIndex = 0;
            while (charAtIndex[currentCharIndex] != readChar)
                currentCharIndex++;
            BinaryStdOut.write(currentCharIndex, 8);
            // move readChar to front! increase index of the rest of characters
            for (int i = currentCharIndex; i > 0; i--)
                charAtIndex[i] = charAtIndex[i - 1];
            charAtIndex[0] = readChar;
        }
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {

        int charAtBeginning = 0;
        int[] charAtIndex = new int[256];

        for (int i = 0; i < 256; i++)
            charAtIndex[i] = i;

        while (!BinaryStdIn.isEmpty()) {
            int curCharIndex = BinaryStdIn.readChar(8);
            int curChar = charAtIndex[curCharIndex];
            BinaryStdOut.write(curChar, 8);

            for (int i = curCharIndex; i > 0; i--)
                charAtIndex[i] = charAtIndex[i - 1];
            charAtIndex[0] = curChar;
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