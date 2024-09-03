package com.lab.labwebsite.controller;

import com.lab.labwebsite.constant.MessageConstant;
import lombok.extern.slf4j.Slf4j;
import com.lab.labwebsite.utils.AliOssUtil;
import com.lab.labwebsite.pojo.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@RestController
@RequestMapping("/common")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传：{}", file);

        try {
            String originalFilename = file.getOriginalFilename(); // 获取文件原名
            String extensionName = originalFilename.substring(originalFilename.lastIndexOf(".")); // 获取文件扩展名
            LocalDate date = LocalDate.now(); // 获取当前日期
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String directory = "input/" + date.format(formatter); // 创建日期目录

            String filename = directory + "/" +
                    UUID.randomUUID().toString() + extensionName; // 创建唯一的文件名
            String filePath = aliOssUtil.upload(file.getBytes(), filename);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败：{}", e.getMessage());
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
