package avgreport.mobitv.net.vn.web.rest;

import avgreport.mobitv.net.vn.AvgReportApp;

import avgreport.mobitv.net.vn.domain.SubActiveCount;
import avgreport.mobitv.net.vn.repository.SubActiveCountRepository;
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
 * Test class for the SubActiveCountResource REST controller.
 *
 * @see SubActiveCountResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvgReportApp.class)
public class SubActiveCountResourceIntTest {

    private static final String DEFAULT_SUB_ACTIVE_COUNT_ID = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ACTIVE_COUNT_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_SUB_ACTIVE_COUNTS = 1;
    private static final Integer UPDATED_SUB_ACTIVE_COUNTS = 2;

    private static final String DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_5 = "BBBBBBBBBB";

    private static final Instant DEFAULT_SUB_ACTIVE_COUNT_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUB_ACTIVE_COUNT_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SUB_ACTIVE_COUNT_LAST_UPDATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SUB_ACTIVE_COUNT_LAST_UPDATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private SubActiveCountRepository subActiveCountRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restSubActiveCountMockMvc;

    private SubActiveCount subActiveCount;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SubActiveCountResource subActiveCountResource = new SubActiveCountResource(subActiveCountRepository);
        this.restSubActiveCountMockMvc = MockMvcBuilders.standaloneSetup(subActiveCountResource)
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
    public static SubActiveCount createEntity(EntityManager em) {
        SubActiveCount subActiveCount = new SubActiveCount()
            .subActiveCountID(DEFAULT_SUB_ACTIVE_COUNT_ID)
            .subActiveCounts(DEFAULT_SUB_ACTIVE_COUNTS)
            .subActiveCountUserField1(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_1)
            .subActiveCountUserField2(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_2)
            .subActiveCountUserField3(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_3)
            .subActiveCountUserField4(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_4)
            .subActiveCountUserField5(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_5)
            .subActiveCountDateCreated(DEFAULT_SUB_ACTIVE_COUNT_DATE_CREATED)
            .subActiveCountLastUpdated(DEFAULT_SUB_ACTIVE_COUNT_LAST_UPDATED)
            .isDeleted(DEFAULT_IS_DELETED);
        return subActiveCount;
    }

    @Before
    public void initTest() {
        subActiveCount = createEntity(em);
    }

