package com.example.appbanlinhkien30.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.appbanlinhkien30.R;
import com.example.appbanlinhkien30.Utils.Utils;
import com.example.appbanlinhkien30.model.Cart;
import com.example.appbanlinhkien30.model.NewProduct;

import java.text.DecimalFormat;

public class DetailActivity extends AppCompatActivity {
    TextView productname, price, description;
    Button btnadd;
    ImageView image;
    Spinner spinner;
    Toolbar toolbar;
    NewProduct newProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        ActionToolBar();
        initData();
        initControl();
    }

    private void initControl() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcart();
            }
        });
    }
    private void addcart() {
        if(Utils.listcart.size() > 0){
            boolean flag = false;
            int amount = Integer.parseInt(spinner.getSelectedItem().toString());
            for(int i = 0; i < Utils.listcart.size(); i++){
                if(Utils.listcart.get(i).getIdproduct() == newProduct.getId()){
                    Utils.listcart.get(i).setAmount(amount + Utils.listcart.get(i).getAmount());
                    long price = Long.parseLong(newProduct.getPrice()) * Utils.listcart.get(i).getAmount();
                    Utils.listcart.get(i).setPrice(price);
                    flag = true;
                }
            }
            if(flag == false){
                long price = Long.parseLong(newProduct.getPrice()) * amount;
                Cart cart = new Cart();
                cart.setPrice(price);
                cart.setIdproduct(newProduct.getId());
                cart.setAmount(amount);
                cart.setImage(newProduct.getImage());
                cart.setProductname(newProduct.getProductname());
                Utils.listcart.add(cart);
            }

        }else{
            int amount = Integer.parseInt(spinner.getSelectedItem().toString());
            long price = Long.parseLong(newProduct.getPrice()) * amount;
            Cart cart = new Cart();
            cart.setPrice(price);
            cart.setIdproduct(newProduct.getId());
            cart.setAmount(amount);
            cart.setImage(newProduct.getImage());
            cart.setProductname(newProduct.getProductname());
            Utils.listcart.add(cart);
        }
    }
    private void initData() {
//        newProduct = newProduct = (NewProduct) getIntent().getSerializableExtra("detail");
//        productname.setText(newProduct.getProductname());
//        description.setText(newProduct.getDescription());
//        Glide.with(getApplicationContext()).load(newProduct.getImage()).into(image);
//        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
//        price.setText("Giá: "+decimalFormat.format(Double.parseDouble(newProduct.getPrice())) +"0");
//        Integer[] num = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, num);
//        spinner.getAdapter(adapterspin);
    }

    private void initView(){
        productname = findViewById(R.id.txtproductname);
        price = findViewById(R.id.txtprice);
        description = findViewById(R.id.txtdetail);
        btnadd = findViewById(R.id.btnaddcart);
        spinner = findViewById(R.id.spinner);
        image = findViewById(R.id.imgdetail);
        toolbar = findViewById(R.id.toobar);
    }
    private void ActionToolBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

