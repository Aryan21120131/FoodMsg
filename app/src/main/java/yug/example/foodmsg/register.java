package yug.example.foodmsg;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    private EditText username,password,email;
    DatabaseReference refdata;
    Members member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button register = findViewById(R.id.register);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        member=new Members();
        refdata= FirebaseDatabase.getInstance().getReference().child("Member");
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    member.setUsername(username.getText().toString());
                    member.setPassword(password.getText().toString());
                    member.setEmail(email.getText().toString());
                    refdata.child(String.valueOf(MainActivity.maxId+1)).setValue(member);
                    Intent home = new Intent(getApplicationContext(), home_page.class);
                    startActivity(home);
            }
        });
    }
}