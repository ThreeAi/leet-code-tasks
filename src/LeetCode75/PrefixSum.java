package LeetCode75;

public class PrefixSum {

    //1732. Find the Highest Altitude
    public int largestAltitude(int[] gain) {
        int start = 0;
        int result = 0;

        for (int i : gain) {
            start += i;
            result = Math.max(start, result);
        }

        return result;
    }

    //724. Find Pivot Index
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }

        int leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if (leftSum == sum) return i;
            leftSum += nums[i];
        }
        return -1;
    }
}
