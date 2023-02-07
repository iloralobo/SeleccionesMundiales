package utilidades;

import com.github.javafaker.Faker;
import modelos.*;
import modelos.Seleccion;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GenerarDatos {

    private static Faker faker = new Faker();


    public static List<Seleccion> crearSelecciones(int num){

        List<Seleccion> selecciones = new ArrayList<>();

        for (int x = 0; x < num; x++) {
            Seleccion seleccion = new Seleccion();
            String country = faker.nation().nationality();
            seleccion.setContinente(Continente.values()[faker.number().numberBetween(0, 4)]);
            seleccion.setNombre(country);
            seleccion.setPais(new Pais(country));
            selecciones.add(seleccion);
        }

        return selecciones;

    }

    public static List<Jugador> generarJugadores(int num, Seleccion seleccion){

        List<Jugador> jugadores = new ArrayList<>();

        for(int x = 0; x < num; x++) {
            Jugador jugador = new Jugador();
            String nation = faker.nation().capitalCity();
            jugador.setNombre(faker.funnyName().name());
            jugador.setPosicion(Posicion.values()[faker.number().numberBetween(0, 3)]);
            jugador.setFechaNacimiento(faker.date().past(5000, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            jugador.setLiga(new Liga(nation, new Pais(seleccion.getPais().getNombre())));
            jugadores.add(jugador);
        }
        return jugadores;
    }

}