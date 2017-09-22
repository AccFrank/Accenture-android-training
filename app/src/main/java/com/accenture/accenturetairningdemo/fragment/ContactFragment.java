package com.accenture.accenturetairningdemo.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.accenture.accenturetairningdemo.R;
import com.accenture.accenturetairningdemo.adepter.ContactAdepter;
import com.accenture.accenturetairningdemo.model.Person;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.contactListview)
    ListView contactListview;
    Handler handler;
    ArrayList<Person> personList;
    Unbinder unbinder;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
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
        String[] mName = {"liming", "xiaohong", "heizi", "ahuang"};
        String[] mNum = {"1111", "2222", "3333", "4444"};
        ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        unbinder = ButterKnife.bind(this, view);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    Log.e("handler",((ArrayList<Person>)msg.obj).get(0).getPhone());
                }
            }
        };
        Person person = new Person();
        person.setName("Jerry");
        person.setPhone("13966666666");
        person.setImg("http://img0.ph.126.net/9DCbrlPK7SyN10XryP9ISw==/1276489019401221876.png");
        personList = new ArrayList<>();
        for(int i= 0 ;i<=5 ;i++){
            personList.add(person);
        }

        final ContactAdepter contactAdepter = new ContactAdepter(getContext(), personList, handler);
        contactListview = view.findViewById(R.id.contactListview);

//        SimpleAdapter adapter = new SimpleAdapter(getActivity(),mData,android.R.layout.simple_expandable_list_item_2,
//                new String[]{"name","num"},new int[]{android.R.id.text1,android.R.id.text2});
        contactListview.setAdapter(contactAdepter);
        contactListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Person person = new Person();
                person.setName("11");
                person.setPhone("1");
                person.setImg("1");
                personList.add(person);
                contactAdepter.notifyDataSetChanged();
            }
        });
//        adapter.notifyDataSetChanged();
//        contactAdepter.notifyDataSetChanged();
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
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
