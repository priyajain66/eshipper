package com.eshipper.repository;

import com.eshipper.domain.DocsType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DocsType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DocsTypeRepository extends JpaRepository<DocsType, Long> {

}
