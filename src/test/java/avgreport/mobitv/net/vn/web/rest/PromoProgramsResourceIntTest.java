package avgreport.mobitv.net.vn.web.rest;

import avgreport.mobitv.net.vn.AvgReportApp;

import avgreport.mobitv.net.vn.domain.PromoPrograms;
import avgreport.mobitv.net.vn.repository.PromoProgramsRepository;
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
 * Test class for the PromoProgramsResource REST controller.
 *
 * @see PromoProgramsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvgReportApp.class)
public class PromoProgramsResourceIntTest {

    private static final String DEFAULT_PROMO_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROMONAME = "AAAAAAAAAA";
    private static final String UPDATED_PROMONAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROMO_USER_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_USER_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PROMO_USER_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_USER_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PROMO_USER_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_PROMO_USER_FIELD_3 = "BBBBBBBBBB";

    private static final Instant DEFAULT_PROMO_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROMO_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PROMO_LAST_UPDATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PROMO_LAST_UPDATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private PromoProgramsRepository promoProgramsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restPromoProgramsMockMvc;

    private PromoPrograms promoPrograms;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PromoProgramsResource promoProgramsResource = new PromoProgramsResource(promoProgramsRepository);
        this.restPromoProgramsMockMvc = MockMvcBuilders.standaloneSetup(promoProgramsResource)
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
    public static PromoPrograms createEntity(EntityManager em) {
        PromoPrograms promoPrograms = new PromoPrograms()
            .promoID(DEFAULT_PROMO_ID)
            .promoname(DEFAULT_PROMONAME)
            .promoUserField1(DEFAULT_PROMO_USER_FIELD_1)
            .promoUserField2(DEFAULT_PROMO_USER_FIELD_2)
            .promoUserField3(DEFAULT_PROMO_USER_FIELD_3)
            .promoDateCreated(DEFAULT_PROMO_DATE_CREATED)
            .promoLastUpdated(DEFAULT_PROMO_LAST_UPDATED)
            .isDeleted(DEFAULT_IS_DELETED);
        return promoPrograms;
    }

    @Before
    public void initTest() {
        promoPrograms = createEntity(em);
    }

