package net.llira.oxxitoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import net.llira.oxxitoapp.dao.ProductDAO;
import net.llira.oxxitoapp.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListProductActivity extends AppCompatActivity {
    private Button btnNew;
    private ListView lsvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);

        //Inflate
        btnNew = findViewById(R.id.btn_new_product);
        lsvProducts = findViewById(R.id.lsv_products);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewProduct = new Intent(getApplicationContext(), NewProductActivity.class);
                startActivity(iNewProduct);
            }
        });

        // Se crea un objeto para mostrar todos los productos
        ProductDAO dao = new ProductDAO(getApplicationContext());
        List<Product> listProducts;
        HashMap<String, String> register;
        try {
            listProducts = dao.getAll();
            List<HashMap<String, String>> rows = new ArrayList<HashMap<String, String>>();
            for (Product pro : listProducts) {
                register = new HashMap<String, String>();
                register.put("code", pro.getCode());
                register.put("name", pro.getName());
                register.put("price", String.valueOf(pro.getPrice()));
                rows.add(register);
            }
            // Se crea un adapter para visualiza el registro de productos
            SimpleAdapter adapter = new SimpleAdapter(getApplicationContext(), rows,
                    R.layout.activity_register_product, new String[]{"code", "name", "price"},
                    new int[]{R.id.txv_code, R.id.txv_product, R.id.txv_price});
            // Se asigna el adaptador al ListView
            lsvProducts.setAdapter(adapter);
            lsvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                    // Se obtiene el control que contiene el codigo de barras
                    // del elemento seleccionado
                    TextView txtCode = arg1.findViewById(R.id.txv_code);
                    // Se crea el objeto Bundle
                    Bundle bundle = new Bundle();
                    // Se inserta el codigo en el objeto
                    bundle.putString("code", txtCode.getText().toString());
                    // Se crea un objeto item para iniciar la actividad GestionProduct
                    Intent iManage = new Intent(getApplicationContext(), ManageProductActivity.class);
                    iManage.putExtras(bundle);
                    startActivity(iManage);
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Upsi! Error!: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

}//End class
