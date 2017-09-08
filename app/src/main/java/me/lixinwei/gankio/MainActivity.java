package me.lixinwei.gankio;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;
import me.lixinwei.gankio.viewmodel.TestViewModel;

public class MainActivity extends AppCompatActivity implements LifecycleRegistryOwner {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    TestViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        MultiTypeAdapter adapter = new MultiTypeAdapter();
        mRecyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        viewModel.getResults().observe(this, listResource -> {
            if (listResource != null && listResource.data != null && !listResource.data.isEmpty()) {
                adapter.setItems(listResource.data);
                adapter.notifyDataSetChanged();
            }
        });

        Items items = new Items();
        for (int i = 0; i < 10; i++) {
            items.add(new ListItem("item " + i));
        }

        adapter.register(ListItem.class, new ListItemViewBinder());
        adapter.setItems(items);
        adapter.notifyDataSetChanged();


    }

    final LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);
    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }
}
