package nb.scode.a3rapps.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nb.scode.a3rapps.R;

/**
 * Created by neobyte on 3/5/2017.
 */

public class MoreMenuAdapter extends RecyclerView.Adapter<MoreMenuAdapter.MoreViewHolder> {

    private List<Integer> listImage;
    private List<String> listTitle;
    private Context mContext;

    static class MoreViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_card_more)
        ImageView mImageView;
        @BindView(R.id.tv_card_more)
        TextView mTextView;

        public MoreViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }

        void bind(int image, String title, Context context){

            Glide.with(context)
                    .load(image)
                    .asBitmap()
                    .fitCenter()
                    .into(mImageView);
            mTextView.setText(title);
        }

    }

    public MoreMenuAdapter(List<Integer> listImage, List<String> listTitle, Context context) {

        this.listImage = listImage;
        this.listTitle = listTitle;
        mContext = context;
    }

    @Override
    public int getItemCount() {

        return listImage.size();
    }

    @Override
    public MoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_more, null);
        return new MoreViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(MoreViewHolder holder, int position) {
        holder.bind(listImage.get(position),listTitle.get(position),mContext);
    }
}
