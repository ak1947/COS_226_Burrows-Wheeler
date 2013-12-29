import java.util.Arrays;

public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();

//        String s = "CADABRA!ABRA";

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(s);

        for (int i = 0; i < circularSuffixArray.length(); i++) {
            if (circularSuffixArray.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < circularSuffixArray.length(); i++) {
            int index = circularSuffixArray.index(i) - 1;
            if (index == -1) index = circularSuffixArray.length() - 1;
//            System.out.print(s.charAt(index));
            BinaryStdOut.write(s.charAt(index), 8);
        }


        BinaryStdOut.flush();

    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {
        int cur = BinaryStdIn.readInt();
//        int cur = 3;
//        String s = "ARD!RCAAAABB";
        String s = BinaryStdIn.readString();

        char[] chars = s.toCharArray();
        char[] sortedChars = s.toCharArray();
        Arrays.sort(sortedChars);

        int[] next = new int[chars.length];

        int[] index = new int[256];

        for (char c : chars)
            index[c]++;
        for (char c = 1; c < 256; c++)
            index[c] += index[c - 1];
        for (int i = 0; i < chars.length; i++) {
            next[index[chars[i] - 1]++] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            BinaryStdOut.write(sortedChars[cur], 8);
            System.out.print(sortedChars[cur]);
            cur = next[cur];
        }


    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}