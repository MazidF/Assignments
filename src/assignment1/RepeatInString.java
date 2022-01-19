package assignment1;

public class RepeatInString {
    public int StringInString(String one, String two) {
        if (one == null || one.equals("") || two == null || two.equals("")) return 0;
        int counter = 0, sizeTwo = two.length(), sizeOne = one.length();
        char starter = two.charAt(0);
        for (int i = 0; i < one.length(); i++) {
            if (one.charAt(i) == starter && (i + sizeTwo - 1) < sizeOne) {
                int j = 1;
                for (; j < sizeTwo; j++) {
                    if (one.charAt(i+j) != two.charAt(j)) break;
                }
                if (j == sizeTwo) {
                    counter++;
                }
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        System.out.println(new RepeatInString().StringInString("sasasasasas", "sas"));
    }
}
