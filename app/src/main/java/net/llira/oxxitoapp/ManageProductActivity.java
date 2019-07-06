package net.llira.oxxitoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.llira.oxxitoapp.dao.ProductDAO;
import net.llira.oxxitoapp.models.Product;

public class ManageProductActivity extends AppCompatActivity {
    private EditText edtCode, edtName, edtPrice, edtStock, edtDate;
    private Button btnUpdate, btnDelete, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_product);

        // 1. Se relacionan los controles entre la vista y la actividad
        edtCode = findViewById(R.id.edt_code);
        edtName = findViewById(R.id.edt_product);
        edtPrice = findViewById(R.id.edt_price);
        edtStock = findViewById(R.id.edt_stock);
        edtDate = findViewById(R.id.edt_date);
        // Buttons
        btnUpdate = findViewById(R.id.btn_update);
        btnDelete = findViewById(R.id.btn_delete);
        btnCancel = findViewById(R.id.btn_cancel);
        // 2. Se obtiene el valor Codigo de barras de un bundle de la actividad
        String code = getIntent().getExtras().getString("code");
        // 3. Se crea un objeto Producto
        Product p = new Product();
        // 4. Se asigna el codigo de barras al objeto
        p.setCode(code);
        // 5. Se crea un objeto DAO para buscar el producto
        ProductDAO dao = new ProductDAO(getApplicationContext());
        try {
            p = dao.getById(p);
            edtCode.setText(p.getCode());
            edtName.setText(p.getName());
            edtPrice.setText("" + p.getPrice());
            edtStock.setText(p.getStock());
            edtDate.setText(p.getDate());
        } catch (Exception e) {
            // Aqui es donde tengo el error al momento de actualizar
            Toast.makeText(getApplicationContext(), "Upsss! Error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
        // 6. Se agrega un listener al botón btnUpdate
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();
                // Se asigan los datos a los atributos del objeto
                p.setCode(edtCode.getText().toString());
                p.setName(edtName.getText().toString());
                p.setPrice(Double.parseDouble(edtPrice.getText().toString()));
                p.setStock(Integer.valueOf(edtPrice.getText().toString()));
                p.setDate(edtDate.getText().toString());
                // Se crea un objeto DAO para almacenar el objeto
                ProductDAO dao = new ProductDAO(getApplicationContext());
                // Se intenta realizar los cambios
                try {
                    dao.update(p);
                    // Se muestra un mensaje de exito
                    Toast.makeText(getApplicationContext(), "Yay! Updated Product",
                            Toast.LENGTH_LONG).show();
                    System.exit(0);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Ups! Error!: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        // 7. Se agrega listener al boton btnDelete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se crea un objeti Producto
                Product p = new Product();
                // Se asigna sus atributos
                p.setCode(edtCode.getText().toString());
                // Se crea un objeto DAO
                ProductDAO dao = new ProductDAO(getApplicationContext());
                // Se intenta eliminar el objeto
                try {
                    dao.delete(p);
                    // Se muestra un mensaje de exito
                    Toast.makeText(getApplicationContext(), "Yay! Deleted Product",
                            Toast.LENGTH_LONG).show();
                    System.exit(0);
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Ups! Error!: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}//End class
