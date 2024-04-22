package org.example;

 import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Author: yin7331
 * Date: 2023/10/20 23:53
 * Describe:
 */

@Data()
@AllArgsConstructor
public class User {
    Integer id;
    String name;
    Integer age;
    String city;
}
