package com.lab.labwebsite.service;

import com.lab.labwebsite.pojo.dto.ImgDTO;
import com.lab.labwebsite.utils.AliOssUtil;
import jakarta.servlet.annotation.HttpConstraint;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Slf4j
@Service
public class ImgService {
    @Autowired
    private AliOssUtil aliOssUtil;

    @Value("${web.python.env}")
    private String pyEnv;
    @Value("${web.python.program}")
    private String pyProgram;
    @Value("${web.python.result-dir}")
    private String resultDir;

    public String handle(ImgDTO image) {
        String filename = UUID.randomUUID().toString();
        String extension = image.getFile_path().substring(image.getFile_path().lastIndexOf(".") + 1);
        String[] args = new String[]{pyEnv, pyProgram,
                image.getFile_path(), image.getAlgorithm(), image.getColor_space(),
                filename, extension};

        try {
            // 执行Python脚本
            Process proc = Runtime.getRuntime().exec(args);
            proc.waitFor();
            log.info("图像处理成功：{}.{}", filename, extension);
        } catch (IOException | InterruptedException e) {
            log.error("图像处理失败：{}", e.getMessage());
        }

        try {
            // 上传至OSS
            FileInputStream fis = new FileInputStream(resultDir + "/" + filename + "." + extension);
            LocalDate date = LocalDate.now(); // 获取当前日期
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String directory = "output/" + date.format(formatter); // 创建日期目录
            String file_path = aliOssUtil.upload(fis.readAllBytes(),
                    directory + "/" + filename + "." + extension);
            fis.close();
            return file_path;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }
}
