package avgreport.mobitv.net.vn.web.rest;

import com.codahale.metrics.annotation.Timed;
import avgreport.mobitv.net.vn.domain.Addresses;
import avgreport.mobitv.net.vn.repository.AddressesRepository;
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
 * REST controller for managing Addresses.
 */
@RestController
@RequestMapping("/api")
public class AddressesResource {

    private final Logger log = LoggerFactory.getLogger(AddressesResource.class);

    private static final String ENTITY_NAME = "addresses";

    private final AddressesRepository addressesRepository;

    public AddressesResource(AddressesRepository addressesRepository) {
        this.addressesRepository = addressesRepository;
    }

    /**
     * POST  /addresses : Create a new addresses.
     *
     * @param addresses the addresses to create
     * @return the ResponseEntity with status 201 (Created) and with body the new addresses, or with status 400 (Bad Request) if the addresses has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/addresses")
    @Timed
    public ResponseEntity<Addresses> createAddresses(@RequestBody Addresses addresses) throws URISyntaxException {
        log.debug("REST request to save Addresses : {}", addresses);
        if (addresses.getId() != null) {
            throw new BadRequestAlertException("A new addresses cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Addresses result = addressesRepository.save(addresses);
        return ResponseEntity.created(new URI("/api/addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /addresses : Updates an existing addresses.
     *
     * @param addresses the addresses to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated addresses,
     * or with status 400 (Bad Request) if the addresses is not valid,
     * or with status 500 (Internal Server Error) if the addresses couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/addresses")
    @Timed
    public ResponseEntity<Addresses> updateAddresses(@RequestBody Addresses addresses) throws URISyntaxException {
        log.debug("REST request to update Addresses : {}", addresses);
        if (addresses.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Addresses result = addressesRepository.save(addresses);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, addresses.getId().toString()))
            .body(result);
    }

    /**
     * GET  /addresses : get all the addresses.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of addresses in body
     */
    @GetMapping("/addresses")
    @Timed
    public ResponseEntity<List<Addresses>> getAllAddresses(Pageable pageable) {
        log.debug("REST request to get a page of Addresses");
        Page<Addresses> page = addressesRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/addresses");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /addresses/:id : get the "id" addresses.
     *
     * @param id the id of the addresses to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the addresses, or with status 404 (Not Found)
     */
    @GetMapping("/addresses/{id}")
    @Timed
    public ResponseEntity<Addresses> getAddresses(@PathVariable Long id) {
        log.debug("REST request to get Addresses : {}", id);
        Optional<Addresses> addresses = addressesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(addresses);
    }

    /**
     * DELETE  /addresses/:id : delete the "id" addresses.
     *
     * @param id the id of the addresses to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/addresses/{id}")
    @Timed
    public ResponseEntity<Void> deleteAddresses(@PathVariable Long id) {
        log.debug("REST request to delete Addresses : {}", id);

        addressesRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
