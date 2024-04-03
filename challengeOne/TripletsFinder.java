import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletsFinder {

    public static List<List<Integer>> findTriplets(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> triplets = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {
                int left = i + 1;
                int right = nums.length - 1;
                int currentTarget = target - nums[i];

                while (left < right) {
                    if (nums[left] + nums[right] == currentTarget) {
                        triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));

                        // Skip duplicates
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;

                        left++;
                        right--;
                    } else if (nums[left] + nums[right] < currentTarget) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return triplets;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 2, 6, 7, 8, 9};
        int target = 15;

        List<List<Integer>> result = findTriplets(nums, target);
        System.out.println("Triplets that sum up to " + target + ":");
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }
}
