package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.ParentViewHolder;
import io.realm.Realm;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.modelretro.DetailPackage;

import static android.view.View.GONE;

/**
 * Created by neobyte on 2/21/2017.
 */

class DetailPackageViewHolder extends ParentViewHolder {

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    private DetailPackage detailPackage;

    @BindView(R.id.tv_card_penerima)
    TextView tvPenerima;
    @BindView(R.id.tv_card_harga)
    TextView tvHargaPenerima;
    @BindView(R.id.iv_clock_red)
    ImageView clock;
    @BindView(R.id.cat_internal)
    TextView tvCatInternal;
    @BindView(R.id.btn_card_konfirmasi)
    FancyButton btnKonfirmasiPenerima;
    @BindView(R.id.btn_card_edit_penerima)
    FancyButton btnEditPenerima;
    @BindView(R.id.tv_card_total)
    TextView total;

    DetailPackageViewHolder(@NonNull View view){
        super(view);
        ButterKnife.bind(this,view);
    }

    void bind(final DetailPackage detailPackage, @NonNull Context context, final ExpandCartAdapter.CartEvent cartEvent){
        this.detailPackage = detailPackage;
        if(detailPackage.getKeranjang().equals("1")) {
            tvPenerima.setText("Keranjang");
        }
        else {
            tvPenerima.setText(detailPackage.getRecipientDetailList().getName());
        }
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        String rupiahValue =  format.format(detailPackage.getValue());
        tvHargaPenerima.setText("Rp"+rupiahValue.substring(3,rupiahValue.length()));
        tvCatInternal.setText(detailPackage.getPackagenote());

        btnEditPenerima.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {
                cartEvent.editPenerima(detailPackage.getPackaged());
            }
        });
        total.setText(cartEvent.reqProduct(detailPackage.getPackaged())+"/"+
                cartEvent.availProduct(detailPackage.getPackaged()));
        /*
            btnKonfirmasiPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartEvent.konfirmPenerima(position);
            }
        });
         */


        double s = Math.floor((detailPackage.getBatasWaktu() - Math.floor(System.currentTimeMillis()/1000))/ 86400);
        Log.d(detailPackage.getPackaged()+" S =>",String.valueOf(s));
        int pict;
        if(s<1) { //red clock
            pict = R.drawable.ic_watch_later_red_a400_24dp;
        }
        else if (s<2) {
            pict = R.drawable.ic_watch_later_amber_500_24dp;
        }
        else {
            pict = R.drawable.ic_watch_later_light_green_a700_24dp;
        }
        Glide.with(context)
                .load(pict)
                .asBitmap()
                .into(clock);
        if(detailPackage.getConfirmable() == null || !detailPackage.getConfirmable()){
            btnKonfirmasiPenerima.setVisibility(GONE);
        }
    }

    @Override
    protected void expandView() {
        super.expandView();
        saveExpandedState(true);
    }

    @Override
    protected void collapseView() {
        super.collapseView();
        saveExpandedState(false);
    }

    private void saveExpandedState(boolean expanded) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        detailPackage.setExpanded(expanded);
        realm.commitTransaction();
        realm.close();
    }

}
