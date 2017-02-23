package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.ChildViewHolder;
import it.sephiroth.android.library.tooltip.Tooltip;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.modelretro.Products;

/**
 * Created by neobyte on 2/21/2017.
 */

class ProductViewHolder extends ChildViewHolder {

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
    @BindView(R.id.iv_clock_item)
    ImageView clock;
    @BindView(R.id.iv_check_item)
    ImageView check;
    @BindView(R.id.rl_item_btm)
    RelativeLayout layout;

    ProductViewHolder(@NonNull View view){
        super(view);
        ButterKnife.bind(this,view);
    }

    void bind(@NonNull final Products products, @NonNull final Context context,
              ExpandCartAdapter.ProductEvent productEvent, Boolean visible){

        int pict=0;
        final String ket;
        final int pictCek, stylecheck, styleclock;

        if(visible){
            layout.setVisibility(View.VISIBLE);
        }
        else {
            layout.setVisibility(View.GONE);
        }
        String nama = products.getId().substring(0,3);
        String size = products.getId().substring(3,5);
        String color = products.getId().substring(5,9);
        String versi = products.getId().substring(9);
        title.setText(productEvent.nameProduct(nama));
        tvColor.setText(productEvent.colorProduct(color));
        tvSize.setText(productEvent.sizeProduct(size));
        harga.setText(priceFormat(products.getPrice()));
        qty.setText("x"+String.valueOf(products.getRequest()));

        switch (products.getExpireIcon()) {
            default:
            case 0: {
                clock.setVisibility(View.GONE);
                ket = "";
                styleclock = 0;
                break;
            }
            case 1: {
                pict = R.drawable.ic_watch_later_light_green_a700_24dp;
                ket = context.getString(R.string.batas_1_hari);
                styleclock = R.style.ToolTipLayoutGreenStyle;
                break;
            }
            case 2: {
                pict = R.drawable.ic_watch_later_amber_500_24dp;
                ket = context.getString(R.string.batas_2_hari);
                styleclock = R.style.ToolTipLayoutOrangeStyle;
                break;
            }
            case 3: {
                pict = R.drawable.ic_watch_later_red_a400_24dp;
                ket = context.getString(R.string.batas_sebentar_lagi);
                styleclock = R.style.ToolTipLayoutRedStyle;
                break;
            }
        }
        if(pict!=0){
            Glide.with(context)
                    .load(pict)
                    .asBitmap()
                    .into(clock);
        }

        boolean available = products.getRequest() <= Integer.parseInt(products.getAvailable());
        if(available){
            pictCek = R.drawable.ic_check_box_green_700_24dp;
            stylecheck = R.style.ToolTipLayoutGreenStyle;
        }
        else {
            pictCek = R.drawable.ic_check_box_grey_500_24dp;
            stylecheck = R.style.ToolTipLayoutRedStyle;
        }
        Glide.with(context)
                .load(pictCek)
                .asBitmap()
                .into(check);

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tooltip.TooltipView tooltip = Tooltip.make(context, new Tooltip.Builder(101).anchor(view,Tooltip.Gravity.TOP)
                        .closePolicy(Tooltip.ClosePolicy.TOUCH_ANYWHERE_NO_CONSUME, 3000)
                        .text(ket)
                        .fadeDuration(200)
                        .fitToScreen(false)
                        .withStyleId(styleclock)
                        .build());
                tooltip.show();
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tooltip.TooltipView tooltip = Tooltip.make(context, new Tooltip.Builder(101).anchor(view,Tooltip.Gravity.TOP)
                        .closePolicy(Tooltip.ClosePolicy.TOUCH_ANYWHERE_NO_CONSUME, 3000)
                        .text(products.getAvailable()+"/"+String.valueOf(products.getRequest()))
                        .fadeDuration(200)
                        .fitToScreen(false)
                        .withStyleId(stylecheck)
                        .build());
                tooltip.show();
            }
        });
    }

    private String priceFormat(int value){
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.getDefault());
        format.setCurrency(Currency.getInstance("IDR"));
        String rp = format.format(value);
        return "Rp"+rp.substring(3,rp.length());
    }

}
