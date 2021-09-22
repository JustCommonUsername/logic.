package app.logic.logic.adapters;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import app.logic.logic.R;

public class HistoryItemSwipeHelper extends ItemTouchHelper.SimpleCallback {

    private HistoryViewAdapter adapter;
    private Resources resources;
    private Drawable icon;

    public HistoryItemSwipeHelper(HistoryViewAdapter adapter) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.adapter = adapter;
        this.resources = adapter.getInflater().getContext().getResources();
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder holder, int direction) {
        if (direction == ItemTouchHelper.RIGHT) {
            adapter.deleteItem(holder.getAdapterPosition());
            icon.setBounds(0, 0, 0, 0);
        } else
            adapter.notifyItemChanged(holder.getAdapterPosition());
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recycler, @NonNull RecyclerView.ViewHolder holder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas canvas, @NonNull RecyclerView recycler, @NonNull RecyclerView.ViewHolder holder,
                          float dX, float dY, int actionState, boolean isCurrentlyActive) {
        icon = resources.getDrawable(R.drawable.ic_history_item_delete_24dp);

        super.onChildDraw(canvas, recycler, holder, dX, dY, actionState, isCurrentlyActive);

        View item = holder.itemView;

        int reduction = canvas.getWidth() - item.getLeft();

        float multiplier = 5 * dX / (resources.getDisplayMetrics().widthPixels - item.getLeft() + dX);
        float reducer = Math.abs(reduction) / (256f * resources.getDisplayMetrics().density);
        int iconMargin = (item.getHeight() - icon.getIntrinsicWidth()) / 2;

        int iconLeft = 50;
        int iconRight = iconLeft + icon.getIntrinsicWidth();
        int iconTop = item.getTop() + iconMargin;
        int iconBottom = iconTop + icon.getIntrinsicHeight();

        if (dX >= icon.getIntrinsicWidth() + iconMargin/* Handling swipe to the right */) {
            icon.setBounds((int)(iconLeft + reducer), (int)(iconTop + reducer), (int)(iconRight - reducer), (int)(iconBottom - reducer));
        } else
            icon.setBounds(0, 0, 0, 0);

        icon.draw(canvas);
    }

}
