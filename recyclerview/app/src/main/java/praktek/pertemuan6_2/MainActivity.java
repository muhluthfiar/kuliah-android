package praktek.pertemuan6_2;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;

import java.util.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WordListAdapter adapter;
    private final LinkedList<String> wordList = new LinkedList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 20; i++) {
            wordList.addLast("Word " + i);
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this, wordList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int wordListSize = wordList.size();
                wordList.addLast("+ Word " + wordListSize);
                recyclerView.getAdapter().notifyItemInserted(wordListSize);
                recyclerView.smoothScrollToPosition(wordListSize);
            }
        });
    }
}
