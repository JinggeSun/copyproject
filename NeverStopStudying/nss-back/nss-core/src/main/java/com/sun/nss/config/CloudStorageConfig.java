package com.sun.nss.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * 7牛云存储
 * 从yml获取配置，启动时，加载到bean里
 */
@Data
@Configuration
public class CloudStorageConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    @Value("${oss.qiniu.domain}")
    private String qiniuDomain;
    /**
     * 七牛路径前缀
     */
    @Value("${oss.qiniu.prefix}")
    private String qiniuPrefix;
    /**
     * 七牛ACCESS_KEY
     */
    @Value("${oss.qiniu.accessKey}")
    private String qiniuAccessKey;
    /**
     * 七牛SECRET_KEY
     */
    @Value("${oss.qiniu.secretKey}")
    private String qiniuSecretKey;
    /**
     * 七牛空间名
     */
    @Value("${oss.qiniu.bucketName}")
    private String qiniuBucketName;
}