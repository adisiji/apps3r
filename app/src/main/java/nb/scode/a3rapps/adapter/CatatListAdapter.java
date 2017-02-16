package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmRecyclerViewAdapater;
import nb.scode.a3rapps.modelretro.Colors;
import nb.scode.a3rapps.modelretro.Products;
import nb.scode.a3rapps.modelretro.Sizes;

/**
 * Created by neobyte on 2/14/2017.
 */

public class CatatListAdapter extends RealmRecyclerViewAdapater<Products> {

    private Context context;
    private List<Colors> colorsList;
    private List<Sizes> sizesList;

    public CatatListAdapter(Context context, List<Colors> colorsList, List<Sizes> sizesList){
        this.context = context;
        this.sizesList = sizesList;
        this.colorsList = colorsList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_produk)
        ImageView ivProduk;
        @BindView(R.id.spinner_jenis)
        MaterialSpinner spinnerJenis;
        @BindView(R.id.spinner_ukuran)
        MaterialSpinner spinnerUkuran;
        @BindView(R.id.et_kode)
        TextInputEditText etKode;
        @BindView(R.id.et_versi)
        TextInputEditText etVersi;
        @BindView(R.id.et_jumlah)
        TextInputEditText etJumlah;
        @BindView(R.id.tv_deskripsi_produk)
        TextView tvDeskripsiProd;
        @BindView(R.id.iv_check_product)
        ImageView ivCheck;
        @BindView(R.id.tv_nomor_jenis)
        TextView tvNomor;

        ViewHolder (View view){
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(Products products, int pos, List<Colors> colorsList, List<Sizes> sizesList){
            List<String> sizes = new ArrayList<>();
            for(Sizes s: sizesList){
                sizes.add(s.getName());
            }
            tvNomor.setText(String.valueOf(pos));
            spinnerUkuran.setItems(sizes);
            etJumlah.setText(String.valueOf(products.getRequest()));
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_product,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Products products = getItem(position);
        ViewHolder viewHolder1 = (ViewHolder)viewHolder;
        viewHolder1.bind(products, position+1, colorsList, sizesList);
    }

    @Override
    public int getItemCount() {
        if (getRealmAdapter() != null) {
            return getRealmAdapter().getCount();
        }
        return 0;
    }
}
