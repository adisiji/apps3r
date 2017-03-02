package nb.scode.a3rapps.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmRecyclerViewAdapater;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.ui.dialogs.PindahDialogFragment;

/**
 * Created by neobyte on 2/23/2017.
 */

public class MassMoverAdapter extends RealmRecyclerViewAdapater<Products> {

    PindahDialogFragment.Connector connector;

    public MassMoverAdapter(PindahDialogFragment.Connector connector){
        this.connector = connector;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_title_item)
        TextView title;
        @BindView(R.id.tv_color)
        TextView tvColor;
        @BindView(R.id.tv_size)
        TextView tvSize;
        @BindView(R.id.tv_card_harga_preview_item)
        TextView harga;
        @BindView(R.id.tv_quantity)
        TextView qty;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }

        void bind(Products products, PindahDialogFragment.Connector connector){
            String nama = products.getId().substring(0,3);
            String size = products.getId().substring(3,5);
            String color = products.getId().substring(5,9);
            String versi = products.getId().substring(9);
            title.setText(connector.nameProduct(nama));
            tvColor.setText(connector.colorProduct(color));
            tvSize.setText(connector.sizeProduct(size));
            qty.setText("x"+String.valueOf(products.getRequest()));
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_pindah_produk,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Products products = getItem(position);
        ViewHolder viewHolder1 = (ViewHolder)viewHolder;
        viewHolder1.bind(products, connector);
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }

}
