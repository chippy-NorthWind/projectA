package com.example.mapper;

import com.example.pojo.Image;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Mapper
@Repository
public interface ImageMapper {
    void add(Image image);

    void deleteById(int id);

    List<Image> selectAll();

    Image selectById(int id);

    Image selectByName(String name);

    void update(Image image);
}
