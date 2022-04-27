package com.example.foodtinder.ui.Swipe;


import static com.example.foodtinder.mappers.ApiToModel.map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DiffUtil;


import com.example.foodtinder.R;
import com.example.foodtinder.adapters.RecipeAdapter;
import com.example.foodtinder.callback.RecipeSwipeCallback;
import com.example.foodtinder.models.RecipeItemModel;
import com.example.foodtinder.repositories.RecipeRepository;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class SwipeFragment extends Fragment {

    private static final String TAG = SwipeFragment.class.getSimpleName();
    private CardStackLayoutManager manager;
    private RecipeAdapter adapter;
    private SwipeViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(SwipeViewModel.class);
        viewModel.init();
        View root = inflater.inflate(R.layout.fragment_swipe, container, false);
        init(root);
        viewModel.getRecipes().observe(getViewLifecycleOwner(), userRecipes -> {
            //Have to do so liveData listeners are registered
        });
        return root;
    }

    private void init(View root) {
        CardStackView cardStackView = root.findViewById(R.id.card_stack_view);
        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {
            @Override
            public void onCardDragging(Direction direction, float ratio) {
                Log.d(TAG, "onCardDragging: d=" + direction.name() + " ratio=" + ratio);
            }

            @Override
            public void onCardSwiped(Direction direction) {
                if (direction == Direction.Right) {
                    viewModel.onCardSwipedRight(adapter.getItems().get(manager.getTopPosition() - 1));
                }
                // Paginating
                if (manager.getTopPosition() == adapter.getItemCount() - 5) {
                    paginate();
                }

            }

            @Override
            public void onCardRewound() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardCanceled() {
                Log.d(TAG, "onCardRewound: " + manager.getTopPosition());
            }

            @Override
            public void onCardAppeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }

            @Override
            public void onCardDisappeared(View view, int position) {
                TextView tv = view.findViewById(R.id.item_name);
                Log.d(TAG, "onCardAppeared: " + position + ", nama: " + tv.getText());
            }
        });
        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());
        adapter = new RecipeAdapter(addList());
        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(adapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());
    }

    private void paginate() {
        List<RecipeItemModel> old = adapter.getItems();
        List<RecipeItemModel> newList = new ArrayList<>(addList());
        RecipeSwipeCallback callback = new RecipeSwipeCallback(old, newList);
        DiffUtil.DiffResult results = DiffUtil.calculateDiff(callback);
        adapter.setItems(newList);
        results.dispatchUpdatesTo(adapter);
    }

    private List<RecipeItemModel> addList() {
        RecipeRepository repository = RecipeRepository.getInstance();
        repository.searchRecipe("chicken");
        return map(repository.getSearchedRecipe().getValue());
    }

}