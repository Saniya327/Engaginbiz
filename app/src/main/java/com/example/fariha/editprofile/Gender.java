package com.example.fariha.editprofile;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

/**
 * Created by Fariha on 26-07-2017.
 */

public class Gender {
    RadioButton male,female,none;
    View view;
    public Gender(Activity act, int male, int female, int none){
        this.male=(RadioButton)act.findViewById(male);
        this.female=(RadioButton)act.findViewById(female);
        this.none=(RadioButton)act.findViewById(none);
    }
    void check(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.male:
                if (checked)
                    // set gender as male

                    break;
            case R.id.female:
                if (checked)
                    // set gender as female
                    break;
            case R.id.none:
                if (checked)
                    // set gender as unspecified
                    break;

        }
    }
}
