package co.com.ies.smol.service.impl;

import co.com.ies.smol.domain.Interfacing;
import co.com.ies.smol.repository.InterfacingRepository;
import co.com.ies.smol.service.InterfacingService;
import co.com.ies.smol.service.dto.InterfacingDTO;
import co.com.ies.smol.service.mapper.InterfacingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Interfacing}.
 */
@Service
@Transactional
public class InterfacingServiceImpl implements InterfacingService {

    private final Logger log = LoggerFactory.getLogger(InterfacingServiceImpl.class);

    private final InterfacingRepository interfacingRepository;

    private final InterfacingMapper interfacingMapper;

    public InterfacingServiceImpl(InterfacingRepository interfacingRepository, InterfacingMapper interfacingMapper) {
        this.interfacingRepository = interfacingRepository;
        this.interfacingMapper = interfacingMapper;
    }

    @Override
    public InterfacingDTO save(InterfacingDTO interfacingDTO) {
        log.debug("Request to save Interfacing : {}", interfacingDTO);
        Interfacing interfacing = interfacingMapper.toEntity(interfacingDTO);
        interfacing = interfacingRepository.save(interfacing);
        return interfacingMapper.toDto(interfacing);
    }

    @Override
    public InterfacingDTO update(InterfacingDTO interfacingDTO) {
        log.debug("Request to update Interfacing : {}", interfacingDTO);
        Interfacing interfacing = interfacingMapper.toEntity(interfacingDTO);
        interfacing = interfacingRepository.save(interfacing);
        return interfacingMapper.toDto(interfacing);
    }

    @Override
    public Optional<InterfacingDTO> partialUpdate(InterfacingDTO interfacingDTO) {
        log.debug("Request to partially update Interfacing : {}", interfacingDTO);

        return interfacingRepository
            .findById(interfacingDTO.getId())
            .map(existingInterfacing -> {
                interfacingMapper.partialUpdate(existingInterfacing, interfacingDTO);

                return existingInterfacing;
            })
            .map(interfacingRepository::save)
            .map(interfacingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InterfacingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Interfacings");
        return interfacingRepository.findAll(pageable).map(interfacingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InterfacingDTO> findOne(Long id) {
        log.debug("Request to get Interfacing : {}", id);
        return interfacingRepository.findById(id).map(interfacingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Interfacing : {}", id);
        interfacingRepository.deleteById(id);
    }
}
