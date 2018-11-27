package avgreport.mobitv.net.vn.web.rest;

import com.codahale.metrics.annotation.Timed;
import avgreport.mobitv.net.vn.domain.ReportTime;
import avgreport.mobitv.net.vn.repository.ReportTimeRepository;
import avgreport.mobitv.net.vn.web.rest.errors.BadRequestAlertException;
import avgreport.mobitv.net.vn.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing ReportTime.
 */
@RestController
@RequestMapping("/api")
public class ReportTimeResource {

    private final Logger log = LoggerFactory.getLogger(ReportTimeResource.class);

    private static final String ENTITY_NAME = "reportTime";

    private final ReportTimeRepository reportTimeRepository;

    public ReportTimeResource(ReportTimeRepository reportTimeRepository) {
        this.reportTimeRepository = reportTimeRepository;
    }

    /**
     * POST  /report-times : Create a new reportTime.
     *
     * @param reportTime the reportTime to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reportTime, or with status 400 (Bad Request) if the reportTime has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/report-times")
    @Timed
    public ResponseEntity<ReportTime> createReportTime(@RequestBody ReportTime reportTime) throws URISyntaxException {
        log.debug("REST request to save ReportTime : {}", reportTime);
        if (reportTime.getId() != null) {
            throw new BadRequestAlertException("A new reportTime cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReportTime result = reportTimeRepository.save(reportTime);
        return ResponseEntity.created(new URI("/api/report-times/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /report-times : Updates an existing reportTime.
     *
     * @param reportTime the reportTime to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reportTime,
     * or with status 400 (Bad Request) if the reportTime is not valid,
     * or with status 500 (Internal Server Error) if the reportTime couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/report-times")
    @Timed
    public ResponseEntity<ReportTime> updateReportTime(@RequestBody ReportTime reportTime) throws URISyntaxException {
        log.debug("REST request to update ReportTime : {}", reportTime);
        if (reportTime.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ReportTime result = reportTimeRepository.save(reportTime);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reportTime.getId().toString()))
            .body(result);
    }

    /**
     * GET  /report-times : get all the reportTimes.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reportTimes in body
     */
    @GetMapping("/report-times")
    @Timed
    public List<ReportTime> getAllReportTimes() {
        log.debug("REST request to get all ReportTimes");
        return reportTimeRepository.findAll();
    }

    /**
     * GET  /report-times/:id : get the "id" reportTime.
     *
     * @param id the id of the reportTime to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reportTime, or with status 404 (Not Found)
     */
    @GetMapping("/report-times/{id}")
    @Timed
    public ResponseEntity<ReportTime> getReportTime(@PathVariable Long id) {
        log.debug("REST request to get ReportTime : {}", id);
        Optional<ReportTime> reportTime = reportTimeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(reportTime);
    }

    /**
     * DELETE  /report-times/:id : delete the "id" reportTime.
     *
     * @param id the id of the reportTime to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/report-times/{id}")
    @Timed
    public ResponseEntity<Void> deleteReportTime(@PathVariable Long id) {
        log.debug("REST request to delete ReportTime : {}", id);

        reportTimeRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
