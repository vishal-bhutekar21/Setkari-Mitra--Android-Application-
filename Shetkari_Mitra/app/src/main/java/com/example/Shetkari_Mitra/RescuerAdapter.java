package com.example.Shetkari_Mitra;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RescuerAdapter extends RecyclerView.Adapter<RescuerAdapter.RescuerViewHolder> implements Filterable {

    private List<Rescuer> rescuerList;
    private List<Rescuer> rescuerListFiltered;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 123;
    private Context context;

    public RescuerAdapter(List<Rescuer> rescuerList) {
        this.rescuerList = rescuerList;
        this.rescuerListFiltered = new ArrayList<>(rescuerList);
    }

    @NonNull
    @Override
    public RescuerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_rescuer, parent, false);
        return new RescuerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RescuerViewHolder holder, int position) {
        Rescuer rescuer = rescuerListFiltered.get(position);
        holder.textViewRescuerName.setText(rescuer.getName());
        holder.Address.setText("Address : "+rescuer.getAddress());
        holder.testviewformobile.setText("Contact : "+rescuer.getMobile());
        holder.textforaddress.setText("Taluka : "+rescuer.getTaluka()+"\nDistrict : "+rescuer.getDistrict());

        holder.btnSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = rescuer.getMobile();
                String message = "Emergency! There is a snake near me, we need your help. My current location is: ";

                // Fetch current location coordinates
                LocationManager locationManager = (LocationManager) v.getContext().getSystemService(Context.LOCATION_SERVICE);
                if (ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                        ActivityCompat.checkSelfPermission(v.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // Request location permissions if not granted
                    ActivityCompat.requestPermissions((Activity) v.getContext(),
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                    return;
                }
                Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();

                    // Reverse geocoding to convert coordinates into a human-readable address
                    Geocoder geocoder = new Geocoder(v.getContext(), Locale.getDefault());
                    try {
                        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                        if (addresses != null && addresses.size() > 0) {
                            Address address = addresses.get(0);
                            StringBuilder addressBuilder = new StringBuilder();
                            // Append address details to the message
                            addressBuilder.append(address.getAddressLine(0)).append(", "); // Road
                            addressBuilder.append(address.getSubAdminArea()).append(", "); // Village
                            addressBuilder.append(address.getAdminArea()).append(", "); // District
                            addressBuilder.append(address.getCountryName()).append(", "); // Country
                            addressBuilder.append(address.getPostalCode()); // Pincode

                            message += addressBuilder.toString();

                            // Append Google Maps link to the message
                            String mapLink = "http://maps.google.com/maps?q=" + latitude + "," + longitude;
                            message += "\n\n" + "Location on Map: " + mapLink;
                        } else {
                            // If address is not available, append coordinates
                            message += "Latitude: " + latitude + ", Longitude: " + longitude;

                            // Append Google Maps link to the message
                            String mapLink = "http://maps.google.com/maps?q=" + latitude + "," + longitude;
                            message += "\n\n" + "Location on Map: " + mapLink;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle case where location is not available
                    message += "Location not available";
                }

                // Create the SMS intent
                Uri uri = Uri.parse("smsto:" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", message);

                // Check if there's an app that can handle the intent
                if (intent!= null) {
                    v.getContext().startActivity(intent);
                } else {
                    // If no app found, inform the user
                    Toast.makeText(v.getContext(), "No app found to handle this action", Toast.LENGTH_SHORT).show();
                }
            }
        });




        holder.btnCallRescuer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = rescuer.getMobile();

                // Create the intent for dialing a phone number
                Uri uri = Uri.parse("tel:" + phoneNumber);
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);

                // Check if there's an app that can handle the intent
                if (intent != null) {
                    v.getContext().startActivity(intent);
                } else {
                    Toast.makeText(v.getContext(), "No app found to handle this action", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return rescuerListFiltered.size();
    }

    static class RescuerViewHolder extends RecyclerView.ViewHolder {
        TextView textViewRescuerName, Address, testviewformobile, textforaddress;
        Button btnCallRescuer, btnSms;

        public RescuerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewRescuerName = itemView.findViewById(R.id.textViewRescuerName);
            Address = itemView.findViewById(R.id.address);
            testviewformobile = itemView.findViewById(R.id.testviewformobile);
            textforaddress = itemView.findViewById(R.id.talukaanddistrict);
            btnCallRescuer = itemView.findViewById(R.id.btnCallRescuer);
            btnSms = itemView.findViewById(R.id.btnSms);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterPattern = constraint.toString().toLowerCase().trim();

                FilterResults results = new FilterResults();
                if (filterPattern.isEmpty()) {
                    results.values = rescuerList;
                } else {
                    List<Rescuer> filteredList = new ArrayList<>();
                    for (Rescuer rescuer : rescuerList) {
                        if (rescuer.getName().toLowerCase().contains(filterPattern) ||
                                rescuer.getEmail().toLowerCase().contains(filterPattern) ||
                                rescuer.getTaluka().toLowerCase().contains(filterPattern) ||
                                rescuer.getDistrict().toLowerCase().contains(filterPattern)) {
                            filteredList.add(rescuer);
                        }
                    }
                    results.values = filteredList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                rescuerListFiltered = (List<Rescuer>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    // Method to reset the filtered list to the original list
    public void resetFilter() {
        rescuerListFiltered = new ArrayList<>(rescuerList);
        notifyDataSetChanged();
    }
}