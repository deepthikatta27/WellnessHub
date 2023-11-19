package com.project.HealthTrackManagement.Model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;



@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User
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
