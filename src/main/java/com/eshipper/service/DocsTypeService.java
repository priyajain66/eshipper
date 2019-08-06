package com.eshipper.service;

import com.eshipper.service.dto.DocsTypeDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.eshipper.domain.DocsType}.
 */
public interface DocsTypeService {

    /**
     * Save a docsType.
     *
     * @param docsTypeDTO the entity to save.
     * @return the persisted entity.
     */
    DocsTypeDTO save(DocsTypeDTO docsTypeDTO);

    /**
     * Get all the docsTypes.
     *
     * @return the list of entities.
     */
    List<DocsTypeDTO> findAll();


    /**
     * Get the "id" docsType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DocsTypeDTO> findOne(Long id);

    /**
     * Delete the "id" docsType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
