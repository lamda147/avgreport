package avgreport.mobitv.net.vn.repository;

import avgreport.mobitv.net.vn.domain.ReportTime;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReportTime entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportTimeRepository extends JpaRepository<ReportTime, Long> {

}
