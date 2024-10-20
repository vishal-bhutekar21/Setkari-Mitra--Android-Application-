package com.example.Shetkari_Mitra;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.*;
import java.util.ArrayList;
import java.util.List;

public class nav_Emergency_Contacts extends AppCompatActivity {

    private RecyclerView recyclerView;
    private EmergencyContactsAdapter adapter;
    private List<EmergencyContact> emergencyContactsList;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;
    private Button buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_emergency_contacts);

        recyclerView = findViewById(R.id.recyclerViewEmergencyContacts);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emergencyContactsList = new ArrayList<>();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(currentUser.getUid()).child("emergencyContacts");


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    emergencyContactsList.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        EmergencyContact emergencyContact = dataSnapshot.getValue(EmergencyContact.class);
                        emergencyContactsList.add(emergencyContact);
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(nav_Emergency_Contacts.this, "Failed to retrieve emergency contacts: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            adapter = new EmergencyContactsAdapter(nav_Emergency_Contacts.this, emergencyContactsList);
            recyclerView.setAdapter(adapter);
        }
    }

}
