package avgreport.mobitv.net.vn.web.rest;

import avgreport.mobitv.net.vn.AvgReportApp;

import avgreport.mobitv.net.vn.domain.Ouunits;
import avgreport.mobitv.net.vn.repository.OuunitsRepository;
import avgreport.mobitv.net.vn.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;


import static avgreport.mobitv.net.vn.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the OuunitsResource REST controller.
 *
 * @see OuunitsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvgReportApp.class)
public class OuunitsResourceIntTest {

    private static final String DEFAULT_OUUNITID = "AAAAAAAAAA";
    private static final String UPDATED_OUUNITID = "BBBBBBBBBB";

    private static final String DEFAULT_OUUNITNAME = "AAAAAAAAAA";
    private static final String UPDATED_OUUNITNAME = "BBBBBBBBBB";

    private static final String DEFAULT_PARENTOUUNIT = "AAAAAAAAAA";
    private static final String UPDATED_PARENTOUUNIT = "BBBBBBBBBB";

    private static final String DEFAULT_GRANDPARENTOUUNIT = "AAAAAAAAAA";
    private static final String UPDATED_GRANDPARENTOUUNIT = "BBBBBBBBBB";

    private static final String DEFAULT_OUUNITUSERFIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_OUUNITUSERFIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_OUUNITUSERFIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_OUUNITUSERFIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_OUUNITUSERFIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_OUUNITUSERFIELD_3 = "BBBBBBBBBB";

    private static final Instant DEFAULT_OUUNIT_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OUUNIT_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_OUUNIT_LAST_UPDATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_OUUNIT_LAST_UPDATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private OuunitsRepository ouunitsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restOuunitsMockMvc;

