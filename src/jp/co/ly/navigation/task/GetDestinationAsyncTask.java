package jp.co.ly.navigation.task;

import java.util.ArrayList;
import java.util.List;

import jp.co.ly.navigation.entity.Destination;
import jp.co.ly.navigation.entity.Response;
import jp.co.ly.navigation.util.HttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;


public class GetDestinationAsyncTask extends AsyncTask<Void, Void, Response> implements DialogInterface.OnClickListener {

    public interface Listner {
        void onFinishAsyncTask(final Response response, String cardId);
    }
    
    private Listner mListner;
    private ProgressDialog dialog;
    private Context mContext;
    private String mCardId;
    
    
    public GetDestinationAsyncTask(Context context, GetDestinationAsyncTask.Listner listner, String cardId) {
        super();
        this.mContext = context;
        this.mCardId = cardId;
        this.mListner = listner;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(mContext,ProgressDialog. THEME_HOLO_LIGHT);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("行き先情報取得中...");
        dialog.setCancelable(false);
        dialog.show();
    }



    @Override
    protected Response doInBackground(Void... params) {

        String string = HttpUtil.getDistinations(mCardId);

        if (string == null) {
            return null;
        }
        
        Response response = new Response();
        
        JSONObject all;
        
        List<Destination> destinations = new ArrayList<Destination>();
        try {
            all = new JSONObject(string);
            JSONArray distinations = all.getJSONArray("destination");
            for (int i = 0; i < distinations.length(); i++) {
                final JSONObject object = distinations.getJSONObject(i);
                Destination destination = new Destination();
                destination.setName(object.getString("name"));
                destination.setLat(object.getDouble("lat"));
                destination.setLon(object.getDouble("lon"));
                destinations.add(destination);
            }
            response.setDistinctions(destinations);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return response;
    }

    @Override
    protected void onPostExecute(final Response result) {
        super.onPostExecute(result);
        if (dialog != null) {
            dialog.dismiss();
        }
        if (this.mListner != null) {
            this.mListner.onFinishAsyncTask(result, mCardId);
        } else {
            Builder alert = new AlertDialog.Builder(this.mContext);
            alert.setTitle("エラー");
            alert.setMessage("行き先情報取得に失敗しました");
            alert.setCancelable(false);
            alert.setNegativeButton("OK", this);
            alert.show();
        }
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }


}
