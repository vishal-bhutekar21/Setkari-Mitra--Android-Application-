package com.example.Shetkari_Mitra;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class PermissionEducationDialog extends DialogFragment {

    public enum PermissionType {
        LOCATION,
        CAMERA,
        NOTIFICATIONS
    }

    public interface PermissionCallback {
        void onContinueRequested();
    }

    private static final String ARG_TYPE = "permission_type";
    private PermissionCallback callback;

    public static PermissionEducationDialog newInstance(PermissionType type, PermissionCallback callback) {
        PermissionEducationDialog dialog = new PermissionEducationDialog();
        dialog.callback = callback;
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type.name());
        dialog.setArguments(args);
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Context context = requireContext();
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_permission_education, null);

        ImageView ivIcon = view.findViewById(R.id.ivPermissionIcon);
        TextView tvTitle = view.findViewById(R.id.tvPermissionTitle);
        TextView tvReason = view.findViewById(R.id.tvPermissionReason);
        TextView tvPrivacyNotice = view.findViewById(R.id.tvPrivacyNotice);

        PermissionType type = PermissionType.LOCATION;
        if (getArguments() != null && getArguments().containsKey(ARG_TYPE)) {
            type = PermissionType.valueOf(getArguments().getString(ARG_TYPE));
        }

        switch (type) {
            case LOCATION:
                ivIcon.setImageResource(R.drawable.location_icon);
                tvTitle.setText(R.string.permission_location_title);
                tvReason.setText(R.string.permission_location_reason);
                tvPrivacyNotice.setText(R.string.permission_location_privacy);
                break;
            case CAMERA:
                ivIcon.setImageResource(R.drawable.camera_logo);
                tvTitle.setText(R.string.permission_camera_title);
                tvReason.setText(R.string.permission_camera_reason);
                tvPrivacyNotice.setText(R.string.permission_camera_privacy);
                break;
            case NOTIFICATIONS:
                ivIcon.setImageResource(R.drawable.baseline_search_24);
                tvTitle.setText(R.string.permission_notification_title);
                tvReason.setText(R.string.permission_notification_reason);
                tvPrivacyNotice.setText(R.string.permission_notification_privacy);
                break;
        }

        return new MaterialAlertDialogBuilder(context)
                .setView(view)
                .setPositiveButton(R.string.btn_allow_continue, (d, w) -> {
                    if (callback != null) callback.onContinueRequested();
                })
                .setNegativeButton(R.string.btn_not_now, null)
                .create();
    }
}
