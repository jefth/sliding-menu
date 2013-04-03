package com.slidingmenu.lib.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.slidingmenu.lib.R;
import com.slidingmenu.lib.SlidingMenu;

public abstract class SlidingMenuFragmentActivity extends SlidingFragmentActivity {

	protected SlidingMenu slidingMenu;
	protected Fragment contentFragment;
	protected Fragment sidebarLeftFragment;
	protected Fragment sidebarRightFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		slidingMenu = getSlidingMenu();
		onConfigSlidingMenu(slidingMenu);
		
		FrameLayout contentView = new FrameLayout(this);
		contentView.setId(R.id.slidingmenu_lib_content_view);
		FrameLayout leftView = new FrameLayout(this);
		leftView.setId(R.id.slidingmenu_lib_left_view);
		FrameLayout rightView = new FrameLayout(this);
		rightView.setId(R.id.slidingmenu_lib_right_view);
		
		setContentView(contentView);
		setBehindContentView(leftView);
		slidingMenu.setSecondaryMenu(rightView);
		
		FragmentTransaction transaction = this.getSupportFragmentManager().beginTransaction();
		
		Fragment contentFragment = onCreateContentFragment();
		transaction.replace(R.id.slidingmenu_lib_content_view, contentFragment);
		
		Fragment leftFragment = onCreateLeftFragment();
		if(leftFragment != null){
			transaction.replace(R.id.slidingmenu_lib_left_view, leftFragment);
		}else{
			slidingMenu.setMode(SlidingMenu.RIGHT);
		}
		
		Fragment rightFragment = onCreateRightFragment();
		if(rightFragment != null){
			transaction.replace(R.id.slidingmenu_lib_right_view, rightFragment);
		}else{
			slidingMenu.setMode(SlidingMenu.LEFT);
		}
		
		transaction.commit();
	}
	
	
	/**
	 * configuration of sliding menu
	 * @param slidingMenu
	 */
	protected abstract void onConfigSlidingMenu(SlidingMenu slidingMenu);
	
	/**
	 * create Main content view 
	 * @return
	 */
	protected abstract Fragment onCreateContentFragment();
	
	/**
	 * create Left side view
	 * @return
	 */
	protected abstract Fragment onCreateLeftFragment();
	
	/**
	 * create Right side view
	 * @return
	 */
	protected Fragment onCreateRightFragment(){
		return null;
	}
}
