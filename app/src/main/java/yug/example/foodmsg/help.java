package yug.example.foodmsg;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class help extends AppCompatActivity {

    TextView username,password,email;
    DatabaseReference reff;
    String users=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        username=findViewById(R.id.username);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);
        for(int i=1;i<=MainActivity.maxId;i++) {
            reff = FirebaseDatabase.getInstance().getReference().child("Member").child(String.valueOf(i));
            reff.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    username.setText(snapshot.child("username").getValue().toString());
                    password.setText(snapshot.child("password").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }
}