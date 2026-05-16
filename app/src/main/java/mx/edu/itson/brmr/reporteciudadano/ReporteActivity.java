package mx.edu.itson.brmr.reporteciudadano;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import mx.edu.itson.brmr.reporteciudadano.models.Reporte;
import mx.edu.itson.brmr.reporteciudadano.services.ApiService;
import mx.edu.itson.brmr.reporteciudadano.services.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

public class ReporteActivity extends AppCompatActivity {

    Spinner spTipoReporte, spColonias;
    Button btnSeleccionarImagen;
    ImageView imgReporte;

    EditText etNombre, etDireccion, etCelular, etCorreo, etDescripcion;

    Button btnEnviar;

    Uri imagenUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        spTipoReporte = findViewById(R.id.spTipoReporte);

        spColonias = findViewById(R.id.spColonias);

        btnSeleccionarImagen = findViewById(R.id.btnSeleccionarImagen);
        imgReporte = findViewById(R.id.imgReporte);

        etNombre = findViewById(R.id.etNombre);
        etDireccion = findViewById(R.id.etDireccion);
        etCelular = findViewById(R.id.etCelular);
        etCorreo = findViewById(R.id.etCorreo);
        etDescripcion = findViewById(R.id.etDescripcion);

        btnEnviar = findViewById(R.id.btnEnviar);

        String[] tipos = {
                "ALUMBRADO PÚBLICO",
                "ANIMALES CALLEJEROS",
                "BACHES",
                "BASURA O ESCOMBRO",
                "FUMIGACIÓN O PLAGAS",
                "FUGAS O DRENAJE",
                "OTRO ASUNTO"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                tipos
        );

        spTipoReporte.setAdapter(adapter);

        String[] colonias = {
                "CENTRO",
                "MIRAMAR",
                "FÁTIMA",
                "GUADALUPE",
                "INDEPENDENCIA",
                "SAN VICENTE",
                "POPULAR",
                "LOMA LINDA",
                "GUAYMAS NORTE",
                "LAS VILLAS"
        };

        ArrayAdapter<String> adapterColonias =
                new ArrayAdapter<>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        colonias
                );

        spColonias.setAdapter(adapterColonias);

        btnSeleccionarImagen.setOnClickListener(v -> {

            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");

            startActivityForResult(intent, 1);

        });

        btnEnviar.setOnClickListener(v -> {

            String nombre = etNombre.getText().toString().trim();
            String colonia = spColonias.getSelectedItem().toString();
            String direccion = etDireccion.getText().toString().trim();
            String celular = etCelular.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String descripcion = etDescripcion.getText().toString().trim();

            if(nombre.isEmpty()){
                etNombre.setError("Ingrese nombre");
                etNombre.requestFocus();
                return;
            }

            if(direccion.isEmpty()){
                etDireccion.setError("Ingrese dirección");
                etDireccion.requestFocus();
                return;
            }

            if(celular.isEmpty()){
                etCelular.setError("Ingrese celular");
                etCelular.requestFocus();
                return;
            }

            if(correo.isEmpty()){
                etCorreo.setError("Ingrese correo");
                etCorreo.requestFocus();
                return;
            }

            if(!Patterns.EMAIL_ADDRESS.matcher(correo).matches()){
                etCorreo.setError("Correo inválido");
                etCorreo.requestFocus();
                return;
            }

            if(descripcion.isEmpty()){
                etDescripcion.setError("Ingrese descripción");
                etDescripcion.requestFocus();
                return;
            }

            if(imagenUri == null){
                Toast.makeText(
                        this,
                        "Seleccione una imagen",
                        Toast.LENGTH_SHORT
                ).show();

                return;
            }

            String tipo = spTipoReporte.getSelectedItem().toString();

            Reporte reporte = new Reporte(
                    nombre,
                    colonia,
                    direccion,
                    celular,
                    correo,
                    tipo,
                    descripcion
            );

            ApiService apiService = RetrofitClient
                    .getClient()
                    .create(ApiService.class);

            Call<Reporte> call =
                    apiService.enviarReporte(reporte);

            call.enqueue(new Callback<Reporte>() {
                @Override
                public void onResponse(Call<Reporte> call,
                                       Response<Reporte> response) {

                    Toast.makeText(
                            ReporteActivity.this,
                            "Reporte enviado al servidor",
                            Toast.LENGTH_LONG
                    ).show();

                }

                @Override
                public void onFailure(Call<Reporte> call,
                                      Throwable t) {

                    Toast.makeText(
                            ReporteActivity.this,
                            "Error de conexión",
                            Toast.LENGTH_LONG
                    ).show();

                }
            });

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            imagenUri = data.getData();

            imgReporte.setImageURI(imagenUri);

        }
    }
}