package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private ProductEvent productEvent;

    private PindahEvent pindahEvent = new PindahEvent() {
        @Override
        public void pindahProductEvt(int pos) {
                  productEvent.pindahProduct(getItem(pos).getPackaged());
        }
    };

    private int last;
    private Context context;

    public ExpandCartAdapter(@NonNull OrderedRealmCollection<DetailPackage> detailPackages,
                             Context context,
                             CartEvent cartEvent,
                             ProductEvent productEvent) {
        super(detailPackages);
        this.cartEvent = cartEvent;
        this.productEvent = productEvent;
        this.context = context;
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
        last = getItem(parentPosition).getChildList().size();
        viewHolder.bind(detailPackage, context, cartEvent);
    }

    @UiThread
    @Override
    public void onBindChildViewHolder(@NonNull ProductViewHolder viewHolder,
                                      int parentPosition,
                                      int childPosition,
                                      @NonNull Products products) {
        viewHolder.bind(products, context, productEvent, last == childPosition+1, pindahEvent, parentPosition);
    }

    @Override
    public int getParentViewType(int parentPosition) {
        if (getItem(parentPosition).getKeranjang().equals("0")) {
            return PARENT_PACKAGE;
        } else {
            return PARENT_KERANJANG;
        }
    }

    @Override
    public int getChildViewType(int parentPosition, int childPosition) {
        if (getItem(parentPosition).getKeranjang().equals("0")) {
            return CHILD_PACKAGE;
        } else {
            return CHILD_KERANJANG;
        }
    }

    @Override
    public boolean isParentViewType(int viewType) {
        return viewType == PARENT_KERANJANG || viewType == PARENT_PACKAGE;
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

    public interface ProductEvent {

        String nameProduct(String id);

        String sizeProduct(String id);

        String colorProduct(String id);

        String versiProduct(String id);

        void pindahProduct(String id);

    }

    public interface PindahEvent {
        void pindahProductEvt(int pos);
    }

}
