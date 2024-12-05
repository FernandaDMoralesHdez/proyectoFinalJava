package metodos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;

public class validacionesCita {

    // Metodo para validar el formato de la fecha
    public static boolean validarFormatoFecha(String fecha) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        try {
            sdf.parse(fecha); // Intenta analizar la fecha
            return true; // Si no lanza excepción, es válida
        } catch (ParseException e) {
            return false; // Si lanza excepción, es inválida
        }
    }

    // Metodo para validar la hora
    public static boolean validarHora(String hora) {
        try {
            LocalTime horaCita = LocalTime.parse(hora); // Intenta analizar la hora
            return !(horaCita.isBefore(LocalTime.of(6, 0)) || horaCita.isAfter(LocalTime.of(22, 0)));
        } catch (Exception e) {
            return false; // Si lanza excepción, es inválida
        }
    }
}

