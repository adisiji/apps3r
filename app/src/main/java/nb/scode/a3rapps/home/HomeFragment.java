package nb.scode.a3rapps.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.OnItemClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.login.LoginActivity;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by neobyte on 2/8/2017.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    private Unbinder unbinder;
    private HomeContract.Presenter mPresenter;
    private Callback callback;
    private Snackbar snackbar;
    @BindView(R.id.root_home)
    ConstraintLayout constraintLayout;

    public HomeFragment(){

    }

    public static HomeFragment newInstance(){
        return new HomeFragment();
    }

    @Override
    public void setPresenter(@NonNull HomeContract.Presenter presenter){
        mPresenter = checkNotNull(presenter,"Presenter cannot be null");
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        callback = (Callback) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this,root);
        if(savedInstanceState==null){
            mPresenter.start();
        }
        return root;
    }

    @Override
    public void onResume(){
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showLoading(){

    }

    @Override
    public void showFailedUpdate(String message) {
        if(snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
        snackbar = Snackbar
                .make(constraintLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showSuccessUpdate(String message) {
        if(snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
        snackbar = Snackbar
                .make(constraintLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showUpdateStatis(String message) {
        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.dialog_confirm))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        if(dialog.findViewById(R.id.btn_ok_dialog) == view){
                            mPresenter.getDataStatis();
                            dialog.dismiss();
                        }
                        else {
                            dialog.dismiss();
                        }
                    }
                })
                .setExpanded(true, 400)  // This will enable the expand feature, (similar to android L share dialog)
                .setCancelable(false)
                .create();
        TextView tv = (TextView)dialog.findViewById(R.id.content_confirm_message);
        tv.setText(message);
        dialog.show();
    }

    @Override
    public void showUpdateJne(String message) {
        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.dialog_confirm))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        if(dialog.findViewById(R.id.btn_ok_dialog) == view){
                            mPresenter.getDataJne();
                            dialog.dismiss();
                        }
                        else {
                            dialog.dismiss();
                        }
                    }
                })
                .setExpanded(true, 400)  // This will enable the expand feature, (similar to android L share dialog)
                .setCancelable(false)
                .create();
        TextView tv = (TextView)dialog.findViewById(R.id.content_confirm_message);
        tv.setText(message);
        dialog.show();
    }

    @Override
    public void showFirstUpdate(String message) {
        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.dialog_confirm))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        if(dialog.findViewById(R.id.btn_ok_dialog) == view){
                            mPresenter.getFirstData();
                            dialog.dismiss();
                        }
                        else {
                            dialog.dismiss();
                            getActivity().finish();
                        }
                    }
                })
                .setExpanded(true, 400)  // This will enable the expand feature, (similar to android L share dialog)
                .setCancelable(false)
                .create();
        TextView tv = (TextView)dialog.findViewById(R.id.content_confirm_message);
        tv.setText(message);
        dialog.show();
    }

    @OnClick(R.id.btn_webview_gudang)
    void gudang(){
        callback.gudang();
    }

    @OnClick(R.id.btn_cart)
    void cart(){
        callback.cart();
    }

    @Override
    public void goLogin(){
        callback.login();
    }

    public interface Callback{
        void login();
        void cart();
        void gudang();
    }

}
