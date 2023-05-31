package curso.api.rest.aws.model;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;

@Builder
@Entity
@Table(name = "tb_image_reference")
public class ImageReference implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private OffsetDateTime createdAt;

    private String name;
    private String contentType;
    private Long contentLength;

    @Builder.Default
    private boolean temp = true;
    
    @Builder.Default
    private String type = "IMAGE";

    protected ImageReference() {

    }

    public ImageReference(
        Long id, 
        OffsetDateTime createdAt, 
        String name, 
        String contentType, 
        Long contentLength,
        boolean temp, 
        String type
    ) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(contentType);
        Objects.requireNonNull(contentLength);
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.temp = temp;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    public Long getContentLength() {
        return contentLength;
    }

    public String getPath() {
        return "images/" + name;
    }

    public boolean getTemp() {
        return temp;
    }

    public void setTemp(boolean temp) {
        this.temp = temp;
    }

    public String getType() {
        return type;
    }

}
