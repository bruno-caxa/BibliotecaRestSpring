package curso.api.rest.aws.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.aws.model.UploadImageRequest;
import curso.api.rest.aws.model.UploadRequestResult;
import curso.api.rest.aws.service.StorageService;

@RestController
@RequestMapping(value = "/api/upload")
public class UploadController {
    
    @Autowired
    private StorageService storageService;

    @PostMapping
    public UploadRequestResult newImageUploadRequest(@RequestBody UploadImageRequest request) {
        return storageService.generateUploadUrl(request.toDomain());
    }
}
