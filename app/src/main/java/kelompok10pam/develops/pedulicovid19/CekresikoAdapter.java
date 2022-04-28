package kelompok10pam.develops.pedulicovid19;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CekresikoAdapter extends ListAdapter<Cekresiko,CekresikoAdapter.CekresikoHolder> {

    private OnItemClickListener listener;

    public  CekresikoAdapter() {
        super(DIFF_CALLBACK);
    }
    private static final DiffUtil.ItemCallback<Cekresiko> DIFF_CALLBACK = new DiffUtil.ItemCallback<Cekresiko>() {
        @Override
        public boolean areItemsTheSame(@NonNull  Cekresiko oldItem, @NonNull  Cekresiko newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull  Cekresiko oldItem, @NonNull  Cekresiko newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getPilihan().equals(newItem.getPilihan());
        }
    };

    @NonNull
    @Override
    public  CekresikoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cekresiko_item, parent, false);
        return new  CekresikoHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CekresikoHolder holder, int position) {
        Cekresiko currentCekresiko =getItem(position);
        holder.textViewTitle.setText(currentCekresiko.getTitle());
        holder.switchPilihan.setText(String.valueOf(currentCekresiko.getPilihan()));
    }



    public Cekresiko getCekresikoAt(int position) {
        return getItem(position);
    }

    class CekresikoHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private Switch switchPilihan;

        public CekresikoHolder(View itemView) {

            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick( Cekresiko cekresiko);

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;


    }
}
