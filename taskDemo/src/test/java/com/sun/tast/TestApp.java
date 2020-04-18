package com.sun.tast;

import com.jayway.jsonpath.internal.filter.Evaluator;
import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import org.nfunk.jep.JEP;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TestApp {


    public static void main(String[] args) {

        //1. 每个值使用double 类型
        //2。 % 取余
        //3. 表达式 true false
        //4。 绝对值

        // 执行字符串表达式：(k-(x-y)*0.1)，进行计算
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("k", 10);
        map.put("x", 24D);
        map.put("y", 4);
        String formula = "!(10*Math.abs(-1)<(2*5)/2-100)";
        Object result = convertToCode(formula,map);
        System.out.println(result);
        if (result.toString().equals("true")){
            System.out.println(result);
        }
    }

    /**
     * java将字符串转换成可执行代码 工具类
     *
     * @param jexlExp
     * @param map
     * @return
     */
    private static Object convertToCode(String jexlExp, Map<String, Object> map) {
        JexlEngine jexl = new JexlEngine();
        JexlContext jexlContext = new MapContext ();
        jexlContext.set("Math", Math.class);

        Expression expression = jexl.createExpression(jexlExp);
        for (String key : map.keySet()) {
            jexlContext.set(key, map.get(key));
        }
        if (null == expression.evaluate(jexlContext)) {
            return "";
        }

        return expression.evaluate(jexlContext);
    }
}
