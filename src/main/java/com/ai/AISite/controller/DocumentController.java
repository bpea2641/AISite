package com.ai.AISite.controller;

import com.ai.AISite.dto.DocumentDto;
import com.ai.AISite.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final DocumentService documentService;

    // 문서 저장
    @PostMapping
    public DocumentDto saveDocument(@RequestBody DocumentDto documentDto) {
        return documentService.saveDocument(documentDto);
    }

    // 문서 조회
    @GetMapping("/{id}")
    public DocumentDto getDocument(@PathVariable Integer id) {
        return documentService.getDocument(id);
    }

    // 문서 목록 조회
    @GetMapping
    public List<DocumentDto> getDocumentList() {
        return documentService.getDocumentList();
    }

    // 문서 업데이트
    @PutMapping("/{id}")
    public DocumentDto updateDocument(@PathVariable Integer id, @RequestBody DocumentDto documentDto) {
        return documentService.updateDocument(id, documentDto);
    }
}
