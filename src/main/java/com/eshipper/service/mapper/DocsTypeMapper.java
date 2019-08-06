package com.eshipper.service.mapper;

import com.eshipper.domain.*;
import com.eshipper.service.dto.DocsTypeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DocsType} and its DTO {@link DocsTypeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DocsTypeMapper extends EntityMapper<DocsTypeDTO, DocsType> {


    @Mapping(target = "soDocs", ignore = true)
    @Mapping(target = "removeSoDocs", ignore = true)
    DocsType toEntity(DocsTypeDTO docsTypeDTO);

    default DocsType fromId(Long id) {
        if (id == null) {
            return null;
        }
        DocsType docsType = new DocsType();
        docsType.setId(id);
        return docsType;
    }
}
