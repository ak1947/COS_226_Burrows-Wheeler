public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        int[] charAtIndex = new int[256];

        for (int i = 0; i < 256; i++)
            charAtIndex[i] = i;

        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        for (int i = 0; i < input.length; i++) {
            char readChar = input[i];
            int currentCharIndex = 0;
            while (charAtIndex[currentCharIndex] != readChar)
                currentCharIndex++;
            BinaryStdOut.write(currentCharIndex, 8);
            // move readChar to front! increase index of the rest of characters
            for (int j = currentCharIndex; j > 0; j--)
                charAtIndex[j] = charAtIndex[j - 1];
            charAtIndex[0] = readChar;
        }

        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {

        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        int[] charAtIndex = new int[256];

        for (int i = 0; i < 256; i++)
            charAtIndex[i] = i;

        for (int j = 0; j < input.length; j++) {
            int curCharIndex = input[j];
            int curChar = charAtIndex[curCharIndex];
            BinaryStdOut.write(curChar, 8);

            for (int i = curCharIndex; i > 0; i--)
                charAtIndex[i] = charAtIndex[i - 1];
            charAtIndex[0] = curChar;
        }

        BinaryStdOut.flush();

    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");

    }
}