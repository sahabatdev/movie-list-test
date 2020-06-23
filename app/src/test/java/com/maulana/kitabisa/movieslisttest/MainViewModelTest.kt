package com.maulana.kitabisa.movieslisttest

import android.app.Application
import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.maulana.kitabisa.movieslisttest.config.AppConfig
import com.maulana.kitabisa.movieslisttest.model.MoviesResponse
import com.maulana.kitabisa.movieslisttest.model.Results
import com.maulana.kitabisa.movieslisttest.network.ApiRetrofit
import com.maulana.kitabisa.movieslisttest.repository.ApiRepository
import com.maulana.kitabisa.movieslisttest.viewmodel.MainViewModel
import com.maulana.kitabisa.movieslisttest.views.MainActivity
import junit.framework.Assert.*
import kotlinx.coroutines.*
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    @Mock
    lateinit var application: Application

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("MainViewModel Thread")
    private lateinit var viewModel: MainViewModel

    @Before
    fun first(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = MainViewModel(application)
    }

    @After
    fun end(){
        Dispatchers.resetMain()
    }

    @Test
    fun testDoGetPopularMovies(){
        runBlocking {
            launch {
                var isSuccess = false
                viewModel.getMovies("popular", AppConfig.API_KEY).observeForever {
                    isSuccess = it!=null
                }
                delay(5000)
                assert(isSuccess)
            }
        }
    }

    @Test
    fun testDoGetUpcomingMovies(){
        runBlocking {
            launch {
                var isSuccess = false
                viewModel.getMovies("upcoming", AppConfig.API_KEY).observeForever {
                    isSuccess = it!=null
                }
                delay(5000)
                assert(isSuccess)
            }
        }
    }

    @Test
    fun testDoGetTopRatedMovies(){
        runBlocking {
            launch {
                var isSuccess = false
                viewModel.getMovies("top_rated", AppConfig.API_KEY).observeForever {
                    isSuccess = it!=null
                }
                delay(5000)
                assert(isSuccess)
            }
        }
    }

    @Test
    fun testDoGetNowPlayingMovies(){
        runBlocking {
            launch {
                var isSuccess = false
                viewModel.getMovies("now_playing", AppConfig.API_KEY).observeForever {
                    isSuccess = it!=null
                }
                delay(5000)
                assert(isSuccess)
            }
        }
    }

}