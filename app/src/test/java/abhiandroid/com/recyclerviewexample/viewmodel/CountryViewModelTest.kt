package abhiandroid.com.recyclerviewexample.viewmodel

import abhiandroid.com.recyclerviewexample.livedatatestutil.LivedataTestUtil
import abhiandroid.com.recyclerviewexample.model.Country
import abhiandroid.com.recyclerviewexample.repository.DefaultCountryRepository
import abhiandroid.com.recyclerviewexample.rules.RxImmediateSchedulerRule
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import io.reactivex.Observable.just
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import java.util.concurrent.TimeUnit

const val countryDataJson = """
{
   "title":"About Canada",
   "rows":[
      {
         "title":"Beavers",
         "description":"Beavers are second only to humans in their ability to manipulate and change their environment. They can measure up to 1.3 metres long. A group of beavers is called a colony",
         "imageHref":"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg"
      },
      {
         "title":"Flag",
         "description":null,
         "imageHref":"https://images.findicons.com/files/icons/662/world_flag/128/flag_of_canada.png"
      },
      {
         "title":"Transportation",
         "description":"It is a well known fact that polar bears are the main mode of transportation in Canada. They consume far less gas and have the added benefit of being difficult to steal.",
         "imageHref":"https://1.bp.blogspot.com/_VZVOmYVm68Q/SMkzZzkGXKI/AAAAAAAAADQ/U89miaCkcyo/s400/the_golden_compass_still.jpg"
      },
      {
         "title":"Hockey Night in Canada",
         "description":"These Saturday night CBC broadcasts originally aired on radio in 1931. In 1952 they debuted on television and continue to unite (and divide) the nation each week.",
         "imageHref":"https://fyimusic.ca/wp-content/uploads/2008/06/hockey-night-in-canada.thumbnail.jpg"
      },
      {
         "title":"Eh",
         "description":"A chiefly Canadian interrogative utterance, usually expressing surprise or doubt or seeking confirmation.",
         "imageHref":null
      },
      {
         "title":"Housing",
         "description":"Warmer than you might think.",
         "imageHref":"https://icons.iconarchive.com/icons/iconshock/alaska/256/Igloo-icon.png"
      },
      {
         "title":"Public Shame",
         "description":" Sadly it's true.",
         "imageHref":"https://static.guim.co.uk/sys-images/Music/Pix/site_furniture/2007/04/19/avril_lavigne.jpg"
      },
      {
         "title":null,
         "description":null,
         "imageHref":null
      },
      {
         "title":"Space Program",
         "description":"Canada hopes to soon launch a man to the moon.",
         "imageHref":"http://files.turbosquid.com/Preview/Content_2009_07_14__10_25_15/trebucheta.jpgdf3f3bf4-935d-40ff-84b2-6ce718a327a9Larger.jpg"
      },
      {
         "title":"Meese",
         "description":"A moose is a common sight in Canada. Tall and majestic, they represent many of the values which Canadians imagine that they possess. They grow up to 2.7 metres long and can weigh over 700 kg. They swim at 10 km/h. Moose antlers weigh roughly 20 kg. The plural of moose is actually 'meese', despite what most dictionaries, encyclopedias, and experts will tell you.",
         "imageHref":"https://caroldeckerwildlifeartstudio.net/wp-content/uploads/2011/04/IMG_2418%20majestic%20moose%201%20copy%20(Small)-96x96.jpg"
      },
      {
         "title":"Geography",
         "description":"It's really big.",
         "imageHref":null
      },
      {
         "title":"Kittens...",
         "description":"Éare illegal. Cats are fine.",
         "imageHref":"https://www.donegalhimalayans.com/images/That%20fish%20was%20this%20big.jpg"
      },
      {
         "title":"Mounties",
         "description":"They are the law. They are also Canada's foreign espionage service. Subtle.",
         "imageHref":"https://3.bp.blogspot.com/__mokxbTmuJM/RnWuJ6cE9cI/AAAAAAAAATw/6z3m3w9JDiU/s400/019843_31.jpg"
      },
      {
         "title":"Language",
         "description":"Nous parlons tous les langues importants.",
         "imageHref":null
      }
   ]
}
"""

class CountryViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    private var countryViewModel = CountryViewModel()/*= mock(CountryViewModel::class.java)*/

    private val countryRepository = mock(DefaultCountryRepository::class.java)

    @Test
    fun getCountryData_should_return_valid_json() {

        val countryObj = Gson().fromJson(countryDataJson, Country::class.java)

        Mockito.`when`(countryRepository.getCountryData()).thenReturn(just(countryObj))
        //doReturn(just(countryObj)).`when`(countryRepository).getCountryData()

        //countryViewModel.countryLivedata.observeForever {countryViewModel.countryLivedata.value = countryObj }

        countryViewModel.getCountryData()

        //val value = LivedataTestUtil.getValue(countryViewModel.countryLivedata)

        assert(countryViewModel.countryLivedata.value == countryObj)

    }


}






