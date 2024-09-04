package com.lab.controller;

import com.lab.dto.ImgDTO;
import com.lab.result.Result;
import com.lab.service.ImgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/img")
public class ImgController {
    @Autowired
    private ImgService imgService;

    // TODO 编写全局跨域配置类
    @PostMapping
    public Result<String> handleImg(ImgDTO image) {
        log.info("处理图片：{}", image);
        String file_path = imgService.handle(image);
        return Result.success(file_path);
    }

}
