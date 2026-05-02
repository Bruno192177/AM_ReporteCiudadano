package mx.edu.itson.brmr.reporteciudadano;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private Button btnMapa, btnLlamar, btnCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMapa = findViewById(R.id.btnMapa);
        btnLlamar = findViewById(R.id.btnLlamar);
        btnCorreo = findViewById(R.id.btnCorreo);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirMapa();
            }
        });
        btnLlamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirLlamada();
            }
        });
        btnCorreo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarCorreo();
            }
        });
    }

    private void abrirMapa() {
        //27.96755481153419, -110.91953354769349
        double latitud = 27.96755481153419;
        double longitud = -110.91953354769349;
        //geo:lat,long?q=lat,long
        Uri uri = Uri.parse("geo" + latitud + "," + longitud + "?q=" + latitud + "," + longitud);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(mapIntent);
    }

    private void abrirLlamada() {
        String telefono = "6221234567";
        //tel:6221234567
        Uri uri = Uri.parse("tel:" + telefono);
        Intent llamadaIntent = new Intent(Intent.ACTION_DIAL);
        llamadaIntent.setData(uri);
        startActivity(llamadaIntent);
    }

    private void enviarCorreo() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        Uri uri = Uri.parse("mailto:");
        intent.setData(uri);
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] { "atencionciudadana@guaymas.gob.mx" });
        intent.putExtra(Intent.EXTRA_SUBJECT, "Reporte Ciudadano"); //El asunto del correo
        intent.putExtra(Intent.EXTRA_TEXT, "Reporte: "); //Contenido del cuerpo del correo
        startActivity(intent);
    }
}