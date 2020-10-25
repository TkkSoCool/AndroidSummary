package com.tkk.androidsummary.knowledgepoint.algorithm.leetcode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created  on 2020-04-08
 *
 * @author 唐开阔
 * @describe
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LeetCodeInfo {
    /**
     * @return 题目
     */
    String subject() default "";
    /**
     * @return 题干
     */
    String stem () default "";

    /**
     * @return 题目示例
     */
    String example() default "";

    /**
     * @return 分析
     */
    String analyse() default "";


}
