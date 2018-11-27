package avgreport.mobitv.net.vn.web.rest;

import com.codahale.metrics.annotation.Timed;
import avgreport.mobitv.net.vn.domain.PromoPrograms;
import avgreport.mobitv.net.vn.repository.PromoProgramsRepository;
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
 * REST controller for managing PromoPrograms.
 */
@RestController
@RequestMapping("/api")
public class PromoProgramsResource {

    private final Logger log = LoggerFactory.getLogger(PromoProgramsResource.class);

    private static final String ENTITY_NAME = "promoPrograms";

    private final PromoProgramsRepository promoProgramsRepository;

    public PromoProgramsResource(PromoProgramsRepository promoProgramsRepository) {
        this.promoProgramsRepository = promoProgramsRepository;
    }

    /**
     * POST  /promo-programs : Create a new promoPrograms.
     *
     * @param promoPrograms the promoPrograms to create
     * @return the ResponseEntity with status 201 (Created) and with body the new promoPrograms, or with status 400 (Bad Request) if the promoPrograms has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/promo-programs")
    @Timed
    public ResponseEntity<PromoPrograms> createPromoPrograms(@RequestBody PromoPrograms promoPrograms) throws URISyntaxException {
        log.debug("REST request to save PromoPrograms : {}", promoPrograms);
        if (promoPrograms.getId() != null) {
            throw new BadRequestAlertException("A new promoPrograms cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PromoPrograms result = promoProgramsRepository.save(promoPrograms);
        return ResponseEntity.created(new URI("/api/promo-programs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /promo-programs : Updates an existing promoPrograms.
     *
     * @param promoPrograms the promoPrograms to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated promoPrograms,
     * or with status 400 (Bad Request) if the promoPrograms is not valid,
     * or with status 500 (Internal Server Error) if the promoPrograms couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/promo-programs")
    @Timed
    public ResponseEntity<PromoPrograms> updatePromoPrograms(@RequestBody PromoPrograms promoPrograms) throws URISyntaxException {
        log.debug("REST request to update PromoPrograms : {}", promoPrograms);
        if (promoPrograms.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PromoPrograms result = promoProgramsRepository.save(promoPrograms);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, promoPrograms.getId().toString()))
            .body(result);
    }

    /**
     * GET  /promo-programs : get all the promoPrograms.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of promoPrograms in body
     */
    @GetMapping("/promo-programs")
    @Timed
    public List<PromoPrograms> getAllPromoPrograms() {
        log.debug("REST request to get all PromoPrograms");
        return promoProgramsRepository.findAll();
    }

    /**
     * GET  /promo-programs/:id : get the "id" promoPrograms.
     *
     * @param id the id of the promoPrograms to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the promoPrograms, or with status 404 (Not Found)
     */
    @GetMapping("/promo-programs/{id}")
    @Timed
    public ResponseEntity<PromoPrograms> getPromoPrograms(@PathVariable Long id) {
        log.debug("REST request to get PromoPrograms : {}", id);
        Optional<PromoPrograms> promoPrograms = promoProgramsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(promoPrograms);
    }

    /**
     * DELETE  /promo-programs/:id : delete the "id" promoPrograms.
     *
     * @param id the id of the promoPrograms to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/promo-programs/{id}")
    @Timed
    public ResponseEntity<Void> deletePromoPrograms(@PathVariable Long id) {
        log.debug("REST request to delete PromoPrograms : {}", id);

        promoProgramsRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
