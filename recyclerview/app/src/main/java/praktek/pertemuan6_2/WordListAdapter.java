package praktek.pertemuan6_2;

import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.content.*;
import android.widget.*;

import java.util.LinkedList;

public class WordListAdapter  extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private final LinkedList<String> wordList;
    private final LayoutInflater inflater;

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView wordItemView;
        final WordListAdapter mAdapter;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = (TextView) itemView.findViewById(R.id.word);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            wordItemView.setText ("Clicked! "+ wordItemView.getText());
        }
    }

    public WordListAdapter(Context context, LinkedList<String> wordList) {
        inflater = LayoutInflater.from(context);
        this.wordList = wordList;
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.wordlist_item, parent, false);
        return new WordViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        String currentText = wordList.get(position);
        holder.wordItemView.setText(currentText);
    }
    @Override
    public int getItemCount() {
        return wordList.size();
    }
}
