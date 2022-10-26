package co.com.ies.smol.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.com.ies.smol.IntegrationTest;
import co.com.ies.smol.domain.Country;
import co.com.ies.smol.domain.Department;
import co.com.ies.smol.repository.DepartmentRepository;
import co.com.ies.smol.service.criteria.DepartmentCriteria;
import co.com.ies.smol.service.dto.DepartmentDTO;
import co.com.ies.smol.service.mapper.DepartmentMapper;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link DepartmentResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class DepartmentResourceIT {

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DANE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_DANE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PHONE_ID = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_ID = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/departments";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepartmentMockMvc;

    private Department department;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Department createEntity(EntityManager em) {
        Department department = new Department()
            .code(DEFAULT_CODE)
            .name(DEFAULT_NAME)
            .daneCode(DEFAULT_DANE_CODE)
            .phoneId(DEFAULT_PHONE_ID);
        return department;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Department createUpdatedEntity(EntityManager em) {
        Department department = new Department()
            .code(UPDATED_CODE)
            .name(UPDATED_NAME)
            .daneCode(UPDATED_DANE_CODE)
            .phoneId(UPDATED_PHONE_ID);
        return department;
    }

    @BeforeEach
    public void initTest() {
        department = createEntity(em);
    }

    @Test
    @Transactional
    void createDepartment() throws Exception {
        int databaseSizeBeforeCreate = departmentRepository.findAll().size();
        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);
        restDepartmentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isCreated());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate + 1);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDepartment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testDepartment.getDaneCode()).isEqualTo(DEFAULT_DANE_CODE);
        assertThat(testDepartment.getPhoneId()).isEqualTo(DEFAULT_PHONE_ID);
    }

    @Test
    @Transactional
    void createDepartmentWithExistingId() throws Exception {
        // Create the Department with an existing ID
        department.setId(1L);
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        int databaseSizeBeforeCreate = departmentRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepartmentMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllDepartments() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList
        restDepartmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(department.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].daneCode").value(hasItem(DEFAULT_DANE_CODE)))
            .andExpect(jsonPath("$.[*].phoneId").value(hasItem(DEFAULT_PHONE_ID)));
    }

    @Test
    @Transactional
    void getDepartment() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get the department
        restDepartmentMockMvc
            .perform(get(ENTITY_API_URL_ID, department.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(department.getId().intValue()))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.daneCode").value(DEFAULT_DANE_CODE))
            .andExpect(jsonPath("$.phoneId").value(DEFAULT_PHONE_ID));
    }

    @Test
    @Transactional
    void getDepartmentsByIdFiltering() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        Long id = department.getId();

        defaultDepartmentShouldBeFound("id.equals=" + id);
        defaultDepartmentShouldNotBeFound("id.notEquals=" + id);

        defaultDepartmentShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDepartmentShouldNotBeFound("id.greaterThan=" + id);

        defaultDepartmentShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDepartmentShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllDepartmentsByCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where code equals to DEFAULT_CODE
        defaultDepartmentShouldBeFound("code.equals=" + DEFAULT_CODE);

        // Get all the departmentList where code equals to UPDATED_CODE
        defaultDepartmentShouldNotBeFound("code.equals=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByCodeIsInShouldWork() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where code in DEFAULT_CODE or UPDATED_CODE
        defaultDepartmentShouldBeFound("code.in=" + DEFAULT_CODE + "," + UPDATED_CODE);

        // Get all the departmentList where code equals to UPDATED_CODE
        defaultDepartmentShouldNotBeFound("code.in=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where code is not null
        defaultDepartmentShouldBeFound("code.specified=true");

        // Get all the departmentList where code is null
        defaultDepartmentShouldNotBeFound("code.specified=false");
    }

    @Test
    @Transactional
    void getAllDepartmentsByCodeContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where code contains DEFAULT_CODE
        defaultDepartmentShouldBeFound("code.contains=" + DEFAULT_CODE);

        // Get all the departmentList where code contains UPDATED_CODE
        defaultDepartmentShouldNotBeFound("code.contains=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByCodeNotContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where code does not contain DEFAULT_CODE
        defaultDepartmentShouldNotBeFound("code.doesNotContain=" + DEFAULT_CODE);

        // Get all the departmentList where code does not contain UPDATED_CODE
        defaultDepartmentShouldBeFound("code.doesNotContain=" + UPDATED_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where name equals to DEFAULT_NAME
        defaultDepartmentShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the departmentList where name equals to UPDATED_NAME
        defaultDepartmentShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDepartmentsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDepartmentShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the departmentList where name equals to UPDATED_NAME
        defaultDepartmentShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDepartmentsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where name is not null
        defaultDepartmentShouldBeFound("name.specified=true");

        // Get all the departmentList where name is null
        defaultDepartmentShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllDepartmentsByNameContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where name contains DEFAULT_NAME
        defaultDepartmentShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the departmentList where name contains UPDATED_NAME
        defaultDepartmentShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDepartmentsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where name does not contain DEFAULT_NAME
        defaultDepartmentShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the departmentList where name does not contain UPDATED_NAME
        defaultDepartmentShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllDepartmentsByDaneCodeIsEqualToSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where daneCode equals to DEFAULT_DANE_CODE
        defaultDepartmentShouldBeFound("daneCode.equals=" + DEFAULT_DANE_CODE);

        // Get all the departmentList where daneCode equals to UPDATED_DANE_CODE
        defaultDepartmentShouldNotBeFound("daneCode.equals=" + UPDATED_DANE_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByDaneCodeIsInShouldWork() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where daneCode in DEFAULT_DANE_CODE or UPDATED_DANE_CODE
        defaultDepartmentShouldBeFound("daneCode.in=" + DEFAULT_DANE_CODE + "," + UPDATED_DANE_CODE);

        // Get all the departmentList where daneCode equals to UPDATED_DANE_CODE
        defaultDepartmentShouldNotBeFound("daneCode.in=" + UPDATED_DANE_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByDaneCodeIsNullOrNotNull() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where daneCode is not null
        defaultDepartmentShouldBeFound("daneCode.specified=true");

        // Get all the departmentList where daneCode is null
        defaultDepartmentShouldNotBeFound("daneCode.specified=false");
    }

    @Test
    @Transactional
    void getAllDepartmentsByDaneCodeContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where daneCode contains DEFAULT_DANE_CODE
        defaultDepartmentShouldBeFound("daneCode.contains=" + DEFAULT_DANE_CODE);

        // Get all the departmentList where daneCode contains UPDATED_DANE_CODE
        defaultDepartmentShouldNotBeFound("daneCode.contains=" + UPDATED_DANE_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByDaneCodeNotContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where daneCode does not contain DEFAULT_DANE_CODE
        defaultDepartmentShouldNotBeFound("daneCode.doesNotContain=" + DEFAULT_DANE_CODE);

        // Get all the departmentList where daneCode does not contain UPDATED_DANE_CODE
        defaultDepartmentShouldBeFound("daneCode.doesNotContain=" + UPDATED_DANE_CODE);
    }

    @Test
    @Transactional
    void getAllDepartmentsByPhoneIdIsEqualToSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where phoneId equals to DEFAULT_PHONE_ID
        defaultDepartmentShouldBeFound("phoneId.equals=" + DEFAULT_PHONE_ID);

        // Get all the departmentList where phoneId equals to UPDATED_PHONE_ID
        defaultDepartmentShouldNotBeFound("phoneId.equals=" + UPDATED_PHONE_ID);
    }

    @Test
    @Transactional
    void getAllDepartmentsByPhoneIdIsInShouldWork() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where phoneId in DEFAULT_PHONE_ID or UPDATED_PHONE_ID
        defaultDepartmentShouldBeFound("phoneId.in=" + DEFAULT_PHONE_ID + "," + UPDATED_PHONE_ID);

        // Get all the departmentList where phoneId equals to UPDATED_PHONE_ID
        defaultDepartmentShouldNotBeFound("phoneId.in=" + UPDATED_PHONE_ID);
    }

    @Test
    @Transactional
    void getAllDepartmentsByPhoneIdIsNullOrNotNull() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where phoneId is not null
        defaultDepartmentShouldBeFound("phoneId.specified=true");

        // Get all the departmentList where phoneId is null
        defaultDepartmentShouldNotBeFound("phoneId.specified=false");
    }

    @Test
    @Transactional
    void getAllDepartmentsByPhoneIdContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where phoneId contains DEFAULT_PHONE_ID
        defaultDepartmentShouldBeFound("phoneId.contains=" + DEFAULT_PHONE_ID);

        // Get all the departmentList where phoneId contains UPDATED_PHONE_ID
        defaultDepartmentShouldNotBeFound("phoneId.contains=" + UPDATED_PHONE_ID);
    }

    @Test
    @Transactional
    void getAllDepartmentsByPhoneIdNotContainsSomething() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        // Get all the departmentList where phoneId does not contain DEFAULT_PHONE_ID
        defaultDepartmentShouldNotBeFound("phoneId.doesNotContain=" + DEFAULT_PHONE_ID);

        // Get all the departmentList where phoneId does not contain UPDATED_PHONE_ID
        defaultDepartmentShouldBeFound("phoneId.doesNotContain=" + UPDATED_PHONE_ID);
    }

    @Test
    @Transactional
    void getAllDepartmentsByCountryIsEqualToSomething() throws Exception {
        Country country;
        if (TestUtil.findAll(em, Country.class).isEmpty()) {
            departmentRepository.saveAndFlush(department);
            country = CountryResourceIT.createEntity(em);
        } else {
            country = TestUtil.findAll(em, Country.class).get(0);
        }
        em.persist(country);
        em.flush();
        department.setCountry(country);
        departmentRepository.saveAndFlush(department);
        Long countryId = country.getId();

        // Get all the departmentList where country equals to countryId
        defaultDepartmentShouldBeFound("countryId.equals=" + countryId);

        // Get all the departmentList where country equals to (countryId + 1)
        defaultDepartmentShouldNotBeFound("countryId.equals=" + (countryId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDepartmentShouldBeFound(String filter) throws Exception {
        restDepartmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(department.getId().intValue())))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].daneCode").value(hasItem(DEFAULT_DANE_CODE)))
            .andExpect(jsonPath("$.[*].phoneId").value(hasItem(DEFAULT_PHONE_ID)));

        // Check, that the count call also returns 1
        restDepartmentMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDepartmentShouldNotBeFound(String filter) throws Exception {
        restDepartmentMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDepartmentMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingDepartment() throws Exception {
        // Get the department
        restDepartmentMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingDepartment() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Update the department
        Department updatedDepartment = departmentRepository.findById(department.getId()).get();
        // Disconnect from session so that the updates on updatedDepartment are not directly saved in db
        em.detach(updatedDepartment);
        updatedDepartment.code(UPDATED_CODE).name(UPDATED_NAME).daneCode(UPDATED_DANE_CODE).phoneId(UPDATED_PHONE_ID);
        DepartmentDTO departmentDTO = departmentMapper.toDto(updatedDepartment);

        restDepartmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, departmentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(departmentDTO))
            )
            .andExpect(status().isOk());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDepartment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDepartment.getDaneCode()).isEqualTo(UPDATED_DANE_CODE);
        assertThat(testDepartment.getPhoneId()).isEqualTo(UPDATED_PHONE_ID);
    }

    @Test
    @Transactional
    void putNonExistingDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();
        department.setId(count.incrementAndGet());

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, departmentDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(departmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();
        department.setId(count.incrementAndGet());

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartmentMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(departmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();
        department.setId(count.incrementAndGet());

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartmentMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(departmentDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateDepartmentWithPatch() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Update the department using partial update
        Department partialUpdatedDepartment = new Department();
        partialUpdatedDepartment.setId(department.getId());

        partialUpdatedDepartment.name(UPDATED_NAME).phoneId(UPDATED_PHONE_ID);

        restDepartmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepartment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepartment))
            )
            .andExpect(status().isOk());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testDepartment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDepartment.getDaneCode()).isEqualTo(DEFAULT_DANE_CODE);
        assertThat(testDepartment.getPhoneId()).isEqualTo(UPDATED_PHONE_ID);
    }

    @Test
    @Transactional
    void fullUpdateDepartmentWithPatch() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();

        // Update the department using partial update
        Department partialUpdatedDepartment = new Department();
        partialUpdatedDepartment.setId(department.getId());

        partialUpdatedDepartment.code(UPDATED_CODE).name(UPDATED_NAME).daneCode(UPDATED_DANE_CODE).phoneId(UPDATED_PHONE_ID);

        restDepartmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedDepartment.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedDepartment))
            )
            .andExpect(status().isOk());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
        Department testDepartment = departmentList.get(departmentList.size() - 1);
        assertThat(testDepartment.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testDepartment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testDepartment.getDaneCode()).isEqualTo(UPDATED_DANE_CODE);
        assertThat(testDepartment.getPhoneId()).isEqualTo(UPDATED_PHONE_ID);
    }

    @Test
    @Transactional
    void patchNonExistingDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();
        department.setId(count.incrementAndGet());

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepartmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, departmentDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(departmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();
        department.setId(count.incrementAndGet());

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartmentMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(departmentDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamDepartment() throws Exception {
        int databaseSizeBeforeUpdate = departmentRepository.findAll().size();
        department.setId(count.incrementAndGet());

        // Create the Department
        DepartmentDTO departmentDTO = departmentMapper.toDto(department);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restDepartmentMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(departmentDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Department in the database
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteDepartment() throws Exception {
        // Initialize the database
        departmentRepository.saveAndFlush(department);

        int databaseSizeBeforeDelete = departmentRepository.findAll().size();

        // Delete the department
        restDepartmentMockMvc
            .perform(delete(ENTITY_API_URL_ID, department.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Department> departmentList = departmentRepository.findAll();
        assertThat(departmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
