package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * Author: yin7331
 * Date: 2023/10/21 16:41
 * Describe:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Admin{

    private Integer id = 1;
    private String name = "华";
    private Integer age = 18;

    public String soutMessage(String temp){
        System.out.println(temp+":::::"+this.name);
        return temp+":::::"+this.name;

    }
}
