package xapo.saeed.xapo_test.ui.fragment;

import android.content.Context;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import xapo.saeed.xapo_test.R;
import xapo.saeed.xapo_test.ui.MainActivity;

/**
 * Created on 06/11/2018.
 */
public abstract class BaseFragment extends Fragment {

    protected MainActivity activity;

    private MaterialDialog materialDialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) context;
    }


    public void handleError(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException httpException = (HttpException) throwable;
            showDialogMessage(R.string.error, httpException.message());
        } else {
            showErrorDialog();
        }
    }

    private void showDialogMessage(@StringRes int title, String message) {
        materialDialog = new MaterialDialog.Builder(activity)
                .title(title)
                .iconRes(R.mipmap.ic_launcher)
                .theme(Theme.DARK)
                .backgroundColorRes(R.color.colorPrimaryDark)
                .negativeColorRes(R.color.white)
                .positiveColorRes(R.color.white)
                .content(message)
                .positiveText(R.string.ok)
                .show();

    }

    private void showErrorDialog() {
        materialDialog = new MaterialDialog.Builder(activity)
                .title(R.string.app_name)
                .iconRes(R.mipmap.ic_launcher)
                .theme(Theme.DARK)
                .backgroundColorRes(R.color.colorPrimaryDark)
                .negativeColorRes(R.color.white)
                .positiveColorRes(R.color.white)
                .content(R.string.oops_error_occurred)
                .positiveText(R.string.ok)
                .show();
    }


    void showLoader(@StringRes int message) {
        materialDialog = new MaterialDialog
                .Builder(activity)
                .content(message)
                .progress(true, 100)
                .theme(Theme.DARK)
                .widgetColorRes(R.color.white)
                .backgroundColorRes(R.color.colorPrimaryDark)
                .cancelable(false)
                .show();
    }

    void dismissDialog() {
        if (materialDialog != null && materialDialog.isShowing()) {
            materialDialog.dismiss();
        }
    }

}
