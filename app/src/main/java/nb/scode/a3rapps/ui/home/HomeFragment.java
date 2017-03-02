package nb.scode.a3rapps.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import nb.scode.a3rapps.R;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by neobyte on 2/8/2017.
 */

public class HomeFragment extends Fragment implements HomeContract.View {

    private Unbinder unbinder;
    private HomeContract.Presenter mPresenter;
    private Callback callback;
    private Snackbar snackbar;

    private final String TAG = HomeFragment.class.getSimpleName();

    @BindView(R.id.activity_main)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.iv_logo_home)
    ImageView mImageViewLogo;
    @BindView(R.id.iv_bg_home)
    ImageView mImageViewBg;
    @BindView(R.id.iv_cart_home)
    ImageView mImageViewCart;
    @BindView(R.id.iv_gudang_home)
    ImageView mImageViewGudang;
    @BindDrawable(R.drawable.img_bg_home)
    Drawable imgBg;

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
        loadImage();
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

    private void loadImage(){

        mImageViewBg.setBackground(imgBg);
        Glide.with(getContext())
                .load(R.drawable.logo3r2)
                .fitCenter()
                .into(mImageViewLogo);
        Glide.with(getContext())
                .load(R.drawable.tigargudang)
                .centerCrop()
                .into(mImageViewGudang);
        Glide.with(getContext())
                .load(R.drawable.tigaronline)
                .centerCrop()
                .into(mImageViewCart);

        Log.d(TAG, "loadImage: finish");

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
                .make(mRelativeLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showSuccessUpdate(String message) {
        if(snackbar != null && snackbar.isShown()){
            snackbar.dismiss();
        }
        snackbar = Snackbar
                .make(mRelativeLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }

    @Override
    public void showUpdateJneStatis(String message) {
        DialogPlus dialog = DialogPlus.newDialog(getActivity())
                .setContentHolder(new ViewHolder(R.layout.dialog_confirm))
                .setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(DialogPlus dialog, View view) {
                        if(dialog.findViewById(R.id.btn_ok_dialog) == view){
                            mPresenter.getDataStatis();
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

    @OnClick(R.id.card_view_gudang)
    void gudang(){
        callback.gudang();
    }

    @OnClick(R.id.card_view_cart)
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
