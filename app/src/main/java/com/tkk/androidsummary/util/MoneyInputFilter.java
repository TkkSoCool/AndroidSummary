package com.tkk.androidsummary.util;

import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created  on 2017/12/20
 *
 * @author 唐开阔
 * @describe 金额输入过滤器
 * 如何判断是否为正确的可输入金额（"10."，为正确可输入）
 * 有小数点：
 * 1：最多两位小数
 * 2：前两位不能都是0
 * 3：最多一个小数点
 * 4：小数点不能在第一位
 * 无小数点
 * 1：第一位不能是0
 *
 */
public class MoneyInputFilter implements InputFilter {
    private final  static String TAG = "MoneyInputFilter";
    /**
     * 在将Spanned dest中范围为dstart到dend的 用CharSequence source的start到end范围的替换。
     * @param source 单次输入的内容
     * @param start 输入source的起始位置
     * @param end 输入source的结束位置
     * @param dest 以前输入的之前的内容
     * @param dstart 替换dest的起始位置
     * @param dend 替换dest的结束位置
     * @return 替换的内容，return null相当于return source；
     */
    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        //单次只能输入一个字符
        Log.d(TAG, ">>>输入内容---" + source + "  start = "+start +"  end = " + end);
        Log.d(TAG, ">>>之前的内容---" + dest + "  dstart = "+dstart +"  dend = " + dend);

        //输入的内容
        CharSequence inputChar = source.subSequence(start,end);
        //需要被替换的内容
        CharSequence reChar = dest.subSequence(dstart,dend);
        //新生成内容
        CharSequence newChar = "0";
        boolean isLegal = isLegal(newChar);
        Log.d(TAG, ">>>filter---  " + "newChar = " + inputChar + "  reChar = " + reChar);

        if (end -start == source.length()){

        }
        return null;
    }

    /**
     * 检验是否合法
     * @param newChar
     * @return
     */
    private boolean isLegal(CharSequence newChar) {

        String str = newChar.toString();
        if (str.contains(".")){
            int time = 0;
            for (int i = 0; i < str.length(); i++) {
                if (TextUtils.equals(".",String.valueOf(str.charAt(i)))){
                    time++;
                }
            }
            //多个小数点
            if (time > 1){
                return false;
            }else {
                //最多两位小数
                if (str.length() - str.indexOf(".") > 2){
                    return false;
                }
                //小数点不能在第一位
                if (TextUtils.equals(".",String.valueOf(str.charAt(0)))){
                    return false;
                }

            }
        }else {
            return !TextUtils.equals(String.valueOf(newChar.charAt(0)),"0");
        }
        return false;
    }

}
