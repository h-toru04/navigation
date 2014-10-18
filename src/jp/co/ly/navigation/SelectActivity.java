package jp.co.ly.navigation;

import jp.co.ly.navigation.entity.Response;
import jp.co.ly.navigation.fragment.DestinationListFragment;
import jp.co.ly.navigation.task.GetDestinationAsyncTask;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;


public class SelectActivity extends Activity implements GetDestinationAsyncTask.Listner{

    private boolean mIsResumed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!mIsResumed) {

            FragmentManager manager =  getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();

            Fragment fragment = manager.findFragmentByTag(DestinationListFragment.OWNER_TAG);
            if (fragment != null) {
                transaction.remove(fragment).commit();
            }

            mIsResumed = true;

            Intent intent = getIntent();
            // IDM
            byte[] idm = intent.getByteArrayExtra(NfcAdapter.EXTRA_ID);
            StringBuffer buffer = new StringBuffer();

            for (byte b : idm) {
                buffer.append(String.format("%02X", b));
            }
            final String cardId = buffer.toString();

            new GetDestinationAsyncTask(this, this, cardId).execute();
        }
    }




    @Override
    protected void onStop() {
        super.onStop();
        mIsResumed = false;
    }

    @Override
    public void onFinishAsyncTask(Response response, String cardId) {
        if (response == null) {
            return;
        }

        FragmentManager manager =  getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();


        transaction.add(R.id.main, DestinationListFragment.newInstance(cardId, response.getDistinctions()), DestinationListFragment.OWNER_TAG).commit();

    }

}
