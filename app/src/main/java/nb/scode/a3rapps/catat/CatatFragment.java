package nb.scode.a3rapps.catat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.adapter.CatatListAdapter;
import nb.scode.a3rapps.adapter.RealmAdapter.RealmCatatAdapter;
import nb.scode.a3rapps.modelretro.DetailPackage;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by neobyte on 2/13/2017.
 */

public class CatatFragment extends Fragment implements CatatContract.View {

    private CatatContract.Presenter presenter;
    private CatatListAdapter adapter;
    private Unbinder unbinder;

    @BindView(R.id.rvCatat)
    RecyclerView rvCatat;
    @BindView(R.id.et_catatan)
    TextInputEditText etCatatan;

    @Override
    public void setPresenter(CatatContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter,"Presenter cannot be null");
    }

    public CatatFragment(){

    }

    public static CatatFragment newInstance(){
        return new CatatFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_catat, container, false);
        unbinder = ButterKnife.bind(this, root);
        Bundle bundle = this.getArguments();
        String id = bundle.getString("id");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        adapter = new CatatListAdapter(getContext(),presenter.getColorList(),presenter.getSizesList());
        rvCatat.setHasFixedSize(true);
        rvCatat.setLayoutManager(layoutManager);
        rvCatat.setAdapter(adapter);
        DetailPackage detailPackage = presenter.getDetailPackage(id);
        etCatatan.setText(detailPackage.getPackagenote());
        return  root;
    }

    @Override
    public void onResume() {
        super.onResume();
        RealmCatatAdapter realmCartAdapter = new RealmCatatAdapter(getContext(),
                presenter.getRealmRsultsProduct());
        adapter.setRealmAdapter(realmCartAdapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public String getCatatan(String id) {
        return null;
    }
}
