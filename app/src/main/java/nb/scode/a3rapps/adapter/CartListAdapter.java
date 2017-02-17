package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.os.SystemClock;
import android.provider.Settings;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmRecyclerViewAdapater;
import nb.scode.a3rapps.modelretro.DetailPackage;

/**
 * Created by neobyte on 2/12/2017.
 */

public class CartListAdapter extends RealmRecyclerViewAdapater<DetailPackage> {

    private Context context;
    private CartEvent cartEvent;
    private KeranjangEvent keranjangEvent;

    public CartListAdapter(Context context, CartEvent cartEvent, KeranjangEvent keranjangEvent) {
        this.cartEvent = cartEvent;
        this.context = context;
        this.keranjangEvent = keranjangEvent;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
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


        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }

        void bind(final DetailPackage detailPackage, final CartEvent cartEvent, final int position, Context context){
            tvPenerima.setText(detailPackage.getRecipientDetailList().getName());
            tvHargaPenerima.setText(String.valueOf(detailPackage.getValue()));
            tvCatInternal.setText(detailPackage.getPackagenote());
            btnEditPenerima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartEvent.editPenerima(detailPackage.getPackaged());
                }
            });
            long s = (System.currentTimeMillis() - detailPackage.getBatasWaktu())/ 86400;
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
            btnKonfirmasiPenerima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cartEvent.konfirmPenerima(position);
                }
            });
        }
    }

    static class ViewHolder1 extends RecyclerView.ViewHolder {
        @BindView(R.id.btn_card_edit_keranjang)
        FancyButton btnEditKeranjang;
        @BindView(R.id.tv_card_harga_keranjang)
        TextView tvHargaKeranjang;

        ViewHolder1(View view){
            super(view);
            ButterKnife.bind(this,view);
        }

        void bind(DetailPackage detailPackage, final KeranjangEvent keranjangEvent, final int position){
            tvHargaKeranjang.setText(String.valueOf(detailPackage.getValue()));
            btnEditKeranjang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    keranjangEvent.editKeranjang(position);
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(getItem(position).getKeranjang());
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType){
            case 1: {
                View v1 = inflater.inflate(R.layout.card_keranjang, parent, false);
                viewHolder = new ViewHolder1(v1);
                break;
            }
            default: {
                View v0 = inflater.inflate(R.layout.card_penerima, parent, false);
                viewHolder = new ViewHolder(v0);
                break;
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DetailPackage detailPackage = getItem(position);
        switch (viewHolder.getItemViewType()){
            case 1 : {
                ViewHolder1 holder2 = (ViewHolder1)viewHolder;
                holder2.bind(detailPackage, keranjangEvent, position);
                break;
            }
            default: {
                ViewHolder holder = (ViewHolder)viewHolder;
                holder.bind(detailPackage,cartEvent,position, context);
                break;
            }
        }

    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

    public interface CartEvent {

        void editPenerima(String id);

        void konfirmPenerima(int pos);

    }

    public interface KeranjangEvent {

        void editKeranjang(int pos);

    }
}
