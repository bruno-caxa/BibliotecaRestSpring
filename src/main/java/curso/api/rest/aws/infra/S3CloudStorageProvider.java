package curso.api.rest.aws.infra;

import java.net.URL;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import curso.api.rest.aws.core.StorageProperties;
import curso.api.rest.aws.model.CloudStorageProvider;
import curso.api.rest.aws.model.ImageReference;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration;
import software.amazon.awssdk.awscore.AwsRequestOverrideConfiguration.Builder;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Component
public class S3CloudStorageProvider implements CloudStorageProvider {
    
    @Autowired
    private S3Presigner s3Presigner;

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public URL generatePresignedUploadUrl(ImageReference imageReference) {
        Builder builder = AwsRequestOverrideConfiguration.builder();
        builder.putRawQueryParameter("x-amz-acl", "public-read");


        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                                                            .bucket(storageProperties.getS3().getBucket())
                                                            .key(imageReference.getPath())
                                                            .contentType(imageReference.getContentType())
                                                            .contentLength(imageReference.getContentLength())
                                                            .acl("public-read")
                                                            .overrideConfiguration(builder.build())
                                                            .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                                                                        .signatureDuration(Duration.ofMinutes(30))
                                                                        .putObjectRequest(putObjectRequest)
                                                                        .build();
        return s3Presigner.presignPutObject(presignRequest).url();
    }

}
