package mx.edu.itson.brmr.reporteciudadano;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ContactoActivity extends AppCompatActivity {

    Button btnMapa, btnCorreo, btnLlamar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        btnMapa = findViewById(R.id.btnMapa);
        btnCorreo = findViewById(R.id.btnCorreo);
        btnLlamar = findViewById(R.id.btnLlamar);

        btnMapa.setOnClickListener(v -> {

            Uri uri = Uri.parse("geo:27.9184,-110.8989");

            Intent intent = new Intent(
                    Intent.ACTION_VIEW,
                    uri
            );

            startActivity(intent);

        });

        btnCorreo.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_SENDTO
            );

            intent.setData(
                    Uri.parse("mailto:contacto@reportes.com")
            );

            startActivity(intent);

        });

        btnLlamar.setOnClickListener(v -> {

            Intent intent = new Intent(
                    Intent.ACTION_DIAL
            );

            intent.setData(
                    Uri.parse("tel:6221234567")
            );

            startActivity(intent);

        });

    }
}