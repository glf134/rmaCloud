package com.rma.system.utils;

import com.alibaba.fastjson.JSONObject;
import io.minio.MinioClient;
import io.minio.ObjectStat;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

/**
 * 
 * @ClassName: MinioUtils
 * @Description: (minio工具类)
 * @date 2021/1/4 18:07
 */
@Component
public class MinioUtils {

    @Value("${min.io.endpoint}")
    private String endpoint;
    @Value("${min.io.accessKey}")
    private String accesskey;
    @Value("${min.io.secretKey}")
    private String secretkey;

    private MinioClient client() throws InvalidPortException, InvalidEndpointException {
        return new MinioClient(endpoint, accesskey, secretkey);
    }


    /**
     * @Title: createBucket
     * @Description: (创建bucket)
     * 
     * [bucketName] 桶名
     */
    public void createBucket(String bucketName) throws Exception {
        if (!client().bucketExists(bucketName)) {
            client().makeBucket(bucketName);
        }
    }

    /**
     * @Title: uploadFile
     * @Description: (获取上传文件信息上传文件)
     * 
     * [file 上传文件（MultipartFile）, bucketName 桶名]
     */
    public JSONObject uploadFile(MultipartFile file, String bucketName) throws Exception {
        JSONObject res = new JSONObject();
        res.put("code", 0);
        //判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        //判断存储桶是否存在  不存在则创建
        createBucket(bucketName);
        //文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名 = 存储桶文件名_时间戳.后缀名
        String fileName = bucketName + "_" +
                System.currentTimeMillis() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //开始上传
        client().putObject(bucketName, fileName, file.getInputStream(), file.getContentType());
        res.put("code", 1);
        res.put("fileName", fileName);
        res.put("msg", endpoint + "/" + bucketName + "/" + fileName);
        return res;
    }

    /**

     * @param filepath   文件路径，bucketName 桶名 Uuid UUID
     * @param uuid  文件UUID
     * @param bucketName com.alibaba.fastjson.JSONObject
     * @Title: uploadFile
     * @Description: 获取文件路径上传文件
     * 
     * @Title: uploadFile
     * @Description:
     * 
     */
    public JSONObject uploadFile(String filepath, String uuid, String bucketName) throws Exception {
        MultipartFile file = null;
        File oldFile = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        file = new MockMultipartFile(oldFile.getName(), oldFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        JSONObject res = new JSONObject();
        res.put("code", 0);
        //判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        //判断存储桶是否存在  不存在则创建
        createBucket(bucketName);
        //文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名 = 存储桶文件名_时间戳_UUID.后缀名
        String fileName = bucketName + "_" +
                System.currentTimeMillis() + "_" + uuid +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //开始上传
        client().putObject(bucketName, fileName, file.getInputStream(), file.getContentType());
        res.put("code", 1);
        res.put("fileName", fileName);
        res.put("msg", endpoint + "/" + bucketName + "/" + fileName);
        return res;
    }

    /**
     * @param bucketName com.alibaba.fastjson.JSONObject
     * @Title: uploadFile
     * @Description: 获取文件路径上传文件
     * 
     * *@param filepath 文件路径，bucketName 桶名
     */
    public JSONObject uploadFile(String filepath, String bucketName) throws Exception {
        MultipartFile file = null;
        File oldFile = new File(filepath);
        FileInputStream fileInputStream = new FileInputStream(oldFile);
        file = new MockMultipartFile(oldFile.getName(), oldFile.getName(), ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        JSONObject res = new JSONObject();
        res.put("code", 0);
        //判断文件是否为空
        if (null == file || 0 == file.getSize()) {
            res.put("msg", "上传文件不能为空");
            return res;
        }
        //判断存储桶是否存在  不存在则创建
        createBucket(bucketName);
        //文件名
        String originalFilename = file.getOriginalFilename();
        //新的文件名 = 存储桶文件名_时间戳_UUID.后缀名
        String fileName = bucketName + "_" +
                System.currentTimeMillis() +
                originalFilename.substring(originalFilename.lastIndexOf("."));
        //开始上传
        client().putObject(bucketName, fileName, file.getInputStream(), file.getContentType());
        res.put("code", 1);
        res.put("fileName", fileName);
        res.put("msg", endpoint + "/" + bucketName + "/" + fileName);
        return res;
    }

    /**
     * @Title: getAllBuckets
     * @Description: (获取全部bucket)
     * 
     * []
     */
    public List<Bucket> getAllBuckets() throws Exception {
        return client().listBuckets();
    }

    /**
     * @param bucketName bucket名称
     * @Title: getBucket
     * @Description: (根据bucketName获取信息)
     * 
     * [bucketName] 桶名
     */
    public Optional<Bucket> getBucket(String bucketName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, io.minio.errors.InsufficientDataException, io.minio.errors.InternalException, InvalidPortException, InvalidEndpointException {
        return client().listBuckets().stream().filter(b -> b.name().equals(bucketName)).findFirst();
    }

    /**
     * @param bucketName bucket名称
     * @Title: removeBucket
     * @Description: (根据bucketName删除信息)
     * 
     * [bucketName] 桶名
     */
    public void removeBucket(String bucketName) throws Exception {
        client().removeBucket(bucketName);
    }

    /**
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param expires    过期时间 <=7
     * @return url
     * @Title: getObjectURL
     * @Description: (获取 ⽂ 件外链)
     * 
     * [bucketName 桶名, objectName 文件名, expires 时间<=7]
     */
    public String getObjectUrl(String bucketName, String objectName, Integer expires) throws Exception {
        return client().presignedGetObject(bucketName, objectName, expires);
    }

    /**
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @return ⼆进制流
     * @Title: getObject
     * @Description: (获取文件)
     * 
     * [bucketName 桶名, objectName 文件名]
     */
    public InputStream getObject(String bucketName, String objectName) throws Exception {
        return client().getObject(bucketName, objectName);
    }

    /**
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @param stream     ⽂件流
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     * @Title: putObject
     * @Description: (上传文件)
     * 
     * [bucketName 桶名, objectName 文件名, stream ⽂件流]
     */
    public void putObject(String bucketName, String objectName, InputStream stream) throws
            Exception {
        client().putObject(bucketName, objectName, stream, stream.available(),
                "application/octet-stream");
    }

    /**
     * 上传⽂件
     *
     * @param bucketName  bucket名称
     * @param objectName  ⽂件名称
     * @param stream      ⽂件流
     * @param size        ⼤⼩
     * @param contextType 类型
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#putObject
     * @Title: putObject
     * @Description: $(文件流上传文件)
     * 
     * [bucketName, objectName, stream, size, contextType]
     */
    public void putObject(String bucketName, String objectName, InputStream stream, long
            size, String contextType) throws Exception {
        client().putObject(bucketName, objectName, stream, size, contextType);
    }

    /**
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @throws Exception https://docs.minio.io/cn/java-client-api-reference.html#statObject
     * @Title: getObjectInfo
     * @Description: (获取文件信息)
     * 
     * [bucketName, objectName]
     */
    public ObjectStat getObjectInfo(String bucketName, String objectName) throws Exception {
        return client().statObject(bucketName, objectName);
    }

    /**
     * @param bucketName bucket名称
     * @param objectName ⽂件名称
     * @throws Exception https://docs.minio.io/cn/java-client-apireference.html#removeObject
     * @Title: removeObject
     * @Description: (删除文件)
     * 
     * [bucketName, objectName]
     */
    public void removeObject(String bucketName, String objectName) throws Exception {
        client().removeObject(bucketName, objectName);
    }


}
