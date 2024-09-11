import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.compose.ui.layout.layout
import androidx.compose.ui.semantics.password
import androidx.compose.ui.semantics.text
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        if (sharedPrefs.getBoolean("isLogged", false)) {
            navigateToPublisherActivity()
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(findViewById(android.R.id.content), "Email inválido", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val user = Users.validUsers.find { it.email == email && it.password == password }
            if (user != null) {
                sharedPrefs.edit().putBoolean("isLogged", true).apply()
                navigateToPublisherActivity()
            } else {
                Snackbar.make(findViewById(android.R.id.content), "Credenciales incorrectas", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun navigateToPublisherActivity() {
        val intent = Intent(this, PublisherActivity::class.java)
        startActivity(intent)
        finish()
    }
}