package com.lab.service;

import com.lab.constant.MessageConstant;
import com.lab.dto.ImgDTO;
import com.lab.entity.Image;
import com.lab.exception.ImageHandleException;
import com.lab.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Slf4j
@Service
public class ImgService {
    @Autowired
    private AliOssUtil aliOssUtil;

    @Value("${web.python.env}")
    private String python;
    @Value("${web.python.commandTA}")
    private String commandTA;
    @Value("${web.python.result-dir}")
    private String resultDir;

    private String[] imageDtoToArgs(Image image) {
        // 准备使用commandTA.py处理图片使用的命令行指令及参数
        return new String[]{
                python, commandTA,
                "--img", image.getFile_path(),
                "--method", image.getAlgorithm(),
                "--cs", image.getColor_space(),
                "--name", image.getFilename(),
                "--format", image.getExtension()
        };
    }

    public String handle(ImgDTO imgDto) {
        Image image = new Image(imgDto);
        // 执行Python脚本
        String[] args = imageDtoToArgs(image);
        try {
            Process proc = Runtime.getRuntime().exec(args);
            proc.waitFor();
            // python程序错误处理
            byte[] bytes = proc.getErrorStream().readAllBytes();
            if (bytes.length > 0) {
                String error = new String(bytes);
                // 忽略warning
                if(!error.contains(MessageConstant.PYTHON_WARNING)){
                    log.error(new String(bytes));
                    throw new ImageHandleException(MessageConstant.PYTHON_ERROR);
                }
            } else {
                log.info(MessageConstant.IMG_HANDLE_SUCCESS);
            }
        } catch (IOException | InterruptedException e) {
            throw new ImageHandleException(e.getMessage());
        }

        // 上传至OSS
        File file = new File(resultDir + "/" + image.getFilename() + "." + image.getExtension());
        try (FileInputStream fis = new FileInputStream(file)) {
            String directory = aliOssUtil.createDateDir(false); // 创建日期目录
            // 上传文件至指定目录，并删除本地文件
            return aliOssUtil.upload(fis.readAllBytes(),
                    directory + "/" + image.getFilename() + "." + image.getExtension());
        } catch (IOException e) {
            throw new ImageHandleException(e.getMessage());
        } finally {
            file.delete();
        }
    }
}
