package net.llira.oxxitoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import net.llira.oxxitoapp.dao.ProductDAO;
import net.llira.oxxitoapp.models.Product;

public class NewProductActivity extends AppCompatActivity {
    private EditText txtCode, txtProduct, txtPrice, txtStock, txtDate;
    private Button btnSave, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);

        //Inflate
        txtCode = findViewById(R.id.edt_code);
        txtProduct = findViewById(R.id.edt_product);
        txtPrice = findViewById(R.id.edt_price);
        txtStock = findViewById(R.id.edt_stock);
        txtDate = findViewById(R.id.edt_date);
        btnSave = findViewById(R.id.btn_save);
        btnCancel = findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product p = new Product();
                p.setCode(txtCode.getText().toString());
                p.setName(txtProduct.getText().toString());
                p.setPrice(Double.parseDouble(txtPrice.getText().toString()));
                p.setStock(Integer.valueOf(txtStock.getText().toString()));
                p.setDate(txtDate.getText().toString());
                // Se crea un objeto DAO para almacenar el objeto
                ProductDAO dao = new ProductDAO(getApplicationContext());
                // Se intenta insertar el objeto
                try {
                    dao.insert(p);
                    System.out.println("Nito?");
                    clean();
                    // Se muestra un mensaje de exito
                    Toast.makeText(getApplicationContext(), "Yay! Saved Product",
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
                Toast.makeText(getApplicationContext(), "Canceled!", Toast.LENGTH_LONG).show();
                clean();
                System.exit(0);
            }
        });
    }

    public void clean() {
        txtCode.setText("");
        txtProduct.setText("");
        txtPrice.setText("");
        txtStock.setText("");
        txtDate.setText("");
    }
}//End class
