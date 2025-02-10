package LeetCode75;

public class SlidingWindow {

    //643. Maximum Average Subarray I
    public double findMaxAverage(int[] nums, int k) {
        int left = 0, right = k - 1;
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double res = (double) sum / k;
        while (right < nums.length) {
            sum -= nums[left++];
            sum += nums[++right];
            res = Math.max(res, (double) sum / k);
        }

        return res;
    }

    //1456. Maximum Number of Vowels in a Substring of Given Length
    public int maxVowels(String s, int k) {
        int left = 0;
        int right = k - 1;
        int res = 0;
        boolean[] bitMap = new boolean[s.length()];
        for (int i = 0; i < bitMap.length; i++) {
            switch (s.charAt(i)) {
                case 'a', 'e', 'i', 'o', 'u' -> bitMap[i] = true;
            }
        }
        for (int i = 0; i < k; i++) {
            if (bitMap[i]) res++;
        }
        int temp = res;
        while (right < bitMap.length - 1) {
            if (bitMap[left++]) temp--;
            if (bitMap[++right]) temp++;
            res = Math.max(res, temp);
        }

        return res;
    }
}
