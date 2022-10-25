package co.com.ies.smol.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import co.com.ies.smol.IntegrationTest;
import co.com.ies.smol.domain.Island;
import co.com.ies.smol.repository.IslandRepository;
import co.com.ies.smol.service.criteria.IslandCriteria;
import co.com.ies.smol.service.dto.IslandDTO;
import co.com.ies.smol.service.mapper.IslandMapper;
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
 * Integration tests for the {@link IslandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IslandResourceIT {

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/islands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IslandRepository islandRepository;

    @Autowired
    private IslandMapper islandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIslandMockMvc;

    private Island island;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Island createEntity(EntityManager em) {
        Island island = new Island().description(DEFAULT_DESCRIPTION).name(DEFAULT_NAME);
        return island;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Island createUpdatedEntity(EntityManager em) {
        Island island = new Island().description(UPDATED_DESCRIPTION).name(UPDATED_NAME);
        return island;
    }

    @BeforeEach
    public void initTest() {
        island = createEntity(em);
    }

    @Test
    @Transactional
    void createIsland() throws Exception {
        int databaseSizeBeforeCreate = islandRepository.findAll().size();
        // Create the Island
        IslandDTO islandDTO = islandMapper.toDto(island);
        restIslandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(islandDTO)))
            .andExpect(status().isCreated());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeCreate + 1);
        Island testIsland = islandList.get(islandList.size() - 1);
        assertThat(testIsland.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIsland.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    void createIslandWithExistingId() throws Exception {
        // Create the Island with an existing ID
        island.setId(1L);
        IslandDTO islandDTO = islandMapper.toDto(island);

        int databaseSizeBeforeCreate = islandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIslandMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(islandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllIslands() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList
        restIslandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(island.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }

    @Test
    @Transactional
    void getIsland() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get the island
        restIslandMockMvc
            .perform(get(ENTITY_API_URL_ID, island.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(island.getId().intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }

    @Test
    @Transactional
    void getIslandsByIdFiltering() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        Long id = island.getId();

        defaultIslandShouldBeFound("id.equals=" + id);
        defaultIslandShouldNotBeFound("id.notEquals=" + id);

        defaultIslandShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultIslandShouldNotBeFound("id.greaterThan=" + id);

        defaultIslandShouldBeFound("id.lessThanOrEqual=" + id);
        defaultIslandShouldNotBeFound("id.lessThan=" + id);
    }

    @Test
    @Transactional
    void getAllIslandsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where description equals to DEFAULT_DESCRIPTION
        defaultIslandShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the islandList where description equals to UPDATED_DESCRIPTION
        defaultIslandShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIslandsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultIslandShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the islandList where description equals to UPDATED_DESCRIPTION
        defaultIslandShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIslandsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where description is not null
        defaultIslandShouldBeFound("description.specified=true");

        // Get all the islandList where description is null
        defaultIslandShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    void getAllIslandsByDescriptionContainsSomething() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where description contains DEFAULT_DESCRIPTION
        defaultIslandShouldBeFound("description.contains=" + DEFAULT_DESCRIPTION);

        // Get all the islandList where description contains UPDATED_DESCRIPTION
        defaultIslandShouldNotBeFound("description.contains=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIslandsByDescriptionNotContainsSomething() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where description does not contain DEFAULT_DESCRIPTION
        defaultIslandShouldNotBeFound("description.doesNotContain=" + DEFAULT_DESCRIPTION);

        // Get all the islandList where description does not contain UPDATED_DESCRIPTION
        defaultIslandShouldBeFound("description.doesNotContain=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    void getAllIslandsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where name equals to DEFAULT_NAME
        defaultIslandShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the islandList where name equals to UPDATED_NAME
        defaultIslandShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllIslandsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where name in DEFAULT_NAME or UPDATED_NAME
        defaultIslandShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the islandList where name equals to UPDATED_NAME
        defaultIslandShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllIslandsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where name is not null
        defaultIslandShouldBeFound("name.specified=true");

        // Get all the islandList where name is null
        defaultIslandShouldNotBeFound("name.specified=false");
    }

    @Test
    @Transactional
    void getAllIslandsByNameContainsSomething() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where name contains DEFAULT_NAME
        defaultIslandShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the islandList where name contains UPDATED_NAME
        defaultIslandShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    void getAllIslandsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        // Get all the islandList where name does not contain DEFAULT_NAME
        defaultIslandShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the islandList where name does not contain UPDATED_NAME
        defaultIslandShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultIslandShouldBeFound(String filter) throws Exception {
        restIslandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(island.getId().intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));

        // Check, that the count call also returns 1
        restIslandMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultIslandShouldNotBeFound(String filter) throws Exception {
        restIslandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restIslandMockMvc
            .perform(get(ENTITY_API_URL + "/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }

    @Test
    @Transactional
    void getNonExistingIsland() throws Exception {
        // Get the island
        restIslandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingIsland() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        int databaseSizeBeforeUpdate = islandRepository.findAll().size();

        // Update the island
        Island updatedIsland = islandRepository.findById(island.getId()).get();
        // Disconnect from session so that the updates on updatedIsland are not directly saved in db
        em.detach(updatedIsland);
        updatedIsland.description(UPDATED_DESCRIPTION).name(UPDATED_NAME);
        IslandDTO islandDTO = islandMapper.toDto(updatedIsland);

        restIslandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, islandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(islandDTO))
            )
            .andExpect(status().isOk());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
        Island testIsland = islandList.get(islandList.size() - 1);
        assertThat(testIsland.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIsland.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void putNonExistingIsland() throws Exception {
        int databaseSizeBeforeUpdate = islandRepository.findAll().size();
        island.setId(count.incrementAndGet());

        // Create the Island
        IslandDTO islandDTO = islandMapper.toDto(island);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIslandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, islandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(islandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchIsland() throws Exception {
        int databaseSizeBeforeUpdate = islandRepository.findAll().size();
        island.setId(count.incrementAndGet());

        // Create the Island
        IslandDTO islandDTO = islandMapper.toDto(island);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIslandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(islandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIsland() throws Exception {
        int databaseSizeBeforeUpdate = islandRepository.findAll().size();
        island.setId(count.incrementAndGet());

        // Create the Island
        IslandDTO islandDTO = islandMapper.toDto(island);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIslandMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(islandDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateIslandWithPatch() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        int databaseSizeBeforeUpdate = islandRepository.findAll().size();

        // Update the island using partial update
        Island partialUpdatedIsland = new Island();
        partialUpdatedIsland.setId(island.getId());

        partialUpdatedIsland.name(UPDATED_NAME);

        restIslandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIsland.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIsland))
            )
            .andExpect(status().isOk());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
        Island testIsland = islandList.get(islandList.size() - 1);
        assertThat(testIsland.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testIsland.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void fullUpdateIslandWithPatch() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        int databaseSizeBeforeUpdate = islandRepository.findAll().size();

        // Update the island using partial update
        Island partialUpdatedIsland = new Island();
        partialUpdatedIsland.setId(island.getId());

        partialUpdatedIsland.description(UPDATED_DESCRIPTION).name(UPDATED_NAME);

        restIslandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIsland.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIsland))
            )
            .andExpect(status().isOk());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
        Island testIsland = islandList.get(islandList.size() - 1);
        assertThat(testIsland.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testIsland.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    void patchNonExistingIsland() throws Exception {
        int databaseSizeBeforeUpdate = islandRepository.findAll().size();
        island.setId(count.incrementAndGet());

        // Create the Island
        IslandDTO islandDTO = islandMapper.toDto(island);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIslandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, islandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(islandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIsland() throws Exception {
        int databaseSizeBeforeUpdate = islandRepository.findAll().size();
        island.setId(count.incrementAndGet());

        // Create the Island
        IslandDTO islandDTO = islandMapper.toDto(island);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIslandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(islandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIsland() throws Exception {
        int databaseSizeBeforeUpdate = islandRepository.findAll().size();
        island.setId(count.incrementAndGet());

        // Create the Island
        IslandDTO islandDTO = islandMapper.toDto(island);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIslandMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(islandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Island in the database
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteIsland() throws Exception {
        // Initialize the database
        islandRepository.saveAndFlush(island);

        int databaseSizeBeforeDelete = islandRepository.findAll().size();

        // Delete the island
        restIslandMockMvc
            .perform(delete(ENTITY_API_URL_ID, island.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Island> islandList = islandRepository.findAll();
        assertThat(islandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
