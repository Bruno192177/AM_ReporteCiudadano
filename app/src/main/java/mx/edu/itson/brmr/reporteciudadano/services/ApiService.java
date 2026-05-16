package mx.edu.itson.brmr.reporteciudadano.services;

import mx.edu.itson.brmr.reporteciudadano.models.Reporte;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("posts")
    Call<Reporte> enviarReporte(
            @Body Reporte reporte
    );

}