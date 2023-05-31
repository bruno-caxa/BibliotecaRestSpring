package curso.api.rest.aws.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadRequestResult {
    private String uploadSignedUrl;
}
