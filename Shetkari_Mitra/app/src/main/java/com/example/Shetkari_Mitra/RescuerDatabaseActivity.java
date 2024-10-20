package com.example.Shetkari_Mitra;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.SearchView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
public class RescuerDatabaseActivity extends AppCompatActivity {

    private RecyclerView recyclerViewRescuers;
    private RescuerAdapter rescuerAdapter;
    private List<Rescuer> rescuerList = new ArrayList<>();
 SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rescuer_database);

        recyclerViewRescuers = findViewById(R.id.recyclerViewRescuers);
        searchView = findViewById(R.id.searchView);
        recyclerViewRescuers.setLayoutManager(new LinearLayoutManager(this));

        // Initialize adapter with empty list
        rescuerAdapter = new RescuerAdapter(rescuerList);
        recyclerViewRescuers.setAdapter(rescuerAdapter);

        if (!isInternetConnected()) {
            showInternetDialog();
        } else {
            populateRescuerList();

            // Set up SearchView listener for filtering
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    rescuerAdapter.getFilter().filter(newText);
                    return true;
                }
            });
        }
    }

    private boolean isInternetConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting()) {
                return true;
            }
        }
        return false;
    }

    private void populateRescuerList() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);

        Button cancel = progressDialog.findViewById(R.id.btnClose);
        Button retry=progressDialog.findViewById(R.id.btnWait);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RescuerDatabaseActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        DatabaseReference rescuerRef = FirebaseDatabase.getInstance().getReference().child("snakeresuerregistration");

        rescuerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String email = snapshot.child("email").getValue(String.class);
                    String district = snapshot.child("district").getValue(String.class);
                    String taluka = snapshot.child("taluka").getValue(String.class);
                    String mobile = snapshot.child("mobile").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);

                    // Add the retrieved data to the rescuerList
                    rescuerList.add(new Rescuer(name, email, district, taluka, mobile, address));

                }
                progressDialog.dismiss();

                // Notify adapter after data retrieval
                rescuerAdapter.notifyDataSetChanged();

                // Reset the filter to show all data initially
                rescuerAdapter.resetFilter();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                progressDialog.dismiss();


                Toast.makeText(RescuerDatabaseActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
        private void showInternetDialog() {
            EnableInternetDialogFragment dialogFragment = new EnableInternetDialogFragment();
            dialogFragment.show(getSupportFragmentManager(), "EnableInternetDialogFragment");
        }
}
