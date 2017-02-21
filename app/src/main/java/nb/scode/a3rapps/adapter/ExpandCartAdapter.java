package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import io.realm.OrderedRealmCollection;
import io.realm.RealmExpandableRecyclerAdapter;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.modelretro.DetailPackage;
import nb.scode.a3rapps.modelretro.Products;

/**
 * Created by neobyte on 2/21/2017.
 */

public class ExpandCartAdapter extends RealmExpandableRecyclerAdapter<DetailPackage,Products,DetailPackageViewHolder,ProductViewHolder> {

    private static final int PARENT_PACKAGE = 0;
    private static final int PARENT_KERANJANG = 1;
    private static final int CHILD_PACKAGE = 2;
    private static final int CHILD_KERANJANG = 3;

    private CartEvent cartEvent;

    private List<DetailPackage> detailPackages;
    private Context context;

    public ExpandCartAdapter(@NonNull OrderedRealmCollection<DetailPackage> detailPackages,
                             @NonNull String filterKey,
                             Context context,
                             CartEvent cartEvent) {
        super(detailPackages);
        this.cartEvent = cartEvent;
        this.context = context;
        this.detailPackages = detailPackages;
    }

    @UiThread
    @NonNull
    @Override
    public DetailPackageViewHolder onCreateParentViewHolder(@NonNull ViewGroup parent, int viewType){
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        Log.d("ViewType ==>",String.valueOf(viewType));
        switch (viewType){
            default:
            case(PARENT_PACKAGE):
                view = mInflater.inflate(R.layout.card_penerima, parent, false);
                break;
            case(PARENT_KERANJANG):
                view = mInflater.inflate(R.layout.card_keranjang,parent,false);
                break;
        }
        return new DetailPackageViewHolder(view);
    }

    @UiThread
    @NonNull
    @Override
    public ProductViewHolder onCreateChildViewHolder(@NonNull ViewGroup parent, int viewType){
        View view;
        LayoutInflater mInflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            default:
            case(CHILD_PACKAGE):
                view = mInflater.inflate(R.layout.card_preview_item_cart, parent, false);
                break;
            case(CHILD_KERANJANG):
                view = mInflater.inflate(R.layout.card_keranjang,parent,false);
                break;
        }
        return new ProductViewHolder(view);
    }

    @UiThread
    @Override
    public void onBindParentViewHolder(@NonNull DetailPackageViewHolder viewHolder,
                                       int parentPosition,
                                       @NonNull DetailPackage detailPackage) {
        viewHolder.bind(detailPackage, context, cartEvent);
    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ProductViewHolder viewHolder,
                                      int parentPosition,
                                      int childPosition,
                                      @NonNull Products products) {
        viewHolder.bind(products, context);
    }

    @Override
    public int getParentViewType(int parentPosition) {
        if (Integer.parseInt(detailPackages.get(parentPosition).getKeranjang())==0) {
            return PARENT_PACKAGE;
        } else {
            return PARENT_KERANJANG;
        }
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        if (Integer.parseInt(detailPackages.get(parentPosition).getKeranjang())==0) {
            return CHILD_PACKAGE;
        } else {
            return CHILD_KERANJANG;
        }
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_PACKAGE || viewType == PARENT_KERANJANG;
    }

    public interface CartEvent {

        void editPenerima(String id);

        int reqProduct(String id);

        int availProduct(String id);

        void konfirmPenerima(int pos);

    }

    public interface KeranjangEvent {

        void editKeranjang(int pos);

    }

}