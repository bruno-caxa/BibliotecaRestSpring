package curso.api.rest.aws.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class UploadImageRequest {

    @NotBlank
    private String fileName;

    @NotBlank
    private String contentType;

    @Min(1)
    private Long contentLength;

    public ImageReference toDomain() {
        return ImageReference.builder()
                             .name(fileName)
                             .contentType(contentType)
                             .contentLength(contentLength)
                             .build();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public void setContentLength(Long contentLength) {
        this.contentLength = contentLength;
    }
}
