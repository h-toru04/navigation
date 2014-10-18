package jp.co.ly.navigation;
import android.app.Activity;
import android.os.Bundle;


public class SignUpActivity extends Activity {

    public static final String KEY_CARD_ID = "card_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map);

        //        String cardId = getIntent().getExtras().getString(KEY_CARD_ID);
        //        Toast.makeText(this, cardId, Toast.LENGTH_SHORT).show();
    }

}
