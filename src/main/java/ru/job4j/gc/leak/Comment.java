package ru.job4j.gc.leak;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Comment {
    private String text;
    private User user;
}