package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmRecyclerViewAdapater;
import nb.scode.a3rapps.modelretro.DetailPackage;

/**
 * Created by neobyte on 2/12/2017.
 */

public class CartListAdapter extends RealmRecyclerViewAdapater<DetailPackage> {

    private Context context;
    private Realm realm;
    private CartEvent cartEvent;

    public CartListAdapter(Context context, CartEvent cartEvent) {
        this.cartEvent = cartEvent;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_card_penerima)
        TextView tvPenerima;
        @BindView(R.id.tv_card_harga_penerima)
        TextView tvHargaPenerima;
        @BindView(R.id.iv_clock_red)
        ImageView clock;
        @BindView(R.id.cat_internal)
        TextView tvCatInternal;
        @BindView(R.id.btn_card_konfirmasi)
        FancyButton btnKonfirmasiPenerima;
        @BindView(R.id.btn_card_edit_penerima)
        FancyButton btnEditPenerima;
        @BindView(R.id.btn_card_edit_keranjang)
        FancyButton btnEditKeranjang;
        @BindView(R.id.tv_card_harga_keranjang)
        TextView tvHargaKeranjang;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View recyclerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_penerima, parent, false);
        return new ViewHolder(recyclerView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder = (ViewHolder)viewHolder;
        realm = App.getAppComponent().realm();

        DetailPackage detailPackage = getItem(position);
        holder.tvPenerima.setText(detailPackage.getRecipientDetailList().getName());
        holder.tvHargaPenerima.setText(String.valueOf(detailPackage.getValue()));
        holder.tvCatInternal.setText(detailPackage.getPackagenote());

        holder.btnEditPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartEvent.editPenerima(position);
            }
        });
        holder.btnEditKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartEvent.editKeranjang(position);
            }
        });
        holder.btnKonfirmasiPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cartEvent.konfirmPenerima(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public interface CartEvent {

        void editPenerima(int pos);

        void konfirmPenerima(int pos);

        void editKeranjang(int pos);
    }
}
