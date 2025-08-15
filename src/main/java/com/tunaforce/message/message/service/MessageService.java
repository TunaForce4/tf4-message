package com.tunaforce.message.message.service;


import com.tunaforce.message.message.dto.request.CreateLogRequestDto;
import com.tunaforce.message.message.dto.response.CreateLogResponseDto;
import com.tunaforce.message.message.entity.MessageManagement;
import com.tunaforce.message.message.repository.MessageCreateJPaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final MessageCreateJPaRepository messageCreateJPaRepository;

    public CreateLogResponseDto create(CreateLogRequestDto createLogRequestDto) {
        MessageManagement messageManagement = ;
        messageCreateJPaRepository.save();
        return
    }
}
