package LeetCode75;

import java.util.*;

public class ArrayString {

    public static void main(String[] args) {
        System.out.println(increasingTriplet(new int[] {2,1,5,0,4,6}));
    }

    //1768. Merge Strings Alternately
    public static String mergeAlternately(String word1, String word2) {
        int min = Math.min(word2.length(), word1.length()) * 2;
        StringBuilder res = new StringBuilder();

        res.setLength(word1.length() + word2.length());

        for(int i = 0; i < word1.length(); i ++) {
            res.setCharAt(min > i * 2 ? i * 2 : min / 2 + i, word1.charAt(i));
        }
        for(int i = 0; i < word2.length(); i ++) {
            res.setCharAt(min > i * 2 ? i * 2 + 1 : min / 2 + i, word2.charAt(i));
        }

        return res.toString();
    }

    //1071. Greatest Common Divisor of Strings
    public static String gcdOfStrings(String str1, String str2) {
        if (str1.isEmpty() || str2.isEmpty() || str1.charAt(0) != str2.charAt(0)) return "";
        int len1 = str1.length();
        int len2 = str2.length();
        while (len1 != 0 && len2 != 0) {
            if (len1 > len2) {
                len1 = len1 % len2;
            }
            else {
                len2 = len2 % len1;
            }
        }
        int sum = len1 + len2;
        for (int i = 0; i < str1.length(); i ++) {
            if (str1.charAt(i) != str1.charAt(i % sum)) {
                return "";
            }
        }
        for (int i = 0; i < str2.length(); i ++) {
            if (str2.charAt(i) != str2.charAt(i % sum)) {
                return "";
            }
        }
        return str1.substring(0, sum);
    }

    //1431. Kids With the Greatest Number of Candies
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Arrays.stream(candies).max().getAsInt();
        List<Boolean> res = new ArrayList<>();
        for(int i = 0; i < candies.length; i++) {
            if(candies[i] + extraCandies >= max) {
                res.add(true);
            }
            else {
                res.add(false);
            }
        }
        return res;
    }

    //605. Can Place Flowers
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int countSpaces = 1;
        for (int i : flowerbed) {
            if (i == 1) {
                if (countSpaces >= 3)
                    n -= (countSpaces - 1) / 2;
                countSpaces = 0;
            }
            else {
                countSpaces++;
            }
        }
        if (countSpaces >= 2) {
            n -= (countSpaces) / 2;
        }
        return n <= 0;
    }

    //345. Reverse Vowels of a String
    public static String reverseVowels(String s) {
        int left = 0;
        int right = s.length() - 1;
        Set<Character> setChars = Set.of('a', 'e', 'i', 'o', 'u');
        char[] sArr = s.toCharArray();

        while (left < right) {
            while (!setChars.contains(Character.toLowerCase(sArr[left]))) {
                left++;
            }
            while (!setChars.contains(Character.toLowerCase(sArr[right]))) {
                right --;
            }
            if (left < right) {
                char temp = sArr[left];
                sArr[left] = sArr[right];
                sArr[right] = temp;
                left ++;
                right --;
            }
        }

        return String.valueOf(sArr);
    }

    //334. Increasing Triplet Subsequence
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n <= first) {
                first = n;
            } else if (n <= second) {
                second = n;
            } else {
                return true;
            }
        }
        return false;
    }
}
