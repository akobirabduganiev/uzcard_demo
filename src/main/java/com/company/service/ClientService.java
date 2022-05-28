package com.company.service;

import com.company.dto.ChangeClientDetailDTO;
import com.company.dto.ChangeClientPhoneDTO;
import com.company.dto.ClientDTO;
import com.company.dto.UserDetailDTO;
import com.company.entity.ClientEntity;
import com.company.exceptions.AppBadRequestException;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientDTO create(UserDetailDTO dto, String profileName) {
        var entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setPhone(dto.getPhone());
        entity.setProfileName(profileName);
        entity.setStatus(dto.getStatus());

        clientRepository.save(entity);

        var clientDTO = new ClientDTO();
        clientDTO.setCreatedDate(entity.getCreatedDate());
        clientDTO.setUuid(entity.getId());

        return clientDTO;
    }

    public String update(ChangeClientDetailDTO dto) {

        var entity = clientRepository.findById(dto.getUuid())
                .orElseThrow(() -> new ItemNotFoundException(""));

        if (dto.getName() != null)
            entity.setName(dto.getName());
        if (dto.getSurname() != null)
            entity.setSurname(dto.getSurname());
        if (dto.getStatus() != null)
            entity.setStatus(dto.getStatus());
        entity.setLastModifiedDate(LocalDateTime.now());

        clientRepository.save(entity);

        return "updated successfully";
    }
    public ClientDTO updateStatus(ChangeClientDetailDTO dto, String clientId) {
        ClientEntity entity = getById(clientId);

        if (dto.getStatus().equals(entity.getStatus())) {
            return toDTO(entity);
        }

        entity.setStatus(dto.getStatus());

        clientRepository.save(entity);
        return toDTO(entity);
    }
    public String updatePhone(ChangeClientPhoneDTO dto) {
        ClientEntity entity = clientRepository.findById(dto.getClientId()).orElseThrow(
                () -> new ItemNotFoundException("client not found"));
        entity.setPhone(dto.getNewPhone());
        entity.setLastModifiedDate(LocalDateTime.now());

        clientRepository.save(entity);

        return "client phone number updated successfully!";
    }

    public List<ClientDTO> paginationList(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdDate"));

        var list = clientRepository.findAll(pageable).stream().map(this::toDTO).toList();
        if (list.isEmpty()) throw new ItemNotFoundException("group time table list is empty!");

        return list;
    }

    public ClientDTO getById(String uuid, String profileName) {
        var entity = clientRepository.findById(uuid)
                .orElseThrow(() -> new ItemNotFoundException("client not found"));

        if (!profileName.equals("admin") ||
                !clientRepository.findByProfileName(profileName)
                        .orElseThrow().getProfileName().equals(profileName))
            throw new AppBadRequestException("not authorized!");

        return toDTO(entity);
    }

    public ClientEntity getById(String clientId) {
        return clientRepository
                .findById(clientId)
                .orElseThrow(() -> new ItemNotFoundException("Not found!"));
    }

    public ClientDTO toDTO(ClientEntity entity) {
        var dto = new ClientDTO();

        dto.setUuid(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhone(entity.getPhone());
        dto.setStatus(entity.getStatus());
        dto.setProfileName(entity.getProfileName());
        dto.setLastModifiedDate(entity.getLastModifiedDate());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

}
