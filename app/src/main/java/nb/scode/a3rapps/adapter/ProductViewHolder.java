package nb.scode.a3rapps.adapter;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.ChildViewHolder;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.modelretro.Products;

/**
 * Created by neobyte on 2/21/2017.
 */

class ProductViewHolder extends ChildViewHolder {

    @BindView(R.id.tv_title_item)
    TextView title;
    @BindView(R.id.tv_card_harga_preview_item)
    TextView harga;
    @BindView(R.id.tv_quantity)
    TextView qty;
    @BindView(R.id.iv_clock_item)
    ImageView clock;

    ProductViewHolder(@NonNull View view){
        super(view);
        ButterKnife.bind(this,view);
    }

    void bind(@NonNull Products products){
        title.setText(products.getId());
        harga.setText(priceFormat(products.getPrice()));
        qty.setText("x"+String.valueOf(products.getRequest()));
    }

    private String priceFormat(int value){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        String rp = format.format(value);
        return "Rp"+rp.substring(3,rp.length());
    }

}
