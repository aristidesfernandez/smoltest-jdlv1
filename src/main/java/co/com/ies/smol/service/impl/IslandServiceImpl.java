package co.com.ies.smol.service.impl;

import co.com.ies.smol.domain.Island;
import co.com.ies.smol.repository.IslandRepository;
import co.com.ies.smol.service.IslandService;
import co.com.ies.smol.service.dto.IslandDTO;
import co.com.ies.smol.service.mapper.IslandMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Island}.
 */
@Service
@Transactional
public class IslandServiceImpl implements IslandService {

    private final Logger log = LoggerFactory.getLogger(IslandServiceImpl.class);

    private final IslandRepository islandRepository;

    private final IslandMapper islandMapper;

    public IslandServiceImpl(IslandRepository islandRepository, IslandMapper islandMapper) {
        this.islandRepository = islandRepository;
        this.islandMapper = islandMapper;
    }

    @Override
    public IslandDTO save(IslandDTO islandDTO) {
        log.debug("Request to save Island : {}", islandDTO);
        Island island = islandMapper.toEntity(islandDTO);
        island = islandRepository.save(island);
        return islandMapper.toDto(island);
    }

    @Override
    public IslandDTO update(IslandDTO islandDTO) {
        log.debug("Request to update Island : {}", islandDTO);
        Island island = islandMapper.toEntity(islandDTO);
        island = islandRepository.save(island);
        return islandMapper.toDto(island);
    }

    @Override
    public Optional<IslandDTO> partialUpdate(IslandDTO islandDTO) {
        log.debug("Request to partially update Island : {}", islandDTO);

        return islandRepository
            .findById(islandDTO.getId())
            .map(existingIsland -> {
                islandMapper.partialUpdate(existingIsland, islandDTO);

                return existingIsland;
            })
            .map(islandRepository::save)
            .map(islandMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IslandDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Islands");
        return islandRepository.findAll(pageable).map(islandMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IslandDTO> findOne(Long id) {
        log.debug("Request to get Island : {}", id);
        return islandRepository.findById(id).map(islandMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Island : {}", id);
        islandRepository.deleteById(id);
    }
}
