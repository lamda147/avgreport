package avgreport.mobitv.net.vn.web.rest;

import avgreport.mobitv.net.vn.AvgReportApp;

import avgreport.mobitv.net.vn.domain.Products;
import avgreport.mobitv.net.vn.repository.ProductsRepository;
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
 * Test class for the ProductsResource REST controller.
 *
 * @see ProductsResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AvgReportApp.class)
public class ProductsResourceIntTest {

    private static final String DEFAULT_PROD_ID = "AAAAAAAAAA";
    private static final String UPDATED_PROD_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_DESC = "AAAAAAAAAA";
    private static final String UPDATED_PROD_DESC = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROD_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_TYPE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_SUB_TYPE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_USER_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_PROD_USER_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_USER_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_PROD_USER_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_USER_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_PROD_USER_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_USER_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_PROD_USER_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_PROD_USER_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_PROD_USER_FIELD_5 = "BBBBBBBBBB";

    private static final Instant DEFAULT_PRODUCT_DATE_CREATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PRODUCT_DATE_CREATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_PRODUCT_LAST_UPDATED = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_PRODUCT_LAST_UPDATED = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Boolean DEFAULT_IS_DELETED = false;
    private static final Boolean UPDATED_IS_DELETED = true;

    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restProductsMockMvc;

    private Products products;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProductsResource productsResource = new ProductsResource(productsRepository);
        this.restProductsMockMvc = MockMvcBuilders.standaloneSetup(productsResource)
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
    public static Products createEntity(EntityManager em) {
        Products products = new Products()
            .prodID(DEFAULT_PROD_ID)
            .prodDesc(DEFAULT_PROD_DESC)
            .prodCode(DEFAULT_PROD_CODE)
            .subTypeName(DEFAULT_SUB_TYPE_NAME)
            .prodUserField1(DEFAULT_PROD_USER_FIELD_1)
            .prodUserField2(DEFAULT_PROD_USER_FIELD_2)
            .prodUserField3(DEFAULT_PROD_USER_FIELD_3)
            .prodUserField4(DEFAULT_PROD_USER_FIELD_4)
            .prodUserField5(DEFAULT_PROD_USER_FIELD_5)
            .productDateCreated(DEFAULT_PRODUCT_DATE_CREATED)
            .productLastUpdated(DEFAULT_PRODUCT_LAST_UPDATED)
            .isDeleted(DEFAULT_IS_DELETED);
        return products;
    }

    @Before
    public void initTest() {
        products = createEntity(em);
    }

    @Test
    @Transactional
    public void createProducts() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();

