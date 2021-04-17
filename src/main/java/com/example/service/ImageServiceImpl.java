package com.example.service;

import com.example.mapper.ImageMapper;
import com.example.pojo.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 橙鼠鼠
 */
@Service
public class ImageServiceImpl implements ImageService {
    ImageMapper imageMapper;

    @Autowired
    public void setImageMapper(ImageMapper imageMapper) {
        this.imageMapper = imageMapper;
    }

    @Override
    public void add(Image image) {
        imageMapper.add(image);
    }

    @Override
    public void deleteById(int id) {
        imageMapper.deleteById(id);
    }

    @Override
    public List<Image> selectAll() {
        return imageMapper.selectAll();
    }

    @Override
    public Image selectById(int id) {
        return imageMapper.selectById(id);
    }

    @Override
    public Image selectByName(String name) {
        return imageMapper.selectByName(name);
    }

    @Override
    public void update(Image image) {
        imageMapper.update(image);
    }
}
