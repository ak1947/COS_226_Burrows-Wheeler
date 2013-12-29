public class BurrowsWheeler {
    // apply Burrows-Wheeler encoding, reading from standard input and writing to standard output
    public static void encode() {
        String s = BinaryStdIn.readString();

        CircularSuffixArray circularSuffixArray = new CircularSuffixArray(s);

        for (int i = 0; i < circularSuffixArray.length(); i++) {
            if (circularSuffixArray.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < circularSuffixArray.length(); i++) {
            BinaryStdOut.write(s.charAt(circularSuffixArray.index(i)), 8);
        }

        BinaryStdOut.flush();

    }

    // apply Burrows-Wheeler decoding, reading from standard input and writing to standard output
    public static void decode() {

    }

    // if args[0] is '-', apply Burrows-Wheeler encoding
    // if args[0] is '+', apply Burrows-Wheeler decoding
    public static void main(String[] args) {
        if (args[0].equals("-")) encode();
        else if (args[0].equals("+")) decode();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}