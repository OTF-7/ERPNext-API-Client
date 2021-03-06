// Generated by view binder compiler. Do not edit!
package com.example.apitest.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.apitest.R;
import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityMainBinding implements ViewBinding {
  @NonNull
  private final ScrollView rootView;

  @NonNull
  public final TextInputLayout apiKeyField;

  @NonNull
  public final AppCompatButton connectButton;

  @NonNull
  public final Button demoButton;

  @NonNull
  public final SwitchMaterial hasSslSwitch;

  @NonNull
  public final Toolbar mainToolbar;

  @NonNull
  public final ProgressBar progressBar;

  @NonNull
  public final TextInputLayout secretKeyField;

  @NonNull
  public final TextInputLayout urlField;

  private ActivityMainBinding(@NonNull ScrollView rootView, @NonNull TextInputLayout apiKeyField,
      @NonNull AppCompatButton connectButton, @NonNull Button demoButton,
      @NonNull SwitchMaterial hasSslSwitch, @NonNull Toolbar mainToolbar,
      @NonNull ProgressBar progressBar, @NonNull TextInputLayout secretKeyField,
      @NonNull TextInputLayout urlField) {
    this.rootView = rootView;
    this.apiKeyField = apiKeyField;
    this.connectButton = connectButton;
    this.demoButton = demoButton;
    this.hasSslSwitch = hasSslSwitch;
    this.mainToolbar = mainToolbar;
    this.progressBar = progressBar;
    this.secretKeyField = secretKeyField;
    this.urlField = urlField;
  }

  @Override
  @NonNull
  public ScrollView getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityMainBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_main, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityMainBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.api_key_field;
      TextInputLayout apiKeyField = ViewBindings.findChildViewById(rootView, id);
      if (apiKeyField == null) {
        break missingId;
      }

      id = R.id.connect_button;
      AppCompatButton connectButton = ViewBindings.findChildViewById(rootView, id);
      if (connectButton == null) {
        break missingId;
      }

      id = R.id.demoButton;
      Button demoButton = ViewBindings.findChildViewById(rootView, id);
      if (demoButton == null) {
        break missingId;
      }

      id = R.id.has_ssl_switch;
      SwitchMaterial hasSslSwitch = ViewBindings.findChildViewById(rootView, id);
      if (hasSslSwitch == null) {
        break missingId;
      }

      id = R.id.main_toolbar;
      Toolbar mainToolbar = ViewBindings.findChildViewById(rootView, id);
      if (mainToolbar == null) {
        break missingId;
      }

      id = R.id.progressBar;
      ProgressBar progressBar = ViewBindings.findChildViewById(rootView, id);
      if (progressBar == null) {
        break missingId;
      }

      id = R.id.secret_key_field;
      TextInputLayout secretKeyField = ViewBindings.findChildViewById(rootView, id);
      if (secretKeyField == null) {
        break missingId;
      }

      id = R.id.url_field;
      TextInputLayout urlField = ViewBindings.findChildViewById(rootView, id);
      if (urlField == null) {
        break missingId;
      }

      return new ActivityMainBinding((ScrollView) rootView, apiKeyField, connectButton, demoButton,
          hasSslSwitch, mainToolbar, progressBar, secretKeyField, urlField);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
