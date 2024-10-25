package com.upn.contactsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.contactsapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class    FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        EditText Name=findViewById(R.id.edittextName);
        Button btn = findViewById(R.id.btnCreateOnFirebase);

        btn.setOnClickListener(v -> {
            // Write a message to the database
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference ContactsRef = database.getReference("N00278204").child("contacts");

            List<Contact>listContas=new ArrayList<>();
            ContactsRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                    Log.i("Main_app",snapshot.toString());
                    for (DataSnapshot  child:snapshot.getChildren()){
                        Contact c=child.getValue(Contact.class);
                        listContas.add(c);
                        Log.i("Main_contacsdata",c.uuid);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

//            DatabaseReference table =myRef.child("contacts");


////            Contact c1 = new Contact("Luis", "12345678");
////            c1.uuid = UUID.randomUUID().toString();
////            Contact c2 = new Contact("Miguel", "123456");
////            c2.uuid = UUID.randomUUID().toString();
//
////            myRef.child(c1.uuid).setValue(c1);
////            myRef.child(c2.uuid).setValue(c2);
//
////            List<Contact>lsc=List.of(c1,c2);
////            table.setValue(c1);
//            Contact c1 = new Contact(Name.getText().toString(), "123456");
//            c1.uuid=UUID.randomUUID().toString();
//            table.child(c1.uuid).setValue(c1);
//            Name.setText("");


        });

    }
}