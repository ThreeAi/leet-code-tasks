package LeetCode75;

public class TwoPointers {

    //283. Move Zeroes
    public void moveZeroes(int[] nums) {
        int left = 0;
        int right = 0;
        while(right < nums.length) {
            if(nums[right] == 0) {
                right ++;
            }
            else {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                left ++;
                right ++;
            }
        }
    }
}
