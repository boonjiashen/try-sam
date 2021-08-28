package me.jiashenb.trysam;

import org.apache.commons.lang3.StringUtils;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Lists the child folders of a parent S3 folder, e.g., of "s3://bucket/parent-folder/"
 *
 * <p>Similar to {@code aws s3 ls bucket/parent-folder/} except that the list excludes objects</p>
 */
public class S3FolderLister {

    private static final String BUCKET = "jiashenb-691456347435-ap-northeast-1";
    private static final S3Client S3 = S3Client.builder().build();

    public static void main(String[] args) {
        String parentFolder = "partitions/";
        ListObjectsV2Request listObjectsV2Request = ListObjectsV2Request.builder().bucket(BUCKET)
                .prefix(parentFolder)
                .delimiter("/")
                .build();
        List<String> childFolders = S3.listObjectsV2(listObjectsV2Request)
                .commonPrefixes()
                .stream().map(prefix -> StringUtils.removeStart(prefix.prefix(), parentFolder))
                .collect(Collectors.toList());
        System.out.println(childFolders);
    }
}
