package com.company.service;

import com.company.dto.CardDTO;
import com.company.dto.CardFilterDTO;
import com.company.dto.ChangeCardStatusDTO;
import com.company.dto.ClientDTO;
import com.company.entity.CardEntity;
import com.company.enums.GeneralStatus;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class CardService {
    private final CardRepository cardRepository;
    private final ClientService clientService;

    public CardDTO create(CardDTO dto, String profileName) {
        clientService.getById(dto.getId(), profileName);

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
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());

        return dto;
    }

    public String changeStatus(ChangeCardStatusDTO dto) {
        cardRepository.findById(dto.getUuid())
                .orElseThrow(() -> new ItemNotFoundException("card not found"));

        cardRepository.changeStatus(dto.getStatus(), dto.getUuid());
        cardRepository.updateLastModifiedDate(LocalDateTime.now(), dto.getUuid());

        return "card status updated successfully!";
    }

    public List<CardDTO> getCardListByClientId(String clientId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        return cardRepository
                .findAllByClientId(sort, clientId)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public List<CardDTO> getCardListByPhone(String phone) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");

        return cardRepository
                .findAllByPhone(sort, phone)
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CardEntity getByCardNumber(String cardNumber) {
        return cardRepository
                .findByNumber(cardNumber)
                .orElse(null);
    }

    public List<CardDTO> filter(CardFilterDTO dto) {
        return null;
    }

    /**
     * OTHER METHODS
     **/

    private String getRandomNumberString() {

        var rnd = new Random();
        int number = rnd.nextInt(99999999);

        return "8600" + String.format("%08d", number);
    }

    public String balanceToSum(Long balance) {
        String cash = balance.toString();
        if (cash.equals("0")) {
            return "0 sum";
        }
        if (cash.length() <= 2) {
            return "0,0" + balance + " sum";
        }
        if (cash.length() <= 3) {
            return "0," + balance + " sum";
        }
        return cash.substring(0, cash.length() - 2) + "," + cash.substring(cash.length() - 2) + " sum";
    }

    public CardDTO toDTO(CardEntity entity) {
        CardDTO dto = new CardDTO();
        dto.setId(entity.getId());
        dto.setNumber(entity.getNumber());
        dto.setExpiredDate(entity.getExpiredDate());
        dto.setStatus(entity.getStatus());
        dto.setBalance(balanceToSum(entity.getBalance()));

        if (Optional.ofNullable(entity.getClient()).isPresent()) {
            ClientDTO clientDTO = new ClientDTO();
            clientDTO.setName(entity.getClient().getName());
            clientDTO.setSurname(entity.getClient().getSurname());
            dto.setClient(entity.getClient());
        }

        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
