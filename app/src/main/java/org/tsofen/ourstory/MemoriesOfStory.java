package org.tsofen.ourstory;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ourstory.R;

import org.tsofen.ourstory.model.Memory;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MemoriesOfStory extends AppCompatActivity {

    RecyclerView rv;
    ArrayList<Memory> data;
    MemoryAdapter adapter;
    TextView storyName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memories);
        Intent intent = getIntent();
        String message =intent.getStringExtra(YearActivity.EXTRA_MESSAGE);
        String[] m = message.split(" ");
        String name = m[1]+" " + m[2];
        int year = Integer.parseInt(m[0]);
        rv = findViewById(R.id.recycler);
        storyName = findViewById(R.id.storyname);
        data = Memory.createContactsList();
        adapter = new MemoryAdapter(data);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        filter(year);
        storyName.setText(name);

        //for the inner image recycler (inside memory)
        ArrayList<ImgItem> images = new ArrayList<>();
        ImageAdapter imageAdapter = new ImageAdapter(images);
        ImgItem i1=new ImgItem("pic",R.drawable.pic);
        ImgItem i2=new ImgItem("happy",R.drawable.happy);

        images.add(i1);
        images.add(i2);

        RecyclerView rvImages = findViewById(R.id.memory_pic);
        rvImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvImages.setAdapter(imageAdapter);

    }
    private void filter(int text)
    {
        ArrayList<Memory> filteredList = new ArrayList<>();
        for (Memory memory: data){

            int year2 = memory.getMemoryDate().get(Calendar.YEAR);
            if(year2 == text)
                filteredList.add(memory);
        }

        adapter.filterList(filteredList);
    }
}
