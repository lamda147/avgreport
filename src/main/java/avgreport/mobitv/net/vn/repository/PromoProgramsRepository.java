package avgreport.mobitv.net.vn.repository;

import avgreport.mobitv.net.vn.domain.PromoPrograms;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PromoPrograms entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PromoProgramsRepository extends JpaRepository<PromoPrograms, Long> {

}
