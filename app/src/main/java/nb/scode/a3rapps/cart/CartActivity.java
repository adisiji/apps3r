package nb.scode.a3rapps.cart;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.shehabic.droppy.DroppyClickCallbackInterface;
import com.shehabic.droppy.DroppyMenuItem;
import com.shehabic.droppy.DroppyMenuPopup;
import com.shehabic.droppy.animations.DroppyFadeInAnimation;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import nb.scode.a3rapps.App;
import nb.scode.a3rapps.R;
import nb.scode.a3rapps.WebViewActivity;
import nb.scode.a3rapps.util.ActivityUtils;

public class CartActivity extends AppCompatActivity {

    @Inject
    CartPresenter cartPresenter;
    @BindView(R.id.iv_menu_toolbar)
    ImageView anchorMenu;
    private final String TAG = CartActivity.class.getSimpleName();
    Unbinder unbinder;
    private DroppyMenuPopup droppyMenu;

    @BindString(R.string.link_rp100)
    String link100;
    @BindString(R.string.link_konfirmasi)
    String linkKonfirm;
    @BindString(R.string.link_transaksi)
    String linkTransaksi;
    @BindString(R.string.link_arsip)
    String linkArsip;
    @BindString(R.string.link_order)
    String linkOrder;
    @BindString(R.string.link_profiles)
    String linkProfile;
    @BindString(R.string.link_tambahsaldo)
    String linkTambahSaldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        unbinder = ButterKnife.bind(this);
        CartFragment cartFragment = (CartFragment)getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(cartFragment == null){
            cartFragment = CartFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), cartFragment, R.id.contentFrame);
        }
        DaggerCartComponent.builder().localDataComponent(App.getDataComponent())
                .cartPresenterModule(new CartPresenterModule(cartFragment))
                .build().inject(this);
        setMenu();
    }

    private void setMenu(){
        DroppyMenuPopup.Builder droppyBuilder = new DroppyMenuPopup.Builder(CartActivity.this, anchorMenu);
        droppyMenu = droppyBuilder.addMenuItem(new DroppyMenuItem("Home")) //0
                .addSeparator()
                .addMenuItem(new DroppyMenuItem("Ready   Pre Order")) //1
                .addSeparator()
                .addMenuItem(new DroppyMenuItem(cartPresenter.getName())) //2
                .addMenuItem(new DroppyMenuItem("Rp"+ String.valueOf(cartPresenter.getSaldo())+" Tambah Saldo")) //3
                .addSeparator()
                .addMenuItem(new DroppyMenuItem("Catat")) //4
                .addMenuItem(new DroppyMenuItem("Konfirmasi")) //5
                .addMenuItem(new DroppyMenuItem("Transaksi")) //6
                .addSeparator()
                .addMenuItem(new DroppyMenuItem("Profiles")) //7
                .addMenuItem(new DroppyMenuItem("Arsip")) //8
                .addMenuItem(new DroppyMenuItem("Sign Out")) //9
                .triggerOnAnchorClick(false)
                .setOnClick(new DroppyClickCallbackInterface() {
                    @Override
                    public void call(View v, int id) {
                        Log.d(TAG, String.valueOf(id));
                        switch (id){
                            case 0: {
                                onBackPressed();
                                break;
                            }
                            case 1: {
                                break;
                            }
                            case 2: {
                                break;
                            }
                            case 3: {
                                break;
                            }
                            case 4: {
                                break;
                            }
                            case 5: {
                                startWebView("Konfirmasi",linkKonfirm);
                                break;
                            }
                            case 6: {
                                startWebView("Transaksi",linkTransaksi);
                                break;
                            }
                            case 7: {
                                startWebView("Profile",linkProfile);
                                break;
                            }
                            case 8: {
                                startWebView("Arsip",linkArsip);
                                break;
                            }
                            case 9: {
                                startWebView("Log Out","xxx");
                                break;
                            }
                        }
                    }
                })
                .setOnDismissCallback(new DroppyMenuPopup.OnDismissCallback() {
                    @Override
                    public void call() {
                        Toast.makeText(getApplicationContext(), "Menu dismissed", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPopupAnimation(new DroppyFadeInAnimation())
                .setXOffset(5)
                .setYOffset(5)
                .build();
    }

    private void startWebView(String title, String link){
        Intent i = new Intent(getApplicationContext(), WebViewActivity.class);
        i.putExtra("title",title);
        i.putExtra("link",link);
        startActivity(i);
    }

    @OnClick(R.id.iv_menu_toolbar)
    void showMenu(){
        droppyMenu.show();
    }
}
