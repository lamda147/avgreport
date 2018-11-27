package avgreport.mobitv.net.vn.web.rest;

import avgreport.mobitv.net.vn.AvgReportApp;

import avgreport.mobitv.net.vn.domain.Addresses;
import avgreport.mobitv.net.vn.repository.AddressesRepository;
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
 * Test class for the AddressesResource REST controller.
 *
 * @see AddressesResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvgReportApp.class)
public class AddressesResourceIntTest {

    private static final String DEFAULT_ADDRESSID = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSID = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSAREA = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSAREA = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSDISTRICT = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSDISTRICT = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSCITY = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSCITY = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSSTATE = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSSTATE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSUSERFIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSUSERFIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSUSERFIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSUSERFIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSUSERFIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSUSERFIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSUSERFIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSUSERFIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSUSERFIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSUSERFIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESSUSERFIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESSUSERFIELD_6 = "BBBBBBBBBB";

    private static final Instant DEFAULT_ADDRESS_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADDRESS_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_ADDRESS_LAST_UPDATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ADDRESS_LAST_UPDATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private AddressesRepository addressesRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAddressesMockMvc;

    private Addresses addresses;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AddressesResource addressesResource = new AddressesResource(addressesRepository);
        this.restAddressesMockMvc = MockMvcBuilders.standaloneSetup(addressesResource)
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
    public static Addresses createEntity(EntityManager em) {
        Addresses addresses = new Addresses()
            .addressid(DEFAULT_ADDRESSID)
            .addressarea(DEFAULT_ADDRESSAREA)
            .addressdistrict(DEFAULT_ADDRESSDISTRICT)
            .addresscity(DEFAULT_ADDRESSCITY)
            .addressstate(DEFAULT_ADDRESSSTATE)
            .addressuserfield1(DEFAULT_ADDRESSUSERFIELD_1)
            .addressuserfield2(DEFAULT_ADDRESSUSERFIELD_2)
            .addressuserfield3(DEFAULT_ADDRESSUSERFIELD_3)
            .addressuserfield4(DEFAULT_ADDRESSUSERFIELD_4)
            .addressuserfield5(DEFAULT_ADDRESSUSERFIELD_5)
            .addressuserfield6(DEFAULT_ADDRESSUSERFIELD_6)
            .addressDateCreated(DEFAULT_ADDRESS_DATE_CREATED)
            .addressLastUpdated(DEFAULT_ADDRESS_LAST_UPDATED)
            .isDeleted(DEFAULT_IS_DELETED);
        return addresses;
    }

    @Before
    public void initTest() {
        addresses = createEntity(em);
    }