    private Ouunits ouunits;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OuunitsResource ouunitsResource = new OuunitsResource(ouunitsRepository);
        this.restOuunitsMockMvc = MockMvcBuilders.standaloneSetup(ouunitsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ouunits createEntity(EntityManager em) {
        Ouunits ouunits = new Ouunits()
            .ouunitid(DEFAULT_OUUNITID)
            .ouunitname(DEFAULT_OUUNITNAME)
            .parentouunit(DEFAULT_PARENTOUUNIT)
            .grandparentouunit(DEFAULT_GRANDPARENTOUUNIT)
            .ouunituserfield1(DEFAULT_OUUNITUSERFIELD_1)
            .ouunituserfield2(DEFAULT_OUUNITUSERFIELD_2)
            .ouunituserfield3(DEFAULT_OUUNITUSERFIELD_3)
            .ouunitDateCreated(DEFAULT_OUUNIT_DATE_CREATED)
            .ouunitLastUpdated(DEFAULT_OUUNIT_LAST_UPDATED)
            .isDeleted(DEFAULT_IS_DELETED);
        return ouunits;
    }

    @Before
    public void initTest() {
        ouunits = createEntity(em);
    }

    @Test
    @Transactional
    public void createOuunits() throws Exception {
        int databaseSizeBeforeCreate = ouunitsRepository.findAll().size();

        // Create the Ouunits
        restOuunitsMockMvc.perform(post("/api/ouunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ouunits)))
            .andExpect(status().isCreated());

        // Validate the Ouunits in the database
        List<Ouunits> ouunitsList = ouunitsRepository.findAll();
        assertThat(ouunitsList).hasSize(databaseSizeBeforeCreate + 1);
        Ouunits testOuunits = ouunitsList.get(ouunitsList.size() - 1);
        assertThat(testOuunits.getOuunitid()).isEqualTo(DEFAULT_OUUNITID);
        assertThat(testOuunits.getOuunitname()).isEqualTo(DEFAULT_OUUNITNAME);
        assertThat(testOuunits.getParentouunit()).isEqualTo(DEFAULT_PARENTOUUNIT);
        assertThat(testOuunits.getGrandparentouunit()).isEqualTo(DEFAULT_GRANDPARENTOUUNIT);
        assertThat(testOuunits.getOuunituserfield1()).isEqualTo(DEFAULT_OUUNITUSERFIELD_1);
        assertThat(testOuunits.getOuunituserfield2()).isEqualTo(DEFAULT_OUUNITUSERFIELD_2);
        assertThat(testOuunits.getOuunituserfield3()).isEqualTo(DEFAULT_OUUNITUSERFIELD_3);
        assertThat(testOuunits.getOuunitDateCreated()).isEqualTo(DEFAULT_OUUNIT_DATE_CREATED);
        assertThat(testOuunits.getOuunitLastUpdated()).isEqualTo(DEFAULT_OUUNIT_LAST_UPDATED);
        assertThat(testOuunits.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createOuunitsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ouunitsRepository.findAll().size();

        // Create the Ouunits with an existing ID
        ouunits.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restOuunitsMockMvc.perform(post("/api/ouunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ouunits)))
            .andExpect(status().isBadRequest());

        // Validate the Ouunits in the database
        List<Ouunits> ouunitsList = ouunitsRepository.findAll();
        assertThat(ouunitsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllOuunits() throws Exception {
        // Initialize the database
        ouunitsRepository.saveAndFlush(ouunits);

        // Get all the ouunitsList
        restOuunitsMockMvc.perform(get("/api/ouunits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ouunits.getId().intValue())))
            .andExpect(jsonPath("$.[*].ouunitid").value(hasItem(DEFAULT_OUUNITID.toString())))
            .andExpect(jsonPath("$.[*].ouunitname").value(hasItem(DEFAULT_OUUNITNAME.toString())))
            .andExpect(jsonPath("$.[*].parentouunit").value(hasItem(DEFAULT_PARENTOUUNIT.toString())))
            .andExpect(jsonPath("$.[*].grandparentouunit").value(hasItem(DEFAULT_GRANDPARENTOUUNIT.toString())))
            .andExpect(jsonPath("$.[*].ouunituserfield1").value(hasItem(DEFAULT_OUUNITUSERFIELD_1.toString())))
            .andExpect(jsonPath("$.[*].ouunituserfield2").value(hasItem(DEFAULT_OUUNITUSERFIELD_2.toString())))
            .andExpect(jsonPath("$.[*].ouunituserfield3").value(hasItem(DEFAULT_OUUNITUSERFIELD_3.toString())))
            .andExpect(jsonPath("$.[*].ouunitDateCreated").value(hasItem(DEFAULT_OUUNIT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].ouunitLastUpdated").value(hasItem(DEFAULT_OUUNIT_LAST_UPDATED.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getOuunits() throws Exception {
        // Initialize the database
        ouunitsRepository.saveAndFlush(ouunits);

        // Get the ouunits
        restOuunitsMockMvc.perform(get("/api/ouunits/{id}", ouunits.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(ouunits.getId().intValue()))
            .andExpect(jsonPath("$.ouunitid").value(DEFAULT_OUUNITID.toString()))
            .andExpect(jsonPath("$.ouunitname").value(DEFAULT_OUUNITNAME.toString()))
            .andExpect(jsonPath("$.parentouunit").value(DEFAULT_PARENTOUUNIT.toString()))
            .andExpect(jsonPath("$.grandparentouunit").value(DEFAULT_GRANDPARENTOUUNIT.toString()))
            .andExpect(jsonPath("$.ouunituserfield1").value(DEFAULT_OUUNITUSERFIELD_1.toString()))
            .andExpect(jsonPath("$.ouunituserfield2").value(DEFAULT_OUUNITUSERFIELD_2.toString()))
            .andExpect(jsonPath("$.ouunituserfield3").value(DEFAULT_OUUNITUSERFIELD_3.toString()))
            .andExpect(jsonPath("$.ouunitDateCreated").value(DEFAULT_OUUNIT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.ouunitLastUpdated").value(DEFAULT_OUUNIT_LAST_UPDATED.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingOuunits() throws Exception {
        // Get the ouunits
        restOuunitsMockMvc.perform(get("/api/ouunits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateOuunits() throws Exception {
        // Initialize the database
        ouunitsRepository.saveAndFlush(ouunits);

        int databaseSizeBeforeUpdate = ouunitsRepository.findAll().size();

        // Update the ouunits
        Ouunits updatedOuunits = ouunitsRepository.findById(ouunits.getId()).get();
        // Disconnect from session so that the updates on updatedOuunits are not directly saved in db
        em.detach(updatedOuunits);
        updatedOuunits
            .ouunitid(UPDATED_OUUNITID)
            .ouunitname(UPDATED_OUUNITNAME)
            .parentouunit(UPDATED_PARENTOUUNIT)
            .grandparentouunit(UPDATED_GRANDPARENTOUUNIT)
            .ouunituserfield1(UPDATED_OUUNITUSERFIELD_1)
            .ouunituserfield2(UPDATED_OUUNITUSERFIELD_2)
            .ouunituserfield3(UPDATED_OUUNITUSERFIELD_3)
            .ouunitDateCreated(UPDATED_OUUNIT_DATE_CREATED)
            .ouunitLastUpdated(UPDATED_OUUNIT_LAST_UPDATED)
            .isDeleted(UPDATED_IS_DELETED);

        restOuunitsMockMvc.perform(put("/api/ouunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOuunits)))
            .andExpect(status().isOk());

        // Validate the Ouunits in the database
        List<Ouunits> ouunitsList = ouunitsRepository.findAll();
        assertThat(ouunitsList).hasSize(databaseSizeBeforeUpdate);
        Ouunits testOuunits = ouunitsList.get(ouunitsList.size() - 1);
        assertThat(testOuunits.getOuunitid()).isEqualTo(UPDATED_OUUNITID);
        assertThat(testOuunits.getOuunitname()).isEqualTo(UPDATED_OUUNITNAME);
        assertThat(testOuunits.getParentouunit()).isEqualTo(UPDATED_PARENTOUUNIT);
        assertThat(testOuunits.getGrandparentouunit()).isEqualTo(UPDATED_GRANDPARENTOUUNIT);
        assertThat(testOuunits.getOuunituserfield1()).isEqualTo(UPDATED_OUUNITUSERFIELD_1);
        assertThat(testOuunits.getOuunituserfield2()).isEqualTo(UPDATED_OUUNITUSERFIELD_2);
        assertThat(testOuunits.getOuunituserfield3()).isEqualTo(UPDATED_OUUNITUSERFIELD_3);
        assertThat(testOuunits.getOuunitDateCreated()).isEqualTo(UPDATED_OUUNIT_DATE_CREATED);
        assertThat(testOuunits.getOuunitLastUpdated()).isEqualTo(UPDATED_OUUNIT_LAST_UPDATED);
        assertThat(testOuunits.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingOuunits() throws Exception {
        int databaseSizeBeforeUpdate = ouunitsRepository.findAll().size();

        // Create the Ouunits

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOuunitsMockMvc.perform(put("/api/ouunits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(ouunits)))
            .andExpect(status().isBadRequest());

        // Validate the Ouunits in the database
        List<Ouunits> ouunitsList = ouunitsRepository.findAll();
        assertThat(ouunitsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteOuunits() throws Exception {
        // Initialize the database
        ouunitsRepository.saveAndFlush(ouunits);

        int databaseSizeBeforeDelete = ouunitsRepository.findAll().size();

        // Get the ouunits
        restOuunitsMockMvc.perform(delete("/api/ouunits/{id}", ouunits.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Ouunits> ouunitsList = ouunitsRepository.findAll();
        assertThat(ouunitsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ouunits.class);
        Ouunits ouunits1 = new Ouunits();
        ouunits1.setId(1L);
        Ouunits ouunits2 = new Ouunits();
        ouunits2.setId(ouunits1.getId());
        assertThat(ouunits1).isEqualTo(ouunits2);
        ouunits2.setId(2L);
        assertThat(ouunits1).isNotEqualTo(ouunits2);
        ouunits1.setId(null);
        assertThat(ouunits1).isNotEqualTo(ouunits2);
    }
}
