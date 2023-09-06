package com.marvg.cityweather.data.repository

import com.google.common.truth.Truth.assertThat
import com.marvg.cityweather.data.WeatherAPI
import com.marvg.cityweather.data.invalidGetCitiesResponse
import com.marvg.cityweather.data.validGetCitiesResponse
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class WeatherRepositoryImplTest {

    private lateinit var repository: WeatherRepositoryImpl
    private lateinit var mockWebServer: MockWebServer
    private lateinit var okHttpClient: OkHttpClient
    private lateinit var api: WeatherAPI

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        okHttpClient = OkHttpClient.Builder()
            .writeTimeout(1, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.SECONDS)
            .connectTimeout(1, TimeUnit.SECONDS)
            .build()
        api = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .baseUrl(mockWebServer.url("/"))
            .build()
            .create(WeatherAPI::class.java)
        repository = WeatherRepositoryImpl(
            api = api
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Search cities, valid response, returns results`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(validGetCitiesResponse)
        )
        val result = repository.getCities()

        assertThat(result.isSuccess).isTrue()
    }

    @Test
    fun `Search cities, invalid response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(403)
                .setBody(validGetCitiesResponse)
        )
        val result = repository.getCities()

        assertThat(result.isFailure).isTrue()
    }

    @Test
    fun `Search cities, malformed response, returns failure`() = runBlocking {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(invalidGetCitiesResponse)
        )
        val result = repository.getCities()

        assertThat(result.isFailure).isTrue()
    }
}