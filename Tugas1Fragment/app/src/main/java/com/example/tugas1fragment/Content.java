package com.example.tugas1fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Content} factory method to
 * create an instance of this fragment.
 */
public class Content extends Fragment {

    public Content() {
        // Required empty public constructor
    }

    public static Content newInstance() {
        return new Content();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_content, container, false);
        TextView description = (TextView) rootView.findViewById(R.id.description);
        return rootView;
    }
}