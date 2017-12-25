package com.tkk.androidsummary.knowledgepoint.algorithm;

import android.os.Bundle;
import android.util.Log;

import com.tkk.androidsummary.R;
import com.tkk.androidsummary.annotation.BindLayout;
import com.tkk.androidsummary.annotation.KnowledgeInfo;
import com.tkk.androidsummary.base.BaseActivity;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created  on 2017/11/14
 * @author 唐开阔
 * @describe 排序
 * 冒泡排序
 * 快速排序
 * 插入排序
 * 希尔排序
 *
 * 顺序查找
 * 二分查找
 */
@BindLayout(R.layout.activity_sort)
@KnowledgeInfo(catalog = KnowledgeInfo.ALGROITHM, desc = "排序和查找算法")
public class SortAndFindActivity extends BaseActivity {
    public final static int NUMS = 15;
    public int[] arrs;

    @Override
    protected void initView() {
        arrs = new int[NUMS];
        Random random = new Random();
        for (int i = 0; i < NUMS; i++) {
            arrs[i] = random.nextInt(1000);
        }
    }

    /**
     * 冒泡排序
     * 时间复杂度O(n平方)
     * 一次循环比较未排序两个相邻的值的大小，交换位置
     */
    @OnClick(R.id.bt_mp)
    public void onBtMpClicked() {
        int len = arrs.length;
        int i, j, temp;
        for (i = 0; i < len - 1; i++) {
            for (j = 0; j < len - i - 1; j++) {
                if (arrs[j] > arrs[j + 1]) {
                    exChangeArr(j, j + 1);
                }
            }
        }
        logArr();
    }

    /**
     * 选择排序交换的次数更少，时间复杂度一样。
     * 一次循环找出未排序的最大最小值，与数组头部未排序的元素交换位置
     */
    @OnClick(R.id.bt_xz)
    public void onBtXzClicked() {
        int len = arrs.length;
        int i, j, min = 0;
        for (i = 0; i < len - 1; i++) {
            min = i;
            for (j = i + 1; j < len; j++) {
                if (arrs[j] < arrs[min]) {
                    min = j;
                }
            }
            exChangeArr(i, min);
        }
        logArr();
    }

    /**
     * 插入排序,类似于摸扑克牌排序
     * 假定第1个元素被放在正确的位置，从第2个开始遍历。
     * 对于每次遍历，从0到i-1范围内的元素已经被排好序。
     * 扫描前面已排序部分，找到目标值的插入位置并赋值。
     * 如果目标值小于扫描值，扫描值右移，如果扫描值小于目标值或者扫描完毕时确定位置结束扫描
     */
    @OnClick(R.id.bt_cr)
    public void onBtCrClicked() {
        int len = arrs.length;
        int i, j, temp = 0;
        for (i = 1; i < len - 1; i++) {
            temp = arrs[i];
            j = i;
            while (j > 0 && temp < arrs[j - 1]) {
                arrs[j] = arrs[j - 1];
                j--;
            }
            arrs[j] = temp;
        }
        logArr();
    }

    /**
     * 希尔排序，基于插入排序
     */
    @OnClick(R.id.bt_xe)
    public void onBtXeClicked() {
        int h = 1;
        int len = arrs.length;
        //算出最大间隔，使用h的序列公式为 h = 3 * h +1
        while (h <= len / 3) {
            h = h * 3 + 1;
        }
        while (h > 0){
            //以h做区间插入排序
            for (int i = h; i < len; i += h) {
                int temp = arrs[i];
                int  j = i;
                while (j > 0 && temp < arrs[j-h]) {
                    arrs[j] = arrs[j-h];
                    j-=h;
                }
                arrs[j] = temp;
            }
            // 计算出下一个h值,确保最后一个为1
            h = (h - 1) / 3;
        }
        logArr();
    }

    /**
     * 快速排序
     */
    @OnClick(R.id.bt_ks)
    public void onBtKsClicked() {
    }


    /**
     * 交换数组中两个元素的位置
     * @param i
     * @param j
     */
    private void exChangeArr(int i, int j) {
        if (i == j){
            return;
        }
        int temp;
        temp = arrs[i];
        arrs[i] = arrs[j];
        arrs[j] = temp;
    }

    /**
     * 打印出数组数据
     */
    private void logArr() {
        for (int i = 0; i < arrs.length; i++) {
            Log.d(TAG, ">>>logArr---" + arrs[i]);
        }
    }



}
