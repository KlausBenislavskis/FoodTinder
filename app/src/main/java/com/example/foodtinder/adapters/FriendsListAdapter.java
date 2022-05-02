package com.example.foodtinder.adapters;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodtinder.R;
import com.example.foodtinder.callback.FriendClickCallBack;
import com.example.foodtinder.models.UserItemModel;
import java.util.List;

public class FriendsListAdapter extends RecyclerView.Adapter<FriendsListAdapter.ViewHolder> {
    List<String> friends;
    private OnClickListener listener;
    int previousExpandedPosition = -1;
    int mExpandedPosition = -1;
    public FriendClickCallBack callback;

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public boolean contains(String email) {
        if (friends.contains(email))
        {
            return true;
        }
        return false;
    }

    public FriendsListAdapter(List<String> friends) {
        this.friends = friends;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void set(List<String> friends)
    {
        this.friends = friends;
        notifyDataSetChanged();
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
        String item = friends.get(position);
        holder.name.setText(friends.get(position).toString());

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
                callback.onClick(friends.get(holder.getBindingAdapterPosition()).toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        if(friends != null) {
            return friends.size();
        }
        return 0;
    }
    public void setOnItemClickListener(FriendClickCallBack callBack)
    {
        this.callback = callBack;
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
                //listener.onClick(friends.get(getBindingAdapterPosition()));
                System.out.println("Clicked");

            });
        }
    }

    public interface OnClickListener {
        void onClick(UserItemModel friend);
    }

}
