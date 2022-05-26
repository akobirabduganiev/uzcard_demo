package com.company.service;

import com.company.dto.CardDTO;
import com.company.entity.CardEntity;
import com.company.enums.GeneralStatus;
import com.company.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final ClientService clientService;

    public CardDTO create(CardDTO dto, String profileName) {
        clientService.getById(dto.getUuid(), profileName);

        var cardNum = getRandomNumberString();

        var optional = cardRepository.findByNumber(cardNum);

        while (optional.isPresent()) {
            cardNum = getRandomNumberString();
            optional = cardRepository.findByNumber(cardNum);
        }

        var entity = new CardEntity();

        entity.setClientUuid(dto.getClientUuid());
        entity.setNumber(dto.getNumber());
        entity.setStatus(GeneralStatus.ACTIVE);

        cardRepository.save(entity);

        dto.setCreatedDate(entity.getCreatedDate());
        dto.setUuid(entity.getUuid());
        dto.setNumber(entity.getNumber());

        return dto;
    }

    private String getRandomNumberString() {

        var rnd = new Random();
        int number = rnd.nextInt(99999999);

        return "8600" + String.format("%08d", number);
    }
}
