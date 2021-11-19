package com.project.employeetrackingsystem;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class Backgrounds extends AsyncTask<String, Void, String> {

    Context context;
    private AlertDialog alertDialog;
    Map<String, String> holder = new HashMap<>();

    public Backgrounds(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Error!!!");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        try{
            JSONArray jsonArray = new JSONArray(s);
            JSONObject jsonObject = null;
            holder.clear();

            for(int j=0; j<jsonArray.length(); j++){
                jsonObject = jsonArray.getJSONObject(j);
                String name = jsonObject.getString("user_name");
                String userType = jsonObject.getString("user_type");
                holder.put("userName",name);
                holder.put("userType",userType);
            }
            Toast.makeText(context.getApplicationContext(),"Welcome "+holder.get("userName"), Toast.LENGTH_LONG).show();
            switch(Objects.requireNonNull(holder.get("userType"))){
                case "Admin": Intent iAdmin = new Intent(context.getApplicationContext(), AdminActivity.class);
                    Shared sharedA = new Shared(context.getApplicationContext());
                    sharedA.secondTime(holder.get("userType"), holder.get("userName"));
                    context.startActivity(iAdmin);
                    break;

                case "Employee": Intent iEmployee = new Intent(context.getApplicationContext(), EmployeeActivity.class);
                    Shared sharedE = new Shared(context.getApplicationContext());
                    sharedE.secondTime(holder.get("userType"), holder.get("userName"));
                    context.startActivity(iEmployee);
                    break;

                case "Client": Intent i = new Intent(context.getApplicationContext(), ClientActivity.class);
                    Shared sharedC = new Shared(context.getApplicationContext());
                    sharedC.secondTime(holder.get("userType"), holder.get("userName"));
                    context.startActivity(i);
                    break;
            }
        }catch (Exception e){
            Log.i("Exception", "onPostExecute");
            alertDialog.setMessage("Please try again with proper credentials");
            alertDialog.show();
        }
    }

    @Override
    protected String doInBackground(String... voids) {
        StringBuffer result = new StringBuffer();
        String user = voids[0];
        String pass = voids[1];

        String connStr = "http://192.168.43.101/EmployeeTracking/UserLogin.php";
        try {
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(user,"UTF-8")
                    +"&&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(pass,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while((line = bufferedReader.readLine()) != null)
            {
                result.append(line+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result.toString();

        } catch (MalformedURLException e) {
            Log.i("Exception", "MalformedURLException");
            result.append(e.getMessage());
        } catch (IOException e) {
            Log.i("Exception", "IOException");
            result.append(e.getMessage());
        }


        return result.toString();
    }
}



class BackgroundAddEmployee extends AsyncTask <String, Void, String>{

    Context context;
    private AlertDialog alertDialog;

    public BackgroundAddEmployee(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Message");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.show();
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... voids) {
        StringBuffer result = new StringBuffer();
        String name = voids[0];
        String user = voids[1];
        String pass = voids[2];
        String contact = voids[3];
        String userType = "Employee";

        String connStr = "http://192.168.43.101/EmployeeTracking/AddUser.php";
        try {
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(user,"UTF-8")
                    +"&&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")
                    +"&&"+URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name,"UTF-8")
                    +"&&"+URLEncoder.encode("contact", "UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")
                    +"&&"+URLEncoder.encode("userType", "UTF-8")+"="+URLEncoder.encode(userType,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while((line = bufferedReader.readLine()) != null)
            {
                result.append(line+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result.toString();

        } catch (MalformedURLException e) {
            Log.i("Exception", "MalformedURLException");
            result.append(e.getMessage());
        } catch (IOException e) {
            Log.i("Exception", "IOException");
            result.append(e.getMessage());
        }
        return result.toString();
    }
}

class BackgroundAddClient extends AsyncTask <String, Void, String>{

    Context context;
    private AlertDialog alertDialog;

    public BackgroundAddClient(Context context){
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Message");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.show();
        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... voids) {
        StringBuffer result = new StringBuffer();
        String name = voids[0];
        String user = voids[1];
        String pass = voids[2];
        String contact = voids[3];
        String address = voids[4];
        String userType = "Client";

        String connStr = "http://192.168.43.101/EmployeeTracking/AddUser.php";
        try {
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String data = URLEncoder.encode("username", "UTF-8")+"="+URLEncoder.encode(user,"UTF-8")
                    +"&&"+URLEncoder.encode("password", "UTF-8")+"="+URLEncoder.encode(pass,"UTF-8")
                    +"&&"+URLEncoder.encode("name", "UTF-8")+"="+URLEncoder.encode(name,"UTF-8")
                    +"&&"+URLEncoder.encode("contact", "UTF-8")+"="+URLEncoder.encode(contact,"UTF-8")
                    +"&&"+URLEncoder.encode("userType", "UTF-8")+"="+URLEncoder.encode(userType,"UTF-8")
                    +"&&"+URLEncoder.encode("address", "UTF-8")+"="+URLEncoder.encode(address,"UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while((line = bufferedReader.readLine()) != null)
            {
                result.append(line+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result.toString();

        } catch (MalformedURLException e) {
            Log.i("Exception", "MalformedURLException");
            result.append(e.getMessage());
        } catch (IOException e) {
            Log.i("Exception", "IOException");
            result.append(e.getMessage());
        }
        return result.toString();
    }
}

class GetEmployeeList extends AsyncTask <String, Integer, ArrayList<String>>
{
    private Context context;
    ProgressDialog mProgress;
    private OnGetList mCallback;

    public GetEmployeeList(Context context)
    {
        this.context = context;
        this.mCallback = (OnGetList) context;
    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(context);
        mProgress.setMessage("Fetching Employee List \nPlease wait...");
        mProgress.show();
    }


    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        mProgress.dismiss();
        mCallback.onTaskCompleted(strings);
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        StringBuffer result = new StringBuffer();
        ArrayList<String> empList = new ArrayList<>();

        String connStr = "http://192.168.43.101/EmployeeTracking/GetEmployeeList.php";
        try
        {
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            /*--------------------------------------------------------------------------------------------------------------------------*/

            JSONArray jsonArray = new JSONArray(result.toString());
            JSONObject jsonObject = null;

            for (int j = 0; j < jsonArray.length(); j++) {
                jsonObject = jsonArray.getJSONObject(j);
                String name = jsonObject.getString("emp_name");
                Log.d("Output", name);
                empList.add(name);
            }


        } catch(IOException |
                JSONException e)

        {
            Log.i("Exception", e.getMessage());
            result.append(e.getMessage());
        }
        return empList;
    }
}

class GetClientList extends AsyncTask <String, Integer, ArrayList<String>>
{
    private Context context;
    ProgressDialog mProgress;
    private OnGetList mCallback;

    public GetClientList(Context context)
    {
        this.context = context;
        this.mCallback = (OnGetList) context;
    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(context);
        mProgress.setMessage("Fetching Client List \nPlease wait...");
        mProgress.show();
    }


    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        mProgress.dismiss();
        mCallback.onTaskCompleted(strings);
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        StringBuffer result = new StringBuffer();
        ArrayList<String> empList = new ArrayList<>();

        String connStr = "http://192.168.43.101/EmployeeTracking/GetClientList.php";
        try
        {
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            /*--------------------------------------------------------------------------------------------------------------------------*/

            JSONArray jsonArray = new JSONArray(result.toString());
            JSONObject jsonObject = null;

            for (int j = 0; j < jsonArray.length(); j++) {
                jsonObject = jsonArray.getJSONObject(j);
                String name = jsonObject.getString("client_name");
                Log.d("Output", name);
                empList.add(name);
            }


        } catch(IOException |
                JSONException e)

        {
            Log.i("Exception", e.getMessage());
            result.append(e.getMessage());
        }
        return empList;
    }
}

class AssignTaskBg extends AsyncTask <String, Void, String>
{
    Context context;
    AlertDialog alertDialog;

    public AssignTaskBg(Context context){this.context = context;}

    @Override
    protected String doInBackground(String... strings) {
        String toDo, dateFinal, emp, client;
        StringBuffer result = new StringBuffer();
        toDo = strings[0];
        dateFinal = strings[1];
        emp = strings[2];
        client = strings[3];

        String connStr = "http://192.168.43.101/EmployeeTracking/AssignTask.php";
        try
        {
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String data = URLEncoder.encode("client_name", "UTF-8")+"="+URLEncoder.encode(client,"UTF-8")
                    +"&&"+URLEncoder.encode("emp_name", "UTF-8")+"="+URLEncoder.encode(emp,"UTF-8")
                    +"&&"+URLEncoder.encode("toDo", "UTF-8")+"="+URLEncoder.encode(toDo,"UTF-8")
                    +"&&"+URLEncoder.encode("performDate", "UTF-8")+"="+URLEncoder.encode(dateFinal,"UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while((line = bufferedReader.readLine()) != null)
            {
                result.append(line+"\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result.toString();

        }catch(Exception e){
            Log.i("Exception: ", e.getMessage());
        }
        return result.toString();
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("Message");
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        alertDialog.setMessage(s);
        alertDialog.show();
        super.onPostExecute(s);
    }
}

class TrackEmployeeBg extends AsyncTask <String, Integer, ArrayList<String>>
{
    Context context;
    ProgressDialog mProgress;
    private OnGetList mCallback;

    public TrackEmployeeBg(Context context){
        this.context=context;
        this.mCallback = (OnGetList) context;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        mProgress.dismiss();
        mCallback.onTaskCompleted(strings);
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        StringBuffer result = new StringBuffer();
        ArrayList<String> empList = new ArrayList<>();

        String connStr = "http://192.168.43.101/EmployeeTracking/TrackEmployee.php";

        try{
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");


            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            /*--------------------------------------------------------------------------------------------------------------------------*/

            JSONArray jsonArray = new JSONArray(result.toString());
            JSONObject jsonObject = null;

            for (int j = 0; j < jsonArray.length(); j++) {
                jsonObject = jsonArray.getJSONObject(j);
                String name = jsonObject.getString("emp_name");
                Log.d("Output", name);
                empList.add(name);
            }

        }catch(Exception e){
            Log.i("Exception in TrackEmployee", e.getMessage());
        }
        return empList;
    }

    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(context);
        mProgress.setMessage("Fetching Employee List \nPlease wait...");
        mProgress.show();
    }
}

class EmployeeActivityBg extends AsyncTask <String, Void, ArrayList<String>>
{
    Context context;
    ProgressDialog mProgress;
    private OnGetList mCallBack;

    public EmployeeActivityBg(Context context){
        this.context = context;
        this.mCallBack = (OnGetList) context;
    }
    
    @Override
    protected void onPreExecute() {
        mProgress = new ProgressDialog(context);
        mProgress.setMessage("Fetching your data...");
        mProgress.show();
    }

    @Override
    protected ArrayList<String> doInBackground(String... strings) {
        String username = strings[0];
        StringBuffer result = new StringBuffer();
        ArrayList<String> taskList = new ArrayList<>();

        String connStr = "http://192.168.43.101/EmployeeTracking/EmployeeTask.php";

        try{
            URL url = new URL(connStr);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
            String data = URLEncoder.encode("userName", "UTF-8")+"="+URLEncoder.encode(username,"UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

            /*--------------------------------------------------------------------------------------------------------------------------*/

            JSONArray jsonArray = new JSONArray(result.toString());
            JSONObject jsonObject = null;

            for (int j = 0; j < jsonArray.length(); j++) {
                jsonObject = jsonArray.getJSONObject(j);
                String name = jsonObject.getString("client_name");
                String contact = jsonObject.getString("client_contact");
                String address = jsonObject.getString("client_address");
                String toDo = jsonObject.getString("toDo");
                String performDate = jsonObject.getString("perform_date");
                Log.d("Output_EmployeeTaskDetails", name + contact + address + toDo + performDate);
                taskList.add(name);
                taskList.add(contact);
                taskList.add(address);
                taskList.add(toDo);
                taskList.add(performDate);
            }

        }catch(Exception e){
            Log.i("Exception in TrackEmployee", e.getMessage());
        }
        return taskList;
    }

    @Override
    protected void onPostExecute(ArrayList<String> strings) {
        mProgress.dismiss();
        mCallBack.onTaskCompleted(strings);
    }
}
