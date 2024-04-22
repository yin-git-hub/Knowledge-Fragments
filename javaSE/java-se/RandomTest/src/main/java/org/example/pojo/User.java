package org.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Author: yin7331
 * Date: 2023/10/21 16:41
 * Describe:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Integer id;
    String name;
    Integer age;
}
