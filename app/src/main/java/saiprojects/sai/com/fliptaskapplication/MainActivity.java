package saiprojects.sai.com.fliptaskapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import saiprojects.sai.com.fliptaskapplication.Model.ApiData;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_list;
    RequestQueue requestQueue;
    private String url = "https://www.amiiboapi.com/api/amiibo/";
    ArrayList<ApiData> apiDataArrayList = new ArrayList<>();
    ArrayList<ApiData> apiDataArrayListOrginal = new ArrayList<>();
    EditText ed_roomNumber;

    CustomProgressBar customProgressBar;
    Context mContext;

    LinearLayoutManager listManager;
    DataListAdapter dataListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        customProgressBar = new CustomProgressBar(mContext);

        rv_list = findViewById(R.id.rv_list);
        ed_roomNumber =findViewById(R.id.ed_roomNumber);
        requestQueue = Volley.newRequestQueue(this);
        listManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        ed_roomNumber.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String roomNumber = s.toString();
                if(roomNumber.length() > 0)
                {
                    filterList(roomNumber);
                } else
                {

                    apiDataArrayList.clear();

                    for(int i = 0;i <apiDataArrayListOrginal.size(); i++){

                        if(apiDataArrayListOrginal.get(i).getName().toLowerCase().contains(roomNumber.toLowerCase())) {

                            ApiData searchObj = new ApiData();
                            searchObj.setType(apiDataArrayListOrginal.get(i).getType());
                            searchObj.setTail(apiDataArrayListOrginal.get(i).getTail());
                            searchObj.setName(apiDataArrayListOrginal.get(i).getName());
                            searchObj.setImage(apiDataArrayListOrginal.get(i).getImage());
                            searchObj.setGameSeries(apiDataArrayListOrginal.get(i).getGameSeries());
                            searchObj.setCharacter(apiDataArrayListOrginal.get(i).getCharacter());


                            apiDataArrayList.add(searchObj);

                        }
                    }

                    if(dataListAdapter != null)
                        dataListAdapter.notifyDataSetChanged();
                }
            }
        });



        ApiCalling();
    }


    private void filterList(String roomNumber)
    {

        apiDataArrayList = new ArrayList<>();

        for(int i = 0;i <apiDataArrayListOrginal.size(); i++)
        {
            if(apiDataArrayListOrginal.get(i).getName().toLowerCase().contains(roomNumber.toLowerCase()))
            {

                ApiData searchObj = new ApiData();
                searchObj.setType(apiDataArrayListOrginal.get(i).getType());
                searchObj.setTail(apiDataArrayListOrginal.get(i).getTail());
                searchObj.setName(apiDataArrayListOrginal.get(i).getName());
                searchObj.setImage(apiDataArrayListOrginal.get(i).getImage());
                searchObj.setGameSeries(apiDataArrayListOrginal.get(i).getGameSeries());
                searchObj.setCharacter(apiDataArrayListOrginal.get(i).getCharacter());


                apiDataArrayList.add(searchObj);

            }
        }

        if(dataListAdapter != null)
            dataListAdapter.notifyDataSetChanged();

    }

    private void ApiCalling()
    {
        if (!customProgressBar.isProgressBarShowing()) {
            customProgressBar.showCustomDialog();
            customProgressBar.setCustomCancelable(false);
            customProgressBar.setCustomMessage("loading");
        }


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response)
            {
                customProgressBar.closeCustomDialog();
                try {
                    JSONArray jsonArray = response.getJSONArray("amiibo");
                    Log.i("jsonArray","jsonArray --> "+jsonArray);

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        ApiData apiData = new ApiData();
                        apiData.setAmiiboSeries(jsonObject.getString("amiiboSeries"));
                        apiData.setCharacter(jsonObject.getString("character"));
                        apiData.setGameSeries(jsonObject.getString("gameSeries"));
                        apiData.setImage(jsonObject.getString("image"));
                        apiData.setName(jsonObject.getString("name"));
                        apiData.setTail(jsonObject.getString("tail"));
                        apiData.setType(jsonObject.getString("type"));

                        apiDataArrayList.add(apiData);
                        apiDataArrayListOrginal.add(apiData);
                        Log.i("!!!", "Amb -->" + apiDataArrayList);
                        Log.i("!!!", "Amb -->" + apiDataArrayList.size());
                    }

                    if(apiDataArrayList!=null && apiDataArrayList.size()>0)
                    {
                        rv_list.setLayoutManager(listManager);
                        rv_list.setHasFixedSize(true);
                        dataListAdapter = new DataListAdapter();
                        rv_list.setAdapter(dataListAdapter);
                    }

                } catch (JSONException e) {
                    customProgressBar.closeCustomDialog();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener()
        {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                customProgressBar.closeCustomDialog();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder>
    {


        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_data_display, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, final int position)
        {
            Picasso.get().load(apiDataArrayList.get(position).getImage()).fit().centerInside().into(holder.img_display);
            holder.tv_title.setText(apiDataArrayList.get(position).getName());

            holder.ll_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Log.i("^^^","selected data --> "+apiDataArrayList.get(position).toString());
                    ApiData selectedItemList = apiDataArrayList.get(position);

                    Intent in = new Intent(MainActivity.this, DetailsDisplayActivity.class);
                    in.putExtra("title",  selectedItemList.getName());
                    in.putExtra("image",  selectedItemList.getImage());
                    in.putExtra("character",  selectedItemList.getCharacter());
                    in.putExtra("gameseries",  selectedItemList.getGameSeries());
                    startActivity(in);
                }
            });
        }

        @Override
        public int getItemCount()
        {
            Log.i("apiDataArrayList","apiDataArrayList -->"+apiDataArrayList.size());
            return apiDataArrayList.size();

        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            TextView tv_title;
            ImageView img_display;
            LinearLayout ll_item;

            public ViewHolder(@NonNull View itemView)
            {
                super(itemView);

                img_display = itemView.findViewById(R.id.img_display);
                tv_title = itemView.findViewById(R.id.tv_title);
                ll_item = itemView.findViewById(R.id.ll_item);
            }
        }
    }


}
