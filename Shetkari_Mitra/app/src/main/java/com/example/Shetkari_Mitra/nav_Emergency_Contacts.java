package com.example.Shetkari_Mitra;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class nav_Emergency_Contacts extends AppCompatActivity implements EmergencyContactsAdapter.OnContactActionListener {

    private RecyclerView recyclerView;
    private EmergencyContactsAdapter adapter;
    private final List<EmergencyContact> emergencyContactsList = new ArrayList<>();
    private TextView emptyTextView;
    private FloatingActionButton fabAddContact;

    private AppDatabase db;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_emergency_contacts);

        View btnGlassBack = findViewById(R.id.btnGlassBack);
        if (btnGlassBack != null) {
            btnGlassBack.setOnClickListener(v -> finish());
        }

        db = AppDatabase.getInstance(this);

        recyclerView = findViewById(R.id.recyclerViewEmergencyContacts);
        emptyTextView = findViewById(R.id.emptyTextView);
        fabAddContact = findViewById(R.id.fabAddContact);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new EmergencyContactsAdapter(this, emergencyContactsList, this);
        recyclerView.setAdapter(adapter);

        if (fabAddContact != null) {
            fabAddContact.setOnClickListener(v -> showAddContactDialog());
        }

        BottomNavigationHelper.setupBottomNavigation(this, findViewById(R.id.bottom_navigation), R.id.bottom_nav_profile);

        loadContactsFromDatabase();
    }

    private void loadContactsFromDatabase() {
        executorService.execute(() -> {
            List<EmergencyContactEntity> entities = db.emergencyContactDao().getAll();
            List<EmergencyContact> contacts = new ArrayList<>();
            for (EmergencyContactEntity entity : entities) {
                contacts.add(EmergencyContact.fromEntity(entity));
            }

            mainHandler.post(() -> {
                emergencyContactsList.clear();
                emergencyContactsList.addAll(contacts);
                adapter.notifyDataSetChanged();

                if (emptyTextView != null) {
                    emptyTextView.setVisibility(emergencyContactsList.isEmpty() ? View.VISIBLE : View.GONE);
                }
            });
        });
    }

    private void showAddContactDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_emergency_contact, null);
        EditText etName = dialogView.findViewById(R.id.etContactName);
        EditText etPhone = dialogView.findViewById(R.id.etContactPhone);

        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.add_emergency_contact)
                .setView(dialogView)
                .setPositiveButton(R.string.save, (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                        Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    executorService.execute(() -> {
                        db.emergencyContactDao().insert(new EmergencyContactEntity(name, phone));
                        loadContactsFromDatabase();
                    });
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onEdit(EmergencyContact contact) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_emergency_contact, null);
        EditText etName = dialogView.findViewById(R.id.etContactName);
        EditText etPhone = dialogView.findViewById(R.id.etContactPhone);

        etName.setText(contact.getName());
        etPhone.setText(contact.getNumber());

        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.edit_contact)
                .setView(dialogView)
                .setPositiveButton(R.string.update, (dialog, which) -> {
                    String name = etName.getText().toString().trim();
                    String phone = etPhone.getText().toString().trim();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
                        Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    executorService.execute(() -> {
                        EmergencyContactEntity entity = new EmergencyContactEntity(name, phone);
                        entity.id = contact.getId();
                        db.emergencyContactDao().update(entity);
                        loadContactsFromDatabase();
                    });
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    public void onDelete(EmergencyContact contact) {
        new MaterialAlertDialogBuilder(this)
                .setTitle(R.string.delete_contact)
                .setMessage(getString(R.string.delete_contact_confirm, contact.getName()))
                .setPositiveButton(R.string.delete, (dialog, which) -> {
                    executorService.execute(() -> {
                        db.emergencyContactDao().deleteById(contact.getId());
                        loadContactsFromDatabase();
                    });
                })
                .setNegativeButton(R.string.cancel, null)
                .show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }
}
