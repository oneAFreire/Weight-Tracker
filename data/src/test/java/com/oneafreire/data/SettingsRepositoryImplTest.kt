package com.oneafreire.data

import android.content.SharedPreferences
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.oneafreire.data.repositoryimpl.SettingsRepositoryImpl
import com.oneafreire.domain.common.DisplayUnits
import com.oneafreire.domain.common.Gender
import com.oneafreire.domain.model.Settings

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

class SettingsRepositoryImplTest {

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    @Mock
    private lateinit var gson: Gson

    private lateinit var settingsRepositoryImpl: SettingsRepositoryImpl

    @Before
    fun setUp() {
        sharedPreferences = mock()
        editor = mock()
        gson = mock()

        whenever(sharedPreferences.edit()).thenReturn(editor)
        settingsRepositoryImpl = SettingsRepositoryImpl(sharedPreferences, gson)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun saveSettings_savesToSharedPreferences() {
        val settings = Settings(DisplayUnits.KG, Gender.MALE, 37, 178)
        val json = "mocked json"
        whenever(gson.toJson(settings)).thenReturn(json)
        whenever(editor.putString(any(), any())).thenReturn(editor)

        settingsRepositoryImpl.save(settings)

        verify(editor).putString(SettingsRepositoryImpl.KEY_SETTINGS, json)
        verify(editor).apply()
    }

    @Test
    fun getSettings_returnsSettingsFromSharedPreferences() {
        val settings = Settings(DisplayUnits.KG, Gender.MALE, 37, 178)
        val json = "mocked json"
        whenever(sharedPreferences.getString(SettingsRepositoryImpl.KEY_SETTINGS, null)).thenReturn(json)
        whenever(gson.fromJson(json, Settings::class.java)).thenReturn(settings)

        val retrievedSettings = settingsRepositoryImpl.current()

        assertEquals(settings, retrievedSettings)
    }

    @Test
    fun getSettings_returnsNullWhenNoSettingsStored() {
        whenever(sharedPreferences.getString(SettingsRepositoryImpl.KEY_SETTINGS, null)).thenReturn(null)

        val retrievedSettings = settingsRepositoryImpl.current()

        assertNull(retrievedSettings)
    }
}