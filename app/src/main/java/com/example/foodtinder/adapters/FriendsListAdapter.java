package com.example.foodtinder.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodtinder.R;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.models.UserItemModel;
import com.example.foodtinder.ui.Favourite_Recipe_List.FavouriteRecipeFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {
    List<UserItemModel> friends;
    private OnClickListener listener;
    int previousExpandedPosition = -1;
    int mExpandedPosition = -1;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public FriendsListAdapter(List<UserItemModel> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.friend_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final boolean isExpanded = position == mExpandedPosition;
        UserItemModel item = friends.get(position);
        List<RecipeItemModel> recipeList = item.getFavouriteRecipes();
        holder.name.setText(friends.get(position).getUsername());
        Picasso.get()
                .load(item.getImage())
                .fit()
                .centerCrop()
                .into(holder.image);
        holder.dropDownMenu.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.shareButton.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.friendsFavouriteRecipesButton.setVisibility(isExpanded?View.VISIBLE:View.GONE);
        holder.itemView.setActivated(isExpanded);
        if(isExpanded){
            previousExpandedPosition = position;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mExpandedPosition = isExpanded ? -1:position;
                notifyItemChanged(previousExpandedPosition);
                notifyItemChanged(position);
            }
        });
        holder.friendsFavouriteRecipesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
               //Move to list of recipes of chosen friend
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView image;
        private TextView name;
        private Spinner dropDownMenu;
        private ImageButton shareButton;
        private Button friendsFavouriteRecipesButton;

        ViewHolder(View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.friend_Name);
            image = itemView.findViewById(R.id.friend_Image);
            dropDownMenu = itemView.findViewById(R.id.dropDownMenu);
            shareButton = itemView.findViewById(R.id.share_button);
            friendsFavouriteRecipesButton = itemView.findViewById(R.id.friendsRecipesListButton);
            itemView.setOnClickListener(v ->{
                listener.onClick(friends.get(getBindingAdapterPosition()));
            });
        }
    }

    public interface OnClickListener {
        void onClick(UserItemModel friend);
    }

}