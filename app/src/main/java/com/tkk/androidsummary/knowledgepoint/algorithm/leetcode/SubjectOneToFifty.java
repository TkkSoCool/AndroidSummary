package com.tkk.androidsummary.knowledgepoint.algorithm.leetcode;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

/**
 * Created  on 2020-04-08
 *
 * @author 唐开阔
 * @describe leetcode 1 - 50 题
 */
public class SubjectOneToFifty {


    public static String TAG = "SubjectOneToFifty";

    @LeetCodeInfo(
            subject = "1. 两数之和",
            stem = "给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。" +
                    "你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。\n",
            example = "给定 nums = [2, 7, 11, 15], target = 9 \n 因为 nums[0] + nums[1] = 2 + 7 = 9\n" +
                    "所以返回 [0, 1] "
    )
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> cacheMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            int needNum = target - nums[i];
            if (cacheMap.containsKey(needNum)) {
                return new int[]{i, cacheMap.get(needNum)};
            }
            cacheMap.put(nums[i], i);
        }
        return nums;
    }


    public static class ListNode {
        public int val;
        public ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }

    @LeetCodeInfo(
            subject = "2. 两数相加",
            stem = "给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，" +
                    "并且它们的每个节点只能存储 一位 数字。\n" +
                    "如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。\n" +
                    "您可以假设除了数字 0 之外，这两个数都不会以 0 开头。",
            example = "输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)" +
                    "输出：7 -> 0 -> 8" +
                    "原因：342 + 465 = 807",
            analyse = "1: 类似小学加法" +
                    "2:循环相加" +
                    "3:结束条件，两个列表都没有下一位" +
                    "4:利用链表的哑结点（dummy node是初始值为NULL的节点）处理列表初始化的特殊情况"
    )
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        boolean isAddOne = false;
        while ((l1 != null) || (l2 != null)) {
            int num1 = l1 != null ? l1.val : 0;
            int num2 = l2 != null ? l2.val : 0;
            int sum = num1 + num2 + (isAddOne ? 1 : 0);
            isAddOne = (sum >= 10);
            sum = isAddOne ? sum - 10 : sum;
            if (result == null) {
                result = new ListNode(sum);
            } else {
                result.next = new ListNode(sum);
                result = result.next;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        while (result != null) {
            System.out.println(result.val);
            Log.d(TAG, ">>>addTwoNumbers " + result.val);
            result = result.next;
        }
        return result;
    }

    /**
     * 测试方法
     * @param args
     */
    public static void main(String[] args) {
        int[] l1Arr = {2,4,3};
        ListNode l1 = null;
        for (int i = 0; i < l1Arr.length; i++) {
            if (l1 == null){
                l1 = new ListNode(l1Arr[i]);
            }else {
                l1.next = new ListNode(l1Arr[i]);
                l1 = l1.next;
            }
        }

        int[] l2Arr = {5,6,4};
        ListNode l2 = null;
        for (int i = 0; i < l2Arr.length; i++) {
            if (l2 == null){
                l2 = new ListNode(l2Arr[i]);
            }else {
                l2.next = new ListNode(l2Arr[i]);
                l2 = l2.next;
            }
        }
        addTwoNumbers(l1,l2);
    }
}
