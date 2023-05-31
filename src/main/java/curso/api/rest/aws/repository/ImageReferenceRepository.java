package curso.api.rest.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import curso.api.rest.aws.model.ImageReference;

@Repository
public interface ImageReferenceRepository extends JpaRepository<ImageReference, Long> {
    
}
