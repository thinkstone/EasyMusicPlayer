package fream.com.example.my.musicplayer.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fream.com.example.my.musicplayer.R;
import fream.com.example.my.musicplayer.pagerone.LocalMusic;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyFragment extends BaceFragment {
    private OnFragmentInteractionListener mListener;
    private View relativeView;

    public MyFragment() {
        // Required empty public constructor
    }
    public static MyFragment newInstance(String param1, String param2) {
        MyFragment fragment = new MyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=null;
        if(view==null){
            view=inflater.inflate(R.layout.fragment_my, container, false);
        }
        // 初始化视图：
        initview(view);

        //设置relativeView的监听；
        initrelativeListener();
        return view;
    }
    //relativeView的监听
    private void initrelativeListener() {
        relativeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), LocalMusic.class);
                startActivity(intent);
            }
        });
    }

    private void initview(View view) {
        relativeView = view.findViewById(R.id.btn_relativelayout);
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

}
