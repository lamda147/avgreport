package avgreport.mobitv.net.vn.web.rest;

import avgreport.mobitv.net.vn.AvgReportApp;

import avgreport.mobitv.net.vn.domain.ReportTime;
import avgreport.mobitv.net.vn.repository.ReportTimeRepository;
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
import java.util.List;


import static avgreport.mobitv.net.vn.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the ReportTimeResource REST controller.
 *
 * @see ReportTimeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvgReportApp.class)
public class ReportTimeResourceIntTest {

    private static final String DEFAULT_REPORT_TIME_ID = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TIME_ID = "BBBBBBBBBB";

    private static final Integer DEFAULT_REPORT_TIME_HOUR = 1;
    private static final Integer UPDATED_REPORT_TIME_HOUR = 2;

    private static final Integer DEFAULT_REPORT_TIME_DAY = 1;
    private static final Integer UPDATED_REPORT_TIME_DAY = 2;

    private static final Integer DEFAULT_REPORT_TIME_YEAR_WEEK = 1;
    private static final Integer UPDATED_REPORT_TIME_YEAR_WEEK = 2;

    private static final Integer DEFAULT_REPORT_TIME_MONTH = 1;
    private static final Integer UPDATED_REPORT_TIME_MONTH = 2;

    private static final Integer DEFAULT_REPORT_TIME_YEAR_QUATER = 1;
    private static final Integer UPDATED_REPORT_TIME_YEAR_QUATER = 2;

    private static final Integer DEFAULT_REPORT_TIME_YEAR = 1;
    private static final Integer UPDATED_REPORT_TIME_YEAR = 2;

    private static final String DEFAULT_REPORT_TIME_USER_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TIME_USER_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT_TIME_USER_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TIME_USER_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_REPORT_TIME_USER_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_REPORT_TIME_USER_FIELD_3 = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ReportTimeRepository reportTimeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restReportTimeMockMvc;

    private ReportTime reportTime;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ReportTimeResource reportTimeResource = new ReportTimeResource(reportTimeRepository);
        this.restReportTimeMockMvc = MockMvcBuilders.standaloneSetup(reportTimeResource)
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
    public static ReportTime createEntity(EntityManager em) {
        ReportTime reportTime = new ReportTime()
            .reportTimeID(DEFAULT_REPORT_TIME_ID)
            .reportTimeHour(DEFAULT_REPORT_TIME_HOUR)
            .reportTimeDay(DEFAULT_REPORT_TIME_DAY)
            .reportTimeYearWeek(DEFAULT_REPORT_TIME_YEAR_WEEK)
            .reportTimeMonth(DEFAULT_REPORT_TIME_MONTH)
            .reportTimeYearQuater(DEFAULT_REPORT_TIME_YEAR_QUATER)
            .reportTimeYear(DEFAULT_REPORT_TIME_YEAR)
            .reportTimeUserField1(DEFAULT_REPORT_TIME_USER_FIELD_1)
            .reportTimeUserField2(DEFAULT_REPORT_TIME_USER_FIELD_2)
            .reportTimeUserField3(DEFAULT_REPORT_TIME_USER_FIELD_3)
            .isDeleted(DEFAULT_IS_DELETED);
        return reportTime;
    }

    @Before
    public void initTest() {
        reportTime = createEntity(em);
    }

