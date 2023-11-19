package com.project.HealthTrackManagement.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private String _id;
    private String name;
    private String image;
    private String email;
    private String password;
    private Integer age;
    private Integer height;
    private Integer weight;
}
