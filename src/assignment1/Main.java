package assignment1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String string = input.next();
        Map<Character, Object> map = new HashMap<>();
        char c;

        for (int i = 0; i < string.length(); i++) {
            c = string.toUpperCase().charAt(i);
            map.put(c, (int) map.getOrDefault(c, 0) + 1); // counting repeat of chars
        }

        List<Character> characters = new ArrayList<>(map.keySet());
        for (int i = 0; i < characters.size(); i++) {
            c = characters.get(i);
            map.put(c, fun(c, (int) map.get(c)));
        }

        StringBuilder stringBuilder = new StringBuilder();
        int difference = 'a' - 'A';
        for (int i = 0; i < string.length(); i++) {
            c = string.charAt(i);
            if (c >= 'a' && c <= 'z') {
                stringBuilder.append((char) ((char) map.get(string.toUpperCase().charAt(i)) + difference));
            } else {
                stringBuilder.append((char) (map.get(string.toUpperCase().charAt(i))));
            }
        }
        System.out.println(stringBuilder);
    }

    private static char fun(char c, int count) {
        return (char) (((c - 'A') * count + 1) % 26 + 'A');
    }
}
