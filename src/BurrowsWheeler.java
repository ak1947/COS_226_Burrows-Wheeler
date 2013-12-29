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

        int[] index = new int[257];

        for (char c : chars)
            index[c + 1]++;
        for (char c = 0; c < 256; c++)
            index[c + 1] += index[c];
        for (int i = 0; i < chars.length; i++) {
            next[index[chars[i]]++] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            BinaryStdOut.write(sortedChars[cur], 8);
//            System.out.print(sortedChars[cur]);
            cur = next[cur];
        }
        BinaryStdOut.flush();
    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}