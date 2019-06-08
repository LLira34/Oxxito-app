package net.llira.oxxitoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

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
    }
}
