package com.g45_jones.mobileappsassignment;

import android.util.Log;
import android.view.DragEvent;
import android.view.View;

public class dragListener implements View.OnDragListener {

    @Override
    public boolean onDrag(View v, DragEvent event) {
        int action = event.getAction();
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                Log.d("Hello", "onDrag: ");
                break;
            case DragEvent.ACTION_DRAG_ENTERED:
                Log.d("Hello", "onDrag: ");
                break;
            case DragEvent.ACTION_DRAG_EXITED:
                Log.d("Hello", "onDrag: ");
                break;
            case DragEvent.ACTION_DROP:
                Log.d("Hello", "onDrag: ");
                break;
            case DragEvent.ACTION_DRAG_ENDED:
                Log.d("Hello", "onDrag: ");
            default:
                break;
        }
        return true;
    }
}
