package com.maulana.kitabisa.movieslisttest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import com.maulana.kitabisa.movieslisttest.config.AppDatabase
import com.maulana.kitabisa.movieslisttest.viewmodel.DetailViewModel
import com.maulana.kitabisa.movieslisttest.views.DetailMovieActivity
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import com.maulana.kitabisa.movieslisttest.config.AppConfig
import com.maulana.kitabisa.movieslisttest.model.FavoritTable
import org.junit.*

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    @Mock
    lateinit var application: Application

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("DetailViewModel Thread")
    private lateinit var viewModel: DetailViewModel

    @Before
    fun first(){
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        viewModel = DetailViewModel(application)
    }

    @After
    fun end(){
        Dispatchers.resetMain()
    }

    @Test
    fun testDoGetDetailMovie(){
        runBlocking {
            viewModel.getDetailMovie(4557,AppConfig.BASE_URL).observeForever{
                Assert.assertNotNull(it)
            }
        }
    }

    @Test
    fun testDoGetListReview(){
        runBlocking {
            viewModel.getListReviews(4557,AppConfig.BASE_URL).observeForever{
                Assert.assertNotNull(it)
            }
        }
    }

    @Test
    fun testDoFindFavorit(){
        runBlocking {
            viewModel.findFavorite(4557).observeForever{
                Assert.assertNotNull(it)
            }
        }
    }

    @Test
    fun testDoSaveFavorit(){
        runBlocking {
            viewModel.saveFavorit(FavoritTable(1,"sasa","saas","sass","Asas")).observeForever{
                Assert.assertNotNull(it)
            }
        }
    }

    @Test
    fun testDoDeleteFavorit(){
        runBlocking {
            viewModel.deleteFavorit(4557).observeForever{
                Assert.assertNotNull(it)
            }
        }
    }

}