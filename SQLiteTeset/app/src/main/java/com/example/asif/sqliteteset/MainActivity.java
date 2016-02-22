package com.example.asif.sqliteteset;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    EditText buckysInput;
    TextView buckysText;
    myDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buckysInput = (EditText) findViewById(R.id.buckysInput);
        buckysText = (TextView) findViewById(R.id.buckysText);
        dbHandler = new myDBHandler(this, null,null,1);
        printDatabase();
    }
    // Add product to database
    public void addButtonClicked(View view){
        Product product = new Product(buckysInput.getText().toString());
        dbHandler.addProduct(product);
//        System.out.println("id = " + product.get_id());
        printDatabase();
    }
    // Delete Item
    public void deleteButtonClicked(View view){
        dbHandler.deleteProducts(buckysInput.getText().toString());
        printDatabase();
    }
    public void printDatabase(){
        String dbString = dbHandler.databaseToString();
        buckysText.setText(dbString);
        buckysInput.setText("");
    }
}
