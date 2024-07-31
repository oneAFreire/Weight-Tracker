package com.oneafreire.data

import com.oneafreire.data.db.entity.WeightMeasurementEntity
import com.oneafreire.data.mapper.WeightMeasurementMapper
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.util.Date

class WeightMeasurementMapperTest {
    private lateinit var mapper: WeightMeasurementMapper
    private lateinit var entity: WeightMeasurementEntity
    private lateinit var entities: List<WeightMeasurementEntity>

    @Before
    fun setUp() {
        mapper = WeightMeasurementMapper()
        val date = Date()
        entity = WeightMeasurementEntity(
            id = 1,
            date = date,
            weight = 75.0,
            bodyFat = 20.0,
            bodyWater = 60.0,
            visceralFat = 10,
            bodyMass = 25.0,
            dailyCalories = 2000
        )
        entities = listOf(
            entity,
            WeightMeasurementEntity(
                id = 2,
                date = date,
                weight = 80.0,
                bodyFat = 22.0,
                bodyWater = 62.0,
                visceralFat = 12,
                bodyMass = 26.0,
                dailyCalories = 2200
            )
        )
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `invoke should return null when entry is null`() {
        val result = mapper.invoke(null)
        assertNull(result)
    }

    @Test
    fun `invoke should map entity to domain model`() {
        val result = mapper.invoke(entity)

        assertNotNull(result)
        result?.let {
            assertEquals(entity.date, it.date)
            assertEquals(entity.weight, it.weight, 0.0)
            assertEquals(entity.bodyFat, it.bodyFat, 0.0)
            assertEquals(entity.bodyWater, it.bodyWater, 0.0)
            assertEquals(entity.visceralFat, it.visceralFat)
            assertEquals(entity.bodyMass, it.bodyMass, 0.0)
            assertEquals(entity.dailyCalories, it.dailyCalories)
        }
    }

    @Test
    fun `parseList should map list of entities to list of domain models`() {
        val result = mapper.parseList(entities)

        assertEquals(2, result.size)

        val firstResult = result[0]
        assertEquals(entities[0].date, firstResult.date)
        assertEquals(entities[0].weight, firstResult.weight, 0.0)
        assertEquals(entities[0].bodyFat, firstResult.bodyFat, 0.0)
        assertEquals(entities[0].bodyWater, firstResult.bodyWater, 0.0)
        assertEquals(entities[0].visceralFat, firstResult.visceralFat)
        assertEquals(entities[0].bodyMass, firstResult.bodyMass, 0.0)
        assertEquals(entities[0].dailyCalories, firstResult.dailyCalories)

        val secondResult = result[1]
        assertEquals(entities[1].date, secondResult.date)
        assertEquals(entities[1].weight, secondResult.weight, 0.0)
        assertEquals(entities[1].bodyFat, secondResult.bodyFat, 0.0)
        assertEquals(entities[1].bodyWater, secondResult.bodyWater, 0.0)
        assertEquals(entities[1].visceralFat, secondResult.visceralFat)
        assertEquals(entities[1].bodyMass, secondResult.bodyMass, 0.0)
        assertEquals(entities[1].dailyCalories, secondResult.dailyCalories)
    }
}