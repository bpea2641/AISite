package com.ai.AISite.repository;

import com.ai.AISite.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<DocumentEntity, Integer> {
    DocumentEntity findByDocumentName(String documentName);

}
