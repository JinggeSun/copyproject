package com.sun.learn.entity;

import lombok.*;

/**
 * @author zcm
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class User {

    private int id;
    private String name;
    private int age;
    private int gender;

}
