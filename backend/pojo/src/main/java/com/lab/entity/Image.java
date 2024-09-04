package com.lab.entity;

import com.lab.dto.ImgDTO;
import lombok.Data;

import java.util.UUID;

@Data
public class Image {
    private String file_path;
    private String algorithm;
    private String color_space;
    private String filename;
    private String extension;

    public Image(ImgDTO image) {
        this.file_path = image.getFile_path();
        this.algorithm = image.getAlgorithm();
        this.color_space = image.getColor_space();
        this.filename = UUID.randomUUID().toString();
        this.extension = image.getFile_path().substring(image.getFile_path().lastIndexOf(".") + 1);
    }
}
