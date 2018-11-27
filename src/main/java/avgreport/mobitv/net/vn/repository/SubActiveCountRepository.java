package avgreport.mobitv.net.vn.repository;

import avgreport.mobitv.net.vn.domain.SubActiveCount;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SubActiveCount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubActiveCountRepository extends JpaRepository<SubActiveCount, Long> {

}
