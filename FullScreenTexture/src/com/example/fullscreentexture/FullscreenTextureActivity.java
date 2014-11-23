/*
 * Copyright 2014 TPad Tablet Project. All rights reserved.
 *
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 *    1. Redistributions of source code must retain the above copyright notice, this list of
 *       conditions and the following disclaimer.
 *
 *    2. Redistributions in binary form must reproduce the above copyright notice, this list
 *       of conditions and the following disclaimer in the documentation and/or other materials
 *       provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL ARSHAN POURSOHI OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * The views and conclusions contained in the software and documentation are those of the
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied.
 */

package com.example.fullscreentexture;

import nxr.tpad.lib.TPad;
import nxr.tpad.lib.TPadImpl;
import nxr.tpad.lib.views.FrictionMapView;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.example.fullscreentexture.R;

public class FullscreenTextureActivity extends Activity {

	// Custom Haptic Rendering view defined in TPadLib
	FrictionMapView fricView;
	
	// TPad object defined in TPadLib
	TPad mTpad;

	Bitmap imageMap ;
	// Load in the image stored in the drawables folder
	Bitmap defaultBitmap ;
	Bitmap bitCalc;
	Bitmap iconCalc;
	boolean calcDemo;
	boolean iconView ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Set the content of the screen to the .xml file that is in the layout folder
		setContentView(R.layout.activity_hello_tpad);
		
		// Load new tpad object from TPad Implementation Library
		mTpad = new TPadImpl(this);
		
		// Link friction view to .xml file
		fricView = (FrictionMapView) findViewById(R.id.view1);
		
		// Link local tpad object to the FrictionMapView
		fricView.setTpad(mTpad);
		 imageMap = BitmapFactory.decodeResource(getResources(), R.drawable.icons);
		// Load in the image stored in the drawables folder
		 defaultBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.overlays);
		bitCalc = BitmapFactory.decodeResource(getResources(), R.drawable.androidcalcoverlay);
		iconCalc = BitmapFactory.decodeResource(getResources(), R.drawable.androidcalc);
		 iconView = false;
		calcDemo = false;
		 // Set the friction data bitmap to the test image
		fricView.setDataBitmap(defaultBitmap, defaultBitmap);
	
	}

	public void toggle(View pressed) {
		if (iconView)
		{
			iconView = false;
			if (calcDemo)
			{
				fricView.setDataBitmap(bitCalc, bitCalc);
			}
			else{
				fricView.setDataBitmap(defaultBitmap, defaultBitmap);
			}
		}
		else
		{
			iconView = true;
			if (calcDemo){
				fricView.setDataBitmap(bitCalc, iconCalc);
			}
			else{
				fricView.setDataBitmap(defaultBitmap, imageMap);
			}
		}
	}
	
	public void switchDemo(View pressed){
		if (calcDemo)
		{
			calcDemo = false;
			if (iconView)
			{
				fricView.setDataBitmap(defaultBitmap, imageMap);
			}
			else{
				fricView.setDataBitmap(defaultBitmap, defaultBitmap);
			}
		}
		else
		{
			calcDemo = true;
			if (iconView){
				fricView.setDataBitmap(bitCalc, iconCalc);
			}
			else{
				fricView.setDataBitmap(bitCalc, bitCalc);
			}
		}
	}
	@Override
	protected void onDestroy() {
		mTpad.disconnectTPad();
		super.onDestroy();
	}
	

}