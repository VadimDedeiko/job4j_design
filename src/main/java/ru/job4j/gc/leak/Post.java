package ru.job4j.gc.leak;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Post {
    private int id;
    @NonNull
    private String text;
    @NonNull
    private List<Comment> comments;
}