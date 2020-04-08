package com.tkk.androidsummary.knowledgepoint.algorithm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created  on 2020-04-08
 * @author 唐开阔
 * @describe leetcode 1 - 50 题
 */
public class SubjectOneToFifty {

    @LeetCodeInfo(
            subject = "1. 两数之和",
            stem = "给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。" +
                    "你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。\n",
            example = "给定 nums = [2, 7, 11, 15], target = 9 \n 因为 nums[0] + nums[1] = 2 + 7 = 9\n" +
                    "所以返回 [0, 1] "
    )
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> cacheMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int needNum = target -  nums[i];
            if (cacheMap.containsKey(needNum)){
                return new int[]{i,cacheMap.get(needNum)};
            }
            cacheMap.put(nums[i],i);
        }
        return nums;
    }
}
