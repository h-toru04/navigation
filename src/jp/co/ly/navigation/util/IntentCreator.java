package jp.co.ly.navigation.util;

import android.content.Intent;
import android.net.Uri;

public class IntentCreator {

    public static Intent createNavi(String name, double lat, double lon) {
        final Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.driveabout.app.NavigationActivity");
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("google.navigation");
        builder.authority("///");
        builder.appendQueryParameter("ll", String.valueOf(lat) + "," + String.valueOf(lon));
        builder.appendQueryParameter("q", name);
        intent.setData(builder.build());
        return intent;
    }

}