    @Test
    @Transactional
    public void createPromoPrograms() throws Exception {
        int databaseSizeBeforeCreate = promoProgramsRepository.findAll().size();

        // Create the PromoPrograms
        restPromoProgramsMockMvc.perform(post("/api/promo-programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(promoPrograms)))
            .andExpect(status().isCreated());

        // Validate the PromoPrograms in the database
        List<PromoPrograms> promoProgramsList = promoProgramsRepository.findAll();
        assertThat(promoProgramsList).hasSize(databaseSizeBeforeCreate + 1);
        PromoPrograms testPromoPrograms = promoProgramsList.get(promoProgramsList.size() - 1);
        assertThat(testPromoPrograms.getPromoID()).isEqualTo(DEFAULT_PROMO_ID);
        assertThat(testPromoPrograms.getPromoname()).isEqualTo(DEFAULT_PROMONAME);
        assertThat(testPromoPrograms.getPromoUserField1()).isEqualTo(DEFAULT_PROMO_USER_FIELD_1);
        assertThat(testPromoPrograms.getPromoUserField2()).isEqualTo(DEFAULT_PROMO_USER_FIELD_2);
        assertThat(testPromoPrograms.getPromoUserField3()).isEqualTo(DEFAULT_PROMO_USER_FIELD_3);
        assertThat(testPromoPrograms.getPromoDateCreated()).isEqualTo(DEFAULT_PROMO_DATE_CREATED);
        assertThat(testPromoPrograms.getPromoLastUpdated()).isEqualTo(DEFAULT_PROMO_LAST_UPDATED);
        assertThat(testPromoPrograms.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createPromoProgramsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = promoProgramsRepository.findAll().size();

        // Create the PromoPrograms with an existing ID
        promoPrograms.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPromoProgramsMockMvc.perform(post("/api/promo-programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(promoPrograms)))
            .andExpect(status().isBadRequest());

        // Validate the PromoPrograms in the database
        List<PromoPrograms> promoProgramsList = promoProgramsRepository.findAll();
        assertThat(promoProgramsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllPromoPrograms() throws Exception {
        // Initialize the database
        promoProgramsRepository.saveAndFlush(promoPrograms);

        // Get all the promoProgramsList
        restPromoProgramsMockMvc.perform(get("/api/promo-programs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(promoPrograms.getId().intValue())))
            .andExpect(jsonPath("$.[*].promoID").value(hasItem(DEFAULT_PROMO_ID.toString())))
            .andExpect(jsonPath("$.[*].promoname").value(hasItem(DEFAULT_PROMONAME.toString())))
            .andExpect(jsonPath("$.[*].promoUserField1").value(hasItem(DEFAULT_PROMO_USER_FIELD_1.toString())))
            .andExpect(jsonPath("$.[*].promoUserField2").value(hasItem(DEFAULT_PROMO_USER_FIELD_2.toString())))
            .andExpect(jsonPath("$.[*].promoUserField3").value(hasItem(DEFAULT_PROMO_USER_FIELD_3.toString())))
            .andExpect(jsonPath("$.[*].promoDateCreated").value(hasItem(DEFAULT_PROMO_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].promoLastUpdated").value(hasItem(DEFAULT_PROMO_LAST_UPDATED.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getPromoPrograms() throws Exception {
        // Initialize the database
        promoProgramsRepository.saveAndFlush(promoPrograms);

        // Get the promoPrograms
        restPromoProgramsMockMvc.perform(get("/api/promo-programs/{id}", promoPrograms.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(promoPrograms.getId().intValue()))
            .andExpect(jsonPath("$.promoID").value(DEFAULT_PROMO_ID.toString()))
            .andExpect(jsonPath("$.promoname").value(DEFAULT_PROMONAME.toString()))
            .andExpect(jsonPath("$.promoUserField1").value(DEFAULT_PROMO_USER_FIELD_1.toString()))
            .andExpect(jsonPath("$.promoUserField2").value(DEFAULT_PROMO_USER_FIELD_2.toString()))
            .andExpect(jsonPath("$.promoUserField3").value(DEFAULT_PROMO_USER_FIELD_3.toString()))
            .andExpect(jsonPath("$.promoDateCreated").value(DEFAULT_PROMO_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.promoLastUpdated").value(DEFAULT_PROMO_LAST_UPDATED.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingPromoPrograms() throws Exception {
        // Get the promoPrograms
        restPromoProgramsMockMvc.perform(get("/api/promo-programs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePromoPrograms() throws Exception {
        // Initialize the database
        promoProgramsRepository.saveAndFlush(promoPrograms);

        int databaseSizeBeforeUpdate = promoProgramsRepository.findAll().size();

        // Update the promoPrograms
        PromoPrograms updatedPromoPrograms = promoProgramsRepository.findById(promoPrograms.getId()).get();
        // Disconnect from session so that the updates on updatedPromoPrograms are not directly saved in db
        em.detach(updatedPromoPrograms);
        updatedPromoPrograms
            .promoID(UPDATED_PROMO_ID)
            .promoname(UPDATED_PROMONAME)
            .promoUserField1(UPDATED_PROMO_USER_FIELD_1)
            .promoUserField2(UPDATED_PROMO_USER_FIELD_2)
            .promoUserField3(UPDATED_PROMO_USER_FIELD_3)
            .promoDateCreated(UPDATED_PROMO_DATE_CREATED)
            .promoLastUpdated(UPDATED_PROMO_LAST_UPDATED)
            .isDeleted(UPDATED_IS_DELETED);

        restPromoProgramsMockMvc.perform(put("/api/promo-programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedPromoPrograms)))
            .andExpect(status().isOk());

        // Validate the PromoPrograms in the database
        List<PromoPrograms> promoProgramsList = promoProgramsRepository.findAll();
        assertThat(promoProgramsList).hasSize(databaseSizeBeforeUpdate);
        PromoPrograms testPromoPrograms = promoProgramsList.get(promoProgramsList.size() - 1);
        assertThat(testPromoPrograms.getPromoID()).isEqualTo(UPDATED_PROMO_ID);
        assertThat(testPromoPrograms.getPromoname()).isEqualTo(UPDATED_PROMONAME);
        assertThat(testPromoPrograms.getPromoUserField1()).isEqualTo(UPDATED_PROMO_USER_FIELD_1);
        assertThat(testPromoPrograms.getPromoUserField2()).isEqualTo(UPDATED_PROMO_USER_FIELD_2);
        assertThat(testPromoPrograms.getPromoUserField3()).isEqualTo(UPDATED_PROMO_USER_FIELD_3);
        assertThat(testPromoPrograms.getPromoDateCreated()).isEqualTo(UPDATED_PROMO_DATE_CREATED);
        assertThat(testPromoPrograms.getPromoLastUpdated()).isEqualTo(UPDATED_PROMO_LAST_UPDATED);
        assertThat(testPromoPrograms.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingPromoPrograms() throws Exception {
        int databaseSizeBeforeUpdate = promoProgramsRepository.findAll().size();

        // Create the PromoPrograms

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPromoProgramsMockMvc.perform(put("/api/promo-programs")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(promoPrograms)))
            .andExpect(status().isBadRequest());

        // Validate the PromoPrograms in the database
        List<PromoPrograms> promoProgramsList = promoProgramsRepository.findAll();
        assertThat(promoProgramsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePromoPrograms() throws Exception {
        // Initialize the database
        promoProgramsRepository.saveAndFlush(promoPrograms);

        int databaseSizeBeforeDelete = promoProgramsRepository.findAll().size();

        // Get the promoPrograms
        restPromoProgramsMockMvc.perform(delete("/api/promo-programs/{id}", promoPrograms.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<PromoPrograms> promoProgramsList = promoProgramsRepository.findAll();
        assertThat(promoProgramsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PromoPrograms.class);
        PromoPrograms promoPrograms1 = new PromoPrograms();
        promoPrograms1.setId(1L);
        PromoPrograms promoPrograms2 = new PromoPrograms();
        promoPrograms2.setId(promoPrograms1.getId());
        assertThat(promoPrograms1).isEqualTo(promoPrograms2);
        promoPrograms2.setId(2L);
        assertThat(promoPrograms1).isNotEqualTo(promoPrograms2);
        promoPrograms1.setId(null);
        assertThat(promoPrograms1).isNotEqualTo(promoPrograms2);
    }
}
