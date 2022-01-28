package com.ynov.a_location.fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ynov.a_location.R;
import com.ynov.a_location.adapter.HousingAdapter;
import com.ynov.a_location.bo.Housing;
import com.ynov.a_location.viewmodel.ListFragmentViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ListHousingFragment extends Fragment {
    OkHttpClient client;
    private static final String TAG = "ListeHousingFragment";
    private HousingAdapter adapter;
    private RecyclerView rv;
    private ListFragmentViewModel vm;
    private String city_id;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        client = new OkHttpClient();
        vm = new ViewModelProvider(this).get(ListFragmentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_housing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        city_id = ListHousingFragmentArgs.fromBundle(getArguments()).getCityId();
        Log.e("TAG", city_id);

        //Initialiser notre Toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(view.findViewById(R.id.toolbar));
        setHasOptionsMenu(true);
        //J'initialise ma recyclerview avec un HousingAdapter
        initializeHousings();
        Observer<ArrayList<Housing>> observerList = housings -> {
            adapter.setHousingArrayList(housings);
            rv.scrollToPosition(adapter.getItemCount()-1);
        };
        vm.getArrayListHousing().observe(getViewLifecycleOwner(),observerList);
        if(vm.getArrayListHousing().getValue().isEmpty()){
            fetchHousings();
        }

        super.onViewCreated(view, savedInstanceState);
    }

    public void fetchHousings(){
        Request requestMsg = new Request.Builder()
                .url("https://flutter-learning.mooo.com/logements?place.id="+city_id)
                .build();
        client.newCall(requestMsg).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(TAG, "onFailure: récupération");
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                //Récupérer les housings en ArrayList<Housing>
                if(response.code() == 200){
                    ArrayList<Housing> alMsgs = new Gson().fromJson(
                            response.body().string(),
                            new TypeToken<ArrayList<Housing>>(){}.getType()
                    );
                    vm.getArrayListHousing().postValue(alMsgs);
                }else{
                    Log.e(TAG, "onResponse: " + "authentification incorrecte" );
                }
            }
        });
    }

    private void initializeHousings(){
        rv = getView().findViewById(R.id.recyclerView);
        adapter = new HousingAdapter();
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_msg,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_refresh){
            fetchHousings();
            //Que faire lors de l'appui sur refresh
        }
        return super.onOptionsItemSelected(item);
    }
}