package org.smartregister.path.listener;

import android.view.View;

import org.smartregister.path.domain.ServiceWrapper;
import org.smartregister.path.domain.VaccineWrapper;

import java.util.ArrayList;

/**
 * Created by keyman on 19/05/2017.
 */
public interface ServiceActionListener {

    public void onGiveToday(ServiceWrapper tag, View view);

    public void onGiveEarlier(ServiceWrapper tag, View view);

    public void onUndoService(ServiceWrapper tag, View view);
}