package com.example.loginapputeq;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    // Declaración de campos de entrada y botones
    private TextInputLayout textInputLayoutUsername;
    private TextInputEditText editTextUsername;
    private TextInputLayout textInputLayoutPassword;
    private TextInputEditText editTextPassword;
    private Button btnAceptarLogin; // Cambiado de btnIngresarLogin
    private Button btnCancelarLogin; // Nuevo botón
    private Button btnAtrasLogin;    // Nuevo botón

    // Mapa con credenciales válidas de usuario y contraseña
    private static final Map<String, String> VALID_CREDENTIALS = new HashMap<>();

    // Bloque estático para inicializar el mapa de usuarios y contraseñas
    static {
        VALID_CREDENTIALS.put("Madison Alarcon", "12345");
        VALID_CREDENTIALS.put("Gina Castro", "54321");
        // Puedes agregar más usuarios aquí:
        // VALID_CREDENTIALS.put("Nombre Apellido", "clave");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Vincular variables con elementos de la interfaz gráfica (layout)
        textInputLayoutUsername = findViewById(R.id.textInputLayoutUsername);
        editTextUsername = findViewById(R.id.editTextUsername);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);
        editTextPassword = findViewById(R.id.editTextPassword);

        // Vinculación de los botones
        btnAceptarLogin = findViewById(R.id.btnAceptarLogin); // Usar el ID actualizado
        btnCancelarLogin = findViewById(R.id.btnCancelarLogin); // Vincular el nuevo botón Cancelar
        btnAtrasLogin = findViewById(R.id.btnAtrasLogin);       // Vincular el nuevo botón Atrás

        // Configurar el botón ACEPTAR para ejecutar la validación al hacer clic
        btnAceptarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(); // Ejecuta el método de validación
            }
        });

        // Configurar el botón CANCELAR
        btnCancelarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar la lógica para cancelar la acción
                // Por ejemplo, limpiar los campos de texto o volver a una pantalla anterior.
                editTextUsername.setText(""); // Limpiar campo de usuario
                editTextPassword.setText(""); // Limpiar campo de contraseña
                textInputLayoutUsername.setError(null); // Quitar errores
                textInputLayoutPassword.setError(null); // Quitar errores
                Toast.makeText(LoginActivity.this, "Acción cancelada", Toast.LENGTH_SHORT).show();
            }
        });

        // Configurar el botón ATRÁS
        btnAtrasLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí puedes agregar la lógica para ir hacia atrás
                // Por ejemplo, cerrar la actividad actual o navegar a la anterior.
                Toast.makeText(LoginActivity.this, "Volviendo a la pantalla anterior...", Toast.LENGTH_SHORT).show();
                finish(); // Cierra la actividad actual
            }
        });
    }

    // Método para validar las credenciales ingresadas
    private void attemptLogin() {
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Elimina errores anteriores
        textInputLayoutUsername.setError(null);
        textInputLayoutPassword.setError(null);

        boolean isValid = true;

        // Verifica que el campo de usuario no esté vacío
        if (username.isEmpty()) {
            textInputLayoutUsername.setError("Nombre requerido");
            isValid = false;
        }

        // Verifica que el campo de contraseña no esté vacío
        if (password.isEmpty()) {
            textInputLayoutPassword.setError("Clave requerida");
            isValid = false;
        }

        // Si los campos no están vacíos, valida las credenciales
        if (isValid) {
            if (VALID_CREDENTIALS.containsKey(username) && VALID_CREDENTIALS.get(username).equals(password)) {
                // Usuario y contraseña correctos
                Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                // Aquí se puede iniciar otra actividad (ej. pantalla principal)
                // Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                // startActivity(intent);
                // finish(); // Opcional: cierra esta actividad para que el usuario no pueda volver con el botón "atrás"
            } else {
                // Usuario o contraseña incorrectos
                Toast.makeText(LoginActivity.this, "Usuario o clave incorrectos", Toast.LENGTH_SHORT).show();
            }
        }
    }
}