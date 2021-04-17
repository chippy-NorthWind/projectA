package com.example.service;

import com.example.pojo.Image;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
public interface ImageService {
    void add(Image image);

    void deleteById(int id);

    List<Image> selectAll();

    Image selectById(int id);

    Image selectByName(String name);

    void update(Image image);
}
