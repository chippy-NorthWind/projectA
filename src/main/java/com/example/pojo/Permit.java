package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 橙鼠鼠
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permit {
    String permitName;
    int id;
    int enabled;
}
