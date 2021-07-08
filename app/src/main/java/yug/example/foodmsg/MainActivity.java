package yug.example.foodmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    static SharedPreferences sp;

    DatabaseReference reff;
    String users=null;
    static int maxId=0;

    FirebaseUser user;

    @Override
    protected void onStart() {
        super.onStart();

        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            startActivity(new Intent(MainActivity.this,home_page.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.login);
        Button register = findViewById(R.id.register);
        TextView text_result = findViewById(R.id.res);
        sp=getSharedPreferences("SHARED_PREFS",MODE_PRIVATE);

        boolean switchOnOff = sp.getBoolean("SWITCH1", false);
        text_result.setText(Boolean.toString(switchOnOff));

        /*if(SwitchOnOff){
            Toast.makeText(this, "Already Loged in", Toast.LENGTH_SHORT).show();
            Intent home=new Intent(getApplicationContext(),home_page.class);
            startActivity(home);
        }*/

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putBoolean("SWITCH1",true).apply();
                Intent login = new Intent(getApplicationContext(), yug.example.foodmsg.login.class);
                startActivity(login);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sp.edit().putBoolean("SWITCH1",true).apply();
                Intent register=new Intent(getApplicationContext(), yug.example.foodmsg.register.class);
                startActivity(register);
            }
        });

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    MainActivity.maxId= (int) snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        for(int i=1;i<=maxId;i++) {
            reff = FirebaseDatabase.getInstance().getReference().child("Member").child(String.valueOf(i));
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    users+="\n"+snapshot.child("username").getValue().toString();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }

}