package com.example.praktikum5;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPostFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPostFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPostFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPostFragment newInstance(String param1, String param2) {
        AddPostFragment fragment = new AddPostFragment();
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

    private AppCompatButton submit;
    private ImageView img;
    private TextView caption;
    private Account account;
    private Uri imgUri;
    private BottomNavigationView bottomNavigationView;
    private ActivityResultLauncher launcherIntentGallery;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_add_post, container, false);

        account = DataSource.currentAccount;

        submit = view.findViewById(R.id.submit);
        img = view.findViewById(R.id.img);
        caption = view.findViewById(R.id.contentField);
        bottomNavigationView = getActivity().findViewById(R.id.botnavbar);

        img.setImageURI(imgUri);
        launcherIntentGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                Uri image = data.getData();
                img.setImageURI(image);
                imgUri = image;
            }
        });
        img.setOnClickListener(v -> {
            Intent openGallery = new Intent(Intent.ACTION_PICK);
            openGallery.setType("image/*");
            launcherIntentGallery.launch(openGallery);
        });

        submit.setOnClickListener(v -> {

            try {
                String imgUriValue = imgUri.toString();
                if (!caption.getText().toString().trim().isEmpty()) {
                    Post post = new Post(caption.getText().toString(), imgUri);

                    if (!DataSource.accounts.contains(account)) {
                        DataSource.addAccountToArrayList(account);
                    }

                    account.createPost(post);

                    Bundle bundle = new Bundle();
                    bundle.putBoolean("newPost",true);
                    ((MainActivity) getActivity()).setBundle(bundle);
                    bottomNavigationView.setSelectedItemId(R.id.menu_home);

                    CharSequence text = "Post Berhasil Ditambahkan!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(getActivity(), text, duration);
                    toast.show();
                    img.setImageResource(R.drawable.baseline_broken_image_24);
                    imgUri = null;
                    caption.setText("");
                } else {
                    caption.setError("Wajib diisi");
                }
            } catch (NullPointerException e) {
                CharSequence text = "Please select an image!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(getActivity(), text, duration);
                toast.show();

            }
        });
        return view;
    }
}