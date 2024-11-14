package com.example.sign_in_up.utilities;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {

    private final SharedPreferences sharedPreferences;

    /**
     * Initializes a new instance of the {@code PreferenceManager} class.
     * @param context The context used to access the shared preferences.
     */
    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(Constants.KEY_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Stores a boolean value in shared preferences.
     * @param key   The key with which the specified value is to be associated.
     * @param value The boolean value to be stored.
     */
    public void putBoolean(String key, Boolean value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Retrieves a boolean value from shared preferences.
     * @param key The key whose associated value is to be returned.
     * @return The boolean value associated with the specified key, or {@code false} if no value is found.
     */
    public Boolean getBoolean(String key){
        return sharedPreferences.getBoolean(key,false);
    }

    /**
     * Stores a string value in shared preferences.
     * @param key   The key with which the specified value is to be associated.
     * @param value The string value to be stored.
     */
    public void putString(String key, String value){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * Retrieves a string value from shared preferences.
     * @param key The key whose associated value is to be returned.
     * @return The string value associated with the specified key, or {@code null} if no value is found.
     */
    public String getString(String key){
        return sharedPreferences.getString(key, null);
    }

    /**
     * Clears all values stored in shared preferences.
     */
    public void clear(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
