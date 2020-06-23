package com.maulana.kitabisa.movieslisttest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.maulana.kitabisa.movieslisttest.viewmodel.FavoritViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoritViewModelTest {
    @Mock
    lateinit var application: Application

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("DetailViewModel Thread")
    private lateinit var viewModel: FavoritViewModel

    @Before
    fun first(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = FavoritViewModel(application)
    }

    @After
    fun end(){
        Dispatchers.resetMain()
    }

    @Test
    fun testDoGetAllFavorite(){
        runBlocking {
            viewModel.getAllFavorit().observeForever{
                Assert.assertNotNull(it)
            }
        }
    }
}