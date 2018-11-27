package avgreport.mobitv.net.vn.repository;

import avgreport.mobitv.net.vn.domain.Ouunits;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ouunits entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OuunitsRepository extends JpaRepository<Ouunits, Long> {

}
