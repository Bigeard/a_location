package com.ynov.a_location.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ynov.a_location.R;
import com.ynov.a_location.adapter.CityAdapter;
import com.ynov.a_location.bo.City;
import com.ynov.a_location.viewmodel.ListCityFragmentVM;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ListCityFragment extends Fragment {
    private static final String TAG = "ListCityFragment";
    ListCityFragmentVM vm;
    private RecyclerView rv;
    private CityAdapter userAdapter;

    public ListCityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = new ViewModelProvider(this).get(ListCityFragmentVM.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_city, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Initialiser notre Toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(view.findViewById(R.id.toolbar));
        setHasOptionsMenu(true);

        //Récupération de l'instance du RV
        rv = view.findViewById(R.id.recyclerViewCitys);
        //Création de l'adapter
        userAdapter = new CityAdapter();
        //On paramètre la RV pour afficher tout les éléments de liste
        // de manière Linéaire et verticale
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        //On associe notre adapter à notre RV
        rv.setAdapter(userAdapter);
        //On observer le changement de données AL Citys
        Observer<ArrayList<City>> alCityObserver = users -> {
            //Lorsque la donnée change, on modifie l'adapter pour afficher la nouvelle AL
            userAdapter.setArrayListCitys(users);
        };
        //On affecte notre observer à notre MustableLiveData
        // Observable <-> Observer
        vm.getArrayListCity().observe(this.getViewLifecycleOwner(),alCityObserver);

        if(vm.getArrayListCity().getValue().isEmpty()){
            fetchCitys();
        }else{
            userAdapter.setArrayListCitys(vm.getArrayListCity().getValue());
        }
    }

    private void fetchCitys() {
        OkHttpClient client = new OkHttpClient();
        Request requestMsg = new Request.Builder()
                .url("https://flutter-learning.mooo.com/villes")
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
                    ArrayList<City> alusers = new Gson().fromJson(
                            response.body().string(),
                            new TypeToken<ArrayList<City>>(){}.getType()
                    );
                    vm.getArrayListCity().postValue(alusers);
                }else{
                    Log.e(TAG, "onResponse: " + "authentification incorrecte" );
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_msg,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.item_refresh){
            fetchCitys();
            //Que faire lors de l'appui sur refresh
        }
        return super.onOptionsItemSelected(item);
    }
}