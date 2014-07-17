package com.tutozone.dragdrop;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.view.View.DragShadowBuilder;
import android.view.View.OnDragListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * 
 * @author zied rebhi 
 * Tuto Zone
 * http://www.tutozone.info
 *
 */
public class MainActivity extends Activity {
	
	   private static final String IMAGE_TAG = "Tuto Zone Logo";
	   private android.widget.RelativeLayout.LayoutParams layoutParams;
	   String msg;
	   ImageView im;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		  im = (ImageView)findViewById(R.id.iv_logo);
	      // Sets the tag
	      im.setTag(IMAGE_TAG);

	      im.setOnLongClickListener(new View.OnLongClickListener() {
	         @Override
	         public boolean onLongClick(View v) {
	            ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());

	            String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
	            ClipData dragData = new ClipData(v.getTag().toString(), 
	            mimeTypes, item);

	            // Instantiates the drag shadow builder.
	            View.DragShadowBuilder myShadow = new DragShadowBuilder(im);

	            // Starts the drag
	            v.startDrag(dragData,  // the data to be dragged
	            myShadow,  // the drag shadow builder
	            null,      // no need to use local data
	            0          // flags (not currently used, set to 0)
	            );
	            return true;
	         }
	      });

	      // Create and set the drag event listener for the View
	      im.setOnDragListener( new OnDragListener(){
	         @Override
	         public boolean onDrag(View v,  DragEvent event){
	         switch(event.getAction())                   
	         {
	            case DragEvent.ACTION_DRAG_STARTED:
	               layoutParams = (RelativeLayout.LayoutParams) 
	               v.getLayoutParams();
	               Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
	               // Do nothing
	               break;
	            case DragEvent.ACTION_DRAG_ENTERED:
	               Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
	               int x_cord = (int) event.getX();
	               int y_cord = (int) event.getY();  
	               break;
	            case DragEvent.ACTION_DRAG_EXITED :
	               Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
	               x_cord = (int) event.getX();
	               y_cord = (int) event.getY();
	               layoutParams.leftMargin = x_cord;
	               layoutParams.topMargin = y_cord;
	               v.setLayoutParams(layoutParams);
	               break;
	            case DragEvent.ACTION_DRAG_LOCATION  :
	               Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
	               x_cord = (int) event.getX();
	               y_cord = (int) event.getY();
	               break;
	            case DragEvent.ACTION_DRAG_ENDED   :
	               Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
	               // Do nothing
	               break;
	            case DragEvent.ACTION_DROP:
	               Log.d(msg, "ACTION_DROP event");
	               // Do nothing
	               break;
	            default: break;
	            }
	            return true;
	         }
	      });
	   }
	}