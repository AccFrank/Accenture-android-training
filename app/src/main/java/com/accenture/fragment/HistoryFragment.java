package com.accenture.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.accenture.accenturetairningdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class HistoryFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ListView listView;

    private OnFragmentInteractionListener mListener;

    public HistoryFragment() {
        // Required empty public constructor
    }

    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_history, container, false);

        String[] locations = {"广州市番禺区1", "广州市天河区2", "广州市海珠区3",
                "广州市越秀区4", "广州市南沙区5", "广州市佛山区6", "广州市番禺区7", "广州市天河区8",
                "广州市佛山区9", "广州市海珠区0", "广州市越秀区11", "广州市南沙区12",
                "广州市佛山区13", "广州市番禺区14", "广州市天河区15", "广州市海珠区16",
                "广州市越秀区17", "广州市南沙区18", "广州市中山大道19"};
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        View view = inflater.inflate(R.layout.fragment_history,null);
        listView = view.findViewById(R.id.history_listview);

        Map<String, Object> item = new HashMap<String, Object>();
        for(int i=0;i < locations.length;i++) {
            item.put("location", locations[i]);
            items.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),items,
                R.layout.history_list_item,new String[]{"locations"},new int[]{R.id.history_location});
        listView.setAdapter(simpleAdapter);
//        listView.setAdapter(new ArrayAdapter<>(view.getContext(), R.layout.history_list_item,locations));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
