// Generated by view binder compiler. Do not edit!
package com.example.apitest.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.example.apitest.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ApiConnectedDialogBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button connectedOkButton;

  @NonNull
  public final TextView connectedText;

  @NonNull
  public final TextView connectedText2;

  @NonNull
  public final LottieAnimationView connectedToapiAnimation;

  private ApiConnectedDialogBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button connectedOkButton, @NonNull TextView connectedText,
      @NonNull TextView connectedText2, @NonNull LottieAnimationView connectedToapiAnimation) {
    this.rootView = rootView;
    this.connectedOkButton = connectedOkButton;
    this.connectedText = connectedText;
    this.connectedText2 = connectedText2;
    this.connectedToapiAnimation = connectedToapiAnimation;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ApiConnectedDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ApiConnectedDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.api_connected_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ApiConnectedDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.connected_ok_button;
      Button connectedOkButton = ViewBindings.findChildViewById(rootView, id);
      if (connectedOkButton == null) {
        break missingId;
      }

      id = R.id.connected_text;
      TextView connectedText = ViewBindings.findChildViewById(rootView, id);
      if (connectedText == null) {
        break missingId;
      }

      id = R.id.connected_text2;
      TextView connectedText2 = ViewBindings.findChildViewById(rootView, id);
      if (connectedText2 == null) {
        break missingId;
      }

      id = R.id.connected_toapi_animation;
      LottieAnimationView connectedToapiAnimation = ViewBindings.findChildViewById(rootView, id);
      if (connectedToapiAnimation == null) {
        break missingId;
      }

      return new ApiConnectedDialogBinding((ConstraintLayout) rootView, connectedOkButton,
          connectedText, connectedText2, connectedToapiAnimation);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
