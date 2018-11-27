package avgreport.mobitv.net.vn.web.rest;

import com.codahale.metrics.annotation.Timed;
import avgreport.mobitv.net.vn.domain.SubActiveCount;
import avgreport.mobitv.net.vn.repository.SubActiveCountRepository;
import avgreport.mobitv.net.vn.web.rest.errors.BadRequestAlertException;
import avgreport.mobitv.net.vn.web.rest.util.HeaderUtil;
import avgreport.mobitv.net.vn.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing SubActiveCount.
 */
@RestController
@RequestMapping("/api")
public class SubActiveCountResource {

    private final Logger log = LoggerFactory.getLogger(SubActiveCountResource.class);

    private static final String ENTITY_NAME = "subActiveCount";

    private final SubActiveCountRepository subActiveCountRepository;

    public SubActiveCountResource(SubActiveCountRepository subActiveCountRepository) {
        this.subActiveCountRepository = subActiveCountRepository;
    }

    /**
     * POST  /sub-active-counts : Create a new subActiveCount.
     *
     * @param subActiveCount the subActiveCount to create
     * @return the ResponseEntity with status 201 (Created) and with body the new subActiveCount, or with status 400 (Bad Request) if the subActiveCount has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sub-active-counts")
    @Timed
    public ResponseEntity<SubActiveCount> createSubActiveCount(@RequestBody SubActiveCount subActiveCount) throws URISyntaxException {
        log.debug("REST request to save SubActiveCount : {}", subActiveCount);
        if (subActiveCount.getId() != null) {
            throw new BadRequestAlertException("A new subActiveCount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SubActiveCount result = subActiveCountRepository.save(subActiveCount);
        return ResponseEntity.created(new URI("/api/sub-active-counts/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sub-active-counts : Updates an existing subActiveCount.
     *
     * @param subActiveCount the subActiveCount to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated subActiveCount,
     * or with status 400 (Bad Request) if the subActiveCount is not valid,
     * or with status 500 (Internal Server Error) if the subActiveCount couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sub-active-counts")
    @Timed
    public ResponseEntity<SubActiveCount> updateSubActiveCount(@RequestBody SubActiveCount subActiveCount) throws URISyntaxException {
        log.debug("REST request to update SubActiveCount : {}", subActiveCount);
        if (subActiveCount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SubActiveCount result = subActiveCountRepository.save(subActiveCount);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, subActiveCount.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sub-active-counts : get all the subActiveCounts.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of subActiveCounts in body
     */
    @GetMapping("/sub-active-counts")
    @Timed
    public ResponseEntity<List<SubActiveCount>> getAllSubActiveCounts(Pageable pageable) {
        log.debug("REST request to get a page of SubActiveCounts");
        Page<SubActiveCount> page = subActiveCountRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/sub-active-counts");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /sub-active-counts/:id : get the "id" subActiveCount.
     *
     * @param id the id of the subActiveCount to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the subActiveCount, or with status 404 (Not Found)
     */
    @GetMapping("/sub-active-counts/{id}")
    @Timed
    public ResponseEntity<SubActiveCount> getSubActiveCount(@PathVariable Long id) {
        log.debug("REST request to get SubActiveCount : {}", id);
        Optional<SubActiveCount> subActiveCount = subActiveCountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(subActiveCount);
    }

    /**
     * DELETE  /sub-active-counts/:id : delete the "id" subActiveCount.
     *
     * @param id the id of the subActiveCount to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sub-active-counts/{id}")
    @Timed
    public ResponseEntity<Void> deleteSubActiveCount(@PathVariable Long id) {
        log.debug("REST request to delete SubActiveCount : {}", id);

        subActiveCountRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
