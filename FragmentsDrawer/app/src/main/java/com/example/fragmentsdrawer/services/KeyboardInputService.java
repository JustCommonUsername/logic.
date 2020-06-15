package com.example.fragmentsdrawer.services;

import android.content.Intent;
import android.icu.text.UnicodeSet;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputConnection;
import android.widget.LinearLayout;

import com.example.fragmentsdrawer.R;
import com.example.fragmentsdrawer.adapters.CalculatorEditorViewAdapter;
import com.example.fragmentsdrawer.ui.home.calculator.CalculatorFragment;

public class KeyboardInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    public static final String CHANNEL = "KEYBOARD_CHANNEL";
    public static final String INFO = "INFO";

    private static int fields = 1;

    @Override
    public View onCreateInputView() {
        KeyboardView keyboardView = (KeyboardView)getLayoutInflater().inflate(R.layout.keyboard_view, null);
        Keyboard keyboard = new Keyboard(getApplicationContext(), R.xml.keyboard_layout_view);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        keyboardView.setPreviewEnabled(false);
        return keyboardView;
    }

    @Override
    public void onPress(int primaryCode) {
    }

    @Override
    public void onRelease(int primaryCode) {
    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection connection = getCurrentInputConnection();
        Intent i = new Intent(CHANNEL);

        CharSequence before = connection.getTextBeforeCursor(1, 0);
        CharSequence after = connection.getTextAfterCursor(1, 0);

        if (connection != null && primaryCode != 0) {
            // If statement checks, whether action is to delete text
            if (primaryCode == Keyboard.KEYCODE_DELETE) {
                /* Broadcast to delete empty EditText
                if (TextUtils.isEmpty(before) || fields > 1) {
                    sendBroadcast(i.putExtra(INFO, primaryCode));
                    fields--;
                }
                else
                    connection.deleteSurroundingText(1, 0); */

                // Broadcast to delete empty EditText ONLY
                sendBroadcast(i.putExtra(INFO, primaryCode));
                fields--;
            // If statement checks, whether there is text to add
            } else if (primaryCode > 2) {
                // Check, whether EditText, which is added, is empty
                if (TextUtils.isEmpty(before) && TextUtils.isEmpty(after))  {
                    connection.commitText(String.valueOf((char) primaryCode), 1);
                } else {
                    // Broadcast to add EditText and add text in there
                    sendBroadcast(i.putExtra(INFO, primaryCode));
                    fields++;
                }
            } else
                // Broadcast to perform specific action
                sendBroadcast(i.putExtra(INFO, primaryCode));
        }
    }

    @Override
    public void onText(CharSequence text) {
    }

    @Override
    public void swipeLeft() {
    }

    @Override
    public void swipeRight() {
    }

    @Override
    public void swipeDown() {
    }

    @Override
    public void swipeUp() {
    }

}
