package avgreport.mobitv.net.vn.web.rest;

import com.codahale.metrics.annotation.Timed;
import avgreport.mobitv.net.vn.domain.Ouunits;
import avgreport.mobitv.net.vn.repository.OuunitsRepository;
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
 * REST controller for managing Ouunits.
 */
@RestController
@RequestMapping("/api")
public class OuunitsResource {

    private final Logger log = LoggerFactory.getLogger(OuunitsResource.class);

    private static final String ENTITY_NAME = "ouunits";

    private final OuunitsRepository ouunitsRepository;

    public OuunitsResource(OuunitsRepository ouunitsRepository) {
        this.ouunitsRepository = ouunitsRepository;
    }

    /**
     * POST  /ouunits : Create a new ouunits.
     *
     * @param ouunits the ouunits to create
     * @return the ResponseEntity with status 201 (Created) and with body the new ouunits, or with status 400 (Bad Request) if the ouunits has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/ouunits")
    @Timed
    public ResponseEntity<Ouunits> createOuunits(@RequestBody Ouunits ouunits) throws URISyntaxException {
        log.debug("REST request to save Ouunits : {}", ouunits);
        if (ouunits.getId() != null) {
            throw new BadRequestAlertException("A new ouunits cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Ouunits result = ouunitsRepository.save(ouunits);
        return ResponseEntity.created(new URI("/api/ouunits/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /ouunits : Updates an existing ouunits.
     *
     * @param ouunits the ouunits to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated ouunits,
     * or with status 400 (Bad Request) if the ouunits is not valid,
     * or with status 500 (Internal Server Error) if the ouunits couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/ouunits")
    @Timed
    public ResponseEntity<Ouunits> updateOuunits(@RequestBody Ouunits ouunits) throws URISyntaxException {
        log.debug("REST request to update Ouunits : {}", ouunits);
        if (ouunits.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Ouunits result = ouunitsRepository.save(ouunits);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ouunits.getId().toString()))
            .body(result);
    }

    /**
     * GET  /ouunits : get all the ouunits.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of ouunits in body
     */
    @GetMapping("/ouunits")
    @Timed
    public ResponseEntity<List<Ouunits>> getAllOuunits(Pageable pageable) {
        log.debug("REST request to get a page of Ouunits");
        Page<Ouunits> page = ouunitsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/ouunits");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /ouunits/:id : get the "id" ouunits.
     *
     * @param id the id of the ouunits to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the ouunits, or with status 404 (Not Found)
     */
    @GetMapping("/ouunits/{id}")
    @Timed
    public ResponseEntity<Ouunits> getOuunits(@PathVariable Long id) {
        log.debug("REST request to get Ouunits : {}", id);
        Optional<Ouunits> ouunits = ouunitsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(ouunits);
    }

    /**
     * DELETE  /ouunits/:id : delete the "id" ouunits.
     *
     * @param id the id of the ouunits to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/ouunits/{id}")
    @Timed
    public ResponseEntity<Void> deleteOuunits(@PathVariable Long id) {
        log.debug("REST request to delete Ouunits : {}", id);

        ouunitsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