        // Create the Products
        restProductsMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(products)))
            .andExpect(status().isCreated());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate + 1);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getProdID()).isEqualTo(DEFAULT_PROD_ID);
        assertThat(testProducts.getProdDesc()).isEqualTo(DEFAULT_PROD_DESC);
        assertThat(testProducts.getProdCode()).isEqualTo(DEFAULT_PROD_CODE);
        assertThat(testProducts.getSubTypeName()).isEqualTo(DEFAULT_SUB_TYPE_NAME);
        assertThat(testProducts.getProdUserField1()).isEqualTo(DEFAULT_PROD_USER_FIELD_1);
        assertThat(testProducts.getProdUserField2()).isEqualTo(DEFAULT_PROD_USER_FIELD_2);
        assertThat(testProducts.getProdUserField3()).isEqualTo(DEFAULT_PROD_USER_FIELD_3);
        assertThat(testProducts.getProdUserField4()).isEqualTo(DEFAULT_PROD_USER_FIELD_4);
        assertThat(testProducts.getProdUserField5()).isEqualTo(DEFAULT_PROD_USER_FIELD_5);
        assertThat(testProducts.getProductDateCreated()).isEqualTo(DEFAULT_PRODUCT_DATE_CREATED);
        assertThat(testProducts.getProductLastUpdated()).isEqualTo(DEFAULT_PRODUCT_LAST_UPDATED);
        assertThat(testProducts.isIsDeleted()).isEqualTo(DEFAULT_IS_DELETED);
    }

    @Test
    @Transactional
    public void createProductsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = productsRepository.findAll().size();

        // Create the Products with an existing ID
        products.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProductsMockMvc.perform(post("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(products)))
            .andExpect(status().isBadRequest());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        // Get all the productsList
        restProductsMockMvc.perform(get("/api/products?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(products.getId().intValue())))
            .andExpect(jsonPath("$.[*].prodID").value(hasItem(DEFAULT_PROD_ID.toString())))
            .andExpect(jsonPath("$.[*].prodDesc").value(hasItem(DEFAULT_PROD_DESC.toString())))
            .andExpect(jsonPath("$.[*].prodCode").value(hasItem(DEFAULT_PROD_CODE.toString())))
            .andExpect(jsonPath("$.[*].subTypeName").value(hasItem(DEFAULT_SUB_TYPE_NAME.toString())))
            .andExpect(jsonPath("$.[*].prodUserField1").value(hasItem(DEFAULT_PROD_USER_FIELD_1.toString())))
            .andExpect(jsonPath("$.[*].prodUserField2").value(hasItem(DEFAULT_PROD_USER_FIELD_2.toString())))
            .andExpect(jsonPath("$.[*].prodUserField3").value(hasItem(DEFAULT_PROD_USER_FIELD_3.toString())))
            .andExpect(jsonPath("$.[*].prodUserField4").value(hasItem(DEFAULT_PROD_USER_FIELD_4.toString())))
            .andExpect(jsonPath("$.[*].prodUserField5").value(hasItem(DEFAULT_PROD_USER_FIELD_5.toString())))
            .andExpect(jsonPath("$.[*].productDateCreated").value(hasItem(DEFAULT_PRODUCT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].productLastUpdated").value(hasItem(DEFAULT_PRODUCT_LAST_UPDATED.toString())))
            .andExpect(jsonPath("$.[*].isDeleted").value(hasItem(DEFAULT_IS_DELETED.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", products.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(products.getId().intValue()))
            .andExpect(jsonPath("$.prodID").value(DEFAULT_PROD_ID.toString()))
            .andExpect(jsonPath("$.prodDesc").value(DEFAULT_PROD_DESC.toString()))
            .andExpect(jsonPath("$.prodCode").value(DEFAULT_PROD_CODE.toString()))
            .andExpect(jsonPath("$.subTypeName").value(DEFAULT_SUB_TYPE_NAME.toString()))
            .andExpect(jsonPath("$.prodUserField1").value(DEFAULT_PROD_USER_FIELD_1.toString()))
            .andExpect(jsonPath("$.prodUserField2").value(DEFAULT_PROD_USER_FIELD_2.toString()))
            .andExpect(jsonPath("$.prodUserField3").value(DEFAULT_PROD_USER_FIELD_3.toString()))
            .andExpect(jsonPath("$.prodUserField4").value(DEFAULT_PROD_USER_FIELD_4.toString()))
            .andExpect(jsonPath("$.prodUserField5").value(DEFAULT_PROD_USER_FIELD_5.toString()))
            .andExpect(jsonPath("$.productDateCreated").value(DEFAULT_PRODUCT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.productLastUpdated").value(DEFAULT_PRODUCT_LAST_UPDATED.toString()))
            .andExpect(jsonPath("$.isDeleted").value(DEFAULT_IS_DELETED.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingProducts() throws Exception {
        // Get the products
        restProductsMockMvc.perform(get("/api/products/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // Update the products
        Products updatedProducts = productsRepository.findById(products.getId()).get();
        // Disconnect from session so that the updates on updatedProducts are not directly saved in db
        em.detach(updatedProducts);
        updatedProducts
            .prodID(UPDATED_PROD_ID)
            .prodDesc(UPDATED_PROD_DESC)
            .prodCode(UPDATED_PROD_CODE)
            .subTypeName(UPDATED_SUB_TYPE_NAME)
            .prodUserField1(UPDATED_PROD_USER_FIELD_1)
            .prodUserField2(UPDATED_PROD_USER_FIELD_2)
            .prodUserField3(UPDATED_PROD_USER_FIELD_3)
            .prodUserField4(UPDATED_PROD_USER_FIELD_4)
            .prodUserField5(UPDATED_PROD_USER_FIELD_5)
            .productDateCreated(UPDATED_PRODUCT_DATE_CREATED)
            .productLastUpdated(UPDATED_PRODUCT_LAST_UPDATED)
            .isDeleted(UPDATED_IS_DELETED);

        restProductsMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedProducts)))
            .andExpect(status().isOk());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate);
        Products testProducts = productsList.get(productsList.size() - 1);
        assertThat(testProducts.getProdID()).isEqualTo(UPDATED_PROD_ID);
        assertThat(testProducts.getProdDesc()).isEqualTo(UPDATED_PROD_DESC);
        assertThat(testProducts.getProdCode()).isEqualTo(UPDATED_PROD_CODE);
        assertThat(testProducts.getSubTypeName()).isEqualTo(UPDATED_SUB_TYPE_NAME);
        assertThat(testProducts.getProdUserField1()).isEqualTo(UPDATED_PROD_USER_FIELD_1);
        assertThat(testProducts.getProdUserField2()).isEqualTo(UPDATED_PROD_USER_FIELD_2);
        assertThat(testProducts.getProdUserField3()).isEqualTo(UPDATED_PROD_USER_FIELD_3);
        assertThat(testProducts.getProdUserField4()).isEqualTo(UPDATED_PROD_USER_FIELD_4);
        assertThat(testProducts.getProdUserField5()).isEqualTo(UPDATED_PROD_USER_FIELD_5);
        assertThat(testProducts.getProductDateCreated()).isEqualTo(UPDATED_PRODUCT_DATE_CREATED);
        assertThat(testProducts.getProductLastUpdated()).isEqualTo(UPDATED_PRODUCT_LAST_UPDATED);
        assertThat(testProducts.isIsDeleted()).isEqualTo(UPDATED_IS_DELETED);
    }

    @Test
    @Transactional
    public void updateNonExistingProducts() throws Exception {
        int databaseSizeBeforeUpdate = productsRepository.findAll().size();

        // Create the Products

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProductsMockMvc.perform(put("/api/products")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(products)))
            .andExpect(status().isBadRequest());

        // Validate the Products in the database
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProducts() throws Exception {
        // Initialize the database
        productsRepository.saveAndFlush(products);

        int databaseSizeBeforeDelete = productsRepository.findAll().size();

        // Get the products
        restProductsMockMvc.perform(delete("/api/products/{id}", products.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Products> productsList = productsRepository.findAll();
        assertThat(productsList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Products.class);
        Products products1 = new Products();
        products1.setId(1L);
        Products products2 = new Products();
        products2.setId(products1.getId());
        assertThat(products1).isEqualTo(products2);
        products2.setId(2L);
        assertThat(products1).isNotEqualTo(products2);
        products1.setId(null);
        assertThat(products1).isNotEqualTo(products2);
    }
}
