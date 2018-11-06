package xapo.saeed.xapo_test.ui.fragment;

import android.content.Context;

import androidx.fragment.app.Fragment;
import xapo.saeed.xapo_test.ui.MainActivity;

/**
 * Created on 06/11/2018.
 */
public abstract class BaseFragment extends Fragment {

    protected MainActivity activity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }
}
