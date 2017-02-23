package nb.scode.a3rapps.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.tvbarthel.lib.blurdialogfragment.SupportBlurDialogFragment;
import io.realm.RealmResults;
import mehdi.sakout.fancybuttons.FancyButton;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.MassMoverAdapter;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmMassMoverAdapter;
import nb.scode.a3rapps.modelretro.Products;

/**
 * Created by neobyte on 2/23/2017.
 */

public class PindahDialogFragment extends SupportBlurDialogFragment {
    /**
     * Bundle key used to start the blur dialog with a given scale factor (float).
     */
    private static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";

    /**
     * Bundle key used to start the blur dialog with a given blur radius (int).
     */
    private static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";

    /**
     * Bundle key used to start the blur dialog with a given dimming effect policy.
     */
    private static final String BUNDLE_KEY_DIMMING = "bundle_key_dimming_effect";

    /**
     * Bundle key used to start the blur dialog with a given debug policy.
     */
    private static final String BUNDLE_KEY_DEBUG = "bundle_key_debug_effect";

    private static final String BUNDLE_KEY_ID = "bundle_key";

    private int mRadius;
    private float mDownScaleFactor;
    private boolean mDimming;
    private boolean mDebug;
    private String id;

    /**
     * Retrieve a new instance of the sample fragment.
     *
     * @param radius          blur radius.
     * @param downScaleFactor down scale factor.
     * @param dimming         dimming effect.
     * @param debug           debug policy.
     * @return well instantiated fragment.
     */

    @BindView(R.id.btn_pindahkan)
    FancyButton btnPindah;
    @BindView(R.id.rvMassMover)
    RecyclerView rvMover;
    @BindView(R.id.spinner_penerima)
    Spinner spinner;

    private DialogCallback dialogCallback;
    private Connector connector = new Connector() {
        @Override
        public String nameProduct(String id) {
            return dialogCallback.nameProduct(id);
        }

        @Override
        public String sizeProduct(String id) {
            return dialogCallback.sizeProduct(id);
        }

        @Override
        public String colorProduct(String id) {
            return dialogCallback.colorProduct(id);
        }

        @Override
        public String versiProduct(String id) {
            return null;
        }
    };
    private MassMoverAdapter adapter;

    public static PindahDialogFragment newInstance(String id,
                                                   int radius,
                                                   float downScaleFactor,
                                                   boolean dimming,
                                                   boolean debug) {
        PindahDialogFragment fragment = new PindahDialogFragment();
        Bundle args = new Bundle();
        args.putString(
                BUNDLE_KEY_ID,
                id
        );
        args.putInt(
                BUNDLE_KEY_BLUR_RADIUS,
                radius
        );
        args.putFloat(
                BUNDLE_KEY_DOWN_SCALE_FACTOR,
                downScaleFactor
        );
        args.putBoolean(
                BUNDLE_KEY_DIMMING,
                dimming
        );
        args.putBoolean(
                BUNDLE_KEY_DEBUG,
                debug
        );

        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Bundle args = getArguments();
        id = args.getString(BUNDLE_KEY_ID);
        mRadius = args.getInt(BUNDLE_KEY_BLUR_RADIUS);
        mDownScaleFactor = args.getFloat(BUNDLE_KEY_DOWN_SCALE_FACTOR);
        mDimming = args.getBoolean(BUNDLE_KEY_DIMMING);
        mDebug = args.getBoolean(BUNDLE_KEY_DEBUG);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            dialogCallback = (DialogCallback) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling fragment must implement Callback interface");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_pindah, null);
        ButterKnife.bind(this,view);
        builder.setView(view);
        adapter = new MassMoverAdapter(connector);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvMover.setLayoutManager(layoutManager);
        rvMover.setAdapter(adapter);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item,
                dialogCallback.listPenerima());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        RealmMassMoverAdapter massMoverAdapter = new RealmMassMoverAdapter(getContext(),
                dialogCallback.getProduct(id));
        adapter.setRealmAdapter(massMoverAdapter);
        adapter.notifyDataSetChanged();
    }

    @OnClick(R.id.btn_cancel)
    void cancel(){
        dismiss();
    }

    @OnClick(R.id.btn_pindahkan)
    void pindah(){
        dialogCallback.pindah();
    }

    @Override
    protected boolean isDebugEnable() {
        return mDebug;
    }

    @Override
    protected boolean isDimmingEnable() {
        return mDimming;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return true;
    }

    @Override
    protected float getDownScaleFactor() {
        return mDownScaleFactor;
    }

    @Override
    protected int getBlurRadius() {
        return mRadius;
    }

    /**
     * Interface to communicate between fragmentdialog and fragmentcart
     */

    public interface DialogCallback {

        void pindah();

        RealmResults<Products> getProduct(String id);

        String nameProduct(String id);

        String sizeProduct(String id);

        String colorProduct(String id);

        String versiProduct(String id);

        List<String> listPenerima();

    }

    public interface Connector {

        String nameProduct(String id);

        String sizeProduct(String id);

        String colorProduct(String id);

        String versiProduct(String id);

    }
}
