package com.xyz.rcp.firstapplicaiton.preference;
import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import com.xyz.rcp.firstapplication.Activator;

public class MyInitializer extends AbstractPreferenceInitializer {

  public MyInitializer() {
  }

  @Override
  public void initializeDefaultPreferences() {
    IPreferenceStore store = Activator.getDefault().getPreferenceStore();
    store.setDefault("MySTRING1", "http://www.xyz.com");
  }

} 