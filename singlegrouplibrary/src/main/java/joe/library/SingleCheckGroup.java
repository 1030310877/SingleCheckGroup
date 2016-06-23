package joe.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Description
 * Created by chenqiao on 2016/6/23.
 */
public class SingleCheckGroup extends FrameLayout implements View.OnClickListener {
    private HashMap<String, ArrayList<Checkable>> maps;
    private onGroupItemViewClick itemClickListener;
    private onCheckItemChangedListener checkItemChanged;
    private boolean broadCast = false;
    private int checkId = -1;

    public SingleCheckGroup(Context context) {
        this(context, null);
    }

    public SingleCheckGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SingleCheckGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        maps = new HashMap<>();
    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        deepSearch(child);
    }

    /**
     * 深度遍历所有子view
     */
    private void deepSearch(View view) {
        if (view instanceof Checkable) {
            if (view.getTag() == null) {
                return;
            }
            String key = (String) view.getTag();
            if (maps.containsKey(key)) {
                maps.get(key).add((Checkable) view);
            } else {
                ArrayList<Checkable> lists = new ArrayList<>();
                lists.add((Checkable) view);
                maps.put(key, lists);
            }
            view.setOnClickListener(this);
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                deepSearch(((ViewGroup) view).getChildAt(i));
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (itemClickListener != null && !broadCast) {
            broadCast = true;
            itemClickListener.onItemClick(v);
            checkState(v);
            broadCast = false;
        }
    }

    private void checkState(View v) {
        if (maps != null && v.getTag() != null) {
            int tempId = 0;
            String tag = (String) v.getTag();
            ArrayList<Checkable> checkAbles = maps.get(tag);
            if (v instanceof Checkable) {
                if (((Checkable) v).isChecked()) {
                    tempId = v.getId();
                    for (Checkable check : checkAbles) {
                        if (!check.equals(v)) {
                            check.setChecked(false);
                        }
                    }
                } else {
                    boolean allFalse = true;
                    for (Checkable check : checkAbles) {
                        if (!check.isChecked()) {
                            allFalse = false;
                            break;
                        }
                    }
                    if (allFalse) {
                        tempId = -1;
                    }
                }
                if (checkId != tempId) {
                    checkId = tempId;
                    if (checkItemChanged != null) {
                        checkItemChanged.onCheckItemChanged(checkId);
                    }
                }
            }
        }
    }

    public void setOnItemClickListener(onGroupItemViewClick itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setCheckItemChangedListener(onCheckItemChangedListener checkItemChanged) {
        this.checkItemChanged = checkItemChanged;
    }

    public interface onGroupItemViewClick {
        void onItemClick(View v);
    }

    public interface onCheckItemChangedListener {
        void onCheckItemChanged(int vId);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        maps.clear();
    }
}
