package org.opensrp.path.activity;

import org.opensrp.path.BuildConfig;
import org.opensrp.path.R;
import org.opensrp.repository.AllSharedPreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;

import java.net.MalformedURLException;
import java.net.URL;

import util.PathConstants;

import static org.opensrp.util.Log.logError;
import static org.opensrp.util.Log.logInfo;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
    }


    public static class MyPreferenceFragment extends PreferenceFragment {
        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);

            Preference baseUrlPreference = findPreference("DRISHTI_BASE_URL");
            if (baseUrlPreference != null) {
                EditTextPreference baseUrlEditTextPreference = (EditTextPreference) baseUrlPreference;
                baseUrlEditTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        if (newValue != null) {
                            updateUrl(newValue.toString());
                        }
                        return true;
                    }
                });
            }
        }

        private void updateUrl(String baseUrl) {
            try {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                AllSharedPreferences allSharedPreferences = new AllSharedPreferences(preferences);

                URL url = new URL(baseUrl);

                String base = url.getProtocol() + "://" + url.getHost();
                int port = url.getPort();

                logInfo("Base URL: " + base);
                logInfo("Port: " + port);

                allSharedPreferences.saveHost(base);
                allSharedPreferences.savePort(port);

                logInfo("Saved URL: " + allSharedPreferences.fetchHost(""));
                logInfo("Port: " + allSharedPreferences.fetchPort(0));
            } catch (MalformedURLException e) {
                logError("Malformed Url: " + baseUrl);
            }
        }
    }

}
