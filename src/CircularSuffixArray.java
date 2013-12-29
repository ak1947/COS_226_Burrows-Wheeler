public class CircularSuffixArray {
    private String inputString;
    private int[] index;

    public CircularSuffixArray(String s)  // circular suffix array of s
    {
        inputString = s;
        index = new int[s.length()];
        for (int i = 0; i < index.length; i++)
            index[i] = i;

        msdSort(0, index.length - 1, 0);

//        System.out.println("Yay");
//        for (int i:index)
//            System.out.print(i);
//
//
//        for (int i:index)
//            System.out.print(s.charAt(i));
    }

    private void msdSort(int lo, int hi, int offset) {
        if (lo >= hi) return;
        int lt = lo, gt = hi;
        char v = getCharAt(lo + offset, 0);

        int i = lo + 1;
        while (i <= gt) {
            int t = getCharAt(i, offset);
            if (t < v) exchange(lt++, i++);
            else if (t > v) exchange(i, gt--);
            else i++;
        }

        msdSort(lo, lt - 1, offset);
        if (lt!=gt) msdSort(lt, gt, offset + 1);
        msdSort(gt + 1, hi, offset);
    }

    private void exchange(int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    private char getCharAt(int position, int offset) {
        return inputString.charAt((index[position] + offset) % inputString.length());
    }

    public int length()                   // length of s
    {
        return index.length;
    }

    public int index(int i)               // returns index of ith sorted suffix
    {
        return index[i];
    }

    public static void main(String[] args) {
        new CircularSuffixArray("AFRICA");
    }
}