package com.captain.wds.android4module;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wudongsheng on 2018/5/12.
 */

public class TestFragment extends Fragment {
    @Override public void onAttach(Context context) {
        super.onAttach(context);
        Log.e("TAG", "Fragment onAttach");
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "Fragment onCreate");
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tv).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SecActivity.class);
                startActivity(intent);
            }
        });
        Log.e("TAG", "Fragment  onViewCreated");
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("TAG", "Fragment onCreateView");
        View view = inflater.inflate(R.layout.fragment_layout, container);
        return view;
    }
    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "Fragment onActivityCreated");
    }

    @Override public void onStart() {
        super.onStart();
        Log.e("TAG", "Fragment onStart");
    }

    @Override public void onResume() {
        super.onResume();
        Log.e("TAG", "Fragment onResume");
    }

    @Override public void onPause() {
        super.onPause();
        Log.e("TAG", "Fragment onPause");
    }

    @Override public void onStop() {
        super.onStop();
        Log.e("TAG", "Fragment onStop");
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e("TAG", "Fragment  onSaveInstanceState");
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG", "Fragment  onDestroyView");
    }

    @Override public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "Fragment onDestroy");
    }

    @Override public void onDetach() {
        super.onDetach();
        Log.e("TAG", "Fragment onDetach");
    }

}