    @Test
    @Transactional
    public void createSubActiveCount() throws Exception {
        int databaseSizeBeforeCreate = subActiveCountRepository.findAll().size();

        // Create the SubActiveCount
        restSubActiveCountMockMvc.perform(post("/api/sub-active-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subActiveCount)))
            .andExpect(status().isCreated());

        // Validate the SubActiveCount in the database
        List<SubActiveCount> subActiveCountList = subActiveCountRepository.findAll();
        assertThat(subActiveCountList).hasSize(databaseSizeBeforeCreate + 1);
        SubActiveCount testSubActiveCount = subActiveCountList.get(subActiveCountList.size() - 1);
        assertThat(testSubActiveCount.getSubActiveCountID()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_ID);
        assertThat(testSubActiveCount.getSubActiveCounts()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNTS);
        assertThat(testSubActiveCount.getSubActiveCountUserField1()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_1);
        assertThat(testSubActiveCount.getSubActiveCountUserField2()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_2);
        assertThat(testSubActiveCount.getSubActiveCountUserField3()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_3);
        assertThat(testSubActiveCount.getSubActiveCountUserField4()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_4);
        assertThat(testSubActiveCount.getSubActiveCountUserField5()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_5);
        assertThat(testSubActiveCount.getSubActiveCountDateCreated()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_DATE_CREATED);
        assertThat(testSubActiveCount.getSubActiveCountLastUpdated()).isEqualTo(DEFAULT_SUB_ACTIVE_COUNT_LAST_UPDATED);
        assertThat(testSubActiveCount.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createSubActiveCountWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = subActiveCountRepository.findAll().size();

        // Create the SubActiveCount with an existing ID
        subActiveCount.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSubActiveCountMockMvc.perform(post("/api/sub-active-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subActiveCount)))
            .andExpect(status().isBadRequest());

        // Validate the SubActiveCount in the database
        List<SubActiveCount> subActiveCountList = subActiveCountRepository.findAll();
        assertThat(subActiveCountList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllSubActiveCounts() throws Exception {
        // Initialize the database
        subActiveCountRepository.saveAndFlush(subActiveCount);

        // Get all the subActiveCountList
        restSubActiveCountMockMvc.perform(get("/api/sub-active-counts?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(subActiveCount.getId().intValue())))
            .andExpect(jsonPath("$.[*].subActiveCountID").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_ID.toString())))
            .andExpect(jsonPath("$.[*].subActiveCounts").value(hasItem(DEFAULT_SUB_ACTIVE_COUNTS)))
            .andExpect(jsonPath("$.[*].subActiveCountUserField1").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_1.toString())))
            .andExpect(jsonPath("$.[*].subActiveCountUserField2").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_2.toString())))
            .andExpect(jsonPath("$.[*].subActiveCountUserField3").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_3.toString())))
            .andExpect(jsonPath("$.[*].subActiveCountUserField4").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_4.toString())))
            .andExpect(jsonPath("$.[*].subActiveCountUserField5").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_5.toString())))
            .andExpect(jsonPath("$.[*].subActiveCountDateCreated").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].subActiveCountLastUpdated").value(hasItem(DEFAULT_SUB_ACTIVE_COUNT_LAST_UPDATED.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getSubActiveCount() throws Exception {
        // Initialize the database
        subActiveCountRepository.saveAndFlush(subActiveCount);

        // Get the subActiveCount
        restSubActiveCountMockMvc.perform(get("/api/sub-active-counts/{id}", subActiveCount.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(subActiveCount.getId().intValue()))
            .andExpect(jsonPath("$.subActiveCountID").value(DEFAULT_SUB_ACTIVE_COUNT_ID.toString()))
            .andExpect(jsonPath("$.subActiveCounts").value(DEFAULT_SUB_ACTIVE_COUNTS))
            .andExpect(jsonPath("$.subActiveCountUserField1").value(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_1.toString()))
            .andExpect(jsonPath("$.subActiveCountUserField2").value(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_2.toString()))
            .andExpect(jsonPath("$.subActiveCountUserField3").value(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_3.toString()))
            .andExpect(jsonPath("$.subActiveCountUserField4").value(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_4.toString()))
            .andExpect(jsonPath("$.subActiveCountUserField5").value(DEFAULT_SUB_ACTIVE_COUNT_USER_FIELD_5.toString()))
            .andExpect(jsonPath("$.subActiveCountDateCreated").value(DEFAULT_SUB_ACTIVE_COUNT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.subActiveCountLastUpdated").value(DEFAULT_SUB_ACTIVE_COUNT_LAST_UPDATED.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSubActiveCount() throws Exception {
        // Get the subActiveCount
        restSubActiveCountMockMvc.perform(get("/api/sub-active-counts/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSubActiveCount() throws Exception {
        // Initialize the database
        subActiveCountRepository.saveAndFlush(subActiveCount);

        int databaseSizeBeforeUpdate = subActiveCountRepository.findAll().size();

        // Update the subActiveCount
        SubActiveCount updatedSubActiveCount = subActiveCountRepository.findById(subActiveCount.getId()).get();
        // Disconnect from session so that the updates on updatedSubActiveCount are not directly saved in db
        em.detach(updatedSubActiveCount);
        updatedSubActiveCount
            .subActiveCountID(UPDATED_SUB_ACTIVE_COUNT_ID)
            .subActiveCounts(UPDATED_SUB_ACTIVE_COUNTS)
            .subActiveCountUserField1(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_1)
            .subActiveCountUserField2(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_2)
            .subActiveCountUserField3(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_3)
            .subActiveCountUserField4(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_4)
            .subActiveCountUserField5(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_5)
            .subActiveCountDateCreated(UPDATED_SUB_ACTIVE_COUNT_DATE_CREATED)
            .subActiveCountLastUpdated(UPDATED_SUB_ACTIVE_COUNT_LAST_UPDATED)
            .isDeleted(UPDATED_IS_DELETED);

        restSubActiveCountMockMvc.perform(put("/api/sub-active-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedSubActiveCount)))
            .andExpect(status().isOk());

        // Validate the SubActiveCount in the database
        List<SubActiveCount> subActiveCountList = subActiveCountRepository.findAll();
        assertThat(subActiveCountList).hasSize(databaseSizeBeforeUpdate);
        SubActiveCount testSubActiveCount = subActiveCountList.get(subActiveCountList.size() - 1);
        assertThat(testSubActiveCount.getSubActiveCountID()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_ID);
        assertThat(testSubActiveCount.getSubActiveCounts()).isEqualTo(UPDATED_SUB_ACTIVE_COUNTS);
        assertThat(testSubActiveCount.getSubActiveCountUserField1()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_1);
        assertThat(testSubActiveCount.getSubActiveCountUserField2()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_2);
        assertThat(testSubActiveCount.getSubActiveCountUserField3()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_3);
        assertThat(testSubActiveCount.getSubActiveCountUserField4()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_4);
        assertThat(testSubActiveCount.getSubActiveCountUserField5()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_USER_FIELD_5);
        assertThat(testSubActiveCount.getSubActiveCountDateCreated()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_DATE_CREATED);
        assertThat(testSubActiveCount.getSubActiveCountLastUpdated()).isEqualTo(UPDATED_SUB_ACTIVE_COUNT_LAST_UPDATED);
        assertThat(testSubActiveCount.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingSubActiveCount() throws Exception {
        int databaseSizeBeforeUpdate = subActiveCountRepository.findAll().size();

        // Create the SubActiveCount

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSubActiveCountMockMvc.perform(put("/api/sub-active-counts")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(subActiveCount)))
            .andExpect(status().isBadRequest());

        // Validate the SubActiveCount in the database
        List<SubActiveCount> subActiveCountList = subActiveCountRepository.findAll();
        assertThat(subActiveCountList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSubActiveCount() throws Exception {
        // Initialize the database
        subActiveCountRepository.saveAndFlush(subActiveCount);

        int databaseSizeBeforeDelete = subActiveCountRepository.findAll().size();

        // Get the subActiveCount
        restSubActiveCountMockMvc.perform(delete("/api/sub-active-counts/{id}", subActiveCount.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<SubActiveCount> subActiveCountList = subActiveCountRepository.findAll();
        assertThat(subActiveCountList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SubActiveCount.class);
        SubActiveCount subActiveCount1 = new SubActiveCount();
        subActiveCount1.setId(1L);
        SubActiveCount subActiveCount2 = new SubActiveCount();
        subActiveCount2.setId(subActiveCount1.getId());
        assertThat(subActiveCount1).isEqualTo(subActiveCount2);
        subActiveCount2.setId(2L);
        assertThat(subActiveCount1).isNotEqualTo(subActiveCount2);
        subActiveCount1.setId(null);
        assertThat(subActiveCount1).isNotEqualTo(subActiveCount2);
    }
}
