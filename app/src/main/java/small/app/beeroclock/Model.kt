package small.app.beeroclock

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.runBlocking
import small.app.beeroclock.db.Repository
import java.util.*
import kotlin.concurrent.timerTask

class Model(context: Context) : ViewModel() {

    var text: MutableLiveData<String> = MutableLiveData()
    var repo: Repository = Repository(context)
    val timer = Timer()

    init {
        runBlocking {
            repo.getCities()
        }
        //(TimerTask task, Date firstTime, long period)

        val delay: Long = (60 - Calendar.getInstance().get(Calendar.MINUTE)).toLong() * 60 * 1000
        val period: Long = 3600 * 1000
        timer.schedule(
            timerTask {
                runBlocking {
                    repo.getCities()
                    resetCity()
                }
            },
            delay,
            period
        )

    }


    fun resetCity() {
        val validCities = repo.validCities
        val elementAt = validCities.entries.elementAt(Random().nextInt(validCities.size))
        text.value = "${elementAt.key.name_ascii} : ${elementAt.value}"
    }

}

fun String.to(charset: String): String {
    return String(this.toByteArray(charset(charset)), Charsets.UTF_16)
    //return this.toByteArray(charset(charset)).toString(charset(charset))
}

