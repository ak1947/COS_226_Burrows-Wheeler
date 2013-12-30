import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        int[] charAtIndex = new int[256];
        int[] next = new int[256];
        int start = 0;

        for (int i = 0; i < 256; i++) {
            charAtIndex[i] = i;
            next[i] = i + 1;
        }

        while (!BinaryStdIn.isEmpty()) {
            char readChar = BinaryStdIn.readChar(8);
            int numberOfHops = 0;
            int currentCharIndex = start;
            int prevCharIndex = -1;
            while (charAtIndex[currentCharIndex] != readChar) {
                prevCharIndex = currentCharIndex;
                currentCharIndex = next[currentCharIndex];
                numberOfHops++;
            }
            BinaryStdOut.write(numberOfHops, 8);
//            System.out.println(numberOfHops);
            // move readChar to front! increase index of the rest of characters

            if (prevCharIndex != -1) {
                next[prevCharIndex] = next[currentCharIndex];
                next[currentCharIndex] = start;
            }
            start = currentCharIndex;
        }

        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
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

        BinaryStdOut.flush();

    }

    // if args[0] is '-', apply move-to-front encoding
    // if args[0] is '+', apply move-to-front decoding
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("amendments.txt"));
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");

    }
}