import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        LinkedList linkedList = new LinkedList();

        for (char i = 0; i < 256; i++){
            linkedList.add(i);
        }

        while (!BinaryStdIn.isEmpty()) {
            char readChar = BinaryStdIn.readChar(8);
            int currentCharIndex = linkedList.indexOf(readChar);
            BinaryStdOut.write(currentCharIndex, 8);
            // move readChar to front! increase index of the rest of characters
            linkedList.remove(currentCharIndex);
            linkedList.add(0, readChar);
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
//        System.setIn(new FileInputStream("abra.txt"));
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");

    }
}