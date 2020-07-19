package ar.edu.unq.epers.bichomon.backend.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Un cierto bloque de codigo que se ejecuta recibiendo por parametro
 * un objeto {@link Connection} de jdbc (que representa una conexion
 * existente y ya establecida). El bloque devuelve algo de un tipo T
 * y puede arrojar excepciones de tipo {@link SQLException} (toda la
 * API de JDBC arroja {@link SQLException}, tipicamente querremos
 * manejarlas de forma generica)
 *
 */
@FunctionalInterface
public interface ConnectionBlock<T> {

    T executeWith(Connection conn) throws SQLException;

}