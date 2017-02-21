package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

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
    @BindView(R.id.iv_check_item)
    ImageView check;

    ProductViewHolder(@NonNull View view){
        super(view);
        ButterKnife.bind(this,view);
    }

    void bind(@NonNull Products products, @NonNull Context context){
        title.setText(products.getId());
        harga.setText(priceFormat(products.getPrice()));
        qty.setText("x"+String.valueOf(products.getRequest()));
        int pict=0;
        switch (products.getExpireIcon()) {
            default:
            case 0: {
                clock.setVisibility(View.GONE);
                break;
            }
            case 1: {
                pict = R.drawable.ic_watch_later_light_green_a700_24dp;
                break;
            }
            case 2: {
                pict = R.drawable.ic_watch_later_amber_500_24dp;
                break;
            }
            case 3: {
                pict = R.drawable.ic_watch_later_red_a400_24dp;
                break;
            }
        }
        if(pict!=0){
            Glide.with(context)
                    .load(pict)
                    .asBitmap()
                    .into(clock);
        }

        int pictCek;

        if(products.getRequest() > Integer.parseInt(products.getAvailable())){
            pictCek = R.drawable.ic_check_box_grey_500_24dp;
        }
        else {
            pictCek = R.drawable.ic_check_box_green_700_24dp;
        }
        Glide.with(context)
                .load(pictCek)
                .asBitmap()
                .into(check);
    }

    private String priceFormat(int value){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        String rp = format.format(value);
        return "Rp"+rp.substring(3,rp.length());
    }

}
