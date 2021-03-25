package abhiandroid.com.recyclerviewexample.viewmodel

import abhiandroid.com.recyclerviewexample.model.Country
import abhiandroid.com.recyclerviewexample.model.Row
import abhiandroid.com.recyclerviewexample.repository.DefaultCountryRepository
import abhiandroid.com.recyclerviewexample.rules.RxImmediateSchedulerRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import io.reactivex.Observable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

const val countryDataJsonFailure = """
    
 {
   "title":"",
   "rows":[]
 }
    
"""

class CountryViewModelTest {

    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var countryViewModel: CountryViewModel

    @Mock
    private val countryRepository = DefaultCountryRepository()

    @Mock
    val rowDetails: ArrayList<Row> = arrayListOf(
            Row(
                    "Beavers",
                    "Beavers are second only to humans in their ability to manipulate and change their environment.",
                    "http://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
            )
    )

    @Mock
    val countryObj: Country = Country("", rowDetails)

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        countryViewModel = CountryViewModel()
    }

    @Test
    fun getCountryData_return_valid_json() {

        //val countryObj = Gson().fromJson(countryDataJsonSuccess, Country::class.java)

        Mockito.`when`(countryRepository.getCountryData()).thenReturn(Observable.just(countryObj))

        countryViewModel.getCountryData()

        val data = countryViewModel.countryLivedata.value
        if(data is Country){
            assertEquals(countryObj.rows, data.rows)
        }

    }


    @Test
    fun getCountryData_return_error() {

        val countryObj = Gson().fromJson(countryDataJsonFailure, Country::class.java)

        Mockito.`when`(countryRepository.getCountryData()).thenReturn(Observable.error(Throwable("Api error")))

        countryViewModel.getCountryData()

        assertEquals(countryObj, countryViewModel.countryLivedata.value)

    }

}






