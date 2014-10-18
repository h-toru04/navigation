package jp.co.ly.navigation.fragment;

import java.io.Serializable;
import java.util.List;

import jp.co.ly.navigation.R;
import jp.co.ly.navigation.SignUpActivity;
import jp.co.ly.navigation.adapter.DestinationAdapter;
import jp.co.ly.navigation.entity.Destination;
import jp.co.ly.navigation.util.IntentCreator;
import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class DestinationListFragment extends ListFragment implements View.OnClickListener {

    public static final String OWNER_TAG = DestinationListFragment.class.getName();

    /** DESTINATIONSキー */
    private static final String KEY_DESTINATIONS = "destinations";
    private static final String KEY_CARD_ID = "card_id";

    public static DestinationListFragment newInstance(String cardId, List<Destination> list) {
        DestinationListFragment fragment = new DestinationListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(KEY_CARD_ID, cardId);
        bundle.putSerializable(KEY_DESTINATIONS, (Serializable) list);
        fragment.setArguments(bundle);
        return fragment;
    }

    @SuppressWarnings("unchecked")
    @SuppressLint("InflateParams") @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.custom_list, null);
        view.findViewById(R.id.sign_up).setOnClickListener(this);

        List<Destination> destinations = (List<Destination>) getArguments().getSerializable(KEY_DESTINATIONS);
        setListAdapter(new DestinationAdapter(this.getActivity(), android.R.layout.simple_list_item_1, destinations));
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Destination destination = (Destination)l.getItemAtPosition(position);
        startActivity(IntentCreator.createNavi(destination.getName(), destination.getLat(), destination.getLon()));
        getActivity().finish();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.sign_up) {
            final String cardId = getArguments().getString(KEY_CARD_ID);
            Intent intent = new Intent(getActivity(), SignUpActivity.class);
            intent.putExtra(SignUpActivity.KEY_CARD_ID, cardId);
            startActivity(intent);
        }
    }
}