    @Test
    @Transactional
    public void createReportTime() throws Exception {
        int databaseSizeBeforeCreate = reportTimeRepository.findAll().size();

        // Create the ReportTime
        restReportTimeMockMvc.perform(post("/api/report-times")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportTime)))
            .andExpect(status().isCreated());

        // Validate the ReportTime in the database
        List<ReportTime> reportTimeList = reportTimeRepository.findAll();
        assertThat(reportTimeList).hasSize(databaseSizeBeforeCreate + 1);
        ReportTime testReportTime = reportTimeList.get(reportTimeList.size() - 1);
        assertThat(testReportTime.getReportTimeID()).isEqualTo(DEFAULT_REPORT_TIME_ID);
        assertThat(testReportTime.getReportTimeHour()).isEqualTo(DEFAULT_REPORT_TIME_HOUR);
        assertThat(testReportTime.getReportTimeDay()).isEqualTo(DEFAULT_REPORT_TIME_DAY);
        assertThat(testReportTime.getReportTimeYearWeek()).isEqualTo(DEFAULT_REPORT_TIME_YEAR_WEEK);
        assertThat(testReportTime.getReportTimeMonth()).isEqualTo(DEFAULT_REPORT_TIME_MONTH);
        assertThat(testReportTime.getReportTimeYearQuater()).isEqualTo(DEFAULT_REPORT_TIME_YEAR_QUATER);
        assertThat(testReportTime.getReportTimeYear()).isEqualTo(DEFAULT_REPORT_TIME_YEAR);
        assertThat(testReportTime.getReportTimeUserField1()).isEqualTo(DEFAULT_REPORT_TIME_USER_FIELD_1);
        assertThat(testReportTime.getReportTimeUserField2()).isEqualTo(DEFAULT_REPORT_TIME_USER_FIELD_2);
        assertThat(testReportTime.getReportTimeUserField3()).isEqualTo(DEFAULT_REPORT_TIME_USER_FIELD_3);
        assertThat(testReportTime.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createReportTimeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = reportTimeRepository.findAll().size();

        // Create the ReportTime with an existing ID
        reportTime.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restReportTimeMockMvc.perform(post("/api/report-times")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportTime)))
            .andExpect(status().isBadRequest());

        // Validate the ReportTime in the database
        List<ReportTime> reportTimeList = reportTimeRepository.findAll();
        assertThat(reportTimeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllReportTimes() throws Exception {
        // Initialize the database
        reportTimeRepository.saveAndFlush(reportTime);

        // Get all the reportTimeList
        restReportTimeMockMvc.perform(get("/api/report-times?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(reportTime.getId().intValue())))
            .andExpect(jsonPath("$.[*].reportTimeID").value(hasItem(DEFAULT_REPORT_TIME_ID.toString())))
            .andExpect(jsonPath("$.[*].reportTimeHour").value(hasItem(DEFAULT_REPORT_TIME_HOUR)))
            .andExpect(jsonPath("$.[*].reportTimeDay").value(hasItem(DEFAULT_REPORT_TIME_DAY)))
            .andExpect(jsonPath("$.[*].reportTimeYearWeek").value(hasItem(DEFAULT_REPORT_TIME_YEAR_WEEK)))
            .andExpect(jsonPath("$.[*].reportTimeMonth").value(hasItem(DEFAULT_REPORT_TIME_MONTH)))
            .andExpect(jsonPath("$.[*].reportTimeYearQuater").value(hasItem(DEFAULT_REPORT_TIME_YEAR_QUATER)))
            .andExpect(jsonPath("$.[*].reportTimeYear").value(hasItem(DEFAULT_REPORT_TIME_YEAR)))
            .andExpect(jsonPath("$.[*].reportTimeUserField1").value(hasItem(DEFAULT_REPORT_TIME_USER_FIELD_1.toString())))
            .andExpect(jsonPath("$.[*].reportTimeUserField2").value(hasItem(DEFAULT_REPORT_TIME_USER_FIELD_2.toString())))
            .andExpect(jsonPath("$.[*].reportTimeUserField3").value(hasItem(DEFAULT_REPORT_TIME_USER_FIELD_3.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getReportTime() throws Exception {
        // Initialize the database
        reportTimeRepository.saveAndFlush(reportTime);

        // Get the reportTime
        restReportTimeMockMvc.perform(get("/api/report-times/{id}", reportTime.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(reportTime.getId().intValue()))
            .andExpect(jsonPath("$.reportTimeID").value(DEFAULT_REPORT_TIME_ID.toString()))
            .andExpect(jsonPath("$.reportTimeHour").value(DEFAULT_REPORT_TIME_HOUR))
            .andExpect(jsonPath("$.reportTimeDay").value(DEFAULT_REPORT_TIME_DAY))
            .andExpect(jsonPath("$.reportTimeYearWeek").value(DEFAULT_REPORT_TIME_YEAR_WEEK))
            .andExpect(jsonPath("$.reportTimeMonth").value(DEFAULT_REPORT_TIME_MONTH))
            .andExpect(jsonPath("$.reportTimeYearQuater").value(DEFAULT_REPORT_TIME_YEAR_QUATER))
            .andExpect(jsonPath("$.reportTimeYear").value(DEFAULT_REPORT_TIME_YEAR))
            .andExpect(jsonPath("$.reportTimeUserField1").value(DEFAULT_REPORT_TIME_USER_FIELD_1.toString()))
            .andExpect(jsonPath("$.reportTimeUserField2").value(DEFAULT_REPORT_TIME_USER_FIELD_2.toString()))
            .andExpect(jsonPath("$.reportTimeUserField3").value(DEFAULT_REPORT_TIME_USER_FIELD_3.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingReportTime() throws Exception {
        // Get the reportTime
        restReportTimeMockMvc.perform(get("/api/report-times/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateReportTime() throws Exception {
        // Initialize the database
        reportTimeRepository.saveAndFlush(reportTime);

        int databaseSizeBeforeUpdate = reportTimeRepository.findAll().size();

        // Update the reportTime
        ReportTime updatedReportTime = reportTimeRepository.findById(reportTime.getId()).get();
        // Disconnect from session so that the updates on updatedReportTime are not directly saved in db
        em.detach(updatedReportTime);
        updatedReportTime
            .reportTimeID(UPDATED_REPORT_TIME_ID)
            .reportTimeHour(UPDATED_REPORT_TIME_HOUR)
            .reportTimeDay(UPDATED_REPORT_TIME_DAY)
            .reportTimeYearWeek(UPDATED_REPORT_TIME_YEAR_WEEK)
            .reportTimeMonth(UPDATED_REPORT_TIME_MONTH)
            .reportTimeYearQuater(UPDATED_REPORT_TIME_YEAR_QUATER)
            .reportTimeYear(UPDATED_REPORT_TIME_YEAR)
            .reportTimeUserField1(UPDATED_REPORT_TIME_USER_FIELD_1)
            .reportTimeUserField2(UPDATED_REPORT_TIME_USER_FIELD_2)
            .reportTimeUserField3(UPDATED_REPORT_TIME_USER_FIELD_3)
            .isDeleted(UPDATED_IS_DELETED);

        restReportTimeMockMvc.perform(put("/api/report-times")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedReportTime)))
            .andExpect(status().isOk());

        // Validate the ReportTime in the database
        List<ReportTime> reportTimeList = reportTimeRepository.findAll();
        assertThat(reportTimeList).hasSize(databaseSizeBeforeUpdate);
        ReportTime testReportTime = reportTimeList.get(reportTimeList.size() - 1);
        assertThat(testReportTime.getReportTimeID()).isEqualTo(UPDATED_REPORT_TIME_ID);
        assertThat(testReportTime.getReportTimeHour()).isEqualTo(UPDATED_REPORT_TIME_HOUR);
        assertThat(testReportTime.getReportTimeDay()).isEqualTo(UPDATED_REPORT_TIME_DAY);
        assertThat(testReportTime.getReportTimeYearWeek()).isEqualTo(UPDATED_REPORT_TIME_YEAR_WEEK);
        assertThat(testReportTime.getReportTimeMonth()).isEqualTo(UPDATED_REPORT_TIME_MONTH);
        assertThat(testReportTime.getReportTimeYearQuater()).isEqualTo(UPDATED_REPORT_TIME_YEAR_QUATER);
        assertThat(testReportTime.getReportTimeYear()).isEqualTo(UPDATED_REPORT_TIME_YEAR);
        assertThat(testReportTime.getReportTimeUserField1()).isEqualTo(UPDATED_REPORT_TIME_USER_FIELD_1);
        assertThat(testReportTime.getReportTimeUserField2()).isEqualTo(UPDATED_REPORT_TIME_USER_FIELD_2);
        assertThat(testReportTime.getReportTimeUserField3()).isEqualTo(UPDATED_REPORT_TIME_USER_FIELD_3);
        assertThat(testReportTime.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingReportTime() throws Exception {
        int databaseSizeBeforeUpdate = reportTimeRepository.findAll().size();

        // Create the ReportTime

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restReportTimeMockMvc.perform(put("/api/report-times")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(reportTime)))
            .andExpect(status().isBadRequest());

        // Validate the ReportTime in the database
        List<ReportTime> reportTimeList = reportTimeRepository.findAll();
        assertThat(reportTimeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteReportTime() throws Exception {
        // Initialize the database
        reportTimeRepository.saveAndFlush(reportTime);

        int databaseSizeBeforeDelete = reportTimeRepository.findAll().size();

        // Get the reportTime
        restReportTimeMockMvc.perform(delete("/api/report-times/{id}", reportTime.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<ReportTime> reportTimeList = reportTimeRepository.findAll();
        assertThat(reportTimeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ReportTime.class);
        ReportTime reportTime1 = new ReportTime();
        reportTime1.setId(1L);
        ReportTime reportTime2 = new ReportTime();
        reportTime2.setId(reportTime1.getId());
        assertThat(reportTime1).isEqualTo(reportTime2);
        reportTime2.setId(2L);
        assertThat(reportTime1).isNotEqualTo(reportTime2);
        reportTime1.setId(null);
        assertThat(reportTime1).isNotEqualTo(reportTime2);
    }
}
