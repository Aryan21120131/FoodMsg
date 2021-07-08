package yug.example.foodmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Switch;

public class home_page extends AppCompatActivity {

    String[] b_names={"BEVERAGES","SNACKS","MOCKTAIL","TEA","COFFEE","JUICE","BURGER"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        ImageButton logout = findViewById(R.id.logout);
        AutoCompleteTextView dish = findViewById(R.id.dish);
        ArrayAdapter<String> dish_name=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,b_names);
        dish.setThreshold(1);
        dish.setAdapter(dish_name);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_option,menu);
        super.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if(item.getItemId()==R.id.logout){
            Intent main=new Intent(getApplicationContext(),MainActivity.class);
            MainActivity.sp.edit().putBoolean("SWITCH1",false).apply();
            startActivity(main);
        }
        if(item.getItemId()==R.id.help){
            Intent help=new Intent(getApplicationContext(), yug.example.foodmsg.help.class);
            startActivity(help);
        }
        return super.onOptionsItemSelected(item);
    }
}