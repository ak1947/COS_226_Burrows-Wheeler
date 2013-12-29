public class CircularSuffixArray {
    private int[] index;

    public CircularSuffixArray(String s)  // circular suffix array of s
    {
        index = new int[s.length()];

    }

    public int length()                   // length of s
    {
        return index.length;
    }

    public int index(int i)               // returns index of ith sorted suffix
    {
        return index[i];
    }
}