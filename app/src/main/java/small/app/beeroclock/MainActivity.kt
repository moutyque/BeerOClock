package small.app.beeroclock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import small.app.beeroclock.db.Repository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = Repository(applicationContext)
        runBlocking {
            repo.getCities()
        }

    }

    override fun onStart() {
        super.onStart()
    }


}