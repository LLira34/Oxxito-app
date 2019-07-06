package net.llira.oxxitoapp.dao;

import android.content.Context;

import net.llira.oxxitoapp.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductDAO {
    private Context context;

    public ProductDAO(Context context) {
        this.context = context;
    }

    /**
     * Método para insertar un objeto Producto a la BD
     *
     * @param obj
     * @throws
     */
    public void insert(Product obj) throws Exception {
        // se crea la sentencia de SQL válida
        String command = "INSERT INTO products (code, name, price, stock, date) " +
                "VALUES ('" + obj.getCode() + "', '" + obj.getName() + "', "
                + obj.getPrice() + ", " + obj.getStock() + ", '"
                + obj.getDate() + "')";
        // se crea un objeto de conexion
        Connection conn = new Connection(context);
        try {
            // se ejecuta la sentencia
            conn.executeSentence(command);
        } catch (Exception e) {
            // lanzamos la excepcion
            throw new Exception("Error al insertar: " + e.getMessage());
        }
    }

    /**
     * Método para actualizar un objeto Producto en la BD
     *
     * @param obj
     */
    public void update(Product obj) throws Exception {
        // se crea la sentencia SQL a ejecutar
        String command = "UPDATE products SET " +
                "name='" + obj.getName() + "', " +
                "price= " + obj.getPrice() + ", " +
                "stock= " + obj.getStock() + ",'" +
                "date= '" + obj.getDate() + "' " +
                "WHERE code='" + obj.getCode() + "'";
        // se crea el objeto de conexion
        Connection conn = new Connection(context);
        try {
            // se intenta ejecutar la sentencia
            conn.executeSentence(command);
        } catch (Exception e) {
            // lanzamos la excepcion
            throw new Exception("Error al editar: " + e.getMessage());
        }
    }

    /**
     * Método para eliminar un objeto producto de la BD
     *
     * @param obj
     * @throws
     */
    public void delete(Product obj) throws Exception {
        // se crea la sentencia DELETE
        String command = "DELETE FROM products WHERE code = '" +
                obj.getCode() + "'";
        // se crea el objeto de conexion
        Connection conn = new Connection(context);
        try {
            // se intenta ejecutar la sentencia
            conn.executeSentence(command);
        } catch (Exception e) {
            // lanzamos la excepcion
            throw new Exception("Error al eliminar: " + e.getMessage());
        }
    }

    public List<Product> getAll() throws Exception {
        String table = "Products";
        String fields[] = new String[]{"code", "name", "price", "stock", "date"};
        List<Product> listProducts = new ArrayList<Product>();
        Connection conn = new Connection(context);
        List<HashMap<String, String>> result = conn.execQuery(table, fields, null);
        Product p1;
        for (HashMap<String, String> reg : result) {
            p1 = new Product();
            p1.setCode(reg.get("code"));
            p1.setName(reg.get("name"));
            p1.setPrice(Double.parseDouble(reg.get("price")));
            p1.setStock(Integer.parseInt(reg.get("stock")));
            p1.setDate(reg.get("date"));
            listProducts.add(p1);
        }
        return listProducts;
    }

    public Product getById(Product obj) throws Exception {
        String table = "Products";
        String fields[] = new String[]{"code", "name", "price", "stock", "date"};
        String condition = "code= '" + obj.getCode() + "'";
        Connection conn = new Connection(context);
        List<HashMap<String, String>> result = conn.execQuery(table, fields, condition);
        Product p1 = null;
        for (HashMap<String, String> reg : result){
            p1 = new Product();
            p1.setCode(reg.get("code"));
            p1.setName(reg.get("name"));
            p1.setPrice(Double.parseDouble(reg.get("price")));
            p1.setStock(Integer.parseInt(reg.get("stock")));
            p1.setDate(reg.get("date"));
        }
        return p1;
    }

}//End class
