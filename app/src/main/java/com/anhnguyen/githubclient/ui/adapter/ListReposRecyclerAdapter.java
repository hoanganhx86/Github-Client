package com.anhnguyen.githubclient.ui.adapter;

import com.anhnguyen.githubclient.R;
import com.anhnguyen.githubclient.data.model.Repo;
import com.anhnguyen.githubclient.ui.widget.rcv.ItemTouchHelperAdapter;

import android.animation.Animator;
import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

public class ListReposRecyclerAdapter extends FlexibleAdapter<Repo> implements ItemTouchHelperAdapter {

    // ===========================================================
    // Constants
    // ===========================================================
    private static final String TAG = "RandomRecyclerAdapter";

    private static final int FIRST_STYLE = 1;
    private static final int SECOND_STYLE = 2;

    // ===========================================================
    // Fields
    // ===========================================================

    //private List<Content> items = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    Activity activity;

    // ===========================================================
    // Constructors
    // ===========================================================

    public ListReposRecyclerAdapter(Activity activity, List<Repo> items){
        super(items, activity);
        this.activity = activity;
    }

    // ===========================================================
    // Methods from SuperClass/Interfaces
    // ===========================================================

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_item, parent, false);;
        return new ViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        if(holder instanceof ViewHolder){
            final Repo repo = getItem(position);
            ((ViewHolder)holder).bind(activity, repo, onItemClickListener);

            animateView(holder.itemView, position, isSelected(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return FIRST_STYLE;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onItemDismiss(int position) {
        //items.remove(position);
        removeItem(position);
        notifyItemRemoved(position);
    }

    @Override
    public List<Animator> getAnimators(View itemView, int position, boolean isSelected) {
        List<Animator> animators = new ArrayList<Animator>();
        if (mRecyclerView.getLayoutManager() instanceof GridLayoutManager) {
            //GridLayout
            if (position % 2 != 0)
                addSlideInFromRightAnimator(animators, itemView, 0.5f);
            else
                addSlideInFromLeftAnimator(animators, itemView, 0.5f);
        } else {
            //LinearLayout
            switch (getItemViewType(position)) {
                default:
                    addSlideInFromBottomAnimator(animators, itemView);
                    break;
            }
        }

        //Alpha Animator is automatically added
        return animators;
    }

    // ===========================================================
    // Inner class
    // ===========================================================

    public static class ViewHolder extends FlexibleViewHolder{

        public final View view;
        public final TextView tvName;
        public final TextView tvContent;

        public ViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            this.view = view;
            tvName = (TextView) view.findViewById(R.id.tv_title);
            tvContent = (TextView) view.findViewById(R.id.tv_content);
        }

        public void bind(Activity activity, final Repo repo, final OnItemClickListener onItemClickListener){
            // SizeF sizeView = content.getSize();
            tvName.setText(repo.name);
            tvContent.setText(repo.description);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + tvName.getText();
        }

    }

    public interface OnItemClickListener {
        void onContentItemClicked(View view, Repo repo);
    }


}
