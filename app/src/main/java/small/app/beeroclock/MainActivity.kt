package small.app.beeroclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import small.app.beeroclock.db.Repository

class MainActivity : AppCompatActivity() {

    lateinit var repo: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repo = Repository(applicationContext)
        runBlocking {
            repo.getCities()
        }

    }


}