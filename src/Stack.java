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


    //71. Simplify Path
    public static String simplifyPath(String path) {
        String[] array = path.split("/");
        java.util.Stack<String> stack = new java.util.Stack<>();
        Arrays.stream(array).forEach(s -> {
            if(!s.isEmpty() && !s.equals(".")) {
                if (s.equals("..")) {
                    if (!stack.isEmpty())
                        stack.pop();
                } else {
                    stack.push(s);
                }
            }
        });
        return "/" + String.join("/", stack);
    }

    //150. Evaluate Reverse Polish Notation
    public int evalRPN(String[] tokens) {
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        Arrays.stream(tokens).forEach(s -> {
            switch (s) {
                case "+" :
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-" :
                    var temp = stack.pop();
                    stack.push(stack.pop() - temp);
                    break;
                case "*" :
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/" :
                    var div = stack.pop();
                    stack.push(stack.pop() / div);
                    break;
                default:
                    stack.push(Integer.valueOf(s));
            }
        });
        return stack.pop();
    }
}
