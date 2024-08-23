package com.ai.AISite.service;

import com.ai.AISite.dto.DocumentDto;
import com.ai.AISite.entity.DocumentEntity;
import com.ai.AISite.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    // 문서 저장
    public DocumentDto saveDocument(DocumentDto documentDto) {
        DocumentEntity documentEntity = new DocumentEntity();
        documentEntity.setDocumentName(documentDto.getDocumentName());
        documentEntity.setContext(documentDto.getContext());

        DocumentEntity savedEntity = documentRepository.save(documentEntity);
        return new DocumentDto(savedEntity.getIdx(), savedEntity.getDocumentName(), savedEntity.getContext());
    }

    // 문서 조회
    public DocumentDto getDocument(Integer id) {
        Optional<DocumentEntity> documentEntity = documentRepository.findById(id);
        if (documentEntity.isPresent()) {
            DocumentEntity doc = documentEntity.get();
            return new DocumentDto(doc.getIdx(), doc.getDocumentName(), doc.getContext());
        } else {
            throw new RuntimeException("문서를 찾을 수 없습니다.");
        }
    }

    // 문서 목록 조회
    public List<DocumentDto> getDocumentList() {
        return documentRepository.findAll()
                .stream()
                .map(doc -> new DocumentDto(doc.getIdx(), doc.getDocumentName(), doc.getContext()))
                .collect(Collectors.toList());
    }

    // 문서 업데이트
    public DocumentDto updateDocument(Integer id, DocumentDto documentDto) {
        Optional<DocumentEntity> existingEntity = documentRepository.findById(id);
        if (existingEntity.isPresent()) {
            DocumentEntity documentEntity = existingEntity.get();
            documentEntity.setDocumentName(documentDto.getDocumentName());
            documentEntity.setContext(documentDto.getContext());

            DocumentEntity updatedEntity = documentRepository.save(documentEntity);
            return new DocumentDto(updatedEntity.getIdx(), updatedEntity.getDocumentName(), updatedEntity.getContext());
        } else {
            throw new RuntimeException("문서를 찾을 수 없습니다.");
        }
    }
}
