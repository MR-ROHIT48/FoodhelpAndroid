package com.example.foodhelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.foodhelp.databinding.ActivityRegistrationBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {
    ActivityRegistrationBinding binding;
    private static final String url="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register_user(binding.username1.getText().toString(),binding.editTextTextEmail.getText().toString(),binding.editPassword.getText().toString(),binding.firstname.getText().toString(),binding.lasttname.getText().toString(),binding.contactnumber.getText().length(),binding.entercity.getText().toString(),binding.area.getText().toString(),binding.enteraddress.getText().toString());
                Intent i=new Intent(Registration.this,LoginActivity.class);
                startActivity(i);

            }
        });

    }
    public void register_user(String uname,String email,String pword,String fname,String Lname,Number contact,String area,String city,String address)
    {
        //StringRequest request=new StringRequest(Request.Method.POST);
        String url = "https://foodhelp.pythonanywhere.com/api/register/";

        Map<String, String> params = new HashMap();
        params.put("username", uname);
        params.put("password", pword);
        params.put("first_name", fname);
        params.put("last_name", Lname);
        params.put("email", email);
        params.put("address", address);
        params.put("city", city);
        params.put("area", area);





        JSONObject parameters = new JSONObject(params);

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //change intent here
                    Log.e("a", "The response is ----->" + response.getString("status"));
                    if(Integer.parseInt( response.getString("status")) == 1)
                    {
                        Toast.makeText(Registration.this, "Registration Success", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        //change intent here to home
                        //and save the userid somewhere in sharedpreference
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Log.e("error","adfad");
            }
        });

        Volley.newRequestQueue(this).add(jsonRequest);


    }
}