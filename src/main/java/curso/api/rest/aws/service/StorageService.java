package curso.api.rest.aws.service;

import java.net.URL;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.api.rest.aws.model.CloudStorageProvider;
import curso.api.rest.aws.model.ImageReference;
import curso.api.rest.aws.model.UploadRequestResult;

@Service
public class StorageService {

    @Autowired
    private CloudStorageProvider cloudStorageProvider;

    public UploadRequestResult generateUploadUrl(ImageReference imageReference) {
        Objects.requireNonNull(imageReference);
        URL presignedUploadUrl = cloudStorageProvider.generatePresignedUploadUrl(imageReference);
        return new UploadRequestResult(presignedUploadUrl.toString());
    }

}