    @Test
    @Transactional
    public void createAddresses() throws Exception {
        int databaseSizeBeforeCreate = addressesRepository.findAll().size();

        // Create the Addresses
        restAddressesMockMvc.perform(post("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addresses)))
            .andExpect(status().isCreated());

        // Validate the Addresses in the database
        List<Addresses> addressesList = addressesRepository.findAll();
        assertThat(addressesList).hasSize(databaseSizeBeforeCreate + 1);
        Addresses testAddresses = addressesList.get(addressesList.size() - 1);
        assertThat(testAddresses.getAddressid()).isEqualTo(DEFAULT_ADDRESSID);
        assertThat(testAddresses.getAddressarea()).isEqualTo(DEFAULT_ADDRESSAREA);
        assertThat(testAddresses.getAddressdistrict()).isEqualTo(DEFAULT_ADDRESSDISTRICT);
        assertThat(testAddresses.getAddresscity()).isEqualTo(DEFAULT_ADDRESSCITY);
        assertThat(testAddresses.getAddressstate()).isEqualTo(DEFAULT_ADDRESSSTATE);
        assertThat(testAddresses.getAddressuserfield1()).isEqualTo(DEFAULT_ADDRESSUSERFIELD_1);
        assertThat(testAddresses.getAddressuserfield2()).isEqualTo(DEFAULT_ADDRESSUSERFIELD_2);
        assertThat(testAddresses.getAddressuserfield3()).isEqualTo(DEFAULT_ADDRESSUSERFIELD_3);
        assertThat(testAddresses.getAddressuserfield4()).isEqualTo(DEFAULT_ADDRESSUSERFIELD_4);
        assertThat(testAddresses.getAddressuserfield5()).isEqualTo(DEFAULT_ADDRESSUSERFIELD_5);
        assertThat(testAddresses.getAddressuserfield6()).isEqualTo(DEFAULT_ADDRESSUSERFIELD_6);
        assertThat(testAddresses.getAddressDateCreated()).isEqualTo(DEFAULT_ADDRESS_DATE_CREATED);
        assertThat(testAddresses.getAddressLastUpdated()).isEqualTo(DEFAULT_ADDRESS_LAST_UPDATED);
        assertThat(testAddresses.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createAddressesWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = addressesRepository.findAll().size();

        // Create the Addresses with an existing ID
        addresses.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAddressesMockMvc.perform(post("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addresses)))
            .andExpect(status().isBadRequest());

        // Validate the Addresses in the database
        List<Addresses> addressesList = addressesRepository.findAll();
        assertThat(addressesList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAddresses() throws Exception {
        // Initialize the database
        addressesRepository.saveAndFlush(addresses);

        // Get all the addressesList
        restAddressesMockMvc.perform(get("/api/addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(addresses.getId().intValue())))
            .andExpect(jsonPath("$.[*].addressid").value(hasItem(DEFAULT_ADDRESSID.toString())))
            .andExpect(jsonPath("$.[*].addressarea").value(hasItem(DEFAULT_ADDRESSAREA.toString())))
            .andExpect(jsonPath("$.[*].addressdistrict").value(hasItem(DEFAULT_ADDRESSDISTRICT.toString())))
            .andExpect(jsonPath("$.[*].addresscity").value(hasItem(DEFAULT_ADDRESSCITY.toString())))
            .andExpect(jsonPath("$.[*].addressstate").value(hasItem(DEFAULT_ADDRESSSTATE.toString())))
            .andExpect(jsonPath("$.[*].addressuserfield1").value(hasItem(DEFAULT_ADDRESSUSERFIELD_1.toString())))
            .andExpect(jsonPath("$.[*].addressuserfield2").value(hasItem(DEFAULT_ADDRESSUSERFIELD_2.toString())))
            .andExpect(jsonPath("$.[*].addressuserfield3").value(hasItem(DEFAULT_ADDRESSUSERFIELD_3.toString())))
            .andExpect(jsonPath("$.[*].addressuserfield4").value(hasItem(DEFAULT_ADDRESSUSERFIELD_4.toString())))
            .andExpect(jsonPath("$.[*].addressuserfield5").value(hasItem(DEFAULT_ADDRESSUSERFIELD_5.toString())))
            .andExpect(jsonPath("$.[*].addressuserfield6").value(hasItem(DEFAULT_ADDRESSUSERFIELD_6.toString())))
            .andExpect(jsonPath("$.[*].addressDateCreated").value(hasItem(DEFAULT_ADDRESS_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].addressLastUpdated").value(hasItem(DEFAULT_ADDRESS_LAST_UPDATED.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getAddresses() throws Exception {
        // Initialize the database
        addressesRepository.saveAndFlush(addresses);

        // Get the addresses
        restAddressesMockMvc.perform(get("/api/addresses/{id}", addresses.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(addresses.getId().intValue()))
            .andExpect(jsonPath("$.addressid").value(DEFAULT_ADDRESSID.toString()))
            .andExpect(jsonPath("$.addressarea").value(DEFAULT_ADDRESSAREA.toString()))
            .andExpect(jsonPath("$.addressdistrict").value(DEFAULT_ADDRESSDISTRICT.toString()))
            .andExpect(jsonPath("$.addresscity").value(DEFAULT_ADDRESSCITY.toString()))
            .andExpect(jsonPath("$.addressstate").value(DEFAULT_ADDRESSSTATE.toString()))
            .andExpect(jsonPath("$.addressuserfield1").value(DEFAULT_ADDRESSUSERFIELD_1.toString()))
            .andExpect(jsonPath("$.addressuserfield2").value(DEFAULT_ADDRESSUSERFIELD_2.toString()))
            .andExpect(jsonPath("$.addressuserfield3").value(DEFAULT_ADDRESSUSERFIELD_3.toString()))
            .andExpect(jsonPath("$.addressuserfield4").value(DEFAULT_ADDRESSUSERFIELD_4.toString()))
            .andExpect(jsonPath("$.addressuserfield5").value(DEFAULT_ADDRESSUSERFIELD_5.toString()))
            .andExpect(jsonPath("$.addressuserfield6").value(DEFAULT_ADDRESSUSERFIELD_6.toString()))
            .andExpect(jsonPath("$.addressDateCreated").value(DEFAULT_ADDRESS_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.addressLastUpdated").value(DEFAULT_ADDRESS_LAST_UPDATED.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAddresses() throws Exception {
        // Get the addresses
        restAddressesMockMvc.perform(get("/api/addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAddresses() throws Exception {
        // Initialize the database
        addressesRepository.saveAndFlush(addresses);

        int databaseSizeBeforeUpdate = addressesRepository.findAll().size();

        // Update the addresses
        Addresses updatedAddresses = addressesRepository.findById(addresses.getId()).get();
        // Disconnect from session so that the updates on updatedAddresses are not directly saved in db
        em.detach(updatedAddresses);
        updatedAddresses
            .addressid(UPDATED_ADDRESSID)
            .addressarea(UPDATED_ADDRESSAREA)
            .addressdistrict(UPDATED_ADDRESSDISTRICT)
            .addresscity(UPDATED_ADDRESSCITY)
            .addressstate(UPDATED_ADDRESSSTATE)
            .addressuserfield1(UPDATED_ADDRESSUSERFIELD_1)
            .addressuserfield2(UPDATED_ADDRESSUSERFIELD_2)
            .addressuserfield3(UPDATED_ADDRESSUSERFIELD_3)
            .addressuserfield4(UPDATED_ADDRESSUSERFIELD_4)
            .addressuserfield5(UPDATED_ADDRESSUSERFIELD_5)
            .addressuserfield6(UPDATED_ADDRESSUSERFIELD_6)
            .addressDateCreated(UPDATED_ADDRESS_DATE_CREATED)
            .addressLastUpdated(UPDATED_ADDRESS_LAST_UPDATED)
            .isDeleted(UPDATED_IS_DELETED);

        restAddressesMockMvc.perform(put("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAddresses)))
            .andExpect(status().isOk());

        // Validate the Addresses in the database
        List<Addresses> addressesList = addressesRepository.findAll();
        assertThat(addressesList).hasSize(databaseSizeBeforeUpdate);
        Addresses testAddresses = addressesList.get(addressesList.size() - 1);
        assertThat(testAddresses.getAddressid()).isEqualTo(UPDATED_ADDRESSID);
        assertThat(testAddresses.getAddressarea()).isEqualTo(UPDATED_ADDRESSAREA);
        assertThat(testAddresses.getAddressdistrict()).isEqualTo(UPDATED_ADDRESSDISTRICT);
        assertThat(testAddresses.getAddresscity()).isEqualTo(UPDATED_ADDRESSCITY);
        assertThat(testAddresses.getAddressstate()).isEqualTo(UPDATED_ADDRESSSTATE);
        assertThat(testAddresses.getAddressuserfield1()).isEqualTo(UPDATED_ADDRESSUSERFIELD_1);
        assertThat(testAddresses.getAddressuserfield2()).isEqualTo(UPDATED_ADDRESSUSERFIELD_2);
        assertThat(testAddresses.getAddressuserfield3()).isEqualTo(UPDATED_ADDRESSUSERFIELD_3);
        assertThat(testAddresses.getAddressuserfield4()).isEqualTo(UPDATED_ADDRESSUSERFIELD_4);
        assertThat(testAddresses.getAddressuserfield5()).isEqualTo(UPDATED_ADDRESSUSERFIELD_5);
        assertThat(testAddresses.getAddressuserfield6()).isEqualTo(UPDATED_ADDRESSUSERFIELD_6);
        assertThat(testAddresses.getAddressDateCreated()).isEqualTo(UPDATED_ADDRESS_DATE_CREATED);
        assertThat(testAddresses.getAddressLastUpdated()).isEqualTo(UPDATED_ADDRESS_LAST_UPDATED);
        assertThat(testAddresses.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingAddresses() throws Exception {
        int databaseSizeBeforeUpdate = addressesRepository.findAll().size();

        // Create the Addresses

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAddressesMockMvc.perform(put("/api/addresses")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(addresses)))
            .andExpect(status().isBadRequest());

        // Validate the Addresses in the database
        List<Addresses> addressesList = addressesRepository.findAll();
        assertThat(addressesList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAddresses() throws Exception {
        // Initialize the database
        addressesRepository.saveAndFlush(addresses);

        int databaseSizeBeforeDelete = addressesRepository.findAll().size();

        // Get the addresses
        restAddressesMockMvc.perform(delete("/api/addresses/{id}", addresses.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Addresses> addressesList = addressesRepository.findAll();
        assertThat(addressesList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Addresses.class);
        Addresses addresses1 = new Addresses();
        addresses1.setId(1L);
        Addresses addresses2 = new Addresses();
        addresses2.setId(addresses1.getId());
        assertThat(addresses1).isEqualTo(addresses2);
        addresses2.setId(2L);
        assertThat(addresses1).isNotEqualTo(addresses2);
        addresses1.setId(null);
        assertThat(addresses1).isNotEqualTo(addresses2);
    }
}
