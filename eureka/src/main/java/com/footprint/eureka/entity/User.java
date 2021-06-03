package com.footprint.eureka.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int userId;

    private String userName;

    private String phone;

    private String password;

    private String faceUrl;

    private String province;

    private String city;

    private String gender;

}
