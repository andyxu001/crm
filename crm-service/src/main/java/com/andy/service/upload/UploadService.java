package com.andy.service.upload;

import com.aliyun.oss.OSSClient;
import com.andy.config.AliyunOssProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {

    @Autowired
    private AliyunOssProperties aliyunOssProperties;

    @Autowired
    private OSSClient client;

    public void uploadFile(MultipartFile file) {
        try {
            client.putObject(aliyunOssProperties.getBucket(),"private/"+file.getOriginalFilename(),file.getInputStream());
        }catch (Exception e) {
          e.printStackTrace();
        }finally {

        }
    }

}
