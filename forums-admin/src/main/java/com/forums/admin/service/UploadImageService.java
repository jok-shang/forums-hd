package com.forums.admin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @auther 尚智江
 * @Date 2023/4/17 23:33
 */
@Service
public interface UploadImageService {

    /**
     * 上传文件
     * @param file
     * @return
     */
    String uploadQNImg(MultipartFile file);
    String getPrivateFile(String fileKey);
}
