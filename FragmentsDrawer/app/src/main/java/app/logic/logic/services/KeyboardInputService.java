package app.logic.logic.services;

import android.content.Intent;
import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.view.View;
import android.view.inputmethod.InputConnection;

import app.logic.logic.R;

public class KeyboardInputService extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    public static final String CHANNEL = "KEYBOARD_CHANNEL";
    public static final String INFO = "INFO";
    public static final String TEXT = "TEXT";

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
                if (before != null && after != null)
                    sendBroadcast(i.putExtra(TEXT, before.length() > 0 ? before.toString() : after.toString()));
                else
                    sendBroadcast(i.putExtra(TEXT, ""));
            // If statement checks, whether there is text to add
            } else if (primaryCode > 2) {
                /* Check, whether EditText, which is added, is empty
                if (TextUtils.isEmpty(before) && TextUtils.isEmpty(after))  {
                    connection.commitText(String.valueOf((char) primaryCode), 1);
                } else {
                    // Broadcast to add EditText and add text in there
                    sendBroadcast(i.putExtra(INFO, primaryCode));
                } */

                // Broadcast to add EditText or add text
                sendBroadcast(i.putExtra(INFO, primaryCode));
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
