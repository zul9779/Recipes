package tk.yurkiv.recipes.ui.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import tk.yurkiv.recipes.R;
import tk.yurkiv.recipes.model.Match;

/**
 * Created by yurkiv on 17.08.2015.
 */
public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private Context context;
    private List<Match> matches;

    public RecipesAdapter(Context context, List<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.cvRoot.setVisibility(View.GONE);
        Match match=matches.get(position);
        holder.tvRecipeNameItem.setText(match.getRecipeName());
        holder.tvRatingItem.setText(String.valueOf(match.getLikes()));
        holder.tvTimeItem.setText(match.getDuration());

        Picasso.with(context).load(match.getSmallImageUrls().get(0) + "0").into(holder.ivRecipeItem, new Callback() {
            @Override
            public void onSuccess() {
                holder.cvRoot.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError() {

            }
        });


    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @InjectView(R.id.cvRoot) protected CardView cvRoot;
        @InjectView(R.id.tvRecipeNameItem) protected TextView tvRecipeNameItem;
        @InjectView(R.id.tvRatingItem) protected TextView tvRatingItem;
        @InjectView(R.id.tvTimeItem) protected TextView tvTimeItem;
        @InjectView(R.id.ivRecipeItem) protected SelectableRoundedImageView ivRecipeItem;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
        }
    }
}
