package com.eshipper.repository;

import com.eshipper.domain.SoDocs;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SoDocs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SoDocsRepository extends JpaRepository<SoDocs, Long> {

}
