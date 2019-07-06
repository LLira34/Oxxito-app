package net.llira.oxxitoapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Connection {
    // Se define una variable Context.
    // A partir de esta se crea la base de datos
    private Context context;

    public Connection(Context context) {
        this.context = context;
    }

    /**
     * Método para abrir la conexion a la base de datos
     *
     * @return conn -> connection
     */
    private SQLiteDatabase openConnection() {
        // Se crea un objeto SQLiteDatabase
        SQLiteDatabase conn = context.openOrCreateDatabase(
                "oxxito.db", // Name of database
                SQLiteDatabase.OPEN_READWRITE, //Modo de lectura
                null // Factory para la db
        );
        return conn;
    }

    /**
     * Método para cerrar la conexion con la base de datoa
     */
    private void closeConnection(SQLiteDatabase conn) {
        if (conn != null) {
            conn.close();
        }
    }

    /**
     * Método para ejecutar sentencias de SQL (insert, update y delete)
     *
     * @param sentence Enunciado SQL válido
     * @return
     */
    public boolean executeSentence(String sentence) throws Exception {
        // Se abre la conexion con la bd
        SQLiteDatabase conn = openConnection();
        try {
            // Se ejecuta la sentencia sobre la conexion
            conn.execSQL(sentence);
            // Cerrar conexion
            conn.close();
        } catch (Exception e) {
            // Lanzamos la excepcion al metodo que la invoca
            throw new Exception("Error: " + e.getMessage());
        }
        return true;
    }

    public List<HashMap<String, String>> execQuery(String table, String[] fields, String condition) throws Exception {
        // Se crea una lista de objetos HashMap,
        // donde cada HashMap representara un objeto de la db
        List<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        try {
            // Abrir conexion
            SQLiteDatabase conn = openConnection();
            // Se consulta la db de acuerdo a los parametros señalados
            Cursor result = conn.query(table, fields, condition, null, null, null, null);
            HashMap<String, String> register;
            // Se itera sobre cada uno de los registros arrojados
            // por la consulta
            while (result.moveToNext()) {
                // para cada registro en la db
                // se crea un HashMap
                register = new HashMap<String, String>();
                for (int i = 0; i < fields.length; i++) {
                    // por cada campo se inserta el nombre y el valor
                    register.put(fields[i], result.getString(i));
                }
                // Agregamos el registro a la lista
                data.add(register);
            }
            // cerrar conexion
            conn.close();
        } catch (Exception e) {
            throw new Exception("Error: " + e.getMessage());
        }
        // en caso de exito retorna el resultado de la lista
        return data;
    }

    /**
     * Método para inicializar la base de datos (crear la estructura)
     */
    public void startDb() throws Exception {
        // se abre la conexion a la db
        SQLiteDatabase conn = openConnection();
        // se ejecuta sentencia para borrar la tabla de productos
        // en caso de existir
        conn.execSQL("DROP TABLE IF EXISTS products");
        // se ejecuta la sentencia para crear la tabla de productos
        conn.execSQL("CREATE TABLE products(code TEXT, name TEXT, price REAL, stock INTEGER, date TEXT)");
        // se cierra la conexion
        conn.close();
    }

}//End class
