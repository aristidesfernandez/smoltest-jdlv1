package co.com.ies.smol.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.com.ies.smol.IntegrationTest;
import co.com.ies.smol.domain.CounterEvent;
import co.com.ies.smol.domain.CounterType;
import co.com.ies.smol.domain.EventDevice;
import co.com.ies.smol.repository.CounterEventRepository;
import co.com.ies.smol.service.criteria.CounterEventCriteria;
import co.com.ies.smol.service.dto.CounterEventDTO;
import co.com.ies.smol.service.mapper.CounterEventMapper;
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
 * Integration tests for the {@link CounterEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class CounterEventResourceIT {

    private static final Long DEFAULT_VALUE_COUNTER = 1L;
    private static final Long UPDATED_VALUE_COUNTER = 2L;
    private static final Long SMALLER_VALUE_COUNTER = 1L - 1L;

    private static final Float DEFAULT_DENOMINATION_SALE = 1F;
    private static final Float UPDATED_DENOMINATION_SALE = 2F;
    private static final Float SMALLER_DENOMINATION_SALE = 1F - 1F;

    private static final String ENTITY_API_URL = "/api/counter-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private CounterEventRepository counterEventRepository;

    @Autowired
    private CounterEventMapper counterEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCounterEventMockMvc;

    private CounterEvent counterEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CounterEvent createEntity(EntityManager em) {
        CounterEvent counterEvent = new CounterEvent().valueCounter(DEFAULT_VALUE_COUNTER).denominationSale(DEFAULT_DENOMINATION_SALE);
        // Add required entity
        CounterType counterType;
        if (TestUtil.findAll(em, CounterType.class).isEmpty()) {
            counterType = CounterTypeResourceIT.createEntity(em);
            em.persist(counterType);
            em.flush();
        } else {
            counterType = TestUtil.findAll(em, CounterType.class).get(0);
        }
        counterEvent.setCounterCode(counterType);
        // Add required entity
        EventDevice eventDevice;
        if (TestUtil.findAll(em, EventDevice.class).isEmpty()) {
            eventDevice = EventDeviceResourceIT.createEntity(em);
            em.persist(eventDevice);
            em.flush();
        } else {
            eventDevice = TestUtil.findAll(em, EventDevice.class).get(0);
        }
        counterEvent.setIdEventDevice(eventDevice);
        return counterEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CounterEvent createUpdatedEntity(EntityManager em) {
        CounterEvent counterEvent = new CounterEvent().valueCounter(UPDATED_VALUE_COUNTER).denominationSale(UPDATED_DENOMINATION_SALE);
        // Add required entity
        CounterType counterType;
        if (TestUtil.findAll(em, CounterType.class).isEmpty()) {
            counterType = CounterTypeResourceIT.createUpdatedEntity(em);
            em.persist(counterType);
            em.flush();
        } else {
            counterType = TestUtil.findAll(em, CounterType.class).get(0);
        }
        counterEvent.setCounterCode(counterType);
        // Add required entity
        EventDevice eventDevice;
        if (TestUtil.findAll(em, EventDevice.class).isEmpty()) {
            eventDevice = EventDeviceResourceIT.createUpdatedEntity(em);
            em.persist(eventDevice);
            em.flush();
        } else {
            eventDevice = TestUtil.findAll(em, EventDevice.class).get(0);
        }
        counterEvent.setIdEventDevice(eventDevice);
        return counterEvent;
    }

    @BeforeEach
    public void initTest() {
        counterEvent = createEntity(em);
    }

    @Test
    @Transactional
    void createCounterEvent() throws Exception {
        int databaseSizeBeforeCreate = counterEventRepository.findAll().size();
        // Create the CounterEvent
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);
        restCounterEventMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isCreated());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeCreate + 1);
        CounterEvent testCounterEvent = counterEventList.get(counterEventList.size() - 1);
        assertThat(testCounterEvent.getValueCounter()).isEqualTo(DEFAULT_VALUE_COUNTER);
        assertThat(testCounterEvent.getDenominationSale()).isEqualTo(DEFAULT_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void createCounterEventWithExistingId() throws Exception {
        // Create the CounterEvent with an existing ID
        counterEvent.setId(1L);
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);

        int databaseSizeBeforeCreate = counterEventRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restCounterEventMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllCounterEvents() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList
        restCounterEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(counterEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].valueCounter").value(hasItem(DEFAULT_VALUE_COUNTER.intValue())))
            .andExpect(jsonPath("$.[*].denominationSale").value(hasItem(DEFAULT_DENOMINATION_SALE.doubleValue())));
    }

    @Test
    @Transactional
    void getCounterEvent() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get the counterEvent
        restCounterEventMockMvc
            .perform(get(ENTITY_API_URL_ID, counterEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(counterEvent.getId().intValue()))
            .andExpect(jsonPath("$.valueCounter").value(DEFAULT_VALUE_COUNTER.intValue()))
            .andExpect(jsonPath("$.denominationSale").value(DEFAULT_DENOMINATION_SALE.doubleValue()));
    }

    @Test
    @Transactional
    void getCounterEventsByIdFiltering() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        Long id = counterEvent.getId();

        defaultCounterEventShouldBeFound("id.equals=" + id);
        defaultCounterEventShouldNotBeFound("id.notEquals=" + id);

        defaultCounterEventShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultCounterEventShouldNotBeFound("id.greaterThan=" + id);

        defaultCounterEventShouldBeFound("id.lessThanOrEqual=" + id);
        defaultCounterEventShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllCounterEventsByValueCounterIsEqualToSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where valueCounter equals to DEFAULT_VALUE_COUNTER
        defaultCounterEventShouldBeFound("valueCounter.equals=" + DEFAULT_VALUE_COUNTER);

        // Get all the counterEventList where valueCounter equals to UPDATED_VALUE_COUNTER
        defaultCounterEventShouldNotBeFound("valueCounter.equals=" + UPDATED_VALUE_COUNTER);
    }

    @Test
    @Transactional
    void getAllCounterEventsByValueCounterIsInShouldWork() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where valueCounter in DEFAULT_VALUE_COUNTER or UPDATED_VALUE_COUNTER
        defaultCounterEventShouldBeFound("valueCounter.in=" + DEFAULT_VALUE_COUNTER + "," + UPDATED_VALUE_COUNTER);

        // Get all the counterEventList where valueCounter equals to UPDATED_VALUE_COUNTER
        defaultCounterEventShouldNotBeFound("valueCounter.in=" + UPDATED_VALUE_COUNTER);
    }

    @Test
    @Transactional
    void getAllCounterEventsByValueCounterIsNullOrNotNull() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where valueCounter is not null
        defaultCounterEventShouldBeFound("valueCounter.specified=true");

        // Get all the counterEventList where valueCounter is null
        defaultCounterEventShouldNotBeFound("valueCounter.specified=false");
    }

    @Test
    @Transactional
    void getAllCounterEventsByValueCounterIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where valueCounter is greater than or equal to DEFAULT_VALUE_COUNTER
        defaultCounterEventShouldBeFound("valueCounter.greaterThanOrEqual=" + DEFAULT_VALUE_COUNTER);

        // Get all the counterEventList where valueCounter is greater than or equal to UPDATED_VALUE_COUNTER
        defaultCounterEventShouldNotBeFound("valueCounter.greaterThanOrEqual=" + UPDATED_VALUE_COUNTER);
    }

    @Test
    @Transactional
    void getAllCounterEventsByValueCounterIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where valueCounter is less than or equal to DEFAULT_VALUE_COUNTER
        defaultCounterEventShouldBeFound("valueCounter.lessThanOrEqual=" + DEFAULT_VALUE_COUNTER);

        // Get all the counterEventList where valueCounter is less than or equal to SMALLER_VALUE_COUNTER
        defaultCounterEventShouldNotBeFound("valueCounter.lessThanOrEqual=" + SMALLER_VALUE_COUNTER);
    }

    @Test
    @Transactional
    void getAllCounterEventsByValueCounterIsLessThanSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where valueCounter is less than DEFAULT_VALUE_COUNTER
        defaultCounterEventShouldNotBeFound("valueCounter.lessThan=" + DEFAULT_VALUE_COUNTER);

        // Get all the counterEventList where valueCounter is less than UPDATED_VALUE_COUNTER
        defaultCounterEventShouldBeFound("valueCounter.lessThan=" + UPDATED_VALUE_COUNTER);
    }

    @Test
    @Transactional
    void getAllCounterEventsByValueCounterIsGreaterThanSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where valueCounter is greater than DEFAULT_VALUE_COUNTER
        defaultCounterEventShouldNotBeFound("valueCounter.greaterThan=" + DEFAULT_VALUE_COUNTER);

        // Get all the counterEventList where valueCounter is greater than SMALLER_VALUE_COUNTER
        defaultCounterEventShouldBeFound("valueCounter.greaterThan=" + SMALLER_VALUE_COUNTER);
    }

    @Test
    @Transactional
    void getAllCounterEventsByDenominationSaleIsEqualToSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where denominationSale equals to DEFAULT_DENOMINATION_SALE
        defaultCounterEventShouldBeFound("denominationSale.equals=" + DEFAULT_DENOMINATION_SALE);

        // Get all the counterEventList where denominationSale equals to UPDATED_DENOMINATION_SALE
        defaultCounterEventShouldNotBeFound("denominationSale.equals=" + UPDATED_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void getAllCounterEventsByDenominationSaleIsInShouldWork() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where denominationSale in DEFAULT_DENOMINATION_SALE or UPDATED_DENOMINATION_SALE
        defaultCounterEventShouldBeFound("denominationSale.in=" + DEFAULT_DENOMINATION_SALE + "," + UPDATED_DENOMINATION_SALE);

        // Get all the counterEventList where denominationSale equals to UPDATED_DENOMINATION_SALE
        defaultCounterEventShouldNotBeFound("denominationSale.in=" + UPDATED_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void getAllCounterEventsByDenominationSaleIsNullOrNotNull() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where denominationSale is not null
        defaultCounterEventShouldBeFound("denominationSale.specified=true");

        // Get all the counterEventList where denominationSale is null
        defaultCounterEventShouldNotBeFound("denominationSale.specified=false");
    }

    @Test
    @Transactional
    void getAllCounterEventsByDenominationSaleIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where denominationSale is greater than or equal to DEFAULT_DENOMINATION_SALE
        defaultCounterEventShouldBeFound("denominationSale.greaterThanOrEqual=" + DEFAULT_DENOMINATION_SALE);

        // Get all the counterEventList where denominationSale is greater than or equal to UPDATED_DENOMINATION_SALE
        defaultCounterEventShouldNotBeFound("denominationSale.greaterThanOrEqual=" + UPDATED_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void getAllCounterEventsByDenominationSaleIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where denominationSale is less than or equal to DEFAULT_DENOMINATION_SALE
        defaultCounterEventShouldBeFound("denominationSale.lessThanOrEqual=" + DEFAULT_DENOMINATION_SALE);

        // Get all the counterEventList where denominationSale is less than or equal to SMALLER_DENOMINATION_SALE
        defaultCounterEventShouldNotBeFound("denominationSale.lessThanOrEqual=" + SMALLER_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void getAllCounterEventsByDenominationSaleIsLessThanSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where denominationSale is less than DEFAULT_DENOMINATION_SALE
        defaultCounterEventShouldNotBeFound("denominationSale.lessThan=" + DEFAULT_DENOMINATION_SALE);

        // Get all the counterEventList where denominationSale is less than UPDATED_DENOMINATION_SALE
        defaultCounterEventShouldBeFound("denominationSale.lessThan=" + UPDATED_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void getAllCounterEventsByDenominationSaleIsGreaterThanSomething() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        // Get all the counterEventList where denominationSale is greater than DEFAULT_DENOMINATION_SALE
        defaultCounterEventShouldNotBeFound("denominationSale.greaterThan=" + DEFAULT_DENOMINATION_SALE);

        // Get all the counterEventList where denominationSale is greater than SMALLER_DENOMINATION_SALE
        defaultCounterEventShouldBeFound("denominationSale.greaterThan=" + SMALLER_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void getAllCounterEventsByCounterCodeIsEqualToSomething() throws Exception {
        CounterType counterCode;
        if (TestUtil.findAll(em, CounterType.class).isEmpty()) {
            counterEventRepository.saveAndFlush(counterEvent);
            counterCode = CounterTypeResourceIT.createEntity(em);
        } else {
            counterCode = TestUtil.findAll(em, CounterType.class).get(0);
        }
        em.persist(counterCode);
        em.flush();
        counterEvent.setCounterCode(counterCode);
        counterEventRepository.saveAndFlush(counterEvent);
        Long counterCodeId = counterCode.getId();

        // Get all the counterEventList where counterCode equals to counterCodeId
        defaultCounterEventShouldBeFound("counterCodeId.equals=" + counterCodeId);

        // Get all the counterEventList where counterCode equals to (counterCodeId + 1)
        defaultCounterEventShouldNotBeFound("counterCodeId.equals=" + (counterCodeId + 1));
    }

    @Test
    @Transactional
    void getAllCounterEventsByIdEventDeviceIsEqualToSomething() throws Exception {
        EventDevice idEventDevice;
        if (TestUtil.findAll(em, EventDevice.class).isEmpty()) {
            counterEventRepository.saveAndFlush(counterEvent);
            idEventDevice = EventDeviceResourceIT.createEntity(em);
        } else {
            idEventDevice = TestUtil.findAll(em, EventDevice.class).get(0);
        }
        em.persist(idEventDevice);
        em.flush();
        counterEvent.setIdEventDevice(idEventDevice);
        counterEventRepository.saveAndFlush(counterEvent);
        Long idEventDeviceId = idEventDevice.getId();

        // Get all the counterEventList where idEventDevice equals to idEventDeviceId
        defaultCounterEventShouldBeFound("idEventDeviceId.equals=" + idEventDeviceId);

        // Get all the counterEventList where idEventDevice equals to (idEventDeviceId + 1)
        defaultCounterEventShouldNotBeFound("idEventDeviceId.equals=" + (idEventDeviceId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultCounterEventShouldBeFound(String filter) throws Exception {
        restCounterEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(counterEvent.getId().intValue())))
            .andExpect(jsonPath("$.[*].valueCounter").value(hasItem(DEFAULT_VALUE_COUNTER.intValue())))
            .andExpect(jsonPath("$.[*].denominationSale").value(hasItem(DEFAULT_DENOMINATION_SALE.doubleValue())));

        // Check, that the count call also returns 1
        restCounterEventMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultCounterEventShouldNotBeFound(String filter) throws Exception {
        restCounterEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restCounterEventMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingCounterEvent() throws Exception {
        // Get the counterEvent
        restCounterEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingCounterEvent() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();

        // Update the counterEvent
        CounterEvent updatedCounterEvent = counterEventRepository.findById(counterEvent.getId()).get();
        // Disconnect from session so that the updates on updatedCounterEvent are not directly saved in db
        em.detach(updatedCounterEvent);
        updatedCounterEvent.valueCounter(UPDATED_VALUE_COUNTER).denominationSale(UPDATED_DENOMINATION_SALE);
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(updatedCounterEvent);

        restCounterEventMockMvc
            .perform(
                put(ENTITY_API_URL_ID, counterEventDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isOk());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
        CounterEvent testCounterEvent = counterEventList.get(counterEventList.size() - 1);
        assertThat(testCounterEvent.getValueCounter()).isEqualTo(UPDATED_VALUE_COUNTER);
        assertThat(testCounterEvent.getDenominationSale()).isEqualTo(UPDATED_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void putNonExistingCounterEvent() throws Exception {
        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();
        counterEvent.setId(count.incrementAndGet());

        // Create the CounterEvent
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCounterEventMockMvc
            .perform(
                put(ENTITY_API_URL_ID, counterEventDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchCounterEvent() throws Exception {
        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();
        counterEvent.setId(count.incrementAndGet());

        // Create the CounterEvent
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCounterEventMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamCounterEvent() throws Exception {
        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();
        counterEvent.setId(count.incrementAndGet());

        // Create the CounterEvent
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCounterEventMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateCounterEventWithPatch() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();

        // Update the counterEvent using partial update
        CounterEvent partialUpdatedCounterEvent = new CounterEvent();
        partialUpdatedCounterEvent.setId(counterEvent.getId());

        restCounterEventMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCounterEvent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCounterEvent))
            )
            .andExpect(status().isOk());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
        CounterEvent testCounterEvent = counterEventList.get(counterEventList.size() - 1);
        assertThat(testCounterEvent.getValueCounter()).isEqualTo(DEFAULT_VALUE_COUNTER);
        assertThat(testCounterEvent.getDenominationSale()).isEqualTo(DEFAULT_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void fullUpdateCounterEventWithPatch() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();

        // Update the counterEvent using partial update
        CounterEvent partialUpdatedCounterEvent = new CounterEvent();
        partialUpdatedCounterEvent.setId(counterEvent.getId());

        partialUpdatedCounterEvent.valueCounter(UPDATED_VALUE_COUNTER).denominationSale(UPDATED_DENOMINATION_SALE);

        restCounterEventMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedCounterEvent.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedCounterEvent))
            )
            .andExpect(status().isOk());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
        CounterEvent testCounterEvent = counterEventList.get(counterEventList.size() - 1);
        assertThat(testCounterEvent.getValueCounter()).isEqualTo(UPDATED_VALUE_COUNTER);
        assertThat(testCounterEvent.getDenominationSale()).isEqualTo(UPDATED_DENOMINATION_SALE);
    }

    @Test
    @Transactional
    void patchNonExistingCounterEvent() throws Exception {
        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();
        counterEvent.setId(count.incrementAndGet());

        // Create the CounterEvent
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCounterEventMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, counterEventDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchCounterEvent() throws Exception {
        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();
        counterEvent.setId(count.incrementAndGet());

        // Create the CounterEvent
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCounterEventMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamCounterEvent() throws Exception {
        int databaseSizeBeforeUpdate = counterEventRepository.findAll().size();
        counterEvent.setId(count.incrementAndGet());

        // Create the CounterEvent
        CounterEventDTO counterEventDTO = counterEventMapper.toDto(counterEvent);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restCounterEventMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(counterEventDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the CounterEvent in the database
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteCounterEvent() throws Exception {
        // Initialize the database
        counterEventRepository.saveAndFlush(counterEvent);

        int databaseSizeBeforeDelete = counterEventRepository.findAll().size();

        // Delete the counterEvent
        restCounterEventMockMvc
            .perform(delete(ENTITY_API_URL_ID, counterEvent.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CounterEvent> counterEventList = counterEventRepository.findAll();
        assertThat(counterEventList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
