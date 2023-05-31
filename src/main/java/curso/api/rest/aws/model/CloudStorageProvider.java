package curso.api.rest.aws.model;

import java.net.URL;

public interface CloudStorageProvider {

    URL generatePresignedUploadUrl(ImageReference imageReference);
}
