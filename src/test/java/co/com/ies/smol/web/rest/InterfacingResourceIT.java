package co.com.ies.smol.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.com.ies.smol.IntegrationTest;
import co.com.ies.smol.domain.Establishment;
import co.com.ies.smol.domain.Interfacing;
import co.com.ies.smol.repository.InterfacingRepository;
import co.com.ies.smol.service.criteria.InterfacingCriteria;
import co.com.ies.smol.service.dto.InterfacingDTO;
import co.com.ies.smol.service.mapper.InterfacingMapper;
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
 * Integration tests for the {@link InterfacingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class InterfacingResourceIT {

    private static final Boolean DEFAULT_IS_ASSIGNED = false;
    private static final Boolean UPDATED_IS_ASSIGNED = true;

    private static final String DEFAULT_IP_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_IP_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_HASH = "AAAAAAAAAA";
    private static final String UPDATED_HASH = "BBBBBBBBBB";

    private static final String DEFAULT_SERIAL = "AAAAAAAAAA";
    private static final String UPDATED_SERIAL = "BBBBBBBBBB";

    private static final String DEFAULT_VERSION = "AAAAAAAAAA";
    private static final String UPDATED_VERSION = "BBBBBBBBBB";

    private static final String DEFAULT_PORT = "AAAAAAAAAA";
    private static final String UPDATED_PORT = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/interfacings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private InterfacingRepository interfacingRepository;

    @Autowired
    private InterfacingMapper interfacingMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restInterfacingMockMvc;

    private Interfacing interfacing;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interfacing createEntity(EntityManager em) {
        Interfacing interfacing = new Interfacing()
            .isAssigned(DEFAULT_IS_ASSIGNED)
            .ipAddress(DEFAULT_IP_ADDRESS)
            .hash(DEFAULT_HASH)
            .serial(DEFAULT_SERIAL)
            .version(DEFAULT_VERSION)
            .port(DEFAULT_PORT);
        // Add required entity
        Establishment establishment;
        if (TestUtil.findAll(em, Establishment.class).isEmpty()) {
            establishment = EstablishmentResourceIT.createEntity(em);
            em.persist(establishment);
            em.flush();
        } else {
            establishment = TestUtil.findAll(em, Establishment.class).get(0);
        }
        interfacing.setIdEstablishment(establishment);
        return interfacing;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Interfacing createUpdatedEntity(EntityManager em) {
        Interfacing interfacing = new Interfacing()
            .isAssigned(UPDATED_IS_ASSIGNED)
            .ipAddress(UPDATED_IP_ADDRESS)
            .hash(UPDATED_HASH)
            .serial(UPDATED_SERIAL)
            .version(UPDATED_VERSION)
            .port(UPDATED_PORT);
        // Add required entity
        Establishment establishment;
        if (TestUtil.findAll(em, Establishment.class).isEmpty()) {
            establishment = EstablishmentResourceIT.createUpdatedEntity(em);
            em.persist(establishment);
            em.flush();
        } else {
            establishment = TestUtil.findAll(em, Establishment.class).get(0);
        }
        interfacing.setIdEstablishment(establishment);
        return interfacing;
    }

    @BeforeEach
    public void initTest() {
        interfacing = createEntity(em);
    }

    @Test
    @Transactional
    void createInterfacing() throws Exception {
        int databaseSizeBeforeCreate = interfacingRepository.findAll().size();
        // Create the Interfacing
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);
        restInterfacingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isCreated());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeCreate + 1);
        Interfacing testInterfacing = interfacingList.get(interfacingList.size() - 1);
        assertThat(testInterfacing.getIsAssigned()).isEqualTo(DEFAULT_IS_ASSIGNED);
        assertThat(testInterfacing.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testInterfacing.getHash()).isEqualTo(DEFAULT_HASH);
        assertThat(testInterfacing.getSerial()).isEqualTo(DEFAULT_SERIAL);
        assertThat(testInterfacing.getVersion()).isEqualTo(DEFAULT_VERSION);
        assertThat(testInterfacing.getPort()).isEqualTo(DEFAULT_PORT);
    }

    @Test
    @Transactional
    void createInterfacingWithExistingId() throws Exception {
        // Create the Interfacing with an existing ID
        interfacing.setId(1L);
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);

        int databaseSizeBeforeCreate = interfacingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restInterfacingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllInterfacings() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList
        restInterfacingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interfacing.getId().intValue())))
            .andExpect(jsonPath("$.[*].isAssigned").value(hasItem(DEFAULT_IS_ASSIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].hash").value(hasItem(DEFAULT_HASH)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)));
    }

    @Test
    @Transactional
    void getInterfacing() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get the interfacing
        restInterfacingMockMvc
            .perform(get(ENTITY_API_URL_ID, interfacing.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(interfacing.getId().intValue()))
            .andExpect(jsonPath("$.isAssigned").value(DEFAULT_IS_ASSIGNED.booleanValue()))
            .andExpect(jsonPath("$.ipAddress").value(DEFAULT_IP_ADDRESS))
            .andExpect(jsonPath("$.hash").value(DEFAULT_HASH))
            .andExpect(jsonPath("$.serial").value(DEFAULT_SERIAL))
            .andExpect(jsonPath("$.version").value(DEFAULT_VERSION))
            .andExpect(jsonPath("$.port").value(DEFAULT_PORT));
    }

    @Test
    @Transactional
    void getInterfacingsByIdFiltering() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        Long id = interfacing.getId();

        defaultInterfacingShouldBeFound("id.equals=" + id);
        defaultInterfacingShouldNotBeFound("id.notEquals=" + id);

        defaultInterfacingShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultInterfacingShouldNotBeFound("id.greaterThan=" + id);

        defaultInterfacingShouldBeFound("id.lessThanOrEqual=" + id);
        defaultInterfacingShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllInterfacingsByIsAssignedIsEqualToSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where isAssigned equals to DEFAULT_IS_ASSIGNED
        defaultInterfacingShouldBeFound("isAssigned.equals=" + DEFAULT_IS_ASSIGNED);

        // Get all the interfacingList where isAssigned equals to UPDATED_IS_ASSIGNED
        defaultInterfacingShouldNotBeFound("isAssigned.equals=" + UPDATED_IS_ASSIGNED);
    }

    @Test
    @Transactional
    void getAllInterfacingsByIsAssignedIsInShouldWork() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where isAssigned in DEFAULT_IS_ASSIGNED or UPDATED_IS_ASSIGNED
        defaultInterfacingShouldBeFound("isAssigned.in=" + DEFAULT_IS_ASSIGNED + "," + UPDATED_IS_ASSIGNED);

        // Get all the interfacingList where isAssigned equals to UPDATED_IS_ASSIGNED
        defaultInterfacingShouldNotBeFound("isAssigned.in=" + UPDATED_IS_ASSIGNED);
    }

    @Test
    @Transactional
    void getAllInterfacingsByIsAssignedIsNullOrNotNull() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where isAssigned is not null
        defaultInterfacingShouldBeFound("isAssigned.specified=true");

        // Get all the interfacingList where isAssigned is null
        defaultInterfacingShouldNotBeFound("isAssigned.specified=false");
    }

    @Test
    @Transactional
    void getAllInterfacingsByIpAddressIsEqualToSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where ipAddress equals to DEFAULT_IP_ADDRESS
        defaultInterfacingShouldBeFound("ipAddress.equals=" + DEFAULT_IP_ADDRESS);

        // Get all the interfacingList where ipAddress equals to UPDATED_IP_ADDRESS
        defaultInterfacingShouldNotBeFound("ipAddress.equals=" + UPDATED_IP_ADDRESS);
    }

    @Test
    @Transactional
    void getAllInterfacingsByIpAddressIsInShouldWork() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where ipAddress in DEFAULT_IP_ADDRESS or UPDATED_IP_ADDRESS
        defaultInterfacingShouldBeFound("ipAddress.in=" + DEFAULT_IP_ADDRESS + "," + UPDATED_IP_ADDRESS);

        // Get all the interfacingList where ipAddress equals to UPDATED_IP_ADDRESS
        defaultInterfacingShouldNotBeFound("ipAddress.in=" + UPDATED_IP_ADDRESS);
    }

    @Test
    @Transactional
    void getAllInterfacingsByIpAddressIsNullOrNotNull() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where ipAddress is not null
        defaultInterfacingShouldBeFound("ipAddress.specified=true");

        // Get all the interfacingList where ipAddress is null
        defaultInterfacingShouldNotBeFound("ipAddress.specified=false");
    }

    @Test
    @Transactional
    void getAllInterfacingsByIpAddressContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where ipAddress contains DEFAULT_IP_ADDRESS
        defaultInterfacingShouldBeFound("ipAddress.contains=" + DEFAULT_IP_ADDRESS);

        // Get all the interfacingList where ipAddress contains UPDATED_IP_ADDRESS
        defaultInterfacingShouldNotBeFound("ipAddress.contains=" + UPDATED_IP_ADDRESS);
    }

    @Test
    @Transactional
    void getAllInterfacingsByIpAddressNotContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where ipAddress does not contain DEFAULT_IP_ADDRESS
        defaultInterfacingShouldNotBeFound("ipAddress.doesNotContain=" + DEFAULT_IP_ADDRESS);

        // Get all the interfacingList where ipAddress does not contain UPDATED_IP_ADDRESS
        defaultInterfacingShouldBeFound("ipAddress.doesNotContain=" + UPDATED_IP_ADDRESS);
    }

    @Test
    @Transactional
    void getAllInterfacingsByHashIsEqualToSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where hash equals to DEFAULT_HASH
        defaultInterfacingShouldBeFound("hash.equals=" + DEFAULT_HASH);

        // Get all the interfacingList where hash equals to UPDATED_HASH
        defaultInterfacingShouldNotBeFound("hash.equals=" + UPDATED_HASH);
    }

    @Test
    @Transactional
    void getAllInterfacingsByHashIsInShouldWork() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where hash in DEFAULT_HASH or UPDATED_HASH
        defaultInterfacingShouldBeFound("hash.in=" + DEFAULT_HASH + "," + UPDATED_HASH);

        // Get all the interfacingList where hash equals to UPDATED_HASH
        defaultInterfacingShouldNotBeFound("hash.in=" + UPDATED_HASH);
    }

    @Test
    @Transactional
    void getAllInterfacingsByHashIsNullOrNotNull() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where hash is not null
        defaultInterfacingShouldBeFound("hash.specified=true");

        // Get all the interfacingList where hash is null
        defaultInterfacingShouldNotBeFound("hash.specified=false");
    }

    @Test
    @Transactional
    void getAllInterfacingsByHashContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where hash contains DEFAULT_HASH
        defaultInterfacingShouldBeFound("hash.contains=" + DEFAULT_HASH);

        // Get all the interfacingList where hash contains UPDATED_HASH
        defaultInterfacingShouldNotBeFound("hash.contains=" + UPDATED_HASH);
    }

    @Test
    @Transactional
    void getAllInterfacingsByHashNotContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where hash does not contain DEFAULT_HASH
        defaultInterfacingShouldNotBeFound("hash.doesNotContain=" + DEFAULT_HASH);

        // Get all the interfacingList where hash does not contain UPDATED_HASH
        defaultInterfacingShouldBeFound("hash.doesNotContain=" + UPDATED_HASH);
    }

    @Test
    @Transactional
    void getAllInterfacingsBySerialIsEqualToSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where serial equals to DEFAULT_SERIAL
        defaultInterfacingShouldBeFound("serial.equals=" + DEFAULT_SERIAL);

        // Get all the interfacingList where serial equals to UPDATED_SERIAL
        defaultInterfacingShouldNotBeFound("serial.equals=" + UPDATED_SERIAL);
    }

    @Test
    @Transactional
    void getAllInterfacingsBySerialIsInShouldWork() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where serial in DEFAULT_SERIAL or UPDATED_SERIAL
        defaultInterfacingShouldBeFound("serial.in=" + DEFAULT_SERIAL + "," + UPDATED_SERIAL);

        // Get all the interfacingList where serial equals to UPDATED_SERIAL
        defaultInterfacingShouldNotBeFound("serial.in=" + UPDATED_SERIAL);
    }

    @Test
    @Transactional
    void getAllInterfacingsBySerialIsNullOrNotNull() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where serial is not null
        defaultInterfacingShouldBeFound("serial.specified=true");

        // Get all the interfacingList where serial is null
        defaultInterfacingShouldNotBeFound("serial.specified=false");
    }

    @Test
    @Transactional
    void getAllInterfacingsBySerialContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where serial contains DEFAULT_SERIAL
        defaultInterfacingShouldBeFound("serial.contains=" + DEFAULT_SERIAL);

        // Get all the interfacingList where serial contains UPDATED_SERIAL
        defaultInterfacingShouldNotBeFound("serial.contains=" + UPDATED_SERIAL);
    }

    @Test
    @Transactional
    void getAllInterfacingsBySerialNotContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where serial does not contain DEFAULT_SERIAL
        defaultInterfacingShouldNotBeFound("serial.doesNotContain=" + DEFAULT_SERIAL);

        // Get all the interfacingList where serial does not contain UPDATED_SERIAL
        defaultInterfacingShouldBeFound("serial.doesNotContain=" + UPDATED_SERIAL);
    }

    @Test
    @Transactional
    void getAllInterfacingsByVersionIsEqualToSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where version equals to DEFAULT_VERSION
        defaultInterfacingShouldBeFound("version.equals=" + DEFAULT_VERSION);

        // Get all the interfacingList where version equals to UPDATED_VERSION
        defaultInterfacingShouldNotBeFound("version.equals=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    void getAllInterfacingsByVersionIsInShouldWork() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where version in DEFAULT_VERSION or UPDATED_VERSION
        defaultInterfacingShouldBeFound("version.in=" + DEFAULT_VERSION + "," + UPDATED_VERSION);

        // Get all the interfacingList where version equals to UPDATED_VERSION
        defaultInterfacingShouldNotBeFound("version.in=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    void getAllInterfacingsByVersionIsNullOrNotNull() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where version is not null
        defaultInterfacingShouldBeFound("version.specified=true");

        // Get all the interfacingList where version is null
        defaultInterfacingShouldNotBeFound("version.specified=false");
    }

    @Test
    @Transactional
    void getAllInterfacingsByVersionContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where version contains DEFAULT_VERSION
        defaultInterfacingShouldBeFound("version.contains=" + DEFAULT_VERSION);

        // Get all the interfacingList where version contains UPDATED_VERSION
        defaultInterfacingShouldNotBeFound("version.contains=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    void getAllInterfacingsByVersionNotContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where version does not contain DEFAULT_VERSION
        defaultInterfacingShouldNotBeFound("version.doesNotContain=" + DEFAULT_VERSION);

        // Get all the interfacingList where version does not contain UPDATED_VERSION
        defaultInterfacingShouldBeFound("version.doesNotContain=" + UPDATED_VERSION);
    }

    @Test
    @Transactional
    void getAllInterfacingsByPortIsEqualToSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where port equals to DEFAULT_PORT
        defaultInterfacingShouldBeFound("port.equals=" + DEFAULT_PORT);

        // Get all the interfacingList where port equals to UPDATED_PORT
        defaultInterfacingShouldNotBeFound("port.equals=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    void getAllInterfacingsByPortIsInShouldWork() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where port in DEFAULT_PORT or UPDATED_PORT
        defaultInterfacingShouldBeFound("port.in=" + DEFAULT_PORT + "," + UPDATED_PORT);

        // Get all the interfacingList where port equals to UPDATED_PORT
        defaultInterfacingShouldNotBeFound("port.in=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    void getAllInterfacingsByPortIsNullOrNotNull() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where port is not null
        defaultInterfacingShouldBeFound("port.specified=true");

        // Get all the interfacingList where port is null
        defaultInterfacingShouldNotBeFound("port.specified=false");
    }

    @Test
    @Transactional
    void getAllInterfacingsByPortContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where port contains DEFAULT_PORT
        defaultInterfacingShouldBeFound("port.contains=" + DEFAULT_PORT);

        // Get all the interfacingList where port contains UPDATED_PORT
        defaultInterfacingShouldNotBeFound("port.contains=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    void getAllInterfacingsByPortNotContainsSomething() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        // Get all the interfacingList where port does not contain DEFAULT_PORT
        defaultInterfacingShouldNotBeFound("port.doesNotContain=" + DEFAULT_PORT);

        // Get all the interfacingList where port does not contain UPDATED_PORT
        defaultInterfacingShouldBeFound("port.doesNotContain=" + UPDATED_PORT);
    }

    @Test
    @Transactional
    void getAllInterfacingsByIdEstablishmentIsEqualToSomething() throws Exception {
        Establishment idEstablishment;
        if (TestUtil.findAll(em, Establishment.class).isEmpty()) {
            interfacingRepository.saveAndFlush(interfacing);
            idEstablishment = EstablishmentResourceIT.createEntity(em);
        } else {
            idEstablishment = TestUtil.findAll(em, Establishment.class).get(0);
        }
        em.persist(idEstablishment);
        em.flush();
        interfacing.setIdEstablishment(idEstablishment);
        interfacingRepository.saveAndFlush(interfacing);
        Long idEstablishmentId = idEstablishment.getId();

        // Get all the interfacingList where idEstablishment equals to idEstablishmentId
        defaultInterfacingShouldBeFound("idEstablishmentId.equals=" + idEstablishmentId);

        // Get all the interfacingList where idEstablishment equals to (idEstablishmentId + 1)
        defaultInterfacingShouldNotBeFound("idEstablishmentId.equals=" + (idEstablishmentId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultInterfacingShouldBeFound(String filter) throws Exception {
        restInterfacingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(interfacing.getId().intValue())))
            .andExpect(jsonPath("$.[*].isAssigned").value(hasItem(DEFAULT_IS_ASSIGNED.booleanValue())))
            .andExpect(jsonPath("$.[*].ipAddress").value(hasItem(DEFAULT_IP_ADDRESS)))
            .andExpect(jsonPath("$.[*].hash").value(hasItem(DEFAULT_HASH)))
            .andExpect(jsonPath("$.[*].serial").value(hasItem(DEFAULT_SERIAL)))
            .andExpect(jsonPath("$.[*].version").value(hasItem(DEFAULT_VERSION)))
            .andExpect(jsonPath("$.[*].port").value(hasItem(DEFAULT_PORT)));

        // Check, that the count call also returns 1
        restInterfacingMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultInterfacingShouldNotBeFound(String filter) throws Exception {
        restInterfacingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restInterfacingMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingInterfacing() throws Exception {
        // Get the interfacing
        restInterfacingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingInterfacing() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();

        // Update the interfacing
        Interfacing updatedInterfacing = interfacingRepository.findById(interfacing.getId()).get();
        // Disconnect from session so that the updates on updatedInterfacing are not directly saved in db
        em.detach(updatedInterfacing);
        updatedInterfacing
            .isAssigned(UPDATED_IS_ASSIGNED)
            .ipAddress(UPDATED_IP_ADDRESS)
            .hash(UPDATED_HASH)
            .serial(UPDATED_SERIAL)
            .version(UPDATED_VERSION)
            .port(UPDATED_PORT);
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(updatedInterfacing);

        restInterfacingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, interfacingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isOk());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
        Interfacing testInterfacing = interfacingList.get(interfacingList.size() - 1);
        assertThat(testInterfacing.getIsAssigned()).isEqualTo(UPDATED_IS_ASSIGNED);
        assertThat(testInterfacing.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testInterfacing.getHash()).isEqualTo(UPDATED_HASH);
        assertThat(testInterfacing.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testInterfacing.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testInterfacing.getPort()).isEqualTo(UPDATED_PORT);
    }

    @Test
    @Transactional
    void putNonExistingInterfacing() throws Exception {
        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();
        interfacing.setId(count.incrementAndGet());

        // Create the Interfacing
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterfacingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, interfacingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchInterfacing() throws Exception {
        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();
        interfacing.setId(count.incrementAndGet());

        // Create the Interfacing
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterfacingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamInterfacing() throws Exception {
        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();
        interfacing.setId(count.incrementAndGet());

        // Create the Interfacing
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterfacingMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(interfacingDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateInterfacingWithPatch() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();

        // Update the interfacing using partial update
        Interfacing partialUpdatedInterfacing = new Interfacing();
        partialUpdatedInterfacing.setId(interfacing.getId());

        partialUpdatedInterfacing.serial(UPDATED_SERIAL).version(UPDATED_VERSION).port(UPDATED_PORT);

        restInterfacingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterfacing.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterfacing))
            )
            .andExpect(status().isOk());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
        Interfacing testInterfacing = interfacingList.get(interfacingList.size() - 1);
        assertThat(testInterfacing.getIsAssigned()).isEqualTo(DEFAULT_IS_ASSIGNED);
        assertThat(testInterfacing.getIpAddress()).isEqualTo(DEFAULT_IP_ADDRESS);
        assertThat(testInterfacing.getHash()).isEqualTo(DEFAULT_HASH);
        assertThat(testInterfacing.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testInterfacing.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testInterfacing.getPort()).isEqualTo(UPDATED_PORT);
    }

    @Test
    @Transactional
    void fullUpdateInterfacingWithPatch() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();

        // Update the interfacing using partial update
        Interfacing partialUpdatedInterfacing = new Interfacing();
        partialUpdatedInterfacing.setId(interfacing.getId());

        partialUpdatedInterfacing
            .isAssigned(UPDATED_IS_ASSIGNED)
            .ipAddress(UPDATED_IP_ADDRESS)
            .hash(UPDATED_HASH)
            .serial(UPDATED_SERIAL)
            .version(UPDATED_VERSION)
            .port(UPDATED_PORT);

        restInterfacingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedInterfacing.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedInterfacing))
            )
            .andExpect(status().isOk());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
        Interfacing testInterfacing = interfacingList.get(interfacingList.size() - 1);
        assertThat(testInterfacing.getIsAssigned()).isEqualTo(UPDATED_IS_ASSIGNED);
        assertThat(testInterfacing.getIpAddress()).isEqualTo(UPDATED_IP_ADDRESS);
        assertThat(testInterfacing.getHash()).isEqualTo(UPDATED_HASH);
        assertThat(testInterfacing.getSerial()).isEqualTo(UPDATED_SERIAL);
        assertThat(testInterfacing.getVersion()).isEqualTo(UPDATED_VERSION);
        assertThat(testInterfacing.getPort()).isEqualTo(UPDATED_PORT);
    }

    @Test
    @Transactional
    void patchNonExistingInterfacing() throws Exception {
        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();
        interfacing.setId(count.incrementAndGet());

        // Create the Interfacing
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restInterfacingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, interfacingDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchInterfacing() throws Exception {
        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();
        interfacing.setId(count.incrementAndGet());

        // Create the Interfacing
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterfacingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamInterfacing() throws Exception {
        int databaseSizeBeforeUpdate = interfacingRepository.findAll().size();
        interfacing.setId(count.incrementAndGet());

        // Create the Interfacing
        InterfacingDTO interfacingDTO = interfacingMapper.toDto(interfacing);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restInterfacingMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(interfacingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Interfacing in the database
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteInterfacing() throws Exception {
        // Initialize the database
        interfacingRepository.saveAndFlush(interfacing);

        int databaseSizeBeforeDelete = interfacingRepository.findAll().size();

        // Delete the interfacing
        restInterfacingMockMvc
            .perform(delete(ENTITY_API_URL_ID, interfacing.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Interfacing> interfacingList = interfacingRepository.findAll();
        assertThat(interfacingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
