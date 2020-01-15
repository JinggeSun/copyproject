package com.sun;

import javafx.util.Builder;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author zcm
 */
public class LearnStream {

    public static void main(String[] args) {

        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");


        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        List<Student> streamStudents = testFilter(students);
        streamStudents.forEach(System.out::println);


        List<String> list = Arrays.asList("111","222","333","111","222");

    }

    /**
     * filter筛选
     * @param students
     * @return
     */
    private static List<Student> testFilter(List<Student> students) {
        //筛选年龄大于15岁的学生
//        return students.stream().filter(student -> {
//            return student.getAge() > 15;
//        }).collect(Collectors.toList());

        //筛选住在浙江省的学生
        return students.stream().filter(student -> {
            return "浙江".equals(student.getAddress());
        }).collect(Collectors.toList());
    }

    /**
     * map就是将对应的元素按照给定的方法进行转换。
     * @param students
     * @return
     */
    private static List<String> testMap(List<Student> students){
        //在地址前面加上部分信息，只获取地址输出
        return students.stream().map(student -> {
            return student.getAddress() + "-----";
        }).collect(Collectors.toList());
    }

    /**
     * 去重
     * @param students
     * @return
     */
    private static List<String> testDistinct(List<String> students){
        return students.stream().distinct().collect(Collectors.toList());
    }

    /**
     *引用对象的去重，引用对象要实现hashCode和equal方法，否则去重无效
     * @param students
     * @return
     */
    private static List<Student> testDistinctBean(List<Student> students){
        return students.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 排序 升序
     * @param lists
     * @return
     */
    private static List<Integer> testSorted(List<Integer> lists){
        return lists.stream().sorted().collect(Collectors.toList());
    }


    /**
     * 集合多规则排序
     * @param students
     * @return
     */
    private static List<Student> testSortedBean(List<Student> students){
        return students.stream().sorted(Comparator.comparingLong(Student::getAge)).collect(Collectors.toList());
    }

    /**
     * limit（限制返回个数）
     * @param integerList
     * @return
     */
    private static List<Integer> testLimit(List<Integer> integerList){
        return integerList.stream().limit(2).collect(Collectors.toList());
    }

    /**
     * 集合skip，删除前n个元素
     * @param integerList
     * @return
     */
    private static List<Integer> testSkip(List<Integer> integerList){
        return integerList.stream().skip(2).collect(Collectors.toList());
    }

    /**
     * 集合reduce,将集合中每个元素聚合成一条数据
     * @param list
     * @return
     */
    private static String testReduce(List<String> list){
      return list.stream().reduce("beijing",(a,b) -> a + b);
    }

    /**
     * max同理，求最大值
     * @param integerList
     */
    private static void testMin(List<Integer> integerList){
        Integer i = integerList.stream().min(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1,o2);
            }
        }).get();
    }

    /**
     * anyMatch：Stream 中任意一个元素符合传入的 predicate，返回 true
     * allMatch：Stream 中全部元素符合传入的 predicate，返回 true
     * noneMatch：Stream 中没有一个元素符合传入的 predicate，返回 true
     */
    private static void match(List<Map<String,Object>> mapList){
        Boolean flag = mapList.stream().noneMatch(stringObjectMap -> {
           return "da".equals(stringObjectMap.get("11")) ;
        });
    }

}
