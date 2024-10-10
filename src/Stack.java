    import java.util.*;

public class Stack {

    //20. Valid Parentheses
    public static boolean isValid(String s) {
        Map<Character, Character> mapChars = Map.of(')','(',']','[', '}', '{');
        java.util.Stack<Character> stackChars = new java.util.Stack<>();
        for(char ch : s.toCharArray()) {
            if(mapChars.containsValue(ch)) {
                stackChars.push(ch);
            }
            else {
                if (stackChars.isEmpty() || !stackChars.pop().equals(mapChars.get(ch))) {
                    return false;
                }
            }
        }
        return stackChars.isEmpty();
    }
}
